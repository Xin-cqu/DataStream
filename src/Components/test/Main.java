package Components.test;

import Components.Client.SourcePortI;
import Components.Client.SourcePortI4Block;
import Components.Server.SinkPortI;
import Components.Server.SinkPortI4Block;
import Components.Streams.GenericError;

import java.io.File;
import java.util.Scanner;

/**
 * Created by xinwen on 27/08/2014.
 */
public class Main {
    public static void main(String [] args) throws GenericError, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        println("input s for server and c for client");
        String i1 = scanner.nextLine();
        if(i1.equals("s")){
            println("input path to save the file");
            String path = scanner.nextLine();
            //String path="/Users/xinwen/Downloads/temp";
            SinkPortI sinkPortI = new SinkPortI(path);
            //SinkPortI4Block sinkPortI = new SinkPortI4Block(path);


        }else {
            println("input path of file");
            String path = scanner.nextLine();
           // String path = "/Users/xinwen/Downloads/node-v0.10.29.pkg";
            File file = new File(path);
            if(!file.exists()) throw new IllegalArgumentException("file not exists");
            println("input server ip");
            //String ip = "127.0.0.1";
            String ip = scanner.nextLine();
//            SourcePortI sourcePortI = new SourcePortI(file,ip);
//            sourcePortI.start();
//            long start=System.currentTimeMillis();
            SourcePortI sourcePortI = new SourcePortI(file,ip);
            sourcePortI.start();
//            SourcePortI4Block SourcePortI4Block = new SourcePortI4Block(file,ip);
//            long end=System.currentTimeMillis();
//            System.out.println(end-start);
        }
    }
    private static void println(String s){
        System.out.println(s);
    }
}
