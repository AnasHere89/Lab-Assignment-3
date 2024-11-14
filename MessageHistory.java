import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MessageHistory {
    public static final String HISTORY = "message_history.txt";
    public static void SaveMessage(Message message){
        try(FileWriter fr = new FileWriter(HISTORY, true)) {
            fr.write(message.toString() + "\n");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<String> loadMessage(){
        ArrayList<String> messages = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(HISTORY))) {
            String line;
            while((line = reader.readLine()) != null){
                messages.add(line);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return messages;
    }

    public static void DisplayHistory(){
        ArrayList<String> messages = loadMessage();
        for(String m : messages){
            System.out.println(m);
        }
    }

    public static void SaveAll(ArrayList<String> messages){
        try(FileWriter fr = new FileWriter(HISTORY, false)) {
            for(String m : messages){
                fr.write(m + "\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
