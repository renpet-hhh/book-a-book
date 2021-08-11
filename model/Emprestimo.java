package model;

import java.time.LocalDateTime;

public class Emprestimo {
    
    private Book book;
    private LocalDateTime rentDate;
    private User user;
    
    private static int PRAZO = 7; // prazo em dias

    /**
     * Associa o empréstimo de um livro a um usuário
     * @param book - livro a ser emprestado
     * @param user - usuário associado a esse empréstimo
     */
    public Emprestimo(Book book, User user) {
        this.book = book;
        this.rentDate = LocalDateTime.now();
        this.user = user;
    }

    /* Devolve o livro à biblioteca */
    public void cancel() {
        this.user.getData().devolver(this.book);
    }
    public void resetTime() {
        this.rentDate = LocalDateTime.now();
    }
    public boolean isExpired() {
        return LocalDateTime.now().isAfter(this.getExpireDate());
    }
    public Book getBook() {
        return this.book;
    }
    public User getUser() {
        return this.user;
    }
    public LocalDateTime getExpireDate() {
        return this.rentDate.plusDays(PRAZO);
    }

}
