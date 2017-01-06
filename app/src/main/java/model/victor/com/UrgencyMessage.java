package model.victor.com;

import interfaces.victor.com.MessageInterface;

/**
 * Created by victor on 2016/3/29 0029.
 */
public class UrgencyMessage  extends AbstractMessage{
    /**
     * 构造方法，传入实现部分的对象
     *
     * @param messageInterface
     */
    public UrgencyMessage(MessageInterface messageInterface) {
        super(messageInterface);
    }

    @Override
    public void sendMessage(String message, String toUser) {
        message = "加急：" + message;
        super.sendMessage(message, toUser);
    }

    /**
     *扩展自己的新功能，监控某消息的处理状态
     * @param messageId  被监控的消息编号
     * @return 监控到的消息的处理状态
     */
    public Object watch (String messageId) {
        //根据消息Id获取消息的状态，组织成监控的数据对象，然后返回
        return null;
    }
}
