package mode.victor.com;

import interfaces.victor.com.MessageInterface;
import model.victor.com.AbstractMessage;
import model.victor.com.CommonMessage;
import model.victor.com.MessageEmail;
import model.victor.com.MessageSMS;
import model.victor.com.UrgencyMessage;

/**
 * 桥梁模式
 * Created by victor on 2016/3/29 0029.
 * 将抽象和实现解耦，使得两者可以独立地变化。
 * 桥梁模式的用意是“将抽象化(Abstraction)与实现化(Implementation)脱耦，使得二者可以独立地变化”。
 */
public class BridgeMode {
    private static MessageInterface messageInterface;
    private static AbstractMessage abstractMessage;

    public static void main(String[] args) {
        test ();
    }

    public static void test (){
        //创建具体的实现对象
        messageInterface = new MessageSMS();
        //创建普通消息对象
        abstractMessage = new CommonMessage(messageInterface);
        abstractMessage.sendMessage("加班申请速批","Victor");

        //实现方式切换成邮件，再次发生消息
        messageInterface = new MessageEmail();
        //创建加急消息对象
        abstractMessage = new UrgencyMessage(messageInterface);
        abstractMessage.sendMessage("加班申请速批","Victor");
    }
}
