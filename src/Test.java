import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Test extends Object {
    private static void print(String msg) {
        String name = Thread.currentThread().getName();
        System.out.println(name + ": " + msg);
    }
    public static void main(String[] args) {
        final JLabel label= new JLabel("Initial text");
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(label);
        JFrame f = new JFrame("InvokeLater Test");
        f.setContentPane(panel);
        f.setSize(400, 300);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        try {
            print("sleeping for 5 seconds");
            Thread.sleep(5000);
        } catch(InterruptedException ie) {
            print("interrupted while sleeping");
        }
        print("creating the code block for an event thread");
        Runnable setTextRun = new Runnable() {
            public void run() {
                try {
                    Thread.sleep(100);
                    print("about to do setText()");
                    label.setText("New text");
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        };
        print("about to call invokeLater()");
        SwingUtilities.invokeLater(setTextRun);
        print("back from invokeLater()");
    }
}