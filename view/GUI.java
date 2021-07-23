package view;

import javax.swing.JFrame;

public class GUI {

    private JFrame frame;
    private Page currentPage;
    public GUI() {
        this.frame = new JFrame();
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setVisible(true);
    }

    public void navigate(Page page) {
        this.frame.getContentPane().removeAll();
        this.currentPage = page;
        page.paint(this.frame);
        this.frame.validate();
        this.frame.repaint();
    }
    
    public Page getCurrentPage() {
        return this.currentPage;
    }

    public JFrame getFrame() { return this.frame; }

}
