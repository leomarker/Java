import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(2020);


            Socket socket = ss.accept();

            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            DataInputStream dis = new DataInputStream(is);
            DataOutputStream dos = new DataOutputStream(os);
   while (true){
       System.out.println("client: "+dis.readUTF());
       System.out.print("server:");
       String st = new Scanner(System.in).nextLine();
       dos.writeUTF(st);
   }




    }
}