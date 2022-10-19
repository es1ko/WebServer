import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Processor of HTTP request.
 */
public class Processor {
    private final Socket socket;
    private final HttpRequest request;



    static void checkForPrime(int inputNumber)
    {
        boolean isItPrime = true;

        if(inputNumber <= 1)
        {
            isItPrime = false;

        }
        else
        {
            for (int i = 2; i<= inputNumber/2; i++)
            {
                if ((inputNumber % i) == 0)
                {
                    isItPrime = false;

                    break;
                }
            }

        }

    }


    void count() {
        int count = 0;
        try {
            // create a new file object
            File file = new File("test.txt");


            // associated with the fil
            Scanner sc = new Scanner(file);

            // read each line and
            // count number of lines
            while (sc.hasNextLine()) {
                sc.nextLine();
                count++;
            }
            System.out.println("Total sum of Lines: " + count);

            // close scanner
            sc.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }




    public Processor(Socket socket, HttpRequest request) {
        this.socket = socket;
        this.request = request;
    }


    public void process() throws IOException {



        System.out.println("Got request:");
        System.out.println(request.toString());
        System.out.flush();

        PrintWriter output = new PrintWriter(socket.getOutputStream());

        if (request.getRequestLine().toString().equals("GET /create/item HTTP/1.1")){
            output.println("HTTP/1.1 200 OK");
            output.println("Content-Type: text/html; charset=utf-8");
            output.println();
            output.println("<html>");
            output.println("<head><title>create id</title></head>");
            output.println("<body><p>create id</p></body>");
            output.println("</html>");
            output.flush();
            socket.close();
        }
        else if (request.getRequestLine().toString().equals("GET /delete/item HTTP/1.1")){
            output.println("HTTP/1.1 200 OK");
            output.println("Content-Type: text/html; charset=utf-8");
            output.println();
            output.println("<html>");
            output.println("<head><title>delete id</title></head>");
            output.println("<body><p>delete id</p></body>");
            output.println("</html>");
            output.flush();
            socket.close();
        }
        else if (request.getRequestLine().toString().equals("GET /exec/params HTTP/1.1")){
            for(int i=0;i<1000;i++){
                checkForPrime(2);

            }
            count();
            output.println("HTTP/1.1 200 OK");
            output.println("Content-Type: text/html; charset=utf-8");
            output.println();
            output.println("<html>");
            output.println("<head><title>exec id</title></head>");
            output.println("<body><p>exec id</p></body>");
            output.println("</html>");
            output.flush();
            socket.close();
        }


        // We are returning a simple web page now.
        else if (request.getRequestLine().toString().equals("GET /index/item1 HTTP/1.1")) {
            output.println("HTTP/1.1 200 OK");
            output.println("Content-Type: text/html; charset=utf-8");
            output.println();
            output.println("<html>");
            output.println("<head><title>Hello</title></head>");
            output.println("<body><p>Hello, world!</p></body>");
            output.println("</html>");
            output.flush();
            socket.close();
        }
    }
}