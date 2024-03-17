package algonquin.cst2335.chatroom;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ChatMessage
{
    @ColumnInfo(name="message")
    String message;
    @ColumnInfo(name="timeSent")
    String timeSent;
    @ColumnInfo(name="isSent")
    boolean isSentButton;
    @PrimaryKey(autoGenerate=true)
    @ColumnInfo(name="id")
    public int id;
    public ChatMessage() {
    }

    public ChatMessage(String m, String t, boolean sent)
    {
        message = m;
        timeSent = t;
        isSentButton = sent;
    }

    public String getMessage() {
        return message;
    }

    public String getTimeSent() {
        return timeSent;
    }

    public boolean isSentButton() {
        return isSentButton;
    }

}
