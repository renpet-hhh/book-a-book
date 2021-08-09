package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import framework.App;
import framework.Command;
import controller.commands.RefreshCmd;
import controller.commands.ReserveBookCmd;

public class Library {

    private Map<String, Set<Book>> books;
    private Map<String, Set<Author>> authors;
    private Map<String, Book> booksByISBN;
    private App app;

    public Library(App app) {
        this.app = app;
        this.books = new HashMap<String, Set<Book>>();
        this.authors = new HashMap<String, Set<Author>>();
        this.booksByISBN = new HashMap<String, Book>();
    }
    /* Por enquanto, otimizado para encontrar por Título e por Nome de Autor, mas pode ser alterado */
    public Set<Book> findByTitle(String title) {
        return this.books.getOrDefault(title, new HashSet<Book>());
    }
    public Set<Author> findAuthor(String name) {
        return this.authors.getOrDefault(name, new HashSet<Author>());
    }
    public Set<Book> findByAuthor(String name) {
        Set<Author> authors = this.findAuthor(name);
        Set<Book> books = new HashSet<Book>();
        for (Author a : authors) {
            books.addAll(a.getBooks());
        }
        return books;
    }
    public Book findByISBN(String isbn) {
        return this.booksByISBN.get(isbn);
    }

    public void reserveBook(Book book, User user) {
        Command reserveCmd = new ReserveBookCmd(book, user);
        this.app.control().invoke(reserveCmd);
    }
    /** Adiciona um livro à biblioteca e retorna false se o set não foi alterado. */
    public boolean addBook(Book book) {
        String title = book.getTitle();
        this.booksByISBN.put(book.getIsbn(), book);
        Set<Book> set = this.books.get(title);
        if (set == null) {
            set = new HashSet<>();
            this.books.put(title, set);
        }
        boolean b = set.add(book);
        // notificamos os views que estão observando a mudança de estado "LibraryAddBook"
        this.app.control().invoke(new RefreshCmd("LibraryAddBook"));
        return b;
    }
    /** Retorna uma coleção de livros que satisfazem os filtros.
     * Um filtro nulo é satisfeito por qualquer livro.
     * 
     * O filtro titleFilter só é satisfeito caso o nome do livro contenha
     * titleFilter (ou seja, titleFilter é substring do nome do usuário).
     * 
     * O filtro authorFilter é definido da mesma forma.
     */
    public List<Book> getFilteredBooks(String titleFilter, String authorFilter) {
        List<Book> b = new ArrayList<>();
        for (Set<Book> bookset : this.books.values()) {
            for (Book book : bookset) {
                List<String> authorNames = book.getAuthors();
                boolean satisfiesTitleFilter = titleFilter == null || book.getTitle().contains(titleFilter);
                boolean satisfiesAuthorFilter = false;
                for (String authorName: authorNames) {
                    if (authorFilter == null || authorName.contains(authorFilter)) {
                        satisfiesAuthorFilter = true;
                        break;
                    }
                }
                if (satisfiesTitleFilter && satisfiesAuthorFilter) {
                    b.addAll(bookset);
                }
            }
        }
        return b;
    }
}
