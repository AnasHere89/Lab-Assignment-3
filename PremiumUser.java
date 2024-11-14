import java.util.ArrayList;

public class PremiumUser extends User{

    public PremiumUser(String userName, String contactNo) {
        super(userName, contactNo);
        
    }
    //edit
    public void EditMessage(String oldContent, String newContent){
    ArrayList<String> message=MessageHistory.loadMessage();;
    boolean found = false;   
    for (int i = 0; i < message.size(); i++) {
        if(message.get(i).contains(oldContent)&&message.get(i).contains(this.userName)){
        message.set(i, message.get(i).replace(oldContent, newContent));
    found=true;
        break;
}
    }
if(found){
    MessageHistory.SaveAll(message);
    System.out.println("Message Has Been Edited");

}else{
    System.out.println("Message Not Found");
}
    }


    //Delete Message
    public void DeleteMessage(String Content){
        ArrayList<String>message=MessageHistory.loadMessage();
        boolean found=message.removeIf(msg->msg.contains(Content)&&msg.contains(this.userName));
        if(found){
            MessageHistory.SaveAll(message);
            System.out.println("Message has Been Deleted");

        }else{
            System.out.println("Message Not Found");
        }
    }


}