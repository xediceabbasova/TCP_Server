package tcpserver;

import com.mycompany.file.FileUtility;
import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import lombok.SneakyThrows;

public class TCPServer {

    @SneakyThrows
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("which port to open?");
        int port = scanner.nextInt();
        ServerSocket serverSocket = new ServerSocket(port);

        while (true) {
            Socket connection = serverSocket.accept();
            DataInputStream dataStream = new DataInputStream(connection.getInputStream());
            byte[] arr = readMessage(dataStream);
            FileUtility.writeBytes("C:\\Users\\Admin\\OneDrive\\Masaüstü\\pictures.jpg", arr);
            System.out.println("✅sent successfully");
        }

    }

    @SneakyThrows
    public static byte[] readMessage(DataInputStream din) {
        int msgLen = din.readInt();
        byte[] msg = new byte[msgLen];
        din.readFully(msg);
        return msg;
    }

}
