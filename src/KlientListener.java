import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class KlientListener extends Thread {

    Myframe myframe;
    Socket socket;
    BufferedReader in;

    public KlientListener(Myframe myframe, Socket socket) throws IOException {
        this.myframe = myframe;
        this.socket = socket;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                String message = in.readLine();
                System.out.println(message);
                if (myframe.mapa_czatow.containsKey(message.split(":")[0])) {
                    myframe.mapa_czatow.get(message.split(":")[0]).append(message + "\n");
                } else {
                    myframe.czat(message.split(":")[0]);
                    myframe.mapa_czatow.get(message.split(":")[0]).append(message + "\n");

                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}

