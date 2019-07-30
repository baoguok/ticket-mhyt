package org.linlinjava.litemall.wx.dao;

import org.linlinjava.litemall.db.domain.TicGoods;

import java.util.List;

public class UserTimeDao {
    //访问时间
    private long accessTime;
    //门票类别Id
    private Integer catId;
    // 购买数量
    private Integer num;
    // 用户Id
    private Integer userId;
    // 订单金额
    private String price;
    //用户门票信息
    private String params;

    public long getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(long accessTime) {
        this.accessTime = accessTime;
    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getParams() {
        return params;
    }
    public void setParams(String params) {
        this.params = params;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
