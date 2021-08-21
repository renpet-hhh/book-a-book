package controller.commands;

import framework.App;
import framework.Command;
import model.Book;
import model.Library;

public class RemoveBookCmd implements Command {

    private Book book;
    /* book é o livro a ser removido */
    public RemoveBookCmd(Book book) {
        this.book = book;
    }

    @Override
    public String log() {
        return "RemoveBookCmd: " + this.book.getIsbn();
    }

    @Override
    public void execute() {
        if (this.book == null) return;
        App app = App.get();
        Library library = app.getLibrary();
        if (!library.hasBook(this.book)) {
            app.control().invoke(new DisplayPopupCmd("Livro não está na biblioteca e portanto não pode ser removido"));
            return;
        }
        if (this.book.getHowManyRented() > 0 || this.book.getHowManyReserved() > 0) {
            app.control().invoke(new DisplayPopupCmd("Não é possível remover um livro que ainda possui pendências (reservas ou empréstimos)"));
            return;
        }
        library.removeBook(this.book);
        app.control().invoke(new DisplayPopupCmd("Livro removido com sucesso"));
    }
    
}
