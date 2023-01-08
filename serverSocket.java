import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(2020);
        while (true){

            Socket socket = ss.accept();

            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            DataInputStream dis = new DataInputStream(is);
            DataOutputStream dos = new DataOutputStream(os);

            System.out.println("client: "+dis.readUTF());

            String st = new Scanner(System.in).nextLine();
            System.out.println("server");
            dos.writeUTF(st);
            System.out.println("server: "+dis.readUTF() );
        }


    }
}