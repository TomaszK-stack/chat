import javax.swing.plaf.basic.BasicSplitPaneUI;
import java.io.IOException;
import java.net.Socket;

public class Klient {
    Socket socket;

    public Klient() throws IOException {
        this.socket = new Socket("localhost", 8000);
    }
    public static void main(String[] args) throws IOException{
        Klient klient = new Klient();
        Myframe myframe = new Myframe(klient);


    }

}
