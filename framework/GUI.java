package framework;

import javax.swing.JFrame;

public class GUI {
    /** View - Exibe o modelo como uma interface gráfica */

    private JFrame frame; // frame principal da aplicação
    private Page currentPage; // página atual (atualizamos a cada navigate)
    private App app; // model
    public GUI(App app, Page initialPage) {
        this.app = app;
        this.frame = new JFrame();
        // queremos que o frame principal encerre a aplicação quando o usuário clicar X nele
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setVisible(true);
        this.navigate(initialPage);
    }

    /** Navega até a página passada como argumento, redesenhando o frame principal */
    public void navigate(Page page) {
        this.frame.getContentPane().removeAll();
        this.currentPage = page;
        page.paint(this.app, this.frame); // deixo a página pintar
        this.frame.validate();
        this.frame.repaint();
    }
    
    public Page getCurrentPage() {
        return this.currentPage;
    }

    public JFrame getFrame() { return this.frame; }

}
