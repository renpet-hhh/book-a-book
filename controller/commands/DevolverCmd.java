package controller.commands;

import java.util.Arrays;

import framework.App;
import framework.Command;
import model.Book;
import model.Emprestimo;
import model.Reports;
import model.User;

public class DevolverCmd implements Command {

    Book book;
    User user;
    public DevolverCmd(Book book, User user) {
        this.book = book;
        this.user = user;
    }

    @Override
    public void execute() {
        App app = App.get();
        this.book.setHowManyAvailable(this.book.getHowManyAvailable() + 1);
        Emprestimo emprestimo = this.user.getData().devolver(this.book);
        app.control().invoke(new ReportCmd<>(emprestimo, Reports.Type.DEVOLUCAO));
    }

    @Override
    public String log() {
        Object data[] = new Object[] {this.book};
        return Arrays.toString(data);
    }
    
}
