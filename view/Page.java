package view;

import javax.swing.JFrame;

public interface Page {

    /** Retorna o título da página */
    String getTitle();
    /** Modifica frame, adicionando componentes a ele de forma a construir a página. */
    void paint(JFrame frame);
    
}
