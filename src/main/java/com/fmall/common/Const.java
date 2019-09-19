package com.fmall.common;

/**
 * @author ZSS
 * @description 常量类
 */
public class Const {

    public static final String CURRENT_USER = "currentUser";

    public static final String CURRENT_PRODUCT = "currentProduct";

    public static final String CURRENT_ORDER_DETAIL = "currentOrder";

    public static final String USERNAME = "username";

    public static final String EMAIL = "email";


    public interface RedisCacheExtime {
        // 30分钟
        int REDIS_SESSION_EXTIME = 60 * 30;
    }

    /**
     * 角色
     */
    public interface Role {
        int ROLE_ADMIN = 0;
        int ROLE_SELLER = 1;
        int ROLE_BUYER = 2;
        int APPLY_OPEN_SHOP = 3;
    }

    public interface Check {
        int CHECKED = 1;    // 已经选择
        int UNCHECKED = 0;  // 未选择
    }

    public interface Order {
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
        int ORDER_TRADE_FINISH = 40;
        /**
         * 订单关闭
         */
        int ORDER_CLOSE = 60;
        /**
         * 查询所有订单
         */
        int SELECT_ALL = 0;
    }

    /**
     * 商品展示的选择
     */
    public interface Type {
        int DEFAULT = 1;    // 默认
        int CATEGORY = 2;   // 肉种类
    }

    public interface AlipayCallback {
        String TRADE_STATUS_BUYER_PAY = "WAIT_BUYER_PAY";
        String TRADE_STATUS_TRADE_SUCCESS = "TRADE_SUCCESS";
        String RESPONSE_SUCCESS = "success";
        String RESPONSE_FAILED = "failed";
    }

    public enum OrderStatusEnum {
        // 已取消
        CANCEL(0, "已取消"),
        // 未支付
        NO_PAY(10, "未支付"),
        // 已付款
        PAID(20, "已付款"),
        // 已发货
        SHIPPED(40, "已发货"),
        // 订单完成
        ORDER_SUCCESS(50, "订单完成"),
        // 订单关闭
        ORDER_CLOSE(60, "订单关闭");

        OrderStatusEnum(int code, String value) {
            this.value = value;
            this.code = code;
        }

        private String value;
        private int code;

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }
    }

    public enum PayPlatFornEnum {
        // 支付宝支付
        ALIPAY(1, "支付宝"),
        // 微信支付
        WECHAT(2, "微信");

        PayPlatFornEnum(int code, String value) {
            this.value = value;
            this.code = code;
        }

        private String value;
        private int code;

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }
    }

    public interface Redis {
        // 关闭订单的分布式锁
        String CLOSE_ORDER_TASK_LOCK = "CLOSE_ORDER_TASK_LOCK";
    }

}
