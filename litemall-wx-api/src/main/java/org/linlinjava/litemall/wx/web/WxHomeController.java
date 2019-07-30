package org.linlinjava.litemall.wx.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.linlinjava.litemall.core.system.SystemConfig;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.db.domain.LitemallGoods;
import org.linlinjava.litemall.db.domain.TicCategory;
import org.linlinjava.litemall.db.domain.TicComment;
import org.linlinjava.litemall.db.domain.TicGoods;
import org.linlinjava.litemall.db.domain.TicUser;
import org.linlinjava.litemall.db.service.*;
import org.linlinjava.litemall.wx.annotation.LoginUser;
import org.linlinjava.litemall.wx.service.HomeCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * 首页服务
 */
@RestController
@RequestMapping("/wx/home")
@Validated
public class WxHomeController {
    private final Log logger = LogFactory.getLog(WxHomeController.class);

    @Autowired
    private TicAdService adService;

    @Autowired
    private TicMessageService messageService;
    
    @Autowired
    private TicCommentService commentService;
    
    @Autowired
    private TicUserService userService;
    
    @Autowired
    private TicCategoryService categoryService;
    
    @Autowired
    private TicCouponService couponService;

    @Autowired
    private TicViewpointService viewpointService;

    private final static ArrayBlockingQueue<Runnable> WORK_QUEUE = new ArrayBlockingQueue<>(9);

    private final static RejectedExecutionHandler HANDLER = new ThreadPoolExecutor.CallerRunsPolicy();

    private static ThreadPoolExecutor executorService = new ThreadPoolExecutor(9, 9, 1000, TimeUnit.MILLISECONDS, WORK_QUEUE, HANDLER);

    @GetMapping("/cache")
    public Object cache(@NotNull String key) {
        if (!key.equals("litemall_cache")) {
            return ResponseUtil.fail();
        }
        // 清除缓存
        HomeCacheManager.clearAll();
        return ResponseUtil.ok("缓存已清除");
    }

    /**
     * 首页数据
     * @param userId 当用户已经登录时，非空。为登录状态为null
     * @return 首页数据
     */
    @GetMapping("/index")
    public Object index(@LoginUser Integer userId) {
    	if (userId == null) {
    		return ResponseUtil.unlogin();
    	}
        //优先从缓存中读取
        if (HomeCacheManager.hasData(HomeCacheManager.INDEX)) {
            return ResponseUtil.ok(HomeCacheManager.getCacheData(HomeCacheManager.INDEX));
        }
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Map<String, Object> data = new HashMap<>();
        //景点文章
        Callable<List> viewListCallable = () -> viewpointService.list(null,null,null,null);
        //景区信息
        Callable<List> mesListCallable = () -> messageService.list(1, 20);
        //景区评论
        Callable<Map> commentsCallable = () -> {
			List<TicComment> comments = commentService.queryList(0, 2);
			System.out.println(comments.size());
			List<Map<String, Object>> commentsVo = new ArrayList<>(comments.size());
			long commentCount = PageInfo.of(comments).getTotal();
			long userCount = commentService.countUsers();
			for (TicComment comment : comments) {
				Map<String, Object> c = new HashMap<>();
				c.put("id", comment.getComId());
				c.put("addTime", comment.getComAddTime());
				c.put("content", comment.getComContent());
				TicUser user = userService.findById(comment.getComUserId());
				c.put("nickname", user.getUseNickname());
				c.put("avatar", user.getUseAvatar());
				c.put("picList", comment.getComPicUrls());
				commentsVo.add(c);
			}
			Map<String, Object> commentList = new HashMap<>();
			commentList.put("userCount", userCount);
			commentList.put("count", commentCount);
			commentList.put("data", commentsVo);
			return commentList;
		};
		//优惠券展示
        Callable<List> couponListCallable;
        if(userId == null){
            couponListCallable = () -> couponService.queryList(0, 3);
        } else {
            couponListCallable = () -> couponService.queryAvailableList(userId,0, 3);
        }

        //门票展示
        Callable<List> ticCategoryListCallable = this::getCategoryList;

        FutureTask<List> viewTask = new FutureTask<>(viewListCallable);
        FutureTask<List> mesTask = new FutureTask<>(mesListCallable);
        FutureTask<Map> comTask = new FutureTask<>(commentsCallable);
        FutureTask<List> couponTask = new FutureTask<>(couponListCallable);
        FutureTask<List> ticCategoryTask = new FutureTask<>(ticCategoryListCallable);

        executorService.submit(viewTask);
        executorService.submit(mesTask);
        executorService.submit(comTask);
        executorService.submit(couponTask);
        executorService.submit(ticCategoryTask);

        try {
            data.put("viewList",viewTask.get());
            data.put("mesList", mesTask.get());
            data.put("comList", comTask.get());
            data.put("couponList", couponTask.get());
            data.put("ticCategoryList", ticCategoryTask.get());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        //缓存数据
        HomeCacheManager.loadData(HomeCacheManager.INDEX, data);
        executorService.shutdown();
        return ResponseUtil.ok(data);
    }

    private List<TicCategory> getCategoryList() {
      //List<Map> categoryList = new ArrayList<>();
        List<TicCategory> catList = categoryService.getCategory("cat_sort_order", "asc");
       /* for (TicCategory cat : catList) {
            List<TicGoods> categoryGoods = goodsService.queryByCid(cat.getCatId());
            Map<String, Object> catGoods = new HashMap<>();
            catGoods.put("catList", catList);
            catGoods.put("goodsList", categoryGoods);
            categoryList.add(catGoods);
        }*/
        return catList;
    }
}