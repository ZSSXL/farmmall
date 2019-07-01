package com.fmall.onenet.mq;


import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

public class MqClient {
    private MqttConnectOptions options  = new MqttConnectOptions();;
    private MqttClient client ;

    private String subTopic;


    public synchronized boolean connect(){

        String clientID = "aaa"; //用户自定义合法的UTF-8字符串，可为空
        String serverURI = "ssl://183.230.40.96:8883";
        String userName = "AAAF05AB686730F893237D4DA681D3A6E"; //MQID

        String mqTopic = "test"; //mq topic
        String mqSub = "test"; // mq sub
        try {
            if(null == client) {
               client =  new MqttClient(serverURI, clientID, new MemoryPersistence());
            }
            //获取连接配置
            resetOptions();
            try {
                client.connect(options);
            } catch (MqttException e) {
                e.printStackTrace();
            }
            subTopic = String.format("$sys/pb/consume/%s/%s/%s", userName, mqTopic, mqSub);
            client.setCallback(new PushCallback(this));
            try {
                //订阅 topic $sys/pb/consume/$MQ_ID/$TOPIC/$SUB ，QoS必须大于0，否则订阅失败
                client.subscribe(subTopic, 1);
                System.out.println("sub success");
                return true;
            } catch (MqttException e) {
                System.out.println("sub failed");
                e.printStackTrace();
            }
            return false;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    private void resetOptions(){
        String userName = "AAAF05AB686730F893237D4DA681D3A6E"; //MQIDAFAAE388F0541F1666C49BAB3FDF42D8F
        String accessKey = "N3aygJWWSVs72WSP82MrKNDb38V173eBWK1zCclJP2A="; //mq access_keyMmZM2zpaBRROszP6+HYI9r3TgJ+kKnCAdrVeZtvCfOo=

        String version = "2018-10-31"; //版本号
        String resourceName = "mqs/" + userName;  //通过MQ_ID访问MQ
        String expirationTime = System.currentTimeMillis() / 1000 + 100 * 24 * 60 * 60 + "";
        String signatureMethod = "md5";  //签名方法，支持md5、sha1、sha256
        String password = null;
        try {
            //生成token
            password = Token.assembleToken(version, resourceName, expirationTime, signatureMethod, accessKey);
        } catch (UnsupportedEncodingException | InvalidKeyException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        options.setCleanSession(true); //clean session 必须设置 true
        options.setUserName(userName);
        options.setPassword(password.toCharArray());
        options.setConnectionTimeout(20);
        options.setKeepAliveInterval(30);
        options.setMqttVersion(MqttConnectOptions.MQTT_VERSION_3_1_1);
        InputStream caCrtFile = null;
        try{
            caCrtFile = this.getClass().getResource("/mqca/certificate.pem").openStream();
        } catch (IOException e){
            e.printStackTrace();
            return;
        }

        try {
            options.setSocketFactory(SslUtil.getSocketFactory(caCrtFile));
        } catch (NoSuchAlgorithmException | KeyStoreException | CertificateException
                | IOException | KeyManagementException e) {
            e.printStackTrace();
        }
    }

    public boolean reConnect() {
        if(null != client) {
            try {
                if(!client.isConnected()){ //订阅失败而导致重连是不需要重新连接
                    client.connect(options);
                }
                client.subscribe(subTopic, 1);//订阅失败会抛异常
                System.out.println("reconncet and sub ok");
                return true;
            } catch (Exception e) {//订阅和连接失败都会进到此异常中
                System.out.println("reconnect failed");
                e.printStackTrace();//由于在循环中调用，建议调试时打印堆栈信息，正式中此打印删除
                return false;
            }
        }else{
            return connect();
        }
    }
}
