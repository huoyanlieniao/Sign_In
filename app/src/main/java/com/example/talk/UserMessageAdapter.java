package com.example.talk;

import Model.UserMessage;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;


/**
 * @author sun
 * @Classname UserMessageAdapter
 * @TODN 要办的事
 * @Date 2020/9/9 16:58
 **/

public class UserMessageAdapter extends RecyclerView.Adapter<UserMessageAdapter.UserMessageView> {

    //对话列表
    private final List<UserMessage> UserMessages;

    public UserMessageAdapter(List<UserMessage> userMessages) {
        this.UserMessages = userMessages;
    }

    //信息状态
    @Override
    public int getItemViewType(int position) {
        UserMessage msg = UserMessages.get(position);
        return msg.getType();
    }

    @NonNull
    @Override
    //根据类型创建新的显示，创建的是视图
    public UserMessageAdapter.UserMessageView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == UserMessage.Type_receive) {
            //用户接收的消息，显示在左侧
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.msg_left_layout, parent, false);
            //    Log.v(GameSystem.LOG_TAG, "left view.toString():" + view.toString());
            return new UserMessageView(view);
        } else {
            //用户发送的消息，显示在右侧
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.msg_right_layout, parent, false);
            return new UserMessageView(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull UserMessageAdapter.UserMessageView holder, int position) {
        UserMessage msg = UserMessages.get(position);
        // 根据消息的类型来选择不同的布局
        if (msg.getType() == UserMessage.Type_receive) {
            holder.tvLeft.setText(msg.getText());
        } else {
            holder.tvRight.setText(msg.getText());
        }
    }


    @Override
    public int getItemCount() {
        return UserMessages.size();
    }

    //对话页面
    public class UserMessageView extends RecyclerView.ViewHolder{
        TextView tvLeft, tvRight;

       //操作的是视图里的信息
        public UserMessageView(@NonNull View itemView) {
            super(itemView);
            tvLeft = itemView.findViewById(R.id.User_Msg_Left);
            tvRight = itemView.findViewById(R.id.User_Msg_Right);

        }
    }
}
