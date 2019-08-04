import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class UserUI implements ActionListener {
    private ArrayList<Pair> invList = new ArrayList<>();
    private JTextField tf;
    private JTextArea tf2;

    UserUI(){
        JPanel p = new JPanel();
        p.setLayout(null);
        JFrame frame = new JFrame("Inverted Index");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setLocation(500,250);
        frame.setResizable(false);

        tf2 = new JTextArea("Files that contains the keywords (first choose which directory to be inverted):");
        tf2.setLocation(90,145);
        tf2.setSize(600,300);
        tf2.setVisible(true);
        tf2.setEditable(false);

        tf = new JTextField(30);
        tf.setLocation(90,45);
        tf.setSize(600,60);
        tf.setVisible(true);
        tf.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                tf2.setText("Files that contains the keywords (first choose which directory to be inverted):");
                findMatch();
            }
        });

        JButton jb = new JButton();
        jb.setText("Make inverted list");
        jb.setSize(200,25);
        jb.setLocation(270,10);
        jb.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new java.io.File("."));
            chooser.setDialogTitle("Hello there");
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            try{
                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    WordLookup wl = new WordLookup("IIData");
                    String path = String.valueOf(chooser.getSelectedFile());
                    wl.findFiles(Paths.get(path));
                    invList = wl.getInvList();
                }
            }catch (IOException ex){
                System.out.println("Error");
            }

        });
        p.add(jb);
        p.add(tf2);
        p.add(tf);

        frame.add(p);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    /**
     * This method searches for the paths that matches the keywords.
     */
    private void findMatch(){
        String tmp = tf.getText();
        String[] words = tmp.split(" ");
        ArrayList<String> check;
        ArrayList<String> rw = new ArrayList<>(Arrays.asList(words));

        for (Pair pair:invList) {
            check = rw;

            for (Map.Entry<String,Integer> elm:pair.getHm().entrySet()) {
                if(rw.contains(elm.getKey())){
                    check.remove(elm.getKey());
                }
            }

            if(check.isEmpty()){
                tf2.append("\n" + pair.getPath());
            }
        }
    }
}
