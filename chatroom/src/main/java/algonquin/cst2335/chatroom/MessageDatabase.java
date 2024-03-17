package algonquin.cst2335.chatroom;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {ChatMessage.class}, version=1)
public abstract class MessageDatabase extends RoomDatabase
{
    public abstract ChatMessageDAO cmDAO();

}
