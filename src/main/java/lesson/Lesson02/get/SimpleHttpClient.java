package lesson.Lesson02.get;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Created by dongho on 12/25/16.
 */
public class SimpleHttpClient
{
    public static void main(String[] args) throws Exception
    {
        Socket socket = new Socket("www.daum.net", 80);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
        PrintStream out = new PrintStream(socket.getOutputStream());

        out.println("GET / HTTP/1.1");

        out.println("Host: www.daum.net");
        out.println("User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_0)" +
                    " AppleWebkit/537.36 (KHTML, like Gecko)" +
                    " Chrome/30.0.1599.101 Safari/537.36");

        out.println();

        String line = null;
        while((line = in.readLine()) != null)
        {
            System.out.println(line);
        }

        in.close();
        out.close();
        socket.close();
    }
}
