package controller.commands;

import javax.swing.JOptionPane;

import framework.App;
import framework.Command;
import model.Book;
import model.Reports;
import model.User;

public class RegisterBookCmd implements Command {

    @Override
    public String log() {
        return "RegisterBookCmd: " + (this.book == null ? "null" : this.book.getTitle());
    }

    private Book book;
    private User user;
    public RegisterBookCmd(Book book) {
        this(book, App.get().getLogin().getUser());
    }
    /* user é quem está cadastrando esse livro */
    public RegisterBookCmd(Book book, User user) {
        this.book = book;
        this.user = user;
    }

    @Override
    public void execute() {
        App app = App.get();
        boolean changedLibrary = app.getLibrary().addBook(book);
        if (!changedLibrary) {
            app.control().invoke(new DisplayPopupCmd("Livro já está catalogado", JOptionPane.ERROR_MESSAGE));
            return;
        }
        app.control().invoke(new ReportCmd<>(book, Reports.Type.BOOK_REGISTER, this.user));
        app.control().invoke(new DisplayPopupCmd("Livro catalogado com sucesso."));
    }
    
}
