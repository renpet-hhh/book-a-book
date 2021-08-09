package view.pages.admin;

import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JScrollPane;

import framework.App;
import framework.Page;
import helpers.Margin;
import model.Book;
import view.components.AdminMenu;
import view.components.BookResult;
import view.components.GuestMenu;
import view.components.Label;
import view.components.UserMenu;
import view.components.base.MenuFactory;
import view.pages.pagestemplate.LayoutTemplate;

public class SearchBooksResult extends Page {

    /** Responsável pela página que exibe os resultados da pesquisa de usuários.
     * 
     * Além disso, fornece o método estático popupUserData que faz aparecer
     * um popup exibindo os dados associados a um usuário.
     */

    public final static String TITLE = "Pesquisa >> Livros >> Resultado";
    @Override
    public String getTitle() { return TITLE; }

    final static int SPACEBETWEENRESULTS = 10;

    private String titleFilter, authorFilter;
    private int privilege;
    public SearchBooksResult(String titleFilter, String authorFilter, int privilege) {
        this.titleFilter = titleFilter;
        this.authorFilter = authorFilter;
        this.privilege = privilege;
    }
    
    @Override
    public JComponent paint() {
        JComponent pane = Box.createVerticalBox();
        JComponent menubar = this.privilege == 2 ? AdminMenu.withWrapper(app) : this.privilege == 1 ? UserMenu.withWrapper(app) : GuestMenu.withWrapper(app);
        String path = "Pesquisa >> Livro >> Resultado";
        JComponent content = Margin.horizontal(this.mainContent(app), MenuFactory.WRAPPERHORIZONTALMARGIN);
        LayoutTemplate.build(pane, menubar, content, path);
        return pane;
    }

    private JComponent mainContent(App app) {
        List<Book> books = app.getLibrary().getFilteredBooks(this.titleFilter, this.authorFilter);
        JComponent component = Box.createVerticalBox();
        int length = books.size();
        if (length == 0) {
            // não há usuários!
            return new Label("Nenhum livro encontrado");
        }
        component.add(new BookResult(app, books.get(0), this.privilege == 2, false));
        for (int i = 1; i < length; i++) {
            component.add(Margin.rigidVertical(SPACEBETWEENRESULTS));
            component.add(new BookResult(app, books.get(i), this.privilege == 2, false));
        }
        JScrollPane scrollPane = new JScrollPane(component, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        return scrollPane;
    }

}
