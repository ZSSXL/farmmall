package com.fmall.util;

import com.fmall.pojo.User;
import com.github.pagehelper.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.type.TypeReference;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ZSS
 * @date 2019/9/11 21:59
 * @description 对象序列化和反序列化
 */
@Slf4j
public class JsonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        // 序列化相关配置
        // 对象的所有字段全部列入
        objectMapper.setSerializationInclusion(Inclusion.ALWAYS);

        // 取消默认转换timestamp形式
        objectMapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);

        // 忽略空Bean转json的错误
        objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);

        // 所有的日期格式都统一以下的样式，即：yyyy-MM-dd HH:mm:ss
        objectMapper.setDateFormat(new SimpleDateFormat(DateFormat.STANARD_FORMAT_DETAIL));

        // 反序列化的相关配置
        // 忽略在json字符串中存在，但是在java对象中不存在对应属性的情况，防止错误
        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * 对象序列化
     *
     * @param object 对象
     * @param <T>    泛型
     * @return String
     */
    public static <T> String objectToString(T object) {
        if (object == null) {
            return null;
        }
        try {
            return object instanceof String ? (String) object : objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            log.warn("Parse object to String error", e);
            return null;
        }
    }

    /**
     * 返回已经格式化好的json字符串
     *
     * @param object 对象
     * @param <T>    泛型
     * @return String
     */
    public static <T> String objectToStringPretty(T object) {
        if (object == null) {
            return null;
        }
        try {
            return object instanceof String ? (String) object :
                    objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (Exception e) {
            log.warn("Parse object to String error", e);
            return null;
        }
    }

    /**
     * 将json字符串转化为Object对象
     *
     * @param str   json字符串
     * @param clazz 对象
     * @param <T>   泛型
     * @return 泛型
     */
    public static <T> T stringToObject(String str, Class<T> clazz) {
        if (StringUtil.isEmpty(str) || clazz == null) {
            return null;
        }
        try {
            return clazz.equals(String.class) ? (T) str : objectMapper.readValue(str, clazz);
        } catch (Exception e) {
            log.warn("Parse String to object error", e);
            return null;
        }
    }

    /**
     * 高可用的json字符串转化为class对象
     *
     * @param str           json字符串
     * @param typeReference typeReference
     * @param <T>           泛型
     * @return 泛型
     */
    public static <T> T stringToObject(String str, TypeReference<T> typeReference) {
        if (StringUtil.isEmpty(str) || typeReference == null) {
            return null;
        }
        try {
            return (T) (typeReference.getType().equals(String.class) ? str : objectMapper.readValue(str, typeReference));
        } catch (Exception e) {
            log.warn("Parse String to object error", e);
            return null;
        }
    }

    /**
     * 泛型反序列化
     *
     * @param str             json字符串
     * @param collectionClass collectionClass
     * @param elementsClasses elementsClasses
     * @param <T>             泛型
     * @return 泛型
     */
    public static <T> T stringToObject(String str, Class<?> collectionClass, Class<?>... elementsClasses) {
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(collectionClass, elementsClasses);
        try {
            return objectMapper.readValue(str, javaType);
        } catch (Exception e) {
            log.warn("Parse String to Object error", e);
            return null;
        }
    }


    public static void main(String[] args) {
        User user = User.builder()
                .id(123)
                .answer("今天是星期三")
                .email("123456@qq.com")
                .password("123456")
                .phone("13455556666")
                .role(1)
                .sex(0)
                .shopName("四海商社")
                .username("张三")
                .question("今天是星期几？")
                .build();
        User user2 = User.builder()
                .id(124)
                .answer("今天是星期四")
                .email("123456@qq.com")
                .password("123456")
                .phone("13455556666")
                .role(1)
                .sex(0)
                .shopName("四海商社")
                .username("李四")
                .question("今天是星期几？")
                .build();
        System.out.println("原:" + user);

        String userJson = JsonUtil.objectToString(user);
        System.out.println("json:" + userJson);

        String userJsonPretty = JsonUtil.objectToStringPretty(user);
        System.out.println("prettyJson:" + userJsonPretty);

        User userClass = JsonUtil.stringToObject(userJson, User.class);
        System.out.println("stringToObject:" + userClass);

        List<User> userList = new ArrayList<>();
        userList.add(user);
        userList.add(user2);
        System.out.println(userList.toString());

        String userListStr = JsonUtil.objectToStringPretty(userList);
        System.out.println("userListStr:" + userListStr);

        List<User> userListObject = JsonUtil.stringToObject(userListStr, new TypeReference<List<User>>() {
        });
        System.out.println("userListObject:" + userListObject);

        List<User> userListObject2 = JsonUtil.stringToObject(userListStr, List.class, User.class);
        System.out.println("userListObject:" + userListObject2);

        System.out.println("end");
    }


}
