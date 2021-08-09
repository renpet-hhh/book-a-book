package view.pages.admin;

import javax.swing.Box;
import javax.swing.JComponent;

import java.awt.event.ActionListener;

import model.User;
import controller.handlers.ClearHandler;
import controller.handlers.SearchBooksHandler;
import framework.Page;
import view.components.AdminMenu;
import view.pages.pagestemplate.SearchContentTemplate;
import view.pages.pagestemplate.LayoutTemplate;

public class SearchBooks extends Page {
    
    public final static String TITLE = "Pesquisa >> Livros";
    @Override
    public String getTitle() { return TITLE; }

    @Override
    public JComponent paint() {
        JComponent pane = Box.createVerticalBox();
        JComponent menubar = AdminMenu.withWrapper(app);
        String[] labelsText = new String[] {"Título:", "Autor:"};
        String[] buttonsText = new String[] {"Cancelar", "Buscar"};
        SearchContentTemplate template = new SearchContentTemplate(labelsText, buttonsText, null, true);
        JComponent content = template.build();
        ActionListener cancelHandler = new ClearHandler<>(template.getClearableFields());
        ActionListener searchHandler = new SearchBooksHandler(template.getTextFields(), template.getCheckBoxs(), User.ADMINPRIVILEGE);
        ActionListener[] handlers = new ActionListener[] {cancelHandler, searchHandler};
        template.setHandlers(handlers);
        String path = "Pesquisa >> Livros";
        LayoutTemplate.build(pane, menubar, content, path);
        return pane;
    }

}
