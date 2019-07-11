# 农场品在线销售系统接口文档
-----

**BaseUrl:zssxl.natapp1.cc/farmmall**

----

## 返回结果状态码枚举 **ResponseBody**
```
{
    "0":"请求成功"
    "1":"请求失败"
    "2":"权限错误"
    "10":"需要登录"
}
```

## 常量类 **Const**

```
{
    "CURRENT_USER":"currentUser";

    "CURRENT_PRODUCT":"currentProduct";

    "CURRENT_ORDER_DETAIL":"currentOrder";

    "USERNAME":"username";

    "EMAIL":"email";
    
     /**
     * 商品状态
     */
    public interface Product{
        int ON_SALE = 1;
        int LOWER_SHELF = 2;
        int DELETE = 3;
    }

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

    /**
     * 订单
     */
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
    
     public interface AlipayCallback{
         static final String TRADE_STATUS_BUYER_PAY = "WAIT_BUYER_PAY";
         static final String TRADE_STATUS_TRADE_SUCCESS = "TRADE_SUCCESS";
         static final String RESPONSE_SUCCESS = "success";
         static final String RESPONSE_FAILED = "failed";
     }
    
     public enum OrderStatusEnum{
          CANCEL(0,"已取消"),
          NO_PAY(10,"未支付"),
          PAID(20,"已付款"),
          SHIPPED(40,"已发货"),
          ORDER_SUCCESS(50,"订单完成"),
          ORDER_CLOSE(60,"订单关闭");
    
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
    
     public enum PayPlatFornEnum{
         ALIPAY(1,"支付宝"),
         WECHAT(2,"微信");
    
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
}
```
---

##后台-用户接口

----------

### 用户登录

- POST /fmal/user/login.do

> request

    username: 小明  
    password: 123456    
    
> response

*success*

      {
        "status": 0,
        "msg": "登录成功",
        "data": {
            "id": 27,
            "username": "小明",
            "password": "",
            "shopName": "",
            "email": "1234567@123.com",
            "sex": 1,
            "phone": "136666666",
            "question": "问题",
            "answer": "问题",
            "role": 2,
            "createTime": 1560714530000,
            "updateTime": 1561443681000
        }
    }
    
*fail*
  
    {
        "status": 1,
        "msg": "密码错误"
    }   

---

### 获取卖家信息

- GET /fmall/user/get_seller_info.do

> request

    userId: 23
    
> response

*success*

    {
        "status": 0,
        "data": {
            "id": 23,
            "username": "seller01",
            "password": "",
            "shopName": "第一店铺",
            "email": "",
            "sex": 1,
            "phone": "",
            "question": "",
            "answer": "",
            "role": 1,
            "createTime": 1560674024000,
            "updateTime": 1560674024000
        }
    }
    
*fail*
  
    {
        "status": 1,
        "msg": "获取信息失败"
    }

---

### 获取登录用户信息

- GET /fmall/user/get_user_info.do

> request

    userId: 23
    
> response

*success*

    {
        "status": 0,
        "data": {
            "id": 23,
            "username": "小明",
            "password": "",
            "shopName": "",
            "email": "xiaoming@123.com",
            "sex": 1,
            "phone": "123456789",
            "question": "问题",
            "answer": "答案",
            "role": 2,
            "createTime": 1560674024000,
            "updateTime": 1560674024000
        }
    }
    
*fail*
  
    {
        "status": 1,
        "msg": "获取信息失败"
    }

---

### 用户注册

- POST /fmal/user/register.do

> request

    username: 小二
    password: 123456
    passwordConfirm: 123456
    phone: 13599999999
    email: xiaoer@123.com
    question: 问题
    answer: 答案
    sex: 1
    
> response

*success*

    {
        "status": 0,
        "msg": "注册成功"
    }
    
*fail*
  
    {
        "status": 1,
        "msg": "用户名已存在"
    }

---

###忘记密码

- GET /fmall/user/forget_get_question.do

> request

    username: 小二
    
> response

*success*

    {
        "status": 0,
        "msg": "问题"
    }
    
*fail*

    {
        "status": 1,
        "msg": "没有密保问题"
    }
    
---
    
- POST /fmall/user/forget_check_answer.do

> request

    username: 小二
    question: 问题
    answer: 答案
    
> response

*success*

    {
        "status": 0,
        "msg": "密保答案正确"
    }
    
*fail*

    {
        "status": 1,
        "msg": "密保答案错误"
    } 

- POST /fmall/user/forget_reset_password.do

> request

    username: 小二
    passwordNew: 1234567
    
> response

*success*

    {
        "status": 0,
        "msg": "找回密码成功"
    }
    
*fail*

    {
        "status": 1,
        "msg": "找回密码失败"
    } 

---

### 更改密码

- POST /fmall/user/reset_password.do

> request

    username: 123456
    passwordOld: 1234567
    passwordNew: 1234567
    
> response

*success*

    {
        "status": 0,
        "msg": "修改密码成功！"
    }
    
*fail*
  
    {
        "status": 1,
        "msg": "修改密码失败"
    }

---

### 申请开店

- POST /fmall/user/apply_to_open_shop.do

> request

    username: 小明
    phone: 136666666
    shopName: 小明之家
    
> response

*success*

    {
        "status": 0,
        "msg": "提交申请成功！"
    }
    
*fail*
  
     {
        "status": 1,
        "msg": "提交失败，用户名或者电话不匹配！"
    }


---

##后台-产品接口

---

### 发布商品

- POST /fmall/product/release_product.do

> request

    subImageFiles: (binary)
    subImageFiles: (binary)
    subImageFiles: (binary)
    categoryId: 100033
    livestock: 0
    name: 新鲜五花肉
    subtitle: 美味 新鲜 肥瘦均匀
    detail: 正宗农家五花肉
    price: 23
    file: (binary)
    stock: 198
    status: 1
    
> response

*success*

    {
        "status": 0,
        "msg": "商品上传成功"
    }
    
*fail*
  
     {
        "status": 1,
        "msg": "商品上传失败
    }

---

###展示商品

- GET /fmall/product/show_product.do

> request

    type: 1
    pn: 1
    categoryId: 0
    
> response

*success*

    {
    "status": 0,
    "data": {
        "total": 9,
        "list": [
            {
                "id": 32,
                "userId": 23,
                "categoryId": 100034,
                "livestock": 10003,
                "name": "顶级的猪耳朵",
                "subtitle": "成功中午就取打一份猪耳朵",
                "mainImage": "http://10.168.30.123/farmmall/87323fa9-35d1-4b6f-b5b6-0ba412178618.jpg",
                "subImages": "http://10.168.30.123/farmmall/4f8559eb-7706-43bc-8ce6-f2612c8b2d5e.jpg,",
                "detail": "没有详情 ",
                "price": 200,
                "stock": 186,
                "status": 1,
                "createTime": 1560881415000,
                "updateTime": 1560881415000
            },
            {
                "id": 58,
                "userId": 23,
                "categoryId": 100039,
                "livestock": 10002,
                "name": "上好猪耳朵",
                "subtitle": "啦啦啦啦啦啦啦",
                "mainImage": "http://10.168.30.123/farmmall/371ff4e6-f948-48d1-8d5c-2364e06ff83b.jpg",
                "subImages": "http://10.168.30.123/farmmall/522c911a-2a34-4088-91ae-6831571f78eb.jpg,http://10.168.30.123/farmmall/3b926c2c-74d7-4a73-a874-859fa85292c5.jpg,http://10.168.30.123/farmmall/3c8b7b13-4857-402c-a1c5-e6a8fc966826.jpg,",
                "detail": "没有",
                "price": 13,
                "stock": 25,
                "status": 1,
                "createTime": 1560918291000,
                "updateTime": 1560918291000
            }
        ],
        "pageNum": 1,
        "pageSize": 12,
        "size": 9,
        "startRow": 1,
        "endRow": 9,
        "pages": 1,
        "prePage": 0,
        "nextPage": 0,
        "isFirstPage": true,
        "isLastPage": true,
        "hasPreviousPage": false,
        "hasNextPage": false,
        "navigatePages": 8,
        "navigatepageNums": [
            1
        ],
        "navigateFirstPage": 1,
        "navigateLastPage": 1
    }
}
    
