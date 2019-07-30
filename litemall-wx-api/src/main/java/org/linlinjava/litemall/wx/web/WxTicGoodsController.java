package org.linlinjava.litemall.wx.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.db.domain.CustomTicCouAndCos;
import org.linlinjava.litemall.db.domain.GoodsStock;
import org.linlinjava.litemall.db.domain.TicGoods;
import org.linlinjava.litemall.db.service.TicCouponUserService;
import org.linlinjava.litemall.db.service.TicGoodsService;
import org.linlinjava.litemall.wx.annotation.LoginUser;
import org.linlinjava.litemall.wx.dao.UserTimeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/wx/ticgo")
public class WxTicGoodsController {
	@Autowired
	private TicGoodsService goodsService;
	@Autowired
	private TicCouponUserService couponuserService;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@GetMapping("list")
	private Object GetGoodsByCatId(String Time, Integer CatId, @LoginUser Integer userId) {
		if (userId == null) {
			return ResponseUtil.unlogin();
		}
		if (StringUtils.isEmpty(Time) || StringUtils.isEmpty(CatId)) {
			return ResponseUtil.badArgument();
		}
		List<GoodsStock> goodList = goodsService.GetStock(Time, CatId);
		// 实例化Gson
		Gson gson = new Gson();
		List<GoodsStock> newgoodList  = new ArrayList<GoodsStock>();
		for (GoodsStock gooStock : goodList) {
			// 创建门票key
			String key = gooStock.getGooId() +"-" + Time.split(" ")[0] +"-ticket";
			if (stringRedisTemplate.hasKey(key)) { //查看缓存中是否有门票----这里是有  (没有就直接不管)
				String val = stringRedisTemplate.opsForValue().get(key);// 从缓存中取出门票
				if (val.indexOf("$")==-1) { // 判断是否有多个用户门票缓存----这里是没有
					UserTicket userTicket = gson.fromJson(val.split("&")[1],UserTicket.class);
					if (userTicket.getUserId() == userId) {	// 判断当前门票是否是当前用户的---这里是的
						gooStock.setNum((short)userTicket.getNumber());	// 将用户所占用的门票放入放回值中
					} else { // 这里不是当前用户的 所以就要减掉其他用户占用的门票数量
						gooStock.setStock(gooStock.getStock()-userTicket.getNumber());
					}
				}else{
					String[] gsonstr= val.split("&")[1].split("$");
					for (int i = 0; i < gsonstr.length;i++) { // 循环遍历出所有的缓存用户
						UserTicket userTicket = gson.fromJson(gsonstr[i],UserTicket.class);
						if (userTicket.getUserId() == userId) {	// 判断当前门票是否是当前用户的---这里是的
							gooStock.setNum((short)userTicket.getNumber());	// 将用户所占用的门票放入放回值中
						} else { // 这里不是当前用户的 所以就要减掉其他用户占用的门票数量
							gooStock.setStock(gooStock.getStock()-userTicket.getNumber());
						}
					}
				}
				
			}
			newgoodList.add(gooStock);
		}
		// 查询优惠券
		List<CustomTicCouAndCos> couponUsers = couponuserService.MayUseCoupon(userId);
		Map<String, Object> data = new HashMap<>();
		if (couponUsers.size() > 0) {
			data.put("coupon", true);
		} else {
			data.put("coupon", false);
		}
		data.put("ticgoods", newgoodList);
		return ResponseUtil.ok(data);
	}
	
	/**
	 * 将用户占用门票放入redis中
	 * @param userId 	用户ID
	 * @param goodsId	商品ID
	 * @param type		操作状态（add添加，反之则减少）
	 * @param time		具体哪一天的票
	 * @param surplus	当天的余票
	 * @return
	 */
	@GetMapping("goodCache")
	public Object goodCache(@LoginUser Integer userId, Integer goodsId, String type,String time,Integer surplus) {
		// 创建门票key
		String key = goodsId +"-" + time.split(" ")[0] +"-ticket";
		// 返回的map
		HashMap<String, Object> map = new HashMap<>(); 
		// 实例化Gson
		Gson gson = new Gson();
		// 返回新的门票数量
		Integer newNumber = 1; 
		//返回的别人占用数量
		Integer occupy = 0;
		if (!stringRedisTemplate.hasKey(key)) { // 首先判断redis中有没有该类型的门票 ---这里是没有
			UserTicket utic = new UserTicket();	//	实例化用户门票
			utic.setUserId(userId);
			utic.setNumber(newNumber);
			// 创建一个商品的缓存 key为 goodsId + "-ticket"
			stringRedisTemplate.opsForValue().set(key, surplus+"&"+gson.toJson(utic), 5 * 60, TimeUnit.SECONDS);
			map.put("goodsId", goodsId);	// 	商品ID
			map.put("number", newNumber);		// 	占用数量
			map.put("surplus", surplus - occupy);			//	别人占用数量
		} else {
			// 从reids中取出值
			String val = stringRedisTemplate.opsForValue().get(key);
			// 判断没有"$" ----这里是没有
			if (val.indexOf("$") == -1){
				// 将值解析成UserTicket
				UserTicket userTicket = gson.fromJson(val.split("&")[1],UserTicket.class);
				// 判断是不是当前用户---这里是
				if (userTicket.getUserId() == userId){
					if(type.equals("add")){
						//这里为该实体类加一
						userTicket.setNumber(userTicket.getNumber()+1);
					} else {
						//这里为该实体类减一
						userTicket.setNumber(userTicket.getNumber()-1);
					}
					//替换掉原来的用户门票
					val = val.replace(val.split("&")[1],gson.toJson(userTicket));
					// 为返回门票数量重新赋值
					newNumber = userTicket.getNumber();
				} else {
					UserTicket utic = new UserTicket();	//	实例化用户门票
					utic.setUserId(userId);
					utic.setNumber(newNumber);
					// 当前用户没有买过这门票 所以这里我们就为用户占用的门票放入redis中
					val = val +"$"+ gson.toJson(utic);
				}
			} else {
				String[] gsonstr = val.split("&")[1].split("$");
				for(int i = 0; i < gsonstr.length; i++){
					// 将值解析成UserTicket
					UserTicket userTicket = gson.fromJson(gsonstr[i],UserTicket.class);
					// 查看该用户有没有买该门票---这里是遍历到买了
					if(userTicket.getUserId() == userId) {
						//判断是加还是减----这里是加
						if(type.equals("add")){
							String current = gson.toJson(gsonstr[i]);
							//这里为该实体类加一
							userTicket.setNumber(userTicket.getNumber()+1);
							//替换掉原来的用户门票
							val = val.replace(current,gson.toJson(userTicket));
							// 为返回门票数量重新赋值
							newNumber = userTicket.getNumber();
							// 关掉循环
							break;
						}else {
							String current = gson.toJson(gsonstr[i]);
							//这里为该实体类减一
							userTicket.setNumber(userTicket.getNumber()-1);
							//替换掉原来的用户门票
							val = val.replace(current,gson.toJson(userTicket));
							// 为返回门票数量重新赋值
							newNumber = userTicket.getNumber();
							// 关掉循环
							break;
						}
					} else {
						// 这里是循环最后一次
						if (i == (gsonstr.length-1)) {
							//	判断最后一次有没有当前用户的门票有不做任何操作----这里是没有
							if (userTicket.getUserId() != userId)	{
								UserTicket utic = new UserTicket();	//	实例化用户门票
								utic.setUserId(userId);
								utic.setNumber(newNumber);
								// 当前用户没有买过这门票 所以这里我们就为用户占用的门票放入redis中
								val = val +"$"+ gson.toJson(utic);
								//这里对"&"前面的余票进行更改
								val = surplus + val.split("&")[1];
							}
						}
					}
				}
			}
			map.put("goodsId", goodsId);	// 	商品ID
			map.put("number", newNumber);		// 	占用数量
			map.put("surplus", surplus - occupy);		//	别人占用数量
			stringRedisTemplate.opsForValue().set(key, val, 5 * 60, TimeUnit.SECONDS);
		}
		return ResponseUtil.ok(map);
	}
	@GetMapping("reduction")
	public Object reduction(Integer catId, String time, @LoginUser Integer userId) {
		List<GoodsStock> goodList = goodsService.GetStock(time, catId);
		for (GoodsStock gooStock : goodList) {
			// 实例化Gson
			Gson gson = new Gson();
			// 创建门票key
			String key = gooStock.getGooId() + "-" + time.split(" ")[0] + "-ticket";
			if (stringRedisTemplate.hasKey(key)) {
				// 从reids中取出值
				String val = stringRedisTemplate.opsForValue().get(key);
				// 判断没有"$" ----这里是没有
				if (val.indexOf("$") == -1) {
					stringRedisTemplate.delete(key);
				} else {
					String[] gsonstr = val.split("&")[1].split("$");
					for (int i = 0; i < gsonstr.length; i++) {
						// 将值解析成UserTicket
						UserTicket userTicket = gson.fromJson(gsonstr[i], UserTicket.class);
						// 查看该用户有没有买该门票---这里是遍历到买了 删除该用户占用
						if (userTicket.getUserId() == userId) { // 没有就不管
							val = val.replace("$" + gson.toJson(userTicket), "");
						}
					}
				}
			}
		}
		return ResponseUtil.ok();
	}
	public class UserTicket {
		private int userId;
		private int number;
		public int getUserId() {
			return userId;
		}
		public void setUserId(int userId) {
			this.userId = userId;
		}
		public int getNumber() {
			return number;
		}
		public void setNumber(int number) {
			this.number = number;
		}

		
		
	}
	/**
	 * 增减购票数量(修改缓存)
	 *
	 * @param Time,
	 *            访问时间
	 * @param catId,
	 *            门票类目Id
	 * @param userId,
	 *            用户Id
	 * @param params,
	 *            用户门票信息
	 * @return
	 * @author liuyu
	 *         <p>
	 *         1.访问用户记录缓存 1.1(为空) --> 添加缓存 1.2(非空) --> 遍历访问缓存 1.2.1 -->
	 *         移除过期记录(超五分钟) --> 修改库存缓存数量(还原) 1.2.2 --> 添加(修改)记录缓存 -->
	 *         修改库存缓存数量(加、减)
	 */
	@GetMapping("getStock")
	public Object getStock(String Time, Integer catId, @LoginUser Integer userId, Integer gooId, Short gooNum,
			String params, Integer count, String price) {
		List<GoodsStock> stockList;
		Integer returnCode = 200;
		if (StringUtils.isEmpty(userId)) {
			return ResponseUtil.unlogin();
		}
		if (StringUtils.isEmpty(Time) || StringUtils.isEmpty(catId)) {
			return ResponseUtil.badArgument();
		}
		// 添加用户访问记录
		UserTimeDao timeDao = new UserTimeDao();
		timeDao.setUserId(userId);
		timeDao.setAccessTime(System.currentTimeMillis());
		timeDao.setCatId(catId);
		timeDao.setParams(params);
		timeDao.setPrice(price);
		// key值(访问记录)
		String keyUser = Time + "-" + catId + "-user";
		Map<Integer, UserTimeDao> map = new HashMap<>();
		// 缓存为空，则添加用户访问数据
		if (!stringRedisTemplate.hasKey(keyUser)) {
			// 获取用户门票信息
			Gson gson = new Gson();
			List<GoodsStock> goodList = gson.fromJson(params, new TypeToken<List<GoodsStock>>() {
			}.getType());
			for (GoodsStock ticgood : goodList) {
				Integer gooId1 = ticgood.getGooId();
				if (gooId1 == gooId) {
					ticgood.setNum((short) 1);
				}
			}
			params = JSON.toJSONString(goodList);
			timeDao.setParams(params);
			map.put(userId, timeDao);
			// 序列化
			String userTimeStr = JSON.toJSONString(map);
			stringRedisTemplate.opsForValue().set(keyUser, userTimeStr, 5 * 60, TimeUnit.SECONDS);
		}
		// 反序列化(用户访问记录)
		String userTimeStr = stringRedisTemplate.opsForValue().get(keyUser);
		map = JSONObject.parseObject(userTimeStr, new TypeReference<HashMap<Integer, UserTimeDao>>() {
		});
		// key值(门票信息)
		String key = Time + "-" + catId;
		if (!stringRedisTemplate.hasKey(key)) {
			stockList = goodsService.GetStock(Time, catId);
			String stock = JSON.toJSONString(stockList);
			stringRedisTemplate.opsForValue().set(key, stock, 5 * 60, TimeUnit.SECONDS);
		}
		String stockStr = stringRedisTemplate.opsForValue().get(key);
		stockList = JSONObject.parseArray(stockStr, GoodsStock.class);
		// 遍历访问缓存()
		List<UserTimeDao> values = new ArrayList<>(map.values());
		for (UserTimeDao dao : values) {
			// 访问时间
			long accessTime = dao.getAccessTime();
			// 间隔时间
			long intervalTime = (System.currentTimeMillis() - accessTime) / 1000;
			if (intervalTime >= 5 * 60) {
				// 移除访问记录(缓存)
				map.remove(dao.getUserId());
				// 还原库存
				clearUserRedis(params, stockList);
			}
		}
		map.put(userId, timeDao);
		// 序列化, 更新用户记录缓存
		userTimeStr = JSON.toJSONString(map);
		stringRedisTemplate.opsForValue().set(keyUser, userTimeStr, 5 * 60, TimeUnit.SECONDS);
		// 修改库存
		returnCode = updateRedisStock(key, keyUser, userId, gooNum, gooId, stockList, count);
		// 获取记录
		if (returnCode == -1) {
			Gson gson = new Gson();
			// 获取门票信息集合
			List<GoodsStock> list = gson.fromJson(params, new TypeToken<List<GoodsStock>>() {
			}.getType());
			for (GoodsStock goods : list) {
				if (gooId.equals(goods.getGooId())) {
					goods.setNum((short) (gooNum - 1));
				}
			}
			params = JSON.toJSONString(list);
			timeDao.setParams(params);
		}
		// 获取库存
		String goodStr = stringRedisTemplate.opsForValue().get(key);
		List<GoodsStock> list = JSONObject.parseArray(goodStr, GoodsStock.class);
		HashMap<String, Object> rusultsmap = new HashMap<>();
		rusultsmap.put("returnCode", returnCode);
		rusultsmap.put("items", list);
		rusultsmap.put("user", timeDao);
		return ResponseUtil.ok(rusultsmap);
	}

	/**
	 * 增减库存
	 *
	 * @param key
	 * @param keyUser
	 * @param userId
	 * @param num
	 * @param gooId
	 * @param stockList
	 * @param count,
	 *            101:(用户商品数量)加,201:(用户商品数量)减,301:库存还原
	 * @return
	 */
	public int updateRedisStock(String key, String keyUser, Integer userId, Short num, Integer gooId,
			List<GoodsStock> stockList, Integer count) {
		// 反序列化(访问记录)
		String userTimeStr = stringRedisTemplate.opsForValue().get(keyUser);
		Map<Integer, UserTimeDao> map = JSON.parseObject(userTimeStr,
				new TypeReference<HashMap<Integer, UserTimeDao>>() {
				});
		for (GoodsStock stock : stockList) {
			Integer stockGooId = stock.getGooId();
			if (stockGooId.equals(gooId)) {
				// 库存
				Integer stockNum = stock.getStock();
				UserTimeDao userTimeDao = map.get(userId);
				if (count == 101) {
					if (stockNum == 0) {
						return -1;
					}
					stock.setStock(stockNum - 1);
					userTimeDao.setNum(Integer.valueOf(num));
					map.put(userId, userTimeDao);
				} else if (count == 201) {
					stock.setStock(stockNum + 1);
					userTimeDao.setNum(Integer.valueOf(num));
					map.put(userId, userTimeDao);
				} else if (count == 301) {
					stock.setStock(stockNum + num);
				}
			}
		}
		String user = JSON.toJSONString(map);
		String stock = JSON.toJSONString(stockList);
		// 序列化，更新缓存
		stringRedisTemplate.opsForValue().set(keyUser, user, 1 * 60, TimeUnit.SECONDS);
		stringRedisTemplate.opsForValue().set(key, stock, 1 * 60, TimeUnit.SECONDS);
		return 200;
	}

