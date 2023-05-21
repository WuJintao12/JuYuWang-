

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class sever {
    static Set<Socket> on = new HashSet<Socket>();

    public static void main(String[] args) throws Exception {

        int port = 1234;
        ServerSocket s = new ServerSocket(port);
        send sen = new send();

        sen.start();
        while (true) {
            Socket s2 = s.accept();
            System.out.println(s2.getInetAddress());
            on.add(s2);
            System.out.println(on);
            sen.run();
            jieshou j=new jieshou(s2);
            j.start();
        }
    }
    public static class jieshou extends Thread{
        private Socket s;
       public jieshou(Socket s){
           this.s=s;
       }

        @Override
        public void run() {
            try {
                BufferedReader b=new BufferedReader(new InputStreamReader(s.getInputStream()));
                String str;
                while ((str=b.readLine())!=null){
                    jieshousend(str);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public void jieshousend(String st){
            for (Socket s : on) {
                String str = s.getInetAddress()+"连接成功:"+st;
                OutputStream out = null;

                try {
                    out = s.getOutputStream();

                    PrintStream ps = new PrintStream(out);
//                        out.write(str.getBytes());
//                        out.flush();
//                        out.close();
//                        s.close();
//                        out.flush();
                    ps.println(str);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("iiiiii");
            }

        }
    }

    public static class send extends Thread {
        @Override
        public void run() {

            for (Socket s : on) {
                String str = s.getInetAddress()+"连接成功";
                OutputStream out = null;

                try {
                    out = s.getOutputStream();

                    PrintStream ps = new PrintStream(out);
//                        out.write(str.getBytes());
//                        out.flush();
//                        out.close();
//                        s.close();
//                        out.flush();
                    ps.println(str);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("iiiiii");
            }

            /* 遍历集合发送链接成功 */

        }
    }
}
