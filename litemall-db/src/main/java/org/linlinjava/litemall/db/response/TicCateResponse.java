package org.linlinjava.litemall.db.response;

import org.linlinjava.litemall.db.domain.TicCategory;
import org.linlinjava.litemall.db.domain.TicGoods;

import java.util.List;

public class TicCateResponse extends TicCategory {

    /**
     * 门票列表
     */
    List<TicGoods> goodsList;

    public List<TicGoods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<TicGoods> goodsList) {
        this.goodsList = goodsList;
    }
}
