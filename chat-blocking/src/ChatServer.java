import java.net.*;
import java.io.*;
import java.net.ServerSocket;

import javax.sound.sampled.Port;

public class ChatServer {

    private static Throwable ex;
    public static final int PORT = 8080;
    private ServerSocket serverSocket;
    private BufferedWriter out;

    public void start() throws IOException {
        serverSocket = new ServerSocket(PORT);
        System.out.println("servidor iniciado na porta " + PORT);
        clientConnectionLoop();
    }

    private void clientConnectionLoop() throws IOException {
        while (true) {
            ClientSocket clientSocket = new ClientSocket(serverSocket.accept());
            new Thread(() -> clientMenssageloop(clientSocket)).start();

        }
    }

    /**
     * @param clientSocket
     */
    public void clientMenssageloop(ClientSocket clientSocket) {
        String msg;
        try {
            while ((msg = clientSocket.getMenssage()) != null) {
                if ("sair".equalsIgnoreCase(msg)) {
                    return;
                }
                System.out.printf("mensagem recebida do cliente %s: %s\n",
                        clientSocket.getRemoteSocketAddress(), msg);

            }
        } finally {
            clientSocket.close();
        }
    }

    public static void main(String[] args) {
        try {
            ChatServer server = new ChatServer();
            server.start();
        } catch (Exception e) {
            System.out.println("erro ao iniciar o servidor... " + ex.getMessage());
        }
        System.out.println("servidor finalizado...");
    }
}