	/**
	 * 还原库存
	 *
	 * @param params
	 * @param stockList
	 */
	public void clearUserRedis(String params, List<GoodsStock> stockList) {
		// 获取用户门票信息
		Gson gson = new Gson();
		List<TicGoods> goodList = gson.fromJson(params, new TypeToken<List<TicGoods>>() {
		}.getType());
		for (TicGoods goods : goodList) {
			// 获取
			Short num = goods.getNum();
			if (num > 0) {
				for (GoodsStock stock : stockList) {
					Integer stockGooId = stock.getGooId();
					Integer gooId = goods.getGooId();
					if (stockGooId.equals(gooId)) {
						stock.setStock(stock.getStock() + num);
					}
				}
			}
		}
	}

	/**
	 * 校验库存（提交订单前）
	 *
	 * @param userId
	 * @param Time
	 * @param catId
	 */
	@GetMapping("checkStock")
	public Object checkStock(@LoginUser Integer userId, String Time, Integer catId, Integer gooId) {
		if (StringUtils.isEmpty(userId)) {
			return ResponseUtil.unlogin();
		}
		// 查询库存
		if (StringUtils.isEmpty(Time) || StringUtils.isEmpty(catId)) {
			return ResponseUtil.badArgument();
		}
		GoodsStock oneStock = goodsService.getOneStock(Time, catId, gooId);
		return ResponseUtil.ok(oneStock);
	}
}
