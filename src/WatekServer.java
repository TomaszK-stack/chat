import java.io.*;
import java.net.Socket;

public class WatekServer extends Thread{
    Socket incoming;
    Server server;
    String login;
    BufferedWriter out;
    BufferedReader in;
    PrintWriter printWriter;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public WatekServer(Socket incoming, Server server) throws IOException {
        this.printWriter = new PrintWriter(incoming.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(incoming.getInputStream()));
        this.out = new BufferedWriter(new OutputStreamWriter(incoming.getOutputStream()));
        this.incoming = incoming;
        this.server = server;
        this.start();
    }


    @Override
    public void run() {
        try {
            Listener listener = new Listener(incoming);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
