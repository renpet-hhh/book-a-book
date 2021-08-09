package view.pages.admin;

import java.util.Collection;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JScrollPane;

import framework.App;
import framework.Page;
import helpers.Margin;
import model.User;
import view.components.AdminMenu;
import view.components.Label;
import view.components.UserResult;
import view.components.base.MenuFactory;
import view.pages.pagestemplate.LayoutTemplate;

public class SearchUsersResult extends Page {

    /** Responsável pela página que exibe os resultados da pesquisa de usuários.
     * 
     * Além disso, fornece o método estático popupUserData que faz aparecer
     * um popup exibindo os dados associados a um usuário.
     */

    public final static String TITLE = "Pesquisa >> Usuários >> Resultado";
    @Override
    public String getTitle() { return TITLE; }

    final static int SPACEBETWEENRESULTS = 10;
    /* Constantes para o POPUP */
    final static int TOPMARGIN = 40;
    final static int BOTTOMMARGIN = 40;
    final static int LEFTMARGIN = 30;
    final static int RIGHTMARGIN = 30;

    private String nameFilter, matriculaFilter;
    public SearchUsersResult(String nameFilter, String matriculaFilter) {
        this.nameFilter = nameFilter;
        this.matriculaFilter = matriculaFilter;
    }
    
    @Override
    public JComponent paint() {
        JComponent pane = Box.createVerticalBox();
        JComponent menubar = AdminMenu.withWrapper(app);
        String path = "Pesquisa >> Usuários >> Resultado";
        JComponent content = Margin.horizontal(this.mainContent(app), MenuFactory.WRAPPERHORIZONTALMARGIN);
        LayoutTemplate.build(pane, menubar, content, path);
        return pane;
    }

    private JComponent mainContent(App app) {
        Collection<User> users = app.getLogin().getFilteredUsers(this.nameFilter, this.matriculaFilter);
        JComponent component = Box.createVerticalBox();
        Iterator<User> it = users.iterator();
        if (users.size() == 0) {
            // não há usuários!
            return new Label("Nenhum usuário encontrado");
        }
        component.add(new UserResult(app, it.next()));
        while (it.hasNext()) {
            component.add(Margin.rigidVertical(SPACEBETWEENRESULTS));
            component.add(new UserResult(app, it.next()));
        }
        JScrollPane scrollPane = new JScrollPane(component, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        return scrollPane;
    }

}
