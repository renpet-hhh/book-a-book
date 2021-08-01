package view.pages.admin;

import javax.swing.JComponent;
import javax.swing.JFrame;

import java.awt.event.ActionListener;

import model.handlers.ClearHandler;
import model.handlers.SearchUsersHandler;
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
        SearchContentTemplate template = new SearchContentTemplate(labelsText, buttonsText, null, true);
        JComponent content = template.build();
        ActionListener cancelObserver = new ClearHandler<>(template.getClearableFields());
        ActionListener searchHandler = new SearchUsersHandler(template.getTextFields(), template.getCheckBoxs());
        ActionListener[] handlers = new ActionListener[] {cancelObserver, searchHandler};
        template.setHandlers(handlers);
        String path = "Pesquisa >> Usuários";
        LayoutTemplate.build(frame, menubar, content, path);
    }
}
