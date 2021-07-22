package view.pages;

import javax.swing.JComponent;
import javax.swing.JFrame;

import view.Page;
import view.components.LibraryMenu;
import view.pages.pagestemplate.SearchContentTemplate;
import view.pages.pagestemplate.SearchTemplate;

public class Search implements Page {
    
    @Override
    public void paint(JFrame frame) {
        JComponent menubar = LibraryMenu.withWrapper();
        String[] labelsText = new String[] {"Título:", "Autor:"};
        String[] buttonsText = new String[] {"Cancelar", "Buscar"};
        JComponent content = SearchContentTemplate.build(labelsText, buttonsText);
        String path = "Pesquisa Bibliográfica";
        SearchTemplate.build(frame, menubar, content, path);
    }

}