*fail*
  
    {
        "status": 1,
        "msg": "查询失败"
    } 
    
---

### 以卖家的身份展示商品

- GET /fmall/product/show_product_by_seller.do

> request

   无
    
> response

*success*

     {
        "status": 0,
        "data": [
            {
                "id": 31,
                "userId": 23,
                "categoryId": 100033,
                "livestock": 10001,
                "name": "上好猪耳朵",
                "subtitle": "啦啦啦啦啦啦啦",
                "mainImage": "http://10.168.30.123/farmmall/87323fa9-35d1-4b6f-b5b6-0ba412178618.jpg",
                "subImages": "http://10.168.30.123/farmmall/0d8ef19d-3b27-481c-842f-b523aec71b9d.jpg,",
                "detail": "asdf",
                "price": 13,
                "stock": 5,
                "status": 2,
                "createTime": 1560880774000,
                "updateTime": 1560880774000
            },
            {
                "id": 61,
                "userId": 23,
                "categoryId": 100033,
                "livestock": 0,
                "name": "新鲜五花肉",
                "subtitle": "美味 新鲜 肥瘦均匀",
                "mainImage": "http://10.168.30.123/farmmall/33bc5e12-301a-4303-aa17-60ed6d17f808.jpg",
                "subImages": "http://10.168.30.123/farmmall/7726ed5d-3b85-4a1e-97cf-1c58b8cbc0a6.jpg,http://10.168.30.123/farmmall/0c6db090-1b39-4ffc-9624-43bd19450f2a.jpg,http://10.168.30.123/farmmall/e182c224-59e8-4674-b969-8195b99d4dfd.jpg,",
                "detail": "正宗农家五花肉",
                "price": 23,
                "stock": 198,
                "status": 1,
                "createTime": 1561640188000,
                "updateTime": 1561640188000
            }
        ]
    }
    
*fail*
  
    {
        "status": 1,
        "msg": "查询失败"
    }  

---

### 修改商品的状态（上下架，删除）

- POST /fmall/product/update_product_status.do

> request

    status: 1
    productId: 31
    
> response

*success*

    {
        "status": 0,
        "msg": "更新商品状态成功"
    }   
    
*fail*
  
    {
        "status": 1,
        "msg": "更新商品状态失败"
    }   

---

###获取所有商品分类

- GET /fmall/category/get_all_category.do

> request

    无
    
> response

*success*

    {
    "status": 0,
    "msg": "查询成功",
    "data": [
        {
            "id": 100039,
            "name": "后腿",
            "createTime": null,
            "updateTime": null
        },
        {
            "id": 100040,
            "name": "后脚",
            "createTime": null,
            "updateTime": null
        }
    ]
}


###展示某个商品像详情

- POST /fmall/product/show_product_by_id.do

> request

    productId: 32
    
> response

*success*

    {
        "status": 0,
        "msg": "查询成功"
    }
    
*fail*
  
    {
        "status": 1,
        "msg": "查询失败"
    } 

---

### 收藏商品

- POST fmall/product/add_collection.do

> request

    productId: 32
    
> response

*success*

    {
        "status": 0,
        "msg": "收藏成功"
    } 
    
*fail*
  
    {
        "status": 1,
        "msg": "您的购物车中已有该商品"
    } 

---

### 删除收藏

- POST /fmall/product/delete_collection.do

> request

   productId: 31
    
> response

*success*

    {
        "status": 0,
        "msg": "删除成功"
    } 
    
*fail*
  
    {
        "status": 1,
        "msg": "删除失败"
    } 
    
---



---

### 该产品的溯源信息

- GET /fmall/product/show_livestock.do

> request

    livestock: 10003
    
> response

*success*

    {
        "status": 0,
        "data": {
            "id": 25,
            "label": 10003,
            "varieties": "瘦肉猪",
            "weight": 102,
            "faces": "无",
            "stapleFood": "饲料",
            "medicalRecord": "无",
            "health": "健康",
            "vaccine": "无",
            "age": 3,
            "photo": "无",
            "origin": "赣州",
            "createTime": 1560793633000,
            "updateTime": 1560793633000
        }
    }
    
