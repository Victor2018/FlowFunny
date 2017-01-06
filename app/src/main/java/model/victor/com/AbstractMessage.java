package model.victor.com;

import interfaces.victor.com.MessageInterface;

/**
 * Created by victor on 2016/3/29 0029.
 */
public abstract class AbstractMessage {
    //持有一个实现部分的对象
    private MessageInterface messageInterface;

    /**
     * 构造方法，传入实现部分的对象
     * @param messageInterface
     */
    public AbstractMessage (MessageInterface messageInterface) {
        this.messageInterface = messageInterface;
    }

    /**
     * 发送消息，委派给实现部分的方法
     * @param message
     * @param toUser
     */
    public void sendMessage (String message,String toUser) {
        this.messageInterface.sendMessage(message,toUser);

    }
}
