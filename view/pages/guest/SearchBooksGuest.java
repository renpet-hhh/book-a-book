package view.pages.guest;

import javax.swing.JComponent;
import javax.swing.JFrame;

import view.Page;
import view.components.GuestMenu;
import view.pages.pagestemplate.SearchContentTemplate;
import view.pages.pagestemplate.LayoutTemplate;

public class SearchBooksGuest implements Page {
    
    public final static String TITLE = "Pesquisa >> Livros";
    @Override
    public String getTitle() { return TITLE; }

    @Override
    public void paint(JFrame frame) {
        JComponent menubar = GuestMenu.withWrapper();
        String[] labelsText = new String[] {"Título:", "Autor:"};
        String[] buttonsText = new String[] {"Cancelar", "Buscar"};
        JComponent content = SearchContentTemplate.build(labelsText, buttonsText, true);
        String path = "Pesquisa >> Livros";
        LayoutTemplate.build(frame, menubar, content, path);
    }

}
