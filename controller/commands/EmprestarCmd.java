package controller.commands;

import java.util.Arrays;

import framework.App;
import framework.Command;
import model.Book;
import model.Emprestimo;
import model.Reports;
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
        App app = App.get();
        UserData data = user.getData();
        if (data.hasBookRented(this.book)) {
            throw new RuntimeException("Tentativa de emprestar um livro que já está emprestado");
        }
        if (data.getEmprestimos().size() >= Emprestimo.getMaxQuantity()) {
            throw new RuntimeException("Tentativa de ultrapassar o máximo de empréstimos permitidos");
        }
        if (data.getReservedBooks().contains(this.book)) {
            this.book.setHowManyReserved(this.book.getHowManyReserved() - 1);
            data.removeReserved(this.book);
        } else {
            this.book.setHowManyAvailable(this.book.getHowManyAvailable() - 1);
        }
        data.emprestar(this.book);
        Emprestimo emprestimo = data.getEmprestimos().get(data.getEmprestimos().size() - 1);
        app.control().invoke(new ReportCmd<>(emprestimo, Reports.Type.EMPRESTIMO));
    }

    @Override
    public String log() {
        Object data[] = new Object[] {this.book};
        return Arrays.toString(data);
    }
    
}
