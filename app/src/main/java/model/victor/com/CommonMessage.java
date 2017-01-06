package model.victor.com;

import interfaces.victor.com.MessageInterface;

/**
 * Created by victor on 2016/3/29 0029.
 */
public class CommonMessage extends AbstractMessage {
    /**
     * 构造方法，传入实现部分的对象
     *
     * @param messageInterface
     */
    public CommonMessage(MessageInterface messageInterface) {
        super(messageInterface);
    }

    @Override
    public void sendMessage(String message, String toUser) {
        //对象普通消息，直接调用父类方法发送消息即可
        super.sendMessage(message, toUser);
    }
}
