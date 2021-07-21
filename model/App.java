package model;

import javax.swing.JFrame;

import helpers.Logger;
import view.GUI;
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
        GUI gui = new GUI();
        App.get().gui = gui;
        gui.navigate(new Home()); // começamos na página inicial
    }

    
}
