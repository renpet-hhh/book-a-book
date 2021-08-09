package framework;

import java.awt.Dimension;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import helpers.Logger;
import model.Admin;
import model.Book;
import model.Crypto;
import model.Library;
import model.Login;
import model.User;
import model.UserData;
import view.pages.Home;

/** Model */
public class App {
    /* Design Pattern: Singleton */
    public final static App instance = new App();
    private App() {}
    public static App get() {
        return App.instance;
    }
    /* Permite acessar o estado da aplicação */
    /* Estado de login */
    private final Login login = new Login(this);
    public Login getLogin() { return this.login; }
    /* Estado da VIEW */
    private GUI gui;
    public void navigate(Page page) { this.gui.navigate(page); }
    public Page getCurrentPage() { return this.gui.getCurrentPage(); }
    public JFrame getFrame() { return this.gui.getFrame(); }
    /* Estado da biblioteca */
    private final Library library = new Library(this);
    public Library getLibrary() { return this.library; }

    /* Conexão do Model (App) com o Controller */
    private final Controller controller = new Controller(new Logger(true));
    public Controller control() { return this.controller; }


    /** Informações de exibição controladas pelo modelo.
     * 
     * Ao atualizar o estado do modelo, as views que estão observando esse estado
     * serão notificadas. Uma string de identificação (como "UserShow") é passada
     * como argumento para que as views possam saber que tipo de alteração de estado ocorreu.
     * 
     * Os atributos userShow, bookShow, userListShow e bookListShow
     * são de propósito geral, e sua semântica depende de cada página.
     * 
     * Se uma página precisa de mais atributos (por exemplo, a página exibe mais de 1 lista de usuários),
     * essas informações adicionais podem ser passadas como argumento, pois o segundo argumento é Object ...args
     * 
     */
    private User userShow; // usuário do qual se está exibindo informação
    public User getUserShow() { return this.userShow; }
    public void setUserShow(User user, Object... args) {
        this.userShow = user;
        this.getCurrentPage().refresh("UserShow", args);
    }
    
    private Book bookShow; // livro do qual se está exibindo informação
    public Book getBookShow() { return this.bookShow; }
    public void setBookShow(Book book, Object... args) {
        this.bookShow = book;
        this.getCurrentPage().refresh("BookShow", args);
    }

    private List<Book> bookListShow; // lista de livros que está sendo exibida na tela
    public List<Book> getListListShow() { return this.bookListShow; }
    public void setBookListShow(List<Book> books, Object... args) {
        this.bookListShow = books;
        this.getCurrentPage().refresh("BookListShow", args);
    }

    private List<User> userListShow; // lista de usuários que está sendo exibida na tela
    public List<User> getUserListShow() { return this.userListShow; }
    public void setUserListShow(List<User> users, Object... args) {
        this.userListShow = users;
        this.getCurrentPage().refresh("UserListShow", args);
    }

    /* Ponto de partida da Interface Gráfica */
    public static void main(String args[]) {
        App app = App.get(); // Criação do Model
        GUI gui = new GUI(app); // conexão do Model (App) com View
        app.gui = gui;
        app.gui.navigate(new Home()); // página inicial

        // Cadastramos algumas coisas falsas para fins de teste
        // USUÁRIO FALSO
        UserData data = new UserData("Nome mockup", "Endereço mockup", "(88) 99999-9999", "example@gmail.com", "Documento mockup", LocalDate.of(1999, 12, 31));
        String passwordMockup = "aaaaaa";
        String encrypted = Crypto.crypt(passwordMockup);
        app.getLogin().addUser(new User(data, encrypted));
        // ADMIN FALSO
        UserData d = new UserData("Admin mockup", "Endereço mockup", "(88) 99992-9999", "admin@gmail.com", "Documento mockup", LocalDate.of(2000, 12, 31));
        String passwordMockup1 = "bbbbbb";
        String encrypted1 = Crypto.crypt(passwordMockup1);
        app.getLogin().addUser(new Admin(d, encrypted1));
        // LIVRO FALSO
        List<String> authors = new ArrayList<>();
        authors.add("Nome falso 1");
        authors.add("Nome falso 2");
        authors.add("Nome falso 3");
        for (int i = 0; i < 10; i++) {
            Book book = new Book("Título falso" + i, "Subtítulo falso", "Edição falsa", "21313132-123" + i, "Local falso", authors, 1997, 100);
            app.getLibrary().addBook(book);
            data.reserve(book);
        }

        app.getFrame().setSize(new Dimension(1200, 700));
        app.getFrame().revalidate();
    }

    
}
