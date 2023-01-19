import javax.swing.*;
import java.io.IOException;

class NetworkThread extends Thread {
    private JTextArea messageArea;
    public WatekServer watekServer;

    public NetworkThread(JTextArea messageArea, WatekServer watekServer) {
        this.messageArea = messageArea;
        this.watekServer = watekServer;
    }

    @Override
    public void run() {
            // nawiązanie połączenia z serwerem i odbieranie wiadomości
            while (true) {
                String message = null;
                System.out.println("a");
                try {
                    message = receiveMessageFromServer();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("b");
                String finalMessage = message;
                SwingUtilities.invokeLater(() -> messageArea.append(finalMessage + "\n"));
            }
    }

    public String receiveMessageFromServer() throws IOException {
        String message = String.valueOf(watekServer.in.read());
        System.out.println("message receive");
        return message;
    }
}

