import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
//import java.net.UnknownHostException;
import java.util.Scanner;

public class ChatClient {
    private static final String SERVER_ADDRESS = "127.0.0.1";
    private Socket clientSocket;
    private Scanner scanner;
    private PrintWriter out;

    public ChatClient() {
        scanner = new Scanner(System.in);
    }

    public void start() throws IOException {
        clientSocket = new Socket(SERVER_ADDRESS, ChatServer.PORT);

        System.out.println("cliente conectado ao servidor em "
                + SERVER_ADDRESS + ":" + ChatServer.PORT);
        messageloop();
    }

    private void messageloop() throws IOException {
        String msg;
        do {
            System.out.println("digite uma mensagem, ou sair para finalizar :");
            msg = scanner.nextLine();
            out.println(msg);
            // out.flush();
        } while (!msg.equalsIgnoreCase("sair"));
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            ChatClient client = new ChatClient();
            client.start();
        } catch (IOException e) {
            System.out.println("erro ao iniciar clienta ");
        }
        System.out.println("cliente finalizado!");
    }
}