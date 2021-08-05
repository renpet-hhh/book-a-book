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
    


    /* Ponto de partida da Interface Gráfica */
    public static void main(String args[]) {
        App app = App.get(); // Criação do Model

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

        GUI gui = new GUI(app, new Home()); // conexão do Model (App) com View
        app.gui = gui;
        app.getFrame().setSize(new Dimension(1200, 700));
        app.getFrame().revalidate();
    }

    
}
