package view.pages.admin;

import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import model.App;
import model.Book;
import view.Margin;
import view.Page;
import view.components.AdminMenu;
import view.components.BookResult;
import view.components.GuestMenu;
import view.components.Label;
import view.components.UserMenu;
import view.components.base.MenuFactory;
import view.pages.pagestemplate.LayoutTemplate;

public class SearchBooksResult implements Page {

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
    public void paint(JFrame frame) {
        JComponent menubar = this.privilege == 2 ? AdminMenu.withWrapper() : this.privilege == 1 ? UserMenu.withWrapper() : GuestMenu.withWrapper();
        String path = "Pesquisa >> Livro >> Resultado";
        JComponent content = Margin.horizontal(this.mainContent(), MenuFactory.WRAPPERHORIZONTALMARGIN);
        LayoutTemplate.build(frame, menubar, content, path);
    }

    private JComponent mainContent() {
        List<Book> books = App.get().getLibrary().getFilteredBooks(this.titleFilter, this.authorFilter);
        JComponent component = Box.createVerticalBox();
        int length = books.size();
        if (length == 0) {
            // não há usuários!
            return new Label("Nenhum livro encontrado");
        }
        component.add(new BookResult(books.get(0), this.privilege == 2, false));
        for (int i = 1; i < length; i++) {
            component.add(Margin.rigidVertical(SPACEBETWEENRESULTS));
            component.add(new BookResult(books.get(i), this.privilege == 2, false));
        }
        JScrollPane scrollPane = new JScrollPane(component, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        return scrollPane;
    }

}
