package view.pages.admin;

import java.awt.event.ActionListener;
import javax.swing.JComponent;
import javax.swing.JFrame;

import model.handlers.ClearHandler;
import view.Page;
import view.components.AdminMenu;
import view.pages.pagestemplate.SearchContentTemplate;
import view.pages.pagestemplate.LayoutTemplate;

public class Settings implements Page {
    
    public final static String TITLE = "Configurações";
    @Override
    public String getTitle() { return TITLE; }

    @Override
    public void paint(JFrame frame) {
        JComponent menubar = AdminMenu.withWrapper();
        String[] labelsText = new String[] {
            "Alterar tempo de empréstimo:",
            "Alterar quantidade de empréstimos:",
            "Alterar valor da multa:"
        };
        String[] buttonsText = new String[] {"Cancelar", "Salvar"};
        SearchContentTemplate template = new SearchContentTemplate(labelsText, buttonsText, false, true);
        JComponent content = template.build();
        ActionListener cancelObserver = new ClearHandler<>(template.getClearableFields());
        ActionListener[] handlers = new ActionListener[] {cancelObserver, null};
        template.setHandlers(handlers);
        String path = "Administração >> Configurações";
        LayoutTemplate.build(frame, menubar, content, path);
    }

}
