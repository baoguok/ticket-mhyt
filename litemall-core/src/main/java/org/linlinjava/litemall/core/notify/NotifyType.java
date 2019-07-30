package org.linlinjava.litemall.core.notify;

public enum NotifyType {
    PAY_SUCCEED("paySucceed"), // 支付成功 的短信ID
    SHIP("ship"),
    REFUND("refund"),       // 退款成功的短信ID
    CAPTCHA("captcha");

    private String type;

    NotifyType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}
