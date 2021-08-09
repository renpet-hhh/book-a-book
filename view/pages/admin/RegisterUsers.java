package view.pages.admin;

import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JTextField;

import controller.handlers.ClearHandler;
import controller.handlers.RegisterUserHandler;
import framework.Page;
import view.components.AdminMenu;
import view.pages.pagestemplate.SearchContentTemplate;
import view.pages.pagestemplate.LayoutTemplate;

public class RegisterUsers extends Page {
    
    public final static String TITLE = "Cadastro de Usuários";
    @Override
    public String getTitle() { return TITLE; }

    @Override
    public JComponent paint() {
        JComponent pane = Box.createVerticalBox();
        JComponent menubar = AdminMenu.withWrapper(app);
        String[] labelsText = new String[] {
            "Nome Completo:", "Data de Nascimento:", "Documento:", "Endereço:", "E-mail:",
            "Contato:", "Senha:", "Confirmar senha:"
        };
        String[] buttonsText = new String[] {"Cancelar", "Cadastrar"};
        SearchContentTemplate template = new SearchContentTemplate(labelsText, buttonsText, null, false);
        JComponent content = template.build();
        List<JTextField> fields = template.getTextFields();
        // O observer a seguir irá ter acesso a todos os campos
        ActionListener registerHandler = new RegisterUserHandler(fields, false);
        ActionListener cancelHandler = new ClearHandler<JTextField>(fields);
        ActionListener[] handlers = new ActionListener[] {cancelHandler, registerHandler};
        template.setHandlers(handlers);
        String path = "Circulação >> Cadastro de Usuários";
        LayoutTemplate.build(pane, menubar, content, path);
        return pane;
    }

}
