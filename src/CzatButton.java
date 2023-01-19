import javax.swing.*;
import java.awt.*;

public class CzatButton extends JButton {
    String login;
    WatekServer watekServer;
    public CzatButton(String text, WatekServer watekServer) {

        super(text);
        this.watekServer = watekServer;
        this.login = text;

    }
}
