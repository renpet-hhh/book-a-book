package view.pages.admin;

import javax.swing.JComponent;
import javax.swing.JFrame;

import view.Page;
import view.components.AdminMenu;
import view.pages.pagestemplate.SearchContentTemplate;
import view.pages.pagestemplate.SearchTemplate;

public class SearchBooks implements Page {
    
    public final static String TITLE = "Pesquisa >> Livros";
    @Override
    public String getTitle() { return TITLE; }

    @Override
    public void paint(JFrame frame) {
        JComponent menubar = AdminMenu.withWrapper();
        String[] labelsText = new String[] {"Título:", "Autor:"};
        String[] buttonsText = new String[] {"Cancelar", "Buscar"};
        JComponent content = SearchContentTemplate.build(labelsText, buttonsText, true);
        String path = "Pesquisa >> Livros";
        SearchTemplate.build(frame, menubar, content, path);
    }

}
