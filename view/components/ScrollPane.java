package view.components;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;

public class ScrollPane extends JScrollPane {
    
    public ScrollPane(Component view) {
        super(view);
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.getVerticalScrollBar().setUnitIncrement(16);
        this.setBorder(BorderFactory.createEmptyBorder());
    }

    @Override
    public Dimension getMinimumSize() {
        int width = 0;
        for (Component c : this.getComponents()) {
            width += c.getMinimumSize().width;
        }
        return new Dimension(width, super.getMinimumSize().height);
    }
    
}
