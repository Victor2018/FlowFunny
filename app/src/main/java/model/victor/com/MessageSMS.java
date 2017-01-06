package model.victor.com;

import interfaces.victor.com.MessageInterface;

/**
 * Created by victor on 2016/3/29 0029.
 */
public class MessageSMS implements MessageInterface {
    @Override
    public void sendMessage(String message, String toUser) {
        System.out.println("使用系统短信的方法，发送消息：" + message + ",给" + toUser);
    }
}
