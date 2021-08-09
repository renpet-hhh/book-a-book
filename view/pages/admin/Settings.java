package view.pages.admin;

import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JComponent;

import controller.handlers.ClearHandler;
import framework.Page;
import view.components.AdminMenu;
import view.pages.pagestemplate.SearchContentTemplate;
import view.pages.pagestemplate.LayoutTemplate;

public class Settings extends Page {
    
    public final static String TITLE = "Configurações";
    @Override
    public String getTitle() { return TITLE; }

    @Override
    public JComponent paint() {
        JComponent pane = Box.createVerticalBox();
        JComponent menubar = AdminMenu.withWrapper(app);
        String[] labelsText = new String[] {
            "Alterar tempo de empréstimo:",
            "Alterar quantidade de empréstimos:",
            "Alterar valor da multa:"
        };
        String[] buttonsText = new String[] {"Cancelar", "Salvar"};
        SearchContentTemplate template = new SearchContentTemplate(labelsText, buttonsText, false, true);
        JComponent content = template.build();
        ActionListener cancelHandler = new ClearHandler<>(template.getClearableFields());
        ActionListener[] handlers = new ActionListener[] {cancelHandler, null};
        template.setHandlers(handlers);
        String path = "Administração >> Configurações";
        LayoutTemplate.build(pane, menubar, content, path);
        return pane;
    }

}
