package controller.commands;

import java.util.Arrays;

import framework.Command;
import model.Book;
import model.User;

public class ReserveBookCmd implements Command {

    Book book;
    User user;
    public ReserveBookCmd(Book book, User user) {
        this.book = book;
        this.user = user;
    }

    @Override
    public void execute() {
        this.book.setHowManyAvailable(this.book.getHowManyAvailable() - 1);
        this.book.setHowManyReserved(this.book.getHowManyReserved() + 1);
        this.user.getData().reserve(this.book);
    }

    @Override
    public String log() {
        Object data[] = new Object[] {this.book};
        return Arrays.toString(data);
    }
    
}
