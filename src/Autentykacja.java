import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class Autentykacja implements ActionListener {
    Myframe myframe;
    WatekServer watekServer;
    PrintWriter printWriter;
    public Autentykacja(Myframe myframe, PrintWriter printWriter) {
        this.printWriter = printWriter;
        this.myframe = myframe;
}

    @Override
    public void actionPerformed(ActionEvent e) {

        LoginButton loginButton = (LoginButton) e.getSource();
        if(loginButton.getText() == "Logowanie"){
            String username = new String(loginButton.username.getText());
            String password = new String(loginButton.password.getPassword());
            try {
                if (walidacja(username, password) == true) {

                    System.out.println("czat");
                    this.myframe.login = username;
                    printWriter.println("dodaj " + username);
                    myframe.lista_uzyt();
                } else {
                    myframe.logowanie();
                    System.out.println("Nieprawidłowe dane");
                }
            } catch (SQLException | IOException ex) {
                throw new RuntimeException(ex);
            }
        }else{

            try {
                Rejestracja.rejestracja(loginButton.username.getText(), new String(loginButton.password.getPassword()));
                myframe.logowanie();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        }

    }
    public boolean walidacja(String login, String haslo) throws SQLException {
        try {
            if (!SqlConn.koneksja("select count(*) from users where login =" + "'" + login + "'" + " and" + " password = " + "'" + haslo + "'", false).equals("0/")) {
                System.out.println("zalogowano pomyślnie");

                return true;
            } else {
                System.out.println("błąd");
                return false;
            }
        }catch (NullPointerException n){
            System.out.println("Nie udało się zalogować");
            return false;
        }
    }

}
