import javax.swing.*;
import java.awt.*;

public class CzatButton extends JButton {
    String login;
    public CzatButton(String text) {

        super(text);

        this.login = text;

    }

    public String getLogin() {
        return login;
    }
}
