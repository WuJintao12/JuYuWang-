

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class co1{
    static  String massage;
    public static void main(String[] args) throws Exception{
        InetAddress inet = InetAddress.getByName("127.0.0.1");
        int port=1234;
        Scanner jie=new Scanner(System.in);

        Socket s=new Socket(inet,port);
        BufferedReader buff=new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintStream print=new PrintStream(s.getOutputStream());

       
//        while(true){
        while((massage=buff.readLine())!=null){

            System.out.println(massage);
            System.out.println("******");
            System.out.println("自己的话：");
            sss jjj=new sss(jie,print);
            jjj.start();
        }
        System.out.println("kkkkjjj");

//        }


    }
    static class sss extends Thread{
        Scanner jie;
        PrintStream print;
        public sss(Scanner jie,PrintStream print){
            this.jie=jie;
            this.print=print;
        }
        @Override
        public void run() {
            Scanner r=new Scanner(System.in);
            String str=new String(r.next());
            print.println(str);
        }
    }
//    static class shu extends Thread {
//        BufferedReader b;
//        String s;
//        public shu(BufferedReader b){
//            this.b=b;
//        }
//
//        @Override
//        public void run() throws Exception{
//
//
//            while((s=b.readLine())!=null){
//                System.out.println(s);
//
//            }
//
//        }
//
//    }
}
