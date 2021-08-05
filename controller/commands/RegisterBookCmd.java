package controller.commands;

import javax.swing.JOptionPane;

import framework.App;
import framework.Command;
import model.Book;

public class RegisterBookCmd implements Command {

    @Override
    public String log() {
        return "RegisterBookCmd: " + (this.book == null ? "null" : this.book.getTitle());
    }

    private Book book;
    public RegisterBookCmd(Book book) {
        this.book = book;
    }

    @Override
    public void execute() {
        App app = App.get();
        boolean changedLibrary = app.getLibrary().addBook(book);
        if (!changedLibrary) {
            app.control().invoke(new DisplayPopupCmd("Livro já está catalogado", JOptionPane.ERROR_MESSAGE));
            return;
        }
        app.control().invoke(new DisplayPopupCmd("Livro catalogado com sucesso."));
    }
    
}
