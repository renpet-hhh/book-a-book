package view.pages.admin;

import javax.swing.JComponent;
import javax.swing.JFrame;

import view.Page;
import view.components.AdminMenu;
import view.pages.pagestemplate.SearchContentTemplate;
import view.pages.pagestemplate.SearchTemplate;

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
        JComponent content = SearchContentTemplate.build(labelsText, buttonsText, false);
        String path = "Administração >> Configurações";
        SearchTemplate.build(frame, menubar, content, path);
    }

}
