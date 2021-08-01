package view.pages.admin;

import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import model.App;
import model.User;
import model.UserData;
import view.Margin;
import view.Page;
import view.components.AdminMenu;
import view.components.Label;
import view.components.UserResult;
import view.components.base.MenuFactory;
import view.pages.pagestemplate.LayoutTemplate;

public class SearchUsersResult implements Page {

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
    public void paint(JFrame frame) {
        JComponent menubar = AdminMenu.withWrapper();
        String path = "Pesquisa >> Usuários >> Resultado";
        JComponent content = Margin.horizontal(this.mainContent(), MenuFactory.WRAPPERHORIZONTALMARGIN);
        LayoutTemplate.build(frame, menubar, content, path);
    }

    private JComponent mainContent() {
        Collection<User> users = App.get().getLogin().getFilteredUsers(this.nameFilter, this.matriculaFilter);
        JComponent component = Box.createVerticalBox();
        Iterator<User> it = users.iterator();
        if (users.size() == 0) {
            // não há usuários!
            return new Label("Nenhum usuário encontrado");
        }
        component.add(new UserResult(it.next()));
        while (it.hasNext()) {
            component.add(Margin.rigidVertical(SPACEBETWEENRESULTS));
            component.add(new UserResult(it.next()));
        }
        JScrollPane scrollPane = new JScrollPane(component, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        return scrollPane;
    }

    public static void popupUserData(User user) {
        SearchUsersResult.popupUserData(App.get().getFrame(), user);
    }
    public static void popupUserData(JFrame frame, User user) {
        UserData data = user.getData();
        JComponent component = Box.createVerticalBox();
        Label name = new Label("Nome: " + data.name);
        Label birth = new Label("Data de nascimento: " + data.birthdate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        Label address = new Label("Endereço: "+ data.address);
        Label email = new Label("Email: " + data.email);
        Label contact = new Label("Contato: " + data.contact);
        Label status = new Label("Situação: OK");
        component.add(Margin.rigidVertical(TOPMARGIN));
        component.add(name);
        component.add(birth);
        component.add(address);
        component.add(email);
        component.add(contact);
        component.add(Margin.rigidVertical(3));
        component.add(status);
        component.add(Margin.rigidVertical(3));
        component.add(Margin.rigidVertical(BOTTOMMARGIN));
        JComponent wrapper = Box.createHorizontalBox();
        wrapper.add(Margin.rigidHorizontal(LEFTMARGIN));
        wrapper.add(component);
        wrapper.add(Margin.rigidHorizontal(RIGHTMARGIN));
        JDialog dialog = new JDialog(frame);
        dialog.add(wrapper);
        dialog.pack();
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }
}
