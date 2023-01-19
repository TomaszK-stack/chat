import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Autentykacja implements ActionListener {
    Myframe myframe;
    WatekServer watekServer;
    public Autentykacja(Myframe myframe,  WatekServer watekServer) {
        this.myframe = myframe;
        this.watekServer = watekServer;
}

    @Override
    public void actionPerformed(ActionEvent e) {
        LoginButton loginButton = (LoginButton) e.getSource();
        String username = new String(loginButton.username.getText());
        String password = new String(loginButton.password.getPassword());
        try {
            if(walidacja(username, password)){
                myframe.lista_uzyt();
                System.out.println("czat");
                this.myframe.login = username;
            }else {
                myframe.logowanie();
                System.out.println("Nieprawidłowe dane");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }
    public boolean walidacja(String login, String haslo) throws SQLException {
        try {
            if (!SqlConn.koneksja("select count(*) from users where login =" + "'" + login + "'" + " and" + " password = " + "'" + haslo + "'", false).equals("0/")) {
                System.out.println("zalogowano pomyślnie");
                watekServer.setLogin(login);
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
