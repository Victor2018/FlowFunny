package interfaces.victor.com;

/**
 * Created by victor on 2016/3/29 0029.
 */
public interface MessageInterface {
    /**
     *
     * @param message 消息内容
     * @param toUser 消息接受者
     */
    void sendMessage (String message,String toUser);
}
