package algonquin.cst2335.chatroom;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import algonquin.cst2335.chatroom.databinding.ActivityChatRoomBinding;
import algonquin.cst2335.chatroom.databinding.ReceiveMessageBinding;
import algonquin.cst2335.chatroom.databinding.SentMessageBinding;

public class ChatRoom extends AppCompatActivity
{
    ActivityChatRoomBinding binding;
    ChatRoomViewModel chatModel ;

    private RecyclerView.Adapter myAdapter;
    ArrayList<ChatMessage> messages= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        chatModel = new ViewModelProvider(this).get(ChatRoomViewModel.class);
      //  messages = chatModel.messages.getValue();

        if (messages == null) {
            chatModel.messages.postValue(chatModel.messages.getValue());
        }

        binding = ActivityChatRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        binding.sendButton.setOnClickListener(click -> {
            @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("EE, dd-MMM HH-mm-ss");
            String currentTime = sdf.format(new Date());
            String typedMessage = binding.textInput.getText().toString();
            ChatMessage newMessage = new ChatMessage(typedMessage, currentTime, true);
            messages.add(newMessage);
            myAdapter.notifyItemInserted(messages.size()-1);
            //clear the previous text:
            binding.textInput.setText("");
        });

        binding.receiveButton.setOnClickListener(click -> {
            @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("EE, dd-MMM HH-mm-ss");
            String currentDateandTime = sdf.format(new Date());
            String typedMessage = binding.textInput.getText().toString();
            ChatMessage newMessage = new ChatMessage(typedMessage, currentDateandTime, false);
            messages.add(newMessage);
            myAdapter.notifyItemInserted(messages.size()-1);
            //clear the previous text:
            binding.textInput.setText("");
        });

        binding.recyclerView.setAdapter(myAdapter=new RecyclerView.Adapter<MyRowHolder>()
        {
            @NonNull
            @Override
            public MyRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
            {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());

                if (viewType == 0) {
                    SentMessageBinding binding = SentMessageBinding.inflate(inflater);
                    return new MyRowHolder(binding.getRoot());
                } else {
                    // viewType is 1, indicating it's a receive_message layout
                    // Replace ReceiveMessageBinding with the actual binding class for receive_message layout
                    ReceiveMessageBinding binding = ReceiveMessageBinding.inflate(inflater);
                    return new MyRowHolder(binding.getRoot());
                }
            }

            @Override
            public void onBindViewHolder(@NonNull MyRowHolder holder, int position)
            {
                ChatMessage chatMessage = messages.get(position);
                holder.messageText.setText(chatMessage.getMessage());
                holder.timeText.setText(chatMessage.getTimeSent());
            }

            @Override
            public int getItemCount()
            {
                return messages.size();
            }
            public int getItemViewType(int position)
            {
                ChatMessage chatMessage = messages.get(position);
                return chatMessage.isSentButton() ? 0 : 1;
            }
        });

    }

    static class MyRowHolder extends RecyclerView.ViewHolder {

        TextView messageText;
        TextView timeText;
        public MyRowHolder(@NonNull View itemView) {
            super(itemView);
            messageText =itemView.findViewById(R.id.message);
            timeText = itemView.findViewById(R.id.time);
        }
    }
}