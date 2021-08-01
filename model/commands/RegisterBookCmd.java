package model.commands;

import javax.swing.JOptionPane;

import model.App;
import model.Book;
import model.Command;

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
            app.invoke(new DisplayPopupCmd("Livro já está catalogado", JOptionPane.ERROR_MESSAGE));
            return;
        }
        app.invoke(new DisplayPopupCmd("Livro catalogado com sucesso."));
    }
    
}
