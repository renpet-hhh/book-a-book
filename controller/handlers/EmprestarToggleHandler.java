package controller.handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.commands.DevolverCmd;
import controller.commands.DisplayPopupCmd;
import controller.commands.EmprestarCmd;
import framework.App;
import framework.Controller;
import model.Book;
import model.User;

public class EmprestarToggleHandler implements ActionListener {

    private User user;
    private Book book;
    /* "Toggle" porque empresta quando o livro não está emprestado e devolve qunado o livro já está emprestado */
    public EmprestarToggleHandler(User user, Book book) {
        this.user = user;
        this.book = book;
    }
    public EmprestarToggleHandler() {}
    
    @Override
    public void actionPerformed(ActionEvent e) {
        App app = App.get();
        Controller control = app.control();
        User u = this.user;
        Book b = this.book;
        if (u == null) {
            u = app.getUserContext();
        }
        if (b == null) {
            b = app.getBookContext();
        }
        if (u.getData().hasBookRented(b)) {
            control.invoke(new DevolverCmd(b, u));
            control.invoke(new DisplayPopupCmd("Livro devolvido com sucesso"));
        } else {
            control.invoke(new EmprestarCmd(b, u));
            control.invoke(new DisplayPopupCmd("Livro emprestado com sucesso"));
        }
    }
}
