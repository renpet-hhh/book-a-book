package view.pages.admin;

import javax.swing.JComponent;
import javax.swing.JFrame;

import view.Page;
import view.components.AdminMenu;
import view.pages.pagestemplate.SearchContentTemplate;
import view.pages.pagestemplate.LayoutTemplate;

public class SearchUsers implements Page {
    
    public final static String TITLE = "Pesquisa >> Usuários";
    @Override
    public String getTitle() { return TITLE; }
    
    @Override
    public void paint(JFrame frame) {
        JComponent menubar = AdminMenu.withWrapper();
        String[] labelsText = new String[] {"Nome:", "Cód. Matrícula:"};
        String[] buttonsText = new String[] {"Cancelar", "Buscar"};
        JComponent content = SearchContentTemplate.build(labelsText, buttonsText, true);
        String path = "Pesquisa >> Usuários";
        LayoutTemplate.build(frame, menubar, content, path);
    }
}
