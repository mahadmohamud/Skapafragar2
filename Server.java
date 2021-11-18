
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        // Creating new questions
        Question question1 = new Question("Which chilli pepper is hottest?", new String[]{"Jalapeno", "Piquant", "Habanero", "Bell"}, "Food", 3);
        Question question2 = new Question("What is the capital of France?", new String[]{"Berlin", "Barcelona", "Rome", "Paris"}, "General", 4);

        ArrayList<Question> questions = new ArrayList<>();
        questions.add(question1);
        questions.add(question2);

        // don't need to specify a hostname, it will be the current machine
        ServerSocket ss = new ServerSocket(1111);
        System.out.println("ServerSocket awaiting connections...");
        Socket socket = ss.accept(); // blocking call, this will wait until a connection is attempted on this port.
        System.out.println("Connection from " + socket + "!");

        // get the output stream from the socket.
        OutputStream outputStream = socket.getOutputStream();

        // create an object output stream from the output stream so we can send an object through it
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

        // get the input stream from the connected socket
        InputStream inputStream = socket.getInputStream();
        
        // For loop for sending the questions in arraylist 
        for (Question question : questions) {
            // Writing Question object to the client stream
            objectOutputStream.writeObject(question);
            System.out.println("Sending question to the client...");

            // Getting the user selected choice from client
            int choice = inputStream.read();
            System.out.println("Answer recieved from client. Evaluating...");

            // Checking if user choice is correct or not
            if (choice == question.getCorrectOption()) {
                System.out.println("Correct answer. Message sent to client");
                objectOutputStream.writeObject("Correct Answer!");
            } // if user choice is incorrect 
            else {
                System.out.println("Wrong answer. Message sent to client");
                objectOutputStream.writeObject("Wrong Answer!");
            }
        }

        System.out.println("Closing sockets.");
        ss.close();
        socket.close();
    }
}
