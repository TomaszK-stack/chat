import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Listener extends Thread{
    BufferedReader in;
    Socket incoming;

    public Listener(Socket incoming) throws IOException {
        this.in = new BufferedReader(new InputStreamReader(incoming.getInputStream()));
        System.out.println("Uruchomiono listenera");
        this.start();
    }

    @Override
    public void run() {
        String message = "";
        while (true){
            try {
                message = in.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println(message + " to jest wiadomośc");


        }
    }
}
