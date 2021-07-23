package view.components;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import model.App;
import model.commands.NavigateCmd;
import view.components.base.MenuFactory;
import view.pages.admin.RegisterBooks;
import view.pages.admin.SearchBooks;
import view.pages.admin.SearchUsers;

public class AdminMenu extends JMenuBar {

    private JMenu pesquisa() {
        App app = App.get();
        JMenuItem livros = MenuFactory.createMenuItem("Livros", e -> app.invoke(new NavigateCmd(new SearchBooks())));
        JMenuItem usuarios = MenuFactory.createMenuItem("Usuários", e -> app.invoke(new NavigateCmd(new SearchUsers())));
        JMenu menu = MenuFactory.createMenu("Pesquisa", livros, usuarios);
        return menu;
    }

    private JMenu circulacao() {
        JMenuItem cadastro = MenuFactory.createMenuItem("Cadastro de Usuários");
        JMenuItem emprestimos = MenuFactory.createMenuItem("Empréstimos e Devoluções");
        JMenuItem reservas = MenuFactory.createMenuItem("Reservas");
        JMenu menu = MenuFactory.createMenu("Circulação", cadastro, emprestimos, reservas);
        return menu;
    }

    private JButton catalogacao() {
        App app = App.get();
        JButton menu = MenuFactory.createButton("Catalogação", e -> app.invoke(new NavigateCmd(new RegisterBooks())));
        return menu;
    }

    private JMenu administracao() {
        JMenuItem relatorios = MenuFactory.createMenuItem("Relatórios");
        JMenuItem configs = MenuFactory.createMenuItem("Configurações");
        JMenuItem cadastroDeAdmins = MenuFactory.createMenuItem("Cadastro de Admins");
        JMenu menu = MenuFactory.createMenu("Administração", relatorios, configs, cadastroDeAdmins);
        return menu;
    }

    private JButton ajuda() {
        JButton button = MenuFactory.helpButton();
        return button;
    }

    private JButton sair() {
        JButton button = MenuFactory.exitButton();
        return button;
    }

    public AdminMenu() {
        BoxLayout bLayout = new BoxLayout(this, BoxLayout.X_AXIS);
        this.setLayout(bLayout);
        JMenu pesquisaMenu = this.pesquisa();
        JMenu circulacaoMenu = this.circulacao();
        JButton catalogMenu = this.catalogacao();
        JMenu adminMenu = this.administracao();
        JButton helpBttn = this.ajuda();
        JButton exitBttn = this.sair();
        this.add(pesquisaMenu);
        this.add(circulacaoMenu);
        this.add(catalogMenu);
        this.add(adminMenu);
        this.add(helpBttn);
        this.add(Box.createHorizontalGlue());
        this.add(exitBttn);
        this.setBackground(MenuFactory.MENUCOLOR);
    }

    @Override
    public Dimension getMaximumSize() {
        return new Dimension(Integer.MAX_VALUE, this.getPreferredSize().height);
    }

    public static JComponent withWrapper() {
        return MenuFactory.wrap(new AdminMenu());
    }
}
