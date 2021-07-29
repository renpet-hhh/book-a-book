package view.pages.admin;

import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTextField;

import model.handlers.FieldObserver;
import model.handlers.RegisterObserver;
import view.Page;
import view.components.AdminMenu;
import view.pages.pagestemplate.SearchContentTemplate;
import view.pages.pagestemplate.LayoutTemplate;

public class RegisterUsers implements Page {
    
    public final static String TITLE = "Cadastro de Usuários";
    @Override
    public String getTitle() { return TITLE; }

    @Override
    public void paint(JFrame frame) {
        JComponent menubar = AdminMenu.withWrapper();
        String[] labelsText = new String[] {
            "Nome Completo:", "Data de Nascimento:", "Documento:", "Endereço:", "E-mail:",
            "Contato:", "Senha:", "Confirmar senha:"
        };
        String[] buttonsText = new String[] {"Cancelar", "Cadastrar"};
        SearchContentTemplate template = new SearchContentTemplate(labelsText, buttonsText, null, false);
        JComponent content = template.build();
        List<JTextField> fields = template.getTextFields();
        // O observer a seguir irá ter acesso a todos os campos
        FieldObserver registerObserver = new RegisterObserver(fields, false);
        FieldObserver cancelObserver = new FieldObserver(fields, f -> {
            for (JTextField field : f) {
                // limpa todos os campos
                field.setText("");
            }
        });
        ActionListener[] handlers = new ActionListener[] {cancelObserver, registerObserver};
        template.setHandlers(handlers);
        String path = "Circulação >> Cadastro de Usuários";
        LayoutTemplate.build(frame, menubar, content, path);
    }

}
