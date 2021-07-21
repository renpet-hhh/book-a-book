package view.components;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Label extends JLabel {
    public Label(String text) {
        super(text);
    }
    public Label(ImageIcon imageIcon) {
        super(imageIcon);
    }
    public Label(String text, Color color) {
        super(text);
        this.setForeground(color);
    }
}
