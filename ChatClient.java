import java.io.*;
import java.net.*;

public class ChatClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 8080)) {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

           
            System.out.println(in.readLine()); 
            System.out.println(in.readLine()); 
            String userType = stdIn.readLine();
            out.println(userType);

            System.out.println(in.readLine()); 
            String name = stdIn.readLine();
            out.println(name);

            System.out.println(in.readLine()); 

           
            String serverResponse;
            while (true) {
               
             for(int i=0 ; i<=6; i++){
                serverResponse=in.readLine();
                if(serverResponse!=null){
                    System.out.println(serverResponse);
                }

             }

               System.out.print("Your choice: ");
                String clientChoice = stdIn.readLine();
                out.println(clientChoice);

               
                String followUpResponse;
                while ((followUpResponse = in.readLine()) != null && !followUpResponse.equals("END")) {
                    System.out.println(followUpResponse);

                    if (followUpResponse.contains("Enter")) {
                        String followUpInput = stdIn.readLine();
                        out.println(followUpInput);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}



