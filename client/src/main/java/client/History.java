package client;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class History {
    private static PrintWriter out;



    public static void recordStart (String login) {
        try {
            out = new PrintWriter(new FileOutputStream("chatarchive/history_"+login+".txt",true),true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void recordStop () {
        if (out !=null){
            out.close();
        }
    }
    public static void writeMSG (String msg) {
        out.println(msg);
    }
}
