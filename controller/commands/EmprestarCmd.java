package controller.commands;

import java.util.Arrays;

import framework.Command;
import model.Book;
import model.User;
import model.UserData;

public class EmprestarCmd implements Command {

    Book book;
    User user;
    public EmprestarCmd(Book book, User user) {
        this.book = book;
        this.user = user;
    }

    @Override
    public void execute() {
        UserData data = user.getData();
        if (data.hasBookRented(this.book)) {
            throw new RuntimeException("Tentativa de emprestar um livro que já está emprestado");
        }
        if (data.getReservedBooks().contains(this.book)) {
            this.book.setHowManyReserved(this.book.getHowManyReserved() - 1);
            data.removeReserved(this.book);
        } else {
            this.book.setHowManyAvailable(this.book.getHowManyAvailable() - 1);
        }
        data.emprestar(this.book);
    }

    @Override
    public String log() {
        Object data[] = new Object[] {this.book};
        return Arrays.toString(data);
    }
    
}
