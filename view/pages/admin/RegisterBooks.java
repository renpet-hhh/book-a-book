package view.pages.admin;

import javax.swing.Box;
import javax.swing.JComponent;

import java.awt.event.ActionListener;

import controller.handlers.ClearHandler;
import controller.handlers.RegisterBookHandler;
import framework.Page;
import view.components.AdminMenu;
import view.pages.pagestemplate.SearchContentTemplate;
import view.pages.pagestemplate.LayoutTemplate;

import java.util.List;
import javax.swing.JTextField;
import model.Book;
import controller.RefreshID;

public class RegisterBooks extends Page {
    
    public final static String TITLE = "Catalogação";
    @Override
    public String getTitle() { return TITLE; }

    private List<JTextField> fields;
    private Book book;

    public RegisterBooks() {
        this(null);
    }

    public RegisterBooks(Book book) {
        this.book = book;
    }

    @Override
    public JComponent paint() {
        boolean edit = book != null;
        JComponent pane = Box.createVerticalBox();
        JComponent menubar = AdminMenu.withWrapper(app);
        String[] labelsText = new String[] {
            "Título:", "Subtítulo:", "Autor 1:", "Autor 2:", "Autor 3:",
            "Edição:", "Ano de publicação:", "Local de publicação:", "Exemplares:", "ISBN:"
        };
        String[] buttonsText = new String[] {"Cancelar", edit? "Atualizar": "Cadastrar"};
        SearchContentTemplate template = new SearchContentTemplate(labelsText, buttonsText, null, false);
        JComponent content = template.build();
        this.fields = template.getTextFields();
        ActionListener cancelHandler = edit ? (e -> this.refresh(RefreshID.CLEAR)) : new ClearHandler<>(template.getClearableFields());
        RegisterBookHandler registerBookHandler = new RegisterBookHandler(this.fields);
        if (edit) registerBookHandler.setBookToUpdate(book);
        ActionListener[] handlers = new ActionListener[] {cancelHandler, registerBookHandler};
        template.setHandlers(handlers);
        String path = edit ?  "Pesquisa >> Livros >> Resultado >> Editar" : "Catalogação";
        LayoutTemplate.build(pane, menubar, content, path);
        this.refresh(RefreshID.CLEAR);
        return pane;
    }


    @Override
    public void refresh(RefreshID changeID, Object... args) {
        if (this.book != null && changeID == RefreshID.CLEAR) {
            String title = book.getTitle();
            String subtitle = book.getSubtitle();
            String author1 = book.getAuthors().get(0);
            String author2 = book.getAuthors().get(1);
            String author3 = book.getAuthors().get(2);
            String edition = book.getEdition();
            int publishingYear = book.getYearOfPublishment();
            String whereWasPublished = book.getWhereWasPublished();
            int numberOfCopies = book.getHowManyTotal();
            String isbn = book.getIsbn();

            this.fields.get(0).setText(title);
            this.fields.get(1).setText(subtitle);
            this.fields.get(2).setText(author1);
            this.fields.get(3).setText(author2);
            this.fields.get(4).setText(author3);
            this.fields.get(5).setText(edition);
            this.fields.get(6).setText(String.valueOf(publishingYear));
            this.fields.get(7).setText(whereWasPublished);
            this.fields.get(8).setText(String.valueOf(numberOfCopies));
            this.fields.get(9).setText(isbn);
            
        }

        super.refresh(changeID, args);
    }
}
