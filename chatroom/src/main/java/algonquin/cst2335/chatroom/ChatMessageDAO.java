package algonquin.cst2335.chatroom;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface ChatMessageDAO
{
    @Insert
     long insertMessage(ChatMessage m);
    @Query("Select * from ChatMessage")
     List<ChatMessage> getAllMessages();
    @Delete
    void deleteMessage(ChatMessage m);

    @Query("SELECT * FROM ChatMessage WHERE id = :id")
     ChatMessage getMessageById(int id);


}
