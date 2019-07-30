package org.linlinjava.litemall.wx.web;

import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.db.domain.LitemallComment;
import org.linlinjava.litemall.db.domain.TicComment;
import org.linlinjava.litemall.db.service.TicCommentService;
import org.linlinjava.litemall.db.service.LitemallGoodsService;
import org.linlinjava.litemall.db.service.TicMessageService;
import org.linlinjava.litemall.db.service.TicUserService;
import org.linlinjava.litemall.wx.annotation.LoginUser;
import org.linlinjava.litemall.wx.dao.UserInfo;
import org.linlinjava.litemall.wx.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户评论服务
 */
@RestController
@RequestMapping("/wx/comment")
@Validated
public class WxCommentController {
    private final Log logger = LogFactory.getLog(WxCommentController.class);

    @Autowired
    private TicCommentService commentService;
    @Autowired
    private TicUserService userService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private LitemallGoodsService goodsService;
    @Autowired
    private TicMessageService messageService;


    private Object validate(TicComment comment) {
        String content = comment.getComContent();
        if (StringUtils.isEmpty(content)) {
            return ResponseUtil.badArgument();
        }

        Short star = comment.getComStar();
        if (star == null) {
            return ResponseUtil.badArgument();
        }
        if (star < 0 || star > 5) {
            return ResponseUtil.badArgumentValue();
        }
        Boolean hasPicture = comment.getComHasPicture();
        if (hasPicture == null || !hasPicture) {
            comment.setComPicUrls(new String[0]);
        }
        return null;
    }

    /**
     * 发表评论
     *
     * @param userId  用户ID
     * @param comment 评论内容
     * @return 发表评论操作结果
     */
    @PostMapping("post")
    public Object post(@LoginUser Integer userId, @RequestBody TicComment comment) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }
        Object error = validate(comment);
        if (error != null) {
            return error;
        }

        comment.setComUserId(userId);
        commentService.save(comment);
        return ResponseUtil.ok(comment);
    }

    /**
     * 评论数量
     *
     * @return 评论数量
     */
    @GetMapping("count")
    public Object count() {
        int allCount = commentService.count(0);
        int hasPicCount = commentService.count(1);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("allCount", allCount);
        data.put("hasPicCount", hasPicCount);
        return ResponseUtil.ok(data);
    }

    /**
     * 评论列表
     *
     * @param type     类型ID。 如果是0，则查询商品评论；如果是1，则查询专题评论。
     * @param valueId  商品或专题ID。如果type是0，则是商品ID；如果type是1，则是专题ID。
     * @param showType 显示类型。如果是0，则查询全部；如果是1，则查询有图片的评论。
     * @param page     分页页数
     * @param size     分页大小
     * @return 评论列表
     */
    @GetMapping("list")
    public Object list(@NotNull Integer showType,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer size) {
        List<TicComment> commentList = commentService.query(showType, page, size);
        long count = PageInfo.of(commentList).getTotal();

        List<Map<String, Object>> commentVoList = new ArrayList<>(commentList.size());
        for (TicComment comment : commentList) {
            Map<String, Object> commentVo = new HashMap<>();
            commentVo.put("addTime", comment.getComAddTime());
            commentVo.put("content", comment.getComContent());
            commentVo.put("picList", comment.getComPicUrls());
            commentVo.put("like", comment.getComLike());
            UserInfo userInfo = userInfoService.getInfo(comment.getComUserId());
            commentVo.put("userInfo", userInfo);
			/*
			 * String reply = commentService.queryReply(comment.getComId());
			 * commentVo.put("reply", reply);
			 */
            commentVoList.add(commentVo);
        }
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("data", commentVoList);
        data.put("count", count);
        data.put("currentPage", page);
        return ResponseUtil.ok(data);
    }
}