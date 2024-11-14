import java.util.ArrayList;

public class User {
    String userName;
    String contactNo;
    ArrayList<User> contacts = new ArrayList<>();
    ArrayList<User> blockedUsers = new ArrayList<>();

    public User(String userName, String contactNo) {
        this.userName = userName;
        this.contactNo = contactNo;
    }
    //addcontact
    public void addContact(String userName, String contactNo) {
        
        for (User contact : contacts) {
            if (contact.userName.equals(userName)) {
                System.out.println("Contact " + userName + " already exists.");
                return; 
            }
        }
        
        
        User newContact = new User(userName, contactNo);
        contacts.add(newContact);
        System.out.println("Contact " + userName + " added successfully.");
    }
 

    //BlockUser
    public void BlockUser(User user) {
        if (!blockedUsers.contains(user)) {
            blockedUsers.add(user);
            contacts.remove(user);
            System.out.println(this.userName + " has blocked " + user.userName);
        } else {
            System.out.println(user.userName + " is already blocked.");
        }
    }

    
    public void UnblockUser(User user) {
        if (blockedUsers.contains(user)) {
            blockedUsers.remove(user);
            contacts.add(user);
            System.out.println(this.userName + " has unblocked " + user.userName);
        } else {
            System.out.println(user.userName + " is not blocked.");
        }
    }

    //SendRequest
    public void SendRequest(User Reciever){
        if (!contacts.contains(Reciever)) {
            this.RecieveRequest(Reciever);
        }
    }

    //AcceptRequest
    public void acceptRequest(User Sender){
        if (!contacts.contains(Sender)) {
            contacts.add(Sender);
            Sender.contacts.add(this);
            System.out.println(this.userName + "'s Message Request Was Accepted By " + this.userName);
        } else {
            System.out.println("Contact Already Exists In Your Contact List");
        }
    }


    public void RecieveRequest(User Sender) {
        System.out.println(this.userName + " Sent A Message Request To " + Sender.userName);
    }
    //send message
    public void sendMessage(String receiverName, String content) {
        User receiver = null;
        for (User contact : contacts) {
            if (contact.userName.equals(receiverName)) {
                receiver = contact;
                break;
            }
        }
        if (receiver == null) {
            System.out.println("User " + receiverName + " is not in the contact list.");
            return;
        }
        if (blockedUsers.contains(receiver)) {
            System.out.println("You have blocked " + receiver.userName + ". You cannot send messages.");
            return;
        }
        Message message = new Message(content, this.userName, receiver.userName);
        MessageHistory.SaveMessage(message);
        System.out.println("Message sent to " + receiver.userName);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }
}
