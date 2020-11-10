package Model;

/**
 * @author sun
 * @Classname UserMessage
 * @TODN 消息
 * @Date 2020/9/9 15:28
 **/
public class UserMessage {
    public static final int Type_send=0;//用户发送
    public static final int Type_receive=1;

    private String Text;
    private int Type;

    public UserMessage(String text, int type) {
        Text = text;
        Type = type;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }
}
