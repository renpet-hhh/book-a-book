package view.pages.admin;

import javax.swing.Box;
import javax.swing.JComponent;

import java.awt.event.ActionListener;

import controller.handlers.ClearHandler;
import controller.handlers.SearchUsersHandler;
import framework.Page;
import view.components.AdminMenu;
import view.pages.pagestemplate.SearchContentTemplate;
import view.pages.pagestemplate.LayoutTemplate;

public class SearchUsers extends Page {
    
    public final static String TITLE = "Pesquisa >> Usuários";
    @Override
    public String getTitle() { return TITLE; }
    
    @Override
    public JComponent paint() {
        JComponent pane = Box.createVerticalBox();
        JComponent menubar = AdminMenu.withWrapper(app);
        String[] labelsText = new String[] {"Nome:", "Cód. Matrícula:"};
        String[] buttonsText = new String[] {"Cancelar", "Buscar"};
        SearchContentTemplate template = new SearchContentTemplate(labelsText, buttonsText, null, true);
        JComponent content = template.build();
        ActionListener cancelHandler = new ClearHandler<>(template.getClearableFields());
        ActionListener searchHandler = new SearchUsersHandler(template.getTextFields(), template.getCheckBoxs());
        ActionListener[] handlers = new ActionListener[] {cancelHandler, searchHandler};
        template.setHandlers(handlers);
        String path = "Pesquisa >> Usuários";
        LayoutTemplate.build(pane, menubar, content, path);
        return pane;
    }
}
