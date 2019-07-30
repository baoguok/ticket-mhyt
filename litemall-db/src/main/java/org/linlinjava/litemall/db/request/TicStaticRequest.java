package org.linlinjava.litemall.db.request;

import org.linlinjava.litemall.db.domain.TicStatistics;

public class TicStaticRequest extends TicStatistics {

    // 门票变化数量
    private Integer num;

    // (true:售票,false:退票)
    private Boolean bool;

    // 售票员Id
    private Integer ordAdminId;

    //订单状态
    private Integer ordOrderStatus;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Boolean getBool() {
        return bool;
    }

    public void setBool(Boolean bool) {
        this.bool = bool;
    }

    public Integer getOrdAdminId() {
        return ordAdminId;
    }

    public void setOrdAdminId(Integer ordAdminId) {
        this.ordAdminId = ordAdminId;
    }

    public Integer getOrdOrderStatus() {
        return ordOrderStatus;
    }

    public void setOrdOrderStatus(Integer ordOrderStatus) {
        this.ordOrderStatus = ordOrderStatus;
    }
}

