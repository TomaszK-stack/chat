import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class KlientListener extends Thread{
    String uzytkownik;
    String rozmowca;
    JTextArea chatHistory;

    Socket socket;
    BufferedReader in;

    public KlientListener(String rozmowca, JTextArea chatHistory, Socket socket) throws IOException {
        this.rozmowca = rozmowca;
        this.chatHistory = chatHistory;
        this.socket = socket;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        this.start();

    }

    @Override
    public void run() {
        String message = "";
        System.out.println("uruchomiono listenra klient");
        while (true){
            try {
                message = in.readLine();
                if(message.split(":")[0].equals(rozmowca)){
                    chatHistory.append("\n" + rozmowca + ":" +  message.split(":")[1]);

                }

                System.out.println("odczytano");

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            System.out.println(message);
        }


    }
}
