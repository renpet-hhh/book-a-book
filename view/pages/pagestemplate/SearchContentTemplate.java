package view.pages.pagestemplate;

import java.awt.Color;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JPanel;

import view.components.Label;
import view.components.base.MenuFactory;
import view.Margin;
import view.components.fixed.LimitedJTextField;
import view.components.layout.PackLayout;

public class SearchContentTemplate {

    final static int VERTICALGAPMAINCONTENT = 15;
    final static int LEFTRIGHTMARGINMAINCONTENT = 50;
    final static int LEFTRIGHTMARGINWRAPPER = MenuFactory.WRAPPERHORIZONTALMARGIN;
    final static int BOTTOMMARGINMAINWRAPPER = 80;
    final static int SPACEBETWEENCHECKANDLABEL = 2;
    final static int SPACEBETWEENLABELANDFIELD = 10;
    final static int SPACEBETWEENBUTTONS = 30;
    final static int SPACEBETWEENBUTTONSANDMAINCONTENT = 30;
    final static int FIELDMINWIDTH = 200;
    final static int FIELDMAXWIDTH = 500;

    final static Color BGCOLOR = new Color(187, 187, 187);
    final static Color MENUCOLOR = new Color(66, 66, 69);
    final static Color LABELCOLOR = new Color(220, 220, 220);
    final static Color MAINWRAPPERCOLOR = new Color(255, 252, 252);

    private static JComponent mainContent(String[] labelsText) {
        JComponent component = new JPanel();
        PackLayout layout = new PackLayout(component, PackLayout.Y_AXIS);
        component.setLayout(layout);
        for (int i = 0; i < labelsText.length; i++) {
            Box line = Box.createHorizontalBox();
            JCheckBox check = new JCheckBox();
            check.setOpaque(false);
            Label label = new Label(labelsText[i]);
            LimitedJTextField textField = new LimitedJTextField(FIELDMINWIDTH, FIELDMAXWIDTH);
            line.add(check);
            line.add(Margin.rigidHorizontal(SPACEBETWEENCHECKANDLABEL));
            line.add(label);
            line.add(Box.createHorizontalGlue()); // dá certo porcausa de PackLayout
            line.add(Margin.rigidHorizontal(SPACEBETWEENLABELANDFIELD));
            line.add(textField);
            if (i > 0) {
                component.add(Margin.rigidVertical(VERTICALGAPMAINCONTENT));
            }
            component.add(line);
        }
        component.setBackground(MAINWRAPPERCOLOR);
        return component;
    }

    public static JComponent build(String[] labelsText, String[] buttonsText) {
        JComponent wrapper2 = Box.createHorizontalBox();
        JComponent wrapper1 = Box.createVerticalBox();
        Box buttons = Box.createHorizontalBox();
        buttons.add(Box.createHorizontalGlue());
        for (int i = 0; i < buttonsText.length; i++) {
            JButton bttn = new JButton(buttonsText[i]);
            if (i > 0) {
                buttons.add(Margin.rigidHorizontal(SPACEBETWEENBUTTONS));
            }
            buttons.add(bttn);
        }
        buttons.add(Box.createHorizontalGlue());
        wrapper1.add(Box.createVerticalGlue());
        wrapper1.add(mainContent(labelsText));
        wrapper1.add(Margin.rigidVertical(SPACEBETWEENBUTTONSANDMAINCONTENT));
        wrapper1.add(buttons);
        wrapper1.add(Box.createVerticalGlue());
        wrapper1.setOpaque(true);
        wrapper1.setBackground(MAINWRAPPERCOLOR);
        wrapper2.add(Margin.rigidHorizontal(LEFTRIGHTMARGINWRAPPER));
        wrapper2.add(wrapper1);
        wrapper2.add(Margin.rigidHorizontal(LEFTRIGHTMARGINWRAPPER));
        return wrapper2;
    }
    

}
