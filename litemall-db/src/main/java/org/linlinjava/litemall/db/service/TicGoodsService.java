package org.linlinjava.litemall.db.service;

import com.github.pagehelper.PageHelper;
import org.linlinjava.litemall.db.dao.TicGoodsMapper;
import org.linlinjava.litemall.db.domain.GoodsStock;
import org.linlinjava.litemall.db.domain.TicGoods;
import org.linlinjava.litemall.db.domain.TicGoodsExample;
import org.linlinjava.litemall.db.request.TicGoodRequest;
import org.linlinjava.litemall.db.response.TicGoodResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.linlinjava.litemall.db.util.TimeUtil.LocalToDate;

@Service
public class TicGoodsService {
		@Resource
		private TicGoodsMapper goodsMapper;

		/**
		 * 获取库存
		 *
		 * @param Time
		 * @param catId
		 * @return
		 * @author tangkang
		 */
		public List<GoodsStock> GetStock(String Time, Integer catId) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("Time", Time);
				map.put("catId", catId);
				return goodsMapper.GetStock(map);
		}

		public GoodsStock getOneStock(String Time, Integer catId, Integer gooId) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("Time", Time);
				map.put("catId", catId);
				map.put("gooId", gooId);
				return goodsMapper.getOneStock(map);
		}

		public List<TicGoods> queryByCid(Integer catId) {
				TicGoodsExample example = new TicGoodsExample();
				TicGoodsExample.Criteria c = example.createCriteria();
				c.andGooIsOnSaleEqualTo(true).andGooCatIdEqualTo(catId).andGooDeletedEqualTo(false);
				example.setOrderByClause("goo_add_time desc");
				return goodsMapper.selectByExample(example);
		}

		public List<TicGoods> query() {
				TicGoodsExample example = new TicGoodsExample();
				TicGoodsExample.Criteria c = example.createCriteria();
				c.andGooIsOnSaleEqualTo(true).andGooDeletedEqualTo(false);
				example.setOrderByClause("goo_add_time desc");
				return goodsMapper.selectByExample(example);
		}

		/**
		 * 条件查询门票信息(多表查询)
		 *
		 * @param goodReq
		 * @return
		 * @author liuyu
		 */
		public List<TicGoodResponse> list(TicGoodRequest goodReq) {
				Integer page = goodReq.getPage();
				Integer size = goodReq.getLimit();
				if (!StringUtils.isEmpty(page) && !StringUtils.isEmpty(size)) {
					PageHelper.startPage(page, size);
				}
				return goodsMapper.selectGood(goodReq);
		}

		/**
		 * 查询余票
		 *
		 * @param ticGoods
		 * @return
		 * @author liuyu
		 */
		public List<TicGoods> listGood(TicGoods ticGoods) {
				TicGoodsExample example = new TicGoodsExample();
				TicGoodsExample.Criteria criteria = example.createCriteria();
				if (!StringUtils.isEmpty(ticGoods.getGooIsOnSale())) {
						criteria.andGooIsOnSaleEqualTo(ticGoods.getGooIsOnSale());
				}
				if (!StringUtils.isEmpty(ticGoods.getGooSelfHelp())) {
						criteria.andGooSelfHelpEqualTo(ticGoods.getGooSelfHelp());
				}
				criteria.andGooDeletedEqualTo(false);
				return goodsMapper.selectByExample(example);
		}

		/**
		 * 创建门票信息
		 *
		 * @param ticGoods
		 * @return
		 * @author liuyu
		 */
		public int create(TicGoods ticGoods) {
				Date date = LocalToDate(LocalDateTime.now());
				ticGoods.setGooAddTime(date);
				ticGoods.setGooUpdateTime(date);
				return goodsMapper.insertSelective(ticGoods);
		}

		/**
		 * 修改门票信息
		 *
		 * @param ticGoods
		 * @return
		 * @author liuyu
		 */
		public int update(TicGoods ticGoods) {
				if (StringUtils.isEmpty(ticGoods.getGooId())) {
						return 0;
				}
				Date date = LocalToDate(LocalDateTime.now());
				ticGoods.setGooUpdateTime(date);
				return goodsMapper.updateByPrimaryKeySelective(ticGoods);
		}

		/**
		 * 逻辑删除门票信息
		 *
		 * @param goodId
		 * @return
		 * @author liuyu
		 */
		public int delete(Integer goodId) {
				Date date = LocalToDate(LocalDateTime.now());
				if (StringUtils.isEmpty(goodId)) {
						return 0;
				}
				TicGoods ticGoods = new TicGoods();
				ticGoods.setGooId(goodId);
				ticGoods.setGooUpdateTime(date);
				ticGoods.setGooDeleted(true);
				return goodsMapper.updateByPrimaryKeySelective(ticGoods);
		}

}
