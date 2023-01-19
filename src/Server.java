import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ClientInfoStatus;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;


public class Server {
    ArrayList<WatekServer> lista_osob = new ArrayList<>();
    HashMap<Integer, WatekServer> mapa_osob = new HashMap<>();
    public static void main(String[] args) throws IOException,SQLException {
        Server server = new Server();
        server.utworz_server();

    }
    public  void utworz_server() throws IOException, SQLException {

        ServerSocket s = new ServerSocket(8000);
        int i =0;


        while (true){
            Socket incoming = s.accept();
            WatekServer watek = new WatekServer(incoming, this );
            lista_osob.add(watek);
            mapa_osob.put(i, watek);
            i++;


            System.out.println("jesteśmy tutaj");



        }


    }


}