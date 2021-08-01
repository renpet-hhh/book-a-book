package view.pages.admin;

import javax.swing.JComponent;
import javax.swing.JFrame;

import java.awt.event.ActionListener;

import model.handlers.ClearHandler;
import model.handlers.RegisterBookHandler;
import view.Page;
import view.components.AdminMenu;
import view.pages.pagestemplate.SearchContentTemplate;
import view.pages.pagestemplate.LayoutTemplate;

public class RegisterBooks implements Page {
    
    public final static String TITLE = "Catalogação";
    @Override
    public String getTitle() { return TITLE; }

    @Override
    public void paint(JFrame frame) {
        JComponent menubar = AdminMenu.withWrapper();
        String[] labelsText = new String[] {
            "Título:", "Subtítulo:", "Autor 1:", "Autor 2:", "Autor 3:",
            "Edição:", "Ano de publicação:", "Local de publicação:", "Exemplares:", "ISBN:"
        };
        String[] buttonsText = new String[] {"Cancelar", "Cadastrar"};
        SearchContentTemplate template = new SearchContentTemplate(labelsText, buttonsText, null, false);
        JComponent content = template.build();
        ActionListener cancelHandler = new ClearHandler<>(template.getClearableFields());
        ActionListener registerBookHandler = new RegisterBookHandler(template.getTextFields());
        ActionListener[] handlers = new ActionListener[] {cancelHandler, registerBookHandler};
        template.setHandlers(handlers);
        String path = "Catalogação";
        LayoutTemplate.build(frame, menubar, content, path);
    }

}
