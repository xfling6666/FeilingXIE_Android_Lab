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
    @Query("Delete from chatMessage WHERE id = :messageId")
    void deleteMessage(int messageId);

    @Query("SELECT * FROM ChatMessage WHERE message = :message")
    ChatMessage getMessageByContent(String message);



}
