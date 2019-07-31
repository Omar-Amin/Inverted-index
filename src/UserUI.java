import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserUI implements ActionListener {
    private JFrame frame;

    UserUI(){
        JPanel p = new JPanel();
        p.setLayout(null);
        frame = new JFrame("Inverted Index");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setLocation(500,250);

        JTextField tf = new JTextField(30);
        tf.setLocation(30,30);
        tf.setSize(600,60);
        tf.setVisible(true);

        p.add(tf);

        frame.add(p);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
