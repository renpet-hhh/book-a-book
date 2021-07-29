package view.pages.admin;

import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTextField;

import model.App;
import model.commands.RegisterUserCmd;
import model.handlers.FieldObserver;
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
        // O observer a seguir irá ter acesso aos campos "E-mail" e "Senha"
        FieldObserver registerObserver = new FieldObserver(fields, f -> {
            String username = f.get(4).getText();
            String password = f.get(6).getText();
            // o botão Cadastrar foi pressionado! vamos tentar cadastrar o usuário
            // mensagens de erro e confirmação são de responsabilidade do próprio comando abaixo
            App.get().invoke(new RegisterUserCmd(username, password));
        });
        FieldObserver cancelObserver = new FieldObserver(fields, f -> {
            for (JTextField field : f) {
                field.setText("");
            }
        });
        ActionListener[] handlers = new ActionListener[] {cancelObserver, registerObserver};
        template.setHandlers(handlers);
        String path = "Circulação >> Cadastro de Usuários";
        LayoutTemplate.build(frame, menubar, content, path);
    }

}
