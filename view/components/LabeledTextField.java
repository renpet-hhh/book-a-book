package view.components;

import javax.swing.JComponent;

import view.Margin;
import view.components.fixed.LimitedJTextField;
import view.components.layout.PackLayout;

public class LabeledTextField extends JComponent {

    final static int SPACEBETWEENLABELANDTEXT = 10;
    final static int DEFAULTMINFIELDWIDTH = 100;
    final static int DEFAULTMAXFIELDWIDTH = 120;

    public LabeledTextField(String text) {
        PackLayout layout = new PackLayout(this, PackLayout.X_AXIS);
        this.setLayout(layout);
        Label label = new Label(text);
        LimitedJTextField field = new LimitedJTextField(DEFAULTMINFIELDWIDTH, DEFAULTMAXFIELDWIDTH);
        this.add(label);
        this.add(Margin.rigidHorizontal(SPACEBETWEENLABELANDTEXT));
        this.add(field);
    }
    
}
