package algonquin.cst2335.chatroom;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class ChatRoomViewModel extends ViewModel
{
    public MutableLiveData<ArrayList<String>> messages = new MutableLiveData< >();
}