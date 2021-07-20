package view;

import java.awt.Dimension;

import javax.swing.JFrame;

public class GUI {

    private JFrame frame;
    public GUI() {
        this.frame = new JFrame();
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setMinimumSize(new Dimension(300, 300));
        this.frame.setVisible(true);
    }

    public JFrame getFrame() { return this.frame; }

}
