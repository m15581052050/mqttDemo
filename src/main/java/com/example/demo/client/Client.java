package com.example.demo.client;

import com.example.demo.PushCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by pengcheng.du on 2018/10/12.
 */
public class Client {
    public static final String SERVER_URL = "tcp://0.0.0.0:61613";
    public static final String TOPIC = "wether";
    public static final String clientid  = "client4";

    private MqttClient client;
    private MqttConnectOptions options;
    private String userName = "admin";
    private String passWord = "password";

    private ScheduledExecutorService scheduler;

    private void start() throws Exception{
        client = new MqttClient(SERVER_URL, clientid, new MemoryPersistence());
        options = new MqttConnectOptions();
        options.setCleanSession(true);
        options.setUserName(userName);
        options.setPassword(passWord.toCharArray());
        options.setConnectionTimeout(20);
        options.setKeepAliveInterval(20);
        client.setCallback(new PushCallback());
        MqttTopic topic = client.getTopic(TOPIC);
        client.connect(options);
        int[] Qos = {1};
        String[] topic1 = {TOPIC};
        client.subscribe(topic1,Qos);
    }

    public  static void main (String[] args) throws Exception{
        Client client = new Client();
        client.start();
    }


}
