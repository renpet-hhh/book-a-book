package controller.handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.commands.ReserveBookCmd;
import framework.App;
import model.Book;
import model.User;

public class ReserveHandler implements ActionListener {

    private User user;
    private Book book;
    public ReserveHandler(User user, Book book) {
        this.user = user;
        this.book = book;
    }
    public ReserveHandler() {
        App app = App.get();
        this.user = app.getUserContext();
        this.book = app.getBookContext();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        App.get().control().invoke(new ReserveBookCmd(this.book, this.user));
    }
}
