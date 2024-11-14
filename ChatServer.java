

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// ChatServer.java
public class ChatServer {
    private static List<ClientHandler> clients = new ArrayList<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("Server started on port 8080");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                clients.add(clientHandler);
                clientHandler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Nested class for handling each client connection
    static class ClientHandler extends Thread {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;
        private User user;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                out = new PrintWriter(socket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                // Welcome message and user registration
                out.println("Welcome to the chatting application!");
                out.println("Please choose your user type: 1. Base User  2. Premium User");
                // Register user based on client input
                String userType = in.readLine();
                out.println("Enter The Username That you want to use :");
                String USERNAME=in.readLine();
                out.flush();
                if (userType.equals("1")) {
                    user = new BaseUser(USERNAME, "0000");
                    out.println("Registered as Base User. Get our special functions free for the first month with a premium upgrade!");
                } else if (userType.equals("2")) {
                    user = new PremiumUser(USERNAME, "0001");
                    out.println("Registered as Premium User.");
                } else {
                    out.println("Invalid choice, defaulting to Base User.");
                    user = new BaseUser(USERNAME, "0000");
                }

                // Display menu for available actions
                boolean running = true;
                while (running) {
                    out.println("\nSelect an action:");
                    out.println("1. Add Contact");
                    out.println("2. Send Message");
                    out.println("3. Access Message History");
                    out.println("4. Premium Options (Edit/Delete Message)");
                    out.println("5. Exit");
                
                    String choice = in.readLine();
                    System.out.println("Client choice: " + choice);
                
                    switch (choice) {
                        case "1": 
                        out.println("Enter the username to add to contacts:");
                        String contactName = in.readLine().trim();
                        user.addContact(contactName, "0000");
                         out.println(contactName + " added to contacts.");
                        break;

                       case "2": 
                       out.println("Enter the username to send a message to:");
                       String receiverName = in.readLine().trim();
                       out.println("Enter your message:");
                        String messageContent = in.readLine().trim();
                     user.sendMessage(receiverName, messageContent);
                     out.println("Message sent to " + receiverName);
                      break;

                        case "3": 
                            out.println("Message history:");
                            for (String msg : MessageHistory.loadMessage()) {
                                out.println(msg);
                            }
                            out.println("END");
                            break;
                        case "4": 
                            if (user instanceof PremiumUser) {
                                out.println("Premium options: 1. Edit Message  2. Delete Message");
                                String premiumChoice = in.readLine().trim();
                                if (premiumChoice.equals("1")) {
                                    out.println("Enter the old message content:");
                                    String oldMessage = in.readLine().trim();
                                    out.println("Enter the new message content:");
                                    String newMessage = in.readLine().trim();
                                    ((PremiumUser) user).EditMessage(oldMessage, newMessage);
                                    out.println("Message edited.");
                                } else if (premiumChoice.equals("2")) {
                                    out.println("Enter the message content to delete:");
                                    String deleteMessage = in.readLine().trim();
                                    ((PremiumUser) user).DeleteMessage(deleteMessage);
                                    out.println("Message deleted.");
                                } else {
                                    out.println("Invalid choice.");
                                }
                            } else {
                                out.println("Upgrade to Premium to access these features.");
                            }
                            break;
                        case "5": 
                            running = false;
                            out.println("Exiting...");
                            break;
                        default:
                            out.println("Invalid choice.");
                    }
                    out.println("END");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

      
    }
}
