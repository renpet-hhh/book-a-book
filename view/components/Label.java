package view.components;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Label extends JLabel {
    
    /** Classe customizada para facilitar a construção de Labels de uso comum na aplicação */

    public Label(ImageIcon imageIcon) {
        super(imageIcon);
    }
    public Label(String text) {
        this(text, null);
    }
    public Label(String text, Color foreground) { // pinta conforme a cor no argumento
        this(text, foreground, null);
    }
    public Label(String text, Color foreground, Color background) { // pinta conforme a cor no argumento
        super(text);
        if (foreground != null) this.setForeground(foreground);
        if (background != null) this.setForeground(background);
        this.setOpaque(background != null);
    }
}
