package view.pages.admin;

import javax.swing.JComponent;
import javax.swing.JFrame;

import view.Page;
import view.components.AdminMenu;
import view.pages.pagestemplate.SearchContentTemplate;
import view.pages.pagestemplate.SearchTemplate;

public class RegisterAdmins implements Page {
    
    public final static String TITLE = "Cadastro de Admins";
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
        JComponent content = SearchContentTemplate.build(labelsText, buttonsText, false);
        String path = "Administração >> Cadastro de Admins";
        SearchTemplate.build(frame, menubar, content, path);
    }

}
