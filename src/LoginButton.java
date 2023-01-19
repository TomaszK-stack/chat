import javax.swing.*;

public class LoginButton extends JButton {

    JTextField username;
    JPasswordField password;



    public LoginButton(JTextField username, JPasswordField password) {
        this.username = username;
        this.password = password;
        setText("Login");
    }



}
