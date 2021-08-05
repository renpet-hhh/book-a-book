package view.pages.admin;

import java.util.List;

import java.awt.event.ActionListener;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTextField;

import controller.handlers.ClearHandler;
import controller.handlers.RegisterUserHandler;
import framework.App;
import framework.Page;
import view.components.AdminMenu;
import view.pages.pagestemplate.SearchContentTemplate;
import view.pages.pagestemplate.LayoutTemplate;

public class RegisterAdmins implements Page {
    
    public final static String TITLE = "Cadastro de Admins";
    @Override
    public String getTitle() { return TITLE; }

    @Override
    public void paint(App app, JFrame frame) {
        JComponent menubar = AdminMenu.withWrapper(app);
        String[] labelsText = new String[] {
            "Nome Completo:", "Data de Nascimento:", "Documento:", "Endereço:", "E-mail:",
            "Contato:", "Senha:", "Confirmar senha:"
        };
        String[] buttonsText = new String[] {"Cancelar", "Cadastrar"};
        SearchContentTemplate template = new SearchContentTemplate(labelsText, buttonsText, null, false);
        JComponent content = template.build();
        List<JTextField> fields = template.getTextFields();
        // Os handlers a seguir terão acesso a todos os campos
        ActionListener registerHandler = new RegisterUserHandler(fields, true);
        ActionListener cancelHandler = new ClearHandler<JTextField>(fields);
        ActionListener[] handlers = new ActionListener[] {cancelHandler, registerHandler};
        template.setHandlers(handlers);
        String path = "Administração >> Cadastro de Admins";
        LayoutTemplate.build(frame, menubar, content, path);
    }

}
