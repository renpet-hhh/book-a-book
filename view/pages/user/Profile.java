package view.pages.user;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

import model.App;
import view.Page;
import view.components.UserMenu;
import view.pages.pagestemplate.LayoutTemplate;

public class Profile implements Page {

    public final static String TITLE = "Perfil";
    @Override
    public String getTitle() { return TITLE; }

    @Override
    public void paint(JFrame frame) {
        JComponent menubar = UserMenu.withWrapper();
        String path = "Perfil";
        // Página incompleta por enquanto!
        JComponent content = new JLabel("Olá, " + App.get().getLogin().getUsername());
        LayoutTemplate.build(frame, menubar, content, path);
    }
    
}
