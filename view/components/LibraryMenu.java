package view.components;

import java.awt.Color;
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
import view.Margin;

public class LibraryMenu extends JMenuBar {

    final static int WRAPPERTOPMARGIN = 30;
    public final static int WRAPPERHORIZONTALMARGIN = 60;

    final static Color BGCOLOR = new Color(187, 187, 187);
    final static Color MENUCOLOR = new Color(66, 66, 69);
    final static Color MENUITEMCOLOR = MENUCOLOR;
    final static Color LABELCOLOR = new Color(220, 220, 220);
    final static Color MENUITEMLABELCOLOR = LABELCOLOR;
    final static Color WRAPPERCOLOR = new Color(124, 114, 181);

    private static JMenuItem createMenuItem(String text) {
        JMenuItem item = new JMenuItem(text);
        item.setForeground(MENUITEMLABELCOLOR);
        item.setBackground(MENUITEMCOLOR);
        return item;
    }

    private JMenu pesquisa() {
        JMenu menu = new JMenu("Pesquisa");
        JMenuItem livros = LibraryMenu.createMenuItem("Livros");
        JMenuItem usuarios = LibraryMenu.createMenuItem("Usuários");
        menu.add(livros);
        menu.add(usuarios);
        menu.setOpaque(true);
        menu.setBackground(MENUITEMCOLOR);
        return menu;
    }

    private JMenu circulacao() {
        JMenu menu = new JMenu("Circulação");
        JMenuItem cadastro = LibraryMenu.createMenuItem("Cadastro de Usuários");
        JMenuItem emprestimos = LibraryMenu.createMenuItem("Empréstimos e Devoluções");
        JMenuItem reservas = LibraryMenu.createMenuItem("Reservas");
        menu.add(cadastro);
        menu.add(emprestimos);
        menu.add(reservas);
        menu.setOpaque(true);
        menu.setBackground(MENUITEMCOLOR);
        return menu;
    }

    private JMenu catalogacao() {
        JMenu menu = new JMenu("Catalogação");
        return menu;
    }

    private JMenu administracao() {
        JMenu menu = new JMenu("Administração");
        JMenuItem relatorios = LibraryMenu.createMenuItem("Relatórios");
        JMenuItem configs = LibraryMenu.createMenuItem("Configurações");
        JMenuItem cadastroDeAdmins = LibraryMenu.createMenuItem("Cadastro de Admins");
        menu.add(relatorios);
        menu.add(configs);
        menu.add(cadastroDeAdmins);
        menu.setOpaque(true);
        menu.setBackground(MENUITEMCOLOR);
        return menu;
    }

    private JButton ajuda() {
        JButton button = new JButton("Ajuda");
        return button;
    }

    private JButton sair() {
        JButton button = new JButton("Sair");
        button.addActionListener(e -> App.get().invoke(new NavigateCmd(NavigateCmd.PAGINAINICIAL)));
        return button;
    }

    public LibraryMenu() {
        BoxLayout bLayout = new BoxLayout(this, BoxLayout.X_AXIS);
        this.setLayout(bLayout);
        JMenu pesquisaMenu = this.pesquisa();
        JMenu circulacaoMenu = this.circulacao();
        JMenu catalogMenu = this.catalogacao();
        JMenu adminMenu = this.administracao();
        JButton helpBttn = this.ajuda();
        JButton exitBttn = this.sair();
        pesquisaMenu.setForeground(LABELCOLOR);
        circulacaoMenu.setForeground(LABELCOLOR);
        catalogMenu.setForeground(LABELCOLOR);
        adminMenu.setForeground(LABELCOLOR);
        helpBttn.setForeground(LABELCOLOR);
        exitBttn.setForeground(LABELCOLOR);
        helpBttn.setBackground(MENUCOLOR);
        exitBttn.setBackground(MENUCOLOR);
        helpBttn.setBorderPainted(false);
        exitBttn.setBorderPainted(false);
        this.add(pesquisaMenu);
        this.add(circulacaoMenu);
        this.add(catalogMenu);
        this.add(adminMenu);
        this.add(helpBttn);
        this.add(Box.createHorizontalGlue());
        this.add(exitBttn);
        this.setBackground(MENUCOLOR);
    }

    @Override
    public Dimension getMaximumSize() {
        return new Dimension(Integer.MAX_VALUE, this.getPreferredSize().height);
    }

    public static JComponent withWrapper() {
        LibraryMenu menubar = new LibraryMenu();
        Box wrapper2 = Box.createHorizontalBox();
        wrapper2.add(Margin.rigidHorizontal(WRAPPERHORIZONTALMARGIN));
        wrapper2.add(menubar);
        wrapper2.add(Margin.rigidHorizontal(WRAPPERHORIZONTALMARGIN));
        Box wrapper = Box.createVerticalBox();
        wrapper.add(Margin.rigidVertical(WRAPPERTOPMARGIN));
        wrapper.add(wrapper2);
        wrapper.setMaximumSize(new Dimension(Integer.MAX_VALUE, menubar.getMaximumSize().height + WRAPPERTOPMARGIN));
        wrapper.setOpaque(true);
        wrapper.setBackground(WRAPPERCOLOR);
        return wrapper;
    }
}
