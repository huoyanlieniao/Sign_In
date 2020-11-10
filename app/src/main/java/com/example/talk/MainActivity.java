package com.example.talk;

import Model.UserMessage;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG="MyActivity";
    private RecyclerView mRecyclerView;
    private UserMessageAdapter mUserWordMessageAdapter;
    private EditText mEditTextUserWordMessage;
    private List<UserMessage> mUserWordMessageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditTextUserWordMessage = findViewById(R.id.Edit_message);
        mUserWordMessageList = new ArrayList<>();

        mRecyclerView = findViewById(R.id.Recyclerview);
        mUserWordMessageAdapter = new UserMessageAdapter(mUserWordMessageList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mUserWordMessageAdapter);
        Log.v(TAG,"初始化");



    }

    /**
     * 用户按下消息发送按钮时发送消息
     * @param view
     */
    public void onButtonSendMessageClick(View view) {
        Log.v(TAG,"Enter");
        String content = mEditTextUserWordMessage.getText().toString();
        //删除空格
        content = content.trim();
        if (content.length() == 0) {
            Toast.makeText(this, "文本内容不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        addSendMessage(content);
        Log.v(TAG,"放入");
        /**
         * 这里模拟接收对方发送的消息，在实际程序中，应该定时从网络获得相关数据
         */

        addReceiveMessage("我接收到"+content);
    }

    /**
     * 增加接收消息
     * @param content
     */
    private void addReceiveMessage(String content) {
        UserMessage userWordMessage = new UserMessage(content, UserMessage.Type_receive);
        mUserWordMessageList.add(userWordMessage);

        // 表示在消息的末尾插入内容
        mUserWordMessageAdapter.notifyItemInserted(mUserWordMessageList.size() - 1);

        // 让 RecyclerView 自动滚动到最底部
        mRecyclerView.scrollToPosition(mUserWordMessageList.size() - 1);


        mEditTextUserWordMessage.setText("");
    }

    /**
     * 增加发送消息
     * @param content
     */
    private void addSendMessage(String content) {
        Log.v(TAG,"创建视图");
        UserMessage userWordMessage = new UserMessage(content, UserMessage.Type_send);
        mUserWordMessageList.add(userWordMessage);

        // 表示在消息的末尾插入内容
        mUserWordMessageAdapter.notifyItemInserted(mUserWordMessageList.size() - 1);

        // 让 RecyclerView 自动滚动到最底部
        mRecyclerView.scrollToPosition(mUserWordMessageList.size() - 1);


        mEditTextUserWordMessage.setText("");
        Log.v(TAG,"视图创建完成");
    }


   
}
