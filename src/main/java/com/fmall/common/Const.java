package com.fmall.common;

/**
 * 常量类
 */

public class Const {

    public static final String CURRENT_USER = "currentUser";

    public static final String CURRENT_PRODUCT = "currentProduct";

    public static final String CURRENT_ORDER_DETAIL = "currentOrder";

    public static final String USERNAME = "username";

    public static final String EMAIL = "email";

    /**
     * 角色
     */
    public interface  Role{
        int ROLE_ADMIN = 0;
        int ROLE_SELLER = 1;
        int ROLE_BUYER = 2;
        int APPLY_OPEN_SHOP = 3;
    }

    public interface Check{
        int CHECKED = 1;    // 已经选择
        int UNCHECKED = 0;  // 未选择
    }

    public interface Order{
        /**
         * 支付类型 在线支付
         */
        int PAYMENT_TYPE = 1;
        /**
         * 订单取消
         */
        int ORDER_CANCEL = 0;
        /**
         * 未支付
         */
        int ORDER_UNPAY = 10;
        /**
         * 已支付
         */
        int ORDER_PAYED = 20;
        /**
         * 已发货
         */
        int ORDER_SENDED = 40;
        /**
         * 交易完成
         */
        int ORDER_TRADE_FINESH = 40;
        /**
         * 订单关闭
         */
        int ORDER_CLOSE = 60;

        int SELECT_ALL = 0; // 查询全部订单
    }

    /**
     * 商品展示的选择
     */
    public interface Type{
        int DEFAULT = 1;    // 默认
        int CATEGORY = 2;   // 肉种类
    }

}
