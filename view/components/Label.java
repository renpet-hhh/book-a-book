package view.components;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Label extends JLabel {
    
    /** Classe customizada para facilitar a construção de Labels de uso comum na aplicação */

    public Label(String text) {
        super(text);
    }
    public Label(ImageIcon imageIcon) {
        super(imageIcon);
    }
    public Label(String text, Color color) { // pinta conforme a cor no argumento
        super(text);
        this.setForeground(color);
    }
}
