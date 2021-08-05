package framework;

import javax.swing.JFrame;

public interface Page {

    /** Retorna o título da página */
    String getTitle();
    /** Modifica frame, adicionando componentes a ele de forma a construir a página.
     * 
     * Pede para a View (Page) ser exibida conforme dados do Model (App)
     */
    void paint(App app, JFrame frame);
    
}
