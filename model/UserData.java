package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;

import controller.RefreshID;
import controller.commands.DisplayPopupCmd;
import controller.commands.RefreshCmd;
import framework.App;

public class UserData {
    /* Struct de dados. Não define comportamento, então todos os campos são públicos */
    private String name, address, contact, email, document;
    private LocalDate birthdate;
    private int matricula = -1; // sinaliza que o programa ainda não atribuiu matrícula a esse objeto
    private List<Book> reservedBooks = new ArrayList<>(); // livros reservados
    private List<Emprestimo> emprestimos = new ArrayList<>(); // livros emprestados
    User user; // a quem esses dados se referem

    public UserData(String name, String address, String contact, String email, String document, LocalDate birthdate) {
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.email = email;
        this.document = document;
        this.birthdate = birthdate;
    }
    public void owner(User user) {
        this.user = user;
    }

    public String getName() { return name; }
    public String getAddress() { return address; }
    public LocalDate getBirthdate() { return birthdate; }
    public String getContact() { return contact; }
    public String getDocument() { return document; }
    public String getEmail() { return email; }
    public int getMatricula() { return matricula; }
    public void setMatricula(int matricula) { this.matricula = matricula; }
    public void reserve(Book book) {
        this.reservedBooks.add(book);
        App.get().control().invoke(new RefreshCmd(RefreshID.UserReserveBook, book)); // notifica views observadoras
    }
    public void removeReserved(Book book) {
        this.reservedBooks.remove(book);
        App.get().control().invoke(new RefreshCmd(RefreshID.UserUnreserveBook, book)); // notifica views observadoras
    }
    public List<Book> getReservedBooks() { return Collections.unmodifiableList(this.reservedBooks); }


    public void emprestar(Book book) {
        Emprestimo rent = new Emprestimo(book, this.user);
        this.emprestimos.add(rent);
        App.get().control().invoke(new RefreshCmd(RefreshID.UserEmprestar, book)); // notifica views observadoras
    }
    public void devolver(Book book) {
        App app = App.get();
        Emprestimo toRemove = null;
        for (Emprestimo e: this.emprestimos) {
            if (e.getBook() == book) {
                toRemove = e;
            }
        }
        if (toRemove == null) {
            app.control().invoke(new DisplayPopupCmd("Tentativa falha de devolver livro. Usuário não possui tal livro emprestado", JOptionPane.ERROR_MESSAGE));
            return;
        }
        this.emprestimos.remove(toRemove);
        app.control().invoke(new RefreshCmd(RefreshID.UserDevolver, book)); // notifica views observadoras
    }
    public List<Emprestimo> getEmprestimos() { return Collections.unmodifiableList(this.emprestimos); }

    public boolean hasBookRented(Book book) {
        for (Emprestimo e: this.getEmprestimos()) {
            if (e.getBook() == book) {
                return true;
            }
        }
        return false;
    }
    public boolean hasBookReserved(Book book) {
        return this.getReservedBooks().contains(book);
    }

    
}