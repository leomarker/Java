import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        while (true) {
        Socket ss = new Socket("localhost",2020);


     InputStream is = ss.getInputStream();
     OutputStream os = ss.getOutputStream();

     DataInputStream dis = new DataInputStream(is);
     DataOutputStream dos = new DataOutputStream(os);

     System.out.println("client");

//        String st = new Scanner(System.in).nextLine();
     String st = "hello form the other side";
     System.out.println("server");
     dos.writeUTF(st);
     System.out.println("server: " + dis.readUTF());
            String mes = new Scanner(System.in).nextLine();
            System.out.println("client");
            dos.writeUTF(mes);
            System.out.println("server: "+dis.readUTF() );
 }
    }
}