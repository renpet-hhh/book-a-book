package view;

import javax.swing.JFrame;

public class GUI {

    private JFrame frame; // frame principal da aplicação
    private Page currentPage; // página atual (atualizamos a cada navigate)
    public GUI() {
        this.frame = new JFrame();
        // queremos que o frame principal encerre a aplicação quando o usuário clicar X nele
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setVisible(true);
    }

    /** Navega até a página passada como argumento, redesenhando o frame principal */
    public void navigate(Page page) {
        this.frame.getContentPane().removeAll();
        this.currentPage = page;
        page.paint(this.frame); // deixo a página pintar
        this.frame.validate();
        this.frame.repaint();
    }
    
    public Page getCurrentPage() {
        return this.currentPage;
    }

    public JFrame getFrame() { return this.frame; }

}
