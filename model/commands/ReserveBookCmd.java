package model.commands;

import java.util.Arrays;

import model.Book;
import model.Command;

public class ReserveBookCmd implements Command {

    Book book;
    public ReserveBookCmd(Book book) {
        this.book = book;
    }

    @Override
    public void execute() {
        this.book.setHowManyAvailable(this.book.getHowManyAvailable() - 1);
    }

    @Override
    public String log() {
        Object data[] = new Object[] {this.book};
        return Arrays.toString(data);
    }
    
}
