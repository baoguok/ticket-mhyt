package org.linlinjava.litemall.db.response;

import org.linlinjava.litemall.db.domain.TicOrderGoods;

public class TicOrderGoodResponse extends TicOrderGoods {
    //门票类目
    private String catName;

    // 允许人数
    private Integer catPeopleNum;


    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public Integer getCatPeopleNum() {
        return catPeopleNum;
    }

    public void setCatPeopleNum(Integer catPeopleNum) {
        this.catPeopleNum = catPeopleNum;
    }
}
