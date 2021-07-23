package view.components;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import view.components.base.MenuFactory;

public class AdminMenu extends JMenuBar {

    private JMenu pesquisa() {
        JMenuItem livros = MenuFactory.createMenuItem("Livros");
        JMenuItem usuarios = MenuFactory.createMenuItem("Usuários");
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

    private JMenu catalogacao() {
        JMenu menu = MenuFactory.createMenu("Catalogação");
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
        JMenu catalogMenu = this.catalogacao();
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
