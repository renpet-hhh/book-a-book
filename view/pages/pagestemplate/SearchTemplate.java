package view.pages.pagestemplate;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;

import view.components.Label;
import view.components.base.MenuFactory;
import view.Margin;

public class SearchTemplate {

    final static int BOTTOMMARGINMAINWRAPPER = 80;
    final static int LEFTRIGHTMARGINWRAPPER = MenuFactory.WRAPPERHORIZONTALMARGIN;


    private static JComponent pathComponent(String text) {
        JComponent component = Box.createHorizontalBox();
        Label label = new Label(text);
        component.add(Margin.rigidHorizontal(LEFTRIGHTMARGINWRAPPER));
        component.add(label);
        component.add(Box.createHorizontalGlue());
        return component;
    }

    public static void build(JFrame frame, JComponent menubar, JComponent content, String path) {
        BoxLayout bLayout = new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS);
        frame.setLayout(bLayout);
        frame.add(menubar);
        frame.add(pathComponent(path));
        frame.add(content);
        frame.add(Margin.rigidVertical(BOTTOMMARGINMAINWRAPPER));
    }
    
}
