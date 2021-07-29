package model;

import java.awt.Dimension;
import java.time.LocalDate;

import javax.swing.JFrame;

import helpers.Logger;
import view.GUI;
import view.Page;
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
    private final Login login = new Login();
    public Login getLogin() { return this.login; }
    /* Estado da VIEW */
    private GUI gui;
    public void navigate(Page page) { this.gui.navigate(page); }
    public Page getCurrentPage() { return this.gui.getCurrentPage(); }
    public JFrame getFrame() { return this.gui.getFrame(); }
    /* Estado da biblioteca */
    private final Library library = new Library();
    public Library getLibrary() { return this.library; }

    /* Logger */
    private final Logger logger = new Logger(true);
    

    /* Design Pattern: Invoker | https://pt.wikipedia.org/wiki/Command */
    /* Invoca comandos */
    public void invoke(Command cmd) {
        logger.log(cmd);
        cmd.execute();
    }

    /* Ponto de partida da Interface Gráfica */
    public static void main(String args[]) {
        App app = App.get();
        GUI gui = new GUI();
        app.gui = gui;
        // USUÁRIO FALSO
        UserData data = new UserData();
        data.address = "Endereço mockup";
        data.birthdate = LocalDate.of(1999, 12, 31);
        data.contact = "(88) 99999-9999";
        data.document = "Documento mockup";
        data.email = "example@gmail.com";
        data.name = "Nome mockup";
        String passwordMockup = "aaaaaa";
        String encrypted = Crypto.crypt(passwordMockup);
        app.getLogin().addUser(new User(data, encrypted));
        // ADMIN FALSO
        UserData d = new UserData();
        d.address = "Endereço mockup";
        d.birthdate = LocalDate.of(1999, 12, 31);
        d.contact = "(88) 99999-9999";
        d.document = "Documento mockup";
        d.email = "admin@gmail.com";
        d.name = "Admin mockup";
        String passwordMockup1 = "bbbbbb";
        String encrypted1 = Crypto.crypt(passwordMockup1);
        app.getLogin().addUser(new Admin(d, encrypted1));
        gui.navigate(new Home()); // começamos na página inicial
        app.getFrame().setSize(new Dimension(1200, 700));
        app.getFrame().revalidate();
    }

    
}
