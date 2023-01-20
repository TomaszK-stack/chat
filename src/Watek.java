//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.io.PrintWriter;
//import java.net.Socket;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.HashMap;
//import java.util.Scanner;
//import java.sql.*;
//
//
//public class Watek extends Thread {
//    Socket incoming;
//    String nazwa = "unlogged";
//
//    Server server;
//
//    PrintWriter out;
//
//    Scanner in;
//    @Override
//    public String toString() {
//        return nazwa;
//    }
//
//    public Watek(Socket incoming, Server server) {
//        this.incoming = incoming;
//        this.server = server;
//        this.start();
//
//    }
//
//    @Override
//    public void run() {
//        String rezultat;
//
//
//            try {
//                Thread.sleep(3000);
//
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            System.out.println("obsługa");
//
//
//            InputStream inStream = null;
//            OutputStream outStream = null;
//
//            try {
//                inStream = incoming.getInputStream();
//                outStream = incoming.getOutputStream();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//            in = new Scanner(inStream);
//            out = new PrintWriter(outStream, true /*autoFlush */);
//            String line = "";
//            String line_2 = "";
//
//            out.println("Witamy w naszym czacie, zaloguj się albo zarejestruj. l/r");
//            line = in.nextLine();
//
//            while(!line.equals("l") && !line.equals("r")) {
//                out.println("Podaj prawidłową wartość");
//                line = in.nextLine();
//
//
//            }
//            if(line.equals("l")){
//                out.println("podaj nick");
//                line = in.nextLine();
//                try {
//                    this.nazwa = SqlConn.koneksja("select login from users where login ='"+line+"'", false);
//                } catch (SQLException e) {
//                    throw new RuntimeException(e);
//                }
//                out.println("podaj hasło");
//                line_2 = in.nextLine();
//
//
//
//
//            }else if (line.equals("r")){
//                out.println("podaj nick");
//                line = in.nextLine();
//                out.println("Stwórz swoje hasło");
//                line_2 = in.nextLine();
//                try {
//                    SqlConn.koneksja("insert into users (login, password) values (" + "'" +line.toString() + "'" + "," +"'" + line_2.toString()+ "'" + ")", true);
//                    System.out.println("dodano pomyślnie ");
//                } catch (SQLException e) {
//                    throw new RuntimeException();
//
//                }
//
//
//            }
//
//
//            this.czat(in, out);
//
//
//    }
//    public boolean walidacja(String login, String haslo) throws SQLException {
//        if(SqlConn.koneksja("select count(*) from users where login =" + "'" + login + "'" + " paswword = "+ "'" + haslo + "'", false).equals("1")){
//            System.out.println("zalogowano pomyślnie");
//            return true;
//        }else{
//            System.out.println("błąd");
//            return false;
//        }
//
//    }
//
//    private void czat( Scanner sc, PrintWriter out){
//        out.println("lista zalogowanych osób: " + server.mapa_osob.toString());
//        int liczba = in.nextInt();
//        while (true){
//            String output = in.nextLine();
//            wyslij(liczba, output);
//
//
//        }
//
//    }
//    private void wyslij(int liczba, String message){
//        WatekServer watek = server.mapa_osob.get(liczba);
////        watek.out.println(message);
//
//    }
//
//}