*fail*
  
    {
        "status": 1,
        "msg": "查询失败"
    } 

---


##后台-订单接口

---

### 从购物车中下单

- POST /fmall/order/create_cart.do

> request

    productId: 55
    quantity: 1
    shippingId: 45
    
> response

*success*

    {
        "status": 0,
        "msg": "下单成功"
    } 
    
*fail*
  
    {
        "status": 1,
        "msg": "下单失败"
    } 

---

### 从购物车中下单多个商品

- POST /fmall/order/create_order_from_cart.do

> request

   shippingId: 45
    
> response

*success*

    {
        "status": 0,
        "msg": "下单成功"
    } 
    
*fail*
  
    {
        "status": 1,
        "msg": "下单失败"
    } 
    
---

### 付款

- POST /fmall/order/pay.do

> request

   orderNo: 1561344756224
    
> response

*success*

     {
        "status": 0,
        "msg": "付款成功"
    } 
    
*fail*
  
    {
        "status": 1,
        "msg": "付款失败"
    } 

---

### 支付宝回调

- POST /fmall/order/alipay_callback.do

> request

   无
    
> response

*success*

     {
        success
     } 
    
*fail*
  
    {
        failed
    } 

---

### 查看订单状态

- POST /fmall/order/query_order_pay_status.do

> request

   orderNo: 1561344756224
    
> response

*success*

     {
        status:0
        data:true
     } 
    
*fail*
  
    {
        status:0
        data:false
    } 

---

### 删除订单

- POST /fmall/order/delete.do

> request

   orderNo: 1561344756224
    
> response

*success*

    {
        "status": 0,
        "msg": "删除订单成功"
    } 
    
*fail*
  
    {
        "status": 1,
        "msg": "删除订单失败"
    }  
     
---

### 以卖家的身份查询订单

- GET /fmall/order/select_order_seller_id_type.do

> request

    status: 0
    pn: 1
    
> response

*success*

     {
        "status": 0,
        "data": {
            "total": 1,
            "list": [
                {
                    "userId": 23,
                    "orderNo": 1561425124844,
                    "productId": 60,
                    "productName": "什么是汤骨",
                    "productImage": "http://10.168.30.123/farmmall/075579ed-8206-44b2-926b-72a574325144.jpg",
                    "currentUnitPrice": 998,
                    "quantity": 1,
                    "totalPrice": 998,
                    "username": "seller01",
                    "status": 50
                }
            ],
            "pageNum": 1,
            "pageSize": 20,
            "size": 1,
            "startRow": 1,
            "endRow": 1,
            "pages": 1,
            "prePage": 0,
            "nextPage": 0,
            "isFirstPage": true,
            "isLastPage": true,
            "hasPreviousPage": false,
            "hasNextPage": false,
            "navigatePages": 8,
            "navigatepageNums": [
                1
            ],
            "navigateFirstPage": 1,
            "navigateLastPage": 1
        }
    }
    
*fail*
  
    {
        "status": 1,
        "msg": "查询失败"
    } 

---

### 以卖家的身份单方面删除订单

- POST /fmall/order/delete_by_seller.do

> request

   orderNo: 1561425124844
    
> response

*success*

    {
        "status": 0,
        "msg": "单方面删除成功"
    }  
    
*fail*
  
    {
        "status": 1,
        "msg": "删除失败"
    } 

---

### 卖家发货

- POST /fmall/order/send_order.do

> request

    orderNo: 1561290109558
    boxId: 531651109
    
> response

*success*

    {
        "status": 0,
        "msg": "发货成功"
    }  
    
*fail*
  
    {
        "status": 1,
        "msg": "发货失败"
    } 

---

##后台-品类接口

---

### 展示产品分类

- GET /fmall/category/get_all_category.do

> request

   无
    
> response

*success*

     {
        "status": 0,
        "msg": "查询成功",
        "data": [
            {
                "id": 100032,
                "name": "前腿",
                "createTime": null,
                "updateTime": null
            },
            {
                "id": 100040,
                "name": "后脚",
                "createTime": null,
                "updateTime": null
            },
            {
                "id": 100041,
                "name": "肘",
                "createTime": null,
                "updateTime": null
            }
        ]
    }
    
