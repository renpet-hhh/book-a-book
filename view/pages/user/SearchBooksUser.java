package view.pages.user;

import javax.swing.JComponent;
import javax.swing.JFrame;

import java.awt.event.ActionListener;

import model.handlers.ClearHandler;
import view.Page;
import view.components.UserMenu;
import view.pages.pagestemplate.SearchContentTemplate;
import view.pages.pagestemplate.LayoutTemplate;

public class SearchBooksUser implements Page {
    
    public final static String TITLE = "Pesquisa >> Livros";
    @Override
    public String getTitle() { return TITLE; }

    @Override
    public void paint(JFrame frame) {
        JComponent menubar = UserMenu.withWrapper();
        String[] labelsText = new String[] {"Título:", "Autor:"};
        String[] buttonsText = new String[] {"Cancelar", "Buscar"};
        SearchContentTemplate template = new SearchContentTemplate(labelsText, buttonsText, null, true);
        JComponent content = template.build();
        ActionListener cancelObserver = new ClearHandler<>(template.getClearableFields());
        ActionListener[] handlers = new ActionListener[] {cancelObserver, null};
        template.setHandlers(handlers);
        String path = "Pesquisa Bibliográfica";
        LayoutTemplate.build(frame, menubar, content, path);
    }

}
