import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message {
     String content;
     String timeStamp;
     String sender;
     String reciever;
     String language;

    public Message(String content, String sender, String reciever) {
        this.content = content;
        this.timeStamp = getCurrentTime();
        this.sender = sender;
        this.reciever = reciever;
        this.language = detectLanguage(content);
    }

    private String getCurrentTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }

    private String detectLanguage(String content) {
        String[] urduKeywords = {"tu", "kya", "ap", "mein", "theik", "yaar","kese","ha","bole","bolo"};

        for (String keyword : urduKeywords) {
            if (content.toLowerCase().contains(keyword)) {
                return "Urdu";
            }
        }
        return "English"; 
    }

    @Override
    public String toString(){
        return "\nSent By: " + sender + "\nSent To: " + reciever + "\nMessage Content: " + content + "\nSent At: " + timeStamp + "\nLanguage: " + language;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReciever() {
        return reciever;
    }

    public void setReciever(String reciever) {
        this.reciever = reciever;
    }
}