*fail*
  
     {
        "status": 1,
        "msg": "查询失败"
    } 

---


##后台-购物车接口

---

### 添加购物车

- POST /fmall/cart/add_cart.do

> request

    quantity: 4
    productId: 32
    
> response

*success*

    {
        "status": 0,
        "msg": "添加购物车陈工"
    } 
    
*fail*
  
    {
        "status": 1,
        "msg": "添加购物车失败"
    } 

---

### 展示购物车

- GET /fmall/cart/show_cart.do

> request

    pn: 1
    
> response

*success*

    {
        "status": 0,
        "msg": "查询成功",
        "data": {
            "total": 6,
            "list": [
                {
                    "id": 205,
                    "userId": 27,
                    "productId": 55,
                    "quantity": 1,
                    "checked": 1,
                    "mainImage": "http://10.168.30.123/farmmall/57bc1a6c-a77b-477e-aeea-edb2bc45a420.png",
                    "name": "上好猪耳朵",
                    "price": 13
                },
                {
                    "id": 206,
                    "userId": 27,
                    "productId": 32,
                    "quantity": 4,
                    "checked": 1,
                    "mainImage": "http://10.168.30.123/farmmall/87323fa9-35d1-4b6f-b5b6-0ba412178618.jpg",
                    "name": "顶级的猪耳朵",
                    "price": 200
                }
            ],
            "pageNum": 1,
            "pageSize": 20,
            "size": 6,
            "startRow": 1,
            "endRow": 6,
            "pages": 1,
            "prePage": 0,
            "nextPage": 0,
            "isFirstPage": true,
            "isLastPage": true,
            "hasPreviousPage": false,
            "hasNextPage": false,
            "navigatePages": 8,
            "navigatepageNums": [
                1
            ],
            "navigateFirstPage": 1,
            "navigateLastPage": 1
        }
    }
    
*fail*
  
    {
        "status": 1,
        "msg": "查询失败"
    } 


---


### 购物车商品数量加一

- POST /fmall/cart/add_quantity.do

> request

    cartId: 201
    
> response

*success*

    {"status":0}
    
*fail*
  
    {"status":1}
    
---

### 购物车商品数量减一

- POST /fmall/cart/dec_quantity.do

> request

    cartId: 201
    
> response

*success*

    {"status":0}
    
*fail*
  
    {"status":1}
    
----

### 计算当前购物车中已勾选的商品的总价

- POST /fmall/cart/calcu_total_price.do

> request

    无
    
> response

*success*

    {"status":0,"msg":"1876.0"}
    
*fail*
  
    {"status":1}

---

### 修改购物车中商品的选中状态

- POST /fmall/cart/select_checked.do

> request

    cartId: 191
    type: 0
    
> response

*success*

    {
        "status": 0,
        "msg": "状态已选:0"
    }
    
*fail*
  
     {
        "status": 1,
        "msg": "状态更改失败"
    }

---

##后台-收藏接口

---

### 展示个人收藏

- GET /fmall/product/show_collection.do

> request

   pn: 1
    
> response

