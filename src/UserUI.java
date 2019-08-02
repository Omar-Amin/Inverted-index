import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
        tf.setLocation(90,45);
        tf.setSize(600,60);
        tf.setVisible(true);
        tf.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                System.out.println(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        JButton jb = new JButton();
        jb.setText("Make inverted list");
        jb.setSize(200,25);
        jb.setLocation(270,10);
        p.add(jb);

        p.add(tf);

        frame.add(p);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    private void findMatch(){

    }
}
