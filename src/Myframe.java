import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

public class Myframe extends JFrame implements ActionListener {
    JPanel log_re_panel;
    JButton zaloguj_sie;
    JButton zarejestruj;

    JPanel loginPanel = new JPanel();

    JPanel uzyt_panel = new JPanel();
    Server server;
    WatekServer watekServer;
    String login;
    JButton refresh;


    public Myframe( Klient klient) throws HeadlessException {

        log_re_panel = new JPanel();
        log_re_panel.setLayout(new FlowLayout());

        zaloguj_sie = new JButton("Zaloguj się");
        zarejestruj = new JButton("Zarejestruj");
        zaloguj_sie.addActionListener(this::actionPerformed);

        log_re_panel.add(zaloguj_sie);
        log_re_panel.add(zarejestruj);


// Add the button panel to the frame or another container
        setSize(300, 300);
        add(log_re_panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    Object button = e.getSource();
    if(button ==zaloguj_sie){
        System.out.println("do logowania");
     logowanie();
    }else if(button == refresh) {
        lista_uzyt();
        System.out.println("odświeź");


    } else{
        System.out.println("idziemy do czatu");
        button = (CzatButton) e.getSource();
        czat(((CzatButton) button).login, ((CzatButton) button).watekServer);
    }

    }

    public void logowanie() {
        log_re_panel.setVisible(false);

        loginPanel.setLayout(new GridBagLayout());

        JLabel usernameLabel = new JLabel("Username: ");
        JTextField usernameField = new JTextField(20);

        JLabel passwordLabel = new JLabel("Password: ");
        JPasswordField passwordField = new JPasswordField(20);

        LoginButton loginButton = new LoginButton(usernameField, passwordField);


// Add the components to the panel using a GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        loginPanel.add(usernameLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        loginPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        loginPanel.add(passwordLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        loginPanel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;

        String haslo = new String(passwordField.getPassword());
        String login = usernameField.getText();


        Autentykacja autentykacja = new Autentykacja(this, watekServer);
        loginButton.addActionListener(autentykacja);
        loginPanel.add(loginButton, gbc);


// Add the login panel to the frame or another container
        add(loginPanel);
    }

    public void lista_uzyt() {
        uzyt_panel.setVisible(false);
        loginPanel.setVisible(false);
        setLayout(new BorderLayout());
        setSize(300, 300);
        // Create a panel with a BoxLayout to hold the buttons
        uzyt_panel = new JPanel();


        // Add three buttons to the panel
        JPanel jPanel = new JPanel();
        GridBagLayout gridbag = new GridBagLayout();
        uzyt_panel.setLayout(gridbag);

        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        

        int i  =0;

        try {
            String[] rezultat = SqlConn.koneksja("select login from users", false).split("/");
//            for (WatekServer watekServer : server.lista_osob) {
//
//
//            if(watekServer.login != this.watekServer.login && watekServer.login != null) {
//                CzatButton button = new CzatButton(watekServer.getLogin(), watekServer);
//                button.addActionListener(this::actionPerformed);
//                c.gridx = 0;
//                c.gridy = i;
//                gridbag.setConstraints(button, c);
//                uzyt_panel.add(button);
//                i++;
//            }
//
//
//            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Create a text field


        refresh = new JButton("Odśwież listę");

        refresh.addActionListener(this);

        jPanel.add(refresh);
        // Add the panel and the text field to the frame
//        add(uzyt_panel, BorderLayout.EAST);
        add(uzyt_panel, BorderLayout.EAST);
        add(jPanel, BorderLayout.WEST);


    }


    public void czat(String name, WatekServer odbiorca) {
        JFrame czat_frame = new JFrame("Czat z " + name );

        czat_frame.setSize(300,300);
        // Create the chat history text area
        JTextArea chatHistory = new JTextArea();
        chatHistory.setEditable(false);

        // Create the input field and send button
        JTextField inputField = new JTextField(15);
        JButton sendButton = new JButton("Send");

        // Add an action listener to the send button
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the message from the input field and clear it
                String message = inputField.getText();
                inputField.setText("");

                // Add the message to the chat history
                chatHistory.append("Ty: " + message + "\n");
                odbiorca.printWriter.println(message);
                try {
                    watekServer.out.write(message);
                    watekServer.out.flush();
                    odbiorca.out.write(message);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                System.out.println("Wysłano do " + odbiorca.login);
            }
        });

        // Add the input field and send button to a panel
        JPanel inputPanel = new JPanel();
        inputPanel.setPreferredSize(new Dimension(40, 40));
        inputPanel.add(inputField);
        inputPanel.add(sendButton);

        // Add the chat history and input panel to the main window
        czat_frame.add(chatHistory, "Center");
        czat_frame.add(inputPanel, "South");

        // Show the window
        czat_frame.setVisible(true);




    }


}