*success*

     {
    "status": 0,
    "data": {
        "total": 5,
        "list": [
            {
                "id": 58,
                "userId": 23,
                "categoryId": 100039,
                "livestock": 10002,
                "name": "上好猪耳朵",
                "subtitle": "啦啦啦啦啦啦啦",
                "mainImage": "http://10.168.30.123/farmmall/371ff4e6-f948-48d1-8d5c-2364e06ff83b.jpg",
                "subImages": "http://10.168.30.123/farmmall/522c911a-2a34-4088-91ae-6831571f78eb.jpg,http://10.168.30.123/farmmall/3b926c2c-74d7-4a73-a874-859fa85292c5.jpg,http://10.168.30.123/farmmall/3c8b7b13-4857-402c-a1c5-e6a8fc966826.jpg,",
                "detail": "没有",
                "price": 13,
                "stock": 25,
                "status": 1,
                "createTime": 1560918291000,
                "updateTime": 1560918291000
            },
            {
                "id": 32,
                "userId": 23,
                "categoryId": 100034,
                "livestock": 10003,
                "name": "顶级的猪耳朵",
                "subtitle": "成功中午就取打一份猪耳朵",
                "mainImage": "http://10.168.30.123/farmmall/87323fa9-35d1-4b6f-b5b6-0ba412178618.jpg",
                "subImages": "http://10.168.30.123/farmmall/4f8559eb-7706-43bc-8ce6-f2612c8b2d5e.jpg,",
                "detail": "没有详情 ",
                "price": 200,
                "stock": 186,
                "status": 1,
                "createTime": 1560881415000,
                "updateTime": 1560881415000
            }
        ],
        "pageNum": 1,
        "pageSize": 50,
        "size": 5,
        "startRow": 1,
        "endRow": 5,
        "pages": 1,
        "prePage": 0,
        "nextPage": 0,
        "isFirstPage": true,
        "isLastPage": true,
        "hasPreviousPage": false,
        "hasNextPage": false,
        "navigatePages": 8,
        "navigatepageNums": [
            1
        ],
        "navigateFirstPage": 1,
        "navigateLastPage": 1
    }
}
    
*fail*
  
    {
        "status": 1,
        "msg": "展示失败 "
    } 
    
---

### 添加收藏

- POST /fmall/product/add_collection.do

> request

   productId: 59
    
> response

*success*

     {
        "status": 0,
        "msg": "收藏成功 "
    } 
    
*fail*
  
     {
        "status": 1,
        "msg": "你已经收藏了该商品 "
    } 

---

##后台-收货地址接口

---

### 展示个人收货地址

- GET /fmall/shipping/show_shipping.do

> request

    pn: 1
    
> response

*success*

    {
    "status": 0,
    "data": {
        "total": 4,
        "list": [
            {
                "id": 45,
                "userId": 27,
                "receiverName": "小明他爸",
                "receiverPhone": "123456789123",
                "receiverProvince": "天津市",
                "receiverCity": "天津市",
                "receiverDistrict": "塘沽区",
                "receiverAddress": "这是什么犄角旮旯",
                "receiverZip": "123456",
                "createTime": 1561202914000,
                "updateTime": 1561202914000
            },
            {
                "id": 52,
                "userId": 27,
                "receiverName": "天山童姥",
                "receiverPhone": "13555555555",
                "receiverProvince": "新疆",
                "receiverCity": "乌鲁木齐市",
                "receiverDistrict": "天山区",
                "receiverAddress": "逍遥派",
                "receiverZip": "123456",
                "createTime": 1561469828000,
                "updateTime": 1561469828000
            }
        ],
        "pageNum": 1,
        "pageSize": 6,
        "size": 4,
        "startRow": 1,
        "endRow": 4,
        "pages": 1,
        "prePage": 0,
        "nextPage": 0,
        "isFirstPage": true,
        "isLastPage": true,
        "hasPreviousPage": false,
        "hasNextPage": false,
        "navigatePages": 8,
        "navigatepageNums": [
            1
        ],
        "navigateFirstPage": 1,
        "navigateLastPage": 1
    }
}
    
*fail*

    {
        "status": 1,
        "msg": "目前还没有收货地址 "
    } 
    
---

### 添加收货地址

- POST /fmall/shipping/add_shipping.do

> request

    receiverName: 小明
    receiverPhone: 123456789
    receiverZip: 341000
    receiverAddress: 客家达到156号
    receiverProvince: 江西省
    receiverCity: 赣州市
    receiverDistrict: 章贡区
    
> response

*success*

    {
        "status": 0,
        "msg": "目前还没有收货地址 "
    } 
    
*fail*
  
    {
        "status": 1,
        "msg": "目前还没有收货地址 "
    } 

---

### 删除收货地址

- POST /fmall/shipping/delete_shipping.do

> request

    shippingId: 53
    
> response

*success*

    {
        "status": 0,
        "msg": "删除收货地址成功 "
    } 
    
*fail*
  
    {
        "status": 1,
        "msg": "删除收货地址失败 "
    } 


---
