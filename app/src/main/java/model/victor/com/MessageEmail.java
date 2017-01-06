package model.victor.com;

import android.widget.Toast;

import interfaces.victor.com.MessageInterface;

/**
 * Created by victor on 2016/3/29 0029.
 */
public class MessageEmail implements MessageInterface {
    @Override
    public void sendMessage(String message, String toUser) {
        System.out.println("使用邮件短消息的方法，发送消息：" + message + ",给" + toUser);
    }
}
