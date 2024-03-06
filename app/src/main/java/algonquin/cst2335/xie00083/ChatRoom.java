package algonquin.cst2335.xie00083;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

import algonquin.cst2335.xie00083.databinding.ActivityChatRoomBinding;
import algonquin.cst2335.xie00083.databinding.SentMessageBinding;

public class ChatRoom extends AppCompatActivity
{

    ActivityChatRoomBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        ArrayList<String> messages = new ArrayList<>();

        binding = ActivityChatRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.recyclerView.setAdapter(new RecyclerView.Adapter<MyRowHolder>()
        {
            @NonNull
            @Override
            public MyRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
            {
                SentMessageBinding binding = SentMessageBinding.inflate(getLayoutInflater());
                return new MyRowHolder( binding.getRoot());
            }

            @Override
            public void onBindViewHolder(@NonNull MyRowHolder holder, int position)
            {
                String obj = messages.get(position);
                holder.messageText.setText(obj);
                holder.messageText.setText("");
                holder.timeText.setText("");
            }

            @Override
            public int getItemCount()
            {
              return messages.size();
            }
            public int intgetItemViewType(int position)
            {
                return 0;
            }
        });

    }

    class MyRowHolder extends RecyclerView.ViewHolder {

        TextView messageText;
        TextView timeText;
        public MyRowHolder(@NonNull View itemView) {
            super(itemView);
            messageText =itemView.findViewById(R.id.message);
            timeText = itemView.findViewById(R.id.time);
        }
    }
}