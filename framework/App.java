package framework;

import java.awt.Dimension;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import controller.RefreshID;
import controller.commands.RegisterBookCmd;
import controller.commands.RegisterUserCmd;
import controller.commands.ReserveBookCmd;
import helpers.Logger;
import model.Book;
import model.Crypto;
import model.Library;
import model.Login;
import model.Reports;
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

    private boolean ignorePopup = false;
    public boolean shouldIgnorePopup() { return this.ignorePopup; }
    public void shouldIgnorePopup(boolean should) { this.ignorePopup = should; }


    /** Informações de exibição controladas pelo modelo.
     * 
     * Ao atualizar o estado do modelo, as views que estão observando esse estado
     * serão notificadas. Uma string de identificação (como RefreshID.UserContext) é passada
     * como argumento para que as views possam saber que tipo de alteração de estado ocorreu.
     * 
     * Os atributos userContext, bookContext, userListContext e bookListContext
     * são de propósito geral, e sua semântica depende de cada página.
     * 
     * Se uma página precisa de mais atributos (por exemplo, a página exibe mais de 1 lista de usuários),
     * essas informações adicionais podem ser passadas como argumento, pois o segundo argumento é Object ...args
     * 
     */
    private User userContext; // usuário do qual se está exibindo informação atualmente
    public User getUserContext() { return this.userContext; }
    public void setUserContext(User user, Object... args) {
        this.userContext = user;
        this.getCurrentPage().refresh(RefreshID.UserContext, args);
    }
    
    private Book bookContext; // livro do qual se está exibindo informação atualmente
    public Book getBookContext() { return this.bookContext; }
    public void setBookContext(Book book, Object... args) {
        this.bookContext = book;
        this.getCurrentPage().refresh(RefreshID.BookContext, args);
    }

    private List<Book> bookListContext; // lista de livros que está sendo exibida na tela
    public List<Book> getBookListContext() { return this.bookListContext; }
    public void setBookListContext(List<Book> books, Object... args) {
        this.bookListContext = books;
        this.getCurrentPage().refresh(RefreshID.BookListContext, args);
    }

    private List<User> userListContext; // lista de usuários que está sendo exibida na tela
    public List<User> getUserListContext() { return this.userListContext; }
    public void setUserListContext(List<User> users, Object... args) {
        this.userListContext = users;
        this.getCurrentPage().refresh(RefreshID.UserListContext, args);
    }

    /* Relatórios */
    private Reports reports = new Reports();
    public Reports getReports() { return this.reports; }

    /* Ponto de partida da Interface Gráfica */
    public static void main(String args[]) {
        App app = App.get(); // Criação do Model
        GUI gui = new GUI(app); // conexão do Model (App) com View
        app.gui = gui;
        app.gui.navigate(new Home()); // página inicial

        // Enquanto povoamos com coisas falsas, desativo os popups
        app.shouldIgnorePopup(true);

        // Cadastramos algumas coisas falsas para fins de teste
        // USUÁRIO FALSO
        UserData data = new UserData("Nome mockup", "Endereço mockup", "(88) 99999-9999", "example@gmail.com", "Documento mockup", LocalDate.of(1999, 12, 31));
        String passwordMockup = "aaaaaa";
        String encrypted = Crypto.crypt(passwordMockup);
        User fakeNormalUser = new User(data, encrypted);
        app.control().invoke(new RegisterUserCmd(data, passwordMockup, false));
        // ADMIN FALSO
        UserData d = new UserData("Admin mockup", "Endereço mockup", "(88) 99992-9999", "admin@gmail.com", "Documento mockup", LocalDate.of(2000, 12, 31));
        String passwordMockup1 = "bbbbbb";
        User fakeAdmin = new User(d, Crypto.crypt(passwordMockup1));
        app.control().invoke(new RegisterUserCmd(d, passwordMockup1, true));
        // LIVRO FALSO
        List<String> authors = new ArrayList<>();
        authors.add("Nome falso 1");
        authors.add("Nome falso 2");
        authors.add("Nome falso 3");
        for (int i = 0; i < 10; i++) {
            Book book = new Book("Título falso" + i, "Subtítulo falso", "Edição falsa", "21313132-123" + i, "Local falso", authors, 1997, 100);
            app.control().invoke(new RegisterBookCmd(book, fakeAdmin));
            app.control().invoke(new ReserveBookCmd(book, fakeNormalUser));
        }

        // Já povoamos! Reativo os popups
        app.shouldIgnorePopup(false);

        app.getFrame().setSize(new Dimension(1200, 700));
        app.getFrame().revalidate();
    }

    
}
