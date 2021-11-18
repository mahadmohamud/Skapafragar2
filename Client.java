
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // need host and port, we want to connect to the ServerSocket at port 1111
        Socket socket = new Socket("localhost", 1111);
        System.out.println("Connected!");

        // get the input stream from the connected socket
        InputStream inputStream = socket.getInputStream();
        // create a DataInputStream so we can read data from it.
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

        // get the output stream from the socket.
        OutputStream outputStream = socket.getOutputStream();

        // For loop for downloading 2 questions from the server
        for (int i = 0; i < 2; i++) {
            // Downloading question from server
            Question question = (Question) objectInputStream.readObject();
            System.out.println("Question received from the server!");
            System.out.println();

            // Printing question on console
            System.out.println(question.toString());

            // Getting user option
            System.out.print("Please enter your choice: ");
            Scanner input = new Scanner(System.in);
            int choice = input.nextInt();
            // Sending user selected option to Server
            outputStream.write(choice);
            System.out.println("Sending answer to server...");

            // Getting message from server 
            String message = (String) objectInputStream.readObject();
            // Printing the message received on the console
            System.out.println(message);
            System.out.println();

        }
        System.out.println("Thank you for playing the quiz game!");

        System.out.println("Closing socket and terminating program.");
        socket.close();
    }
}
