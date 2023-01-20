import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Listener extends Thread{
    BufferedReader in;
    Socket incoming;
    Server server;
    ObjectOutputStream out;
    PrintWriter printWriter;
    BufferedWriter bout;
    String login;
    public Listener(Socket incoming, Server server) throws IOException {
        this.server = server;
        this.incoming = incoming;
        this.in = new BufferedReader(new InputStreamReader(incoming.getInputStream()));
        System.out.println("Uruchomiono listenera");
        out = new ObjectOutputStream(incoming.getOutputStream());
        this.printWriter = new PrintWriter(incoming.getOutputStream(), true);
        this.bout = new BufferedWriter(new OutputStreamWriter(incoming.getOutputStream()));
        this.start();

    }

    @Override
    public void run() {
        String message = "";
        while (true){
            try {
                message = in.readLine();
                if(message.split(" ")[0].equals("dodaj")){

                    System.out.println(message.split(" ")[1]);
                    server.lista_osob.add(message.split(" ")[1]);
                    this.login = message.split(" ")[1];
                    server.mapa_osob.put(message.split(" ")[1], printWriter);
                    System.out.println("dodaje");

                }else if(message.equals("lista")){
                    out.writeObject(server.lista_osob);
                    System.out.println("odsułam listę " + server.lista_osob.toString());

                }else{
                    PrintWriter temp_writer = server.mapa_osob.get(message.split(":")[0]);
                    temp_writer.println(this.login + ":" +  message.split(":")[1]);



                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println(message + " to jest wiadomośc");


        }
    }
}
