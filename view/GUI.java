package view;

import javax.swing.JFrame;

public class GUI {

    private JFrame frame;
    public GUI() {
        this.frame = new JFrame();
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setVisible(true);
    }

    public void navigate(Page page) {
        this.frame.getContentPane().removeAll();
        page.paint(this.frame);
        this.frame.validate();
    }

    public JFrame getFrame() { return this.frame; }

}
