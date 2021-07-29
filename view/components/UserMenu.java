package view.components;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JMenuBar;

import view.components.base.MenuFactory;

public class UserMenu extends JMenuBar {

    /** Define como construir o menu de um usuário (User) */

    private JButton pesquisa() {
        JButton button = MenuFactory.createButton("Pesquisa Bibliográfica");
        return button;
    }

    private JButton perfil() {
        JButton button = MenuFactory.createButton("Perfil");
        return button;
    }

    private JButton ajuda() {
        JButton button = MenuFactory.helpButton();
        return button;
    }

    private JButton sair() {
        JButton button = MenuFactory.exitButton();
        return button;
    }

    public UserMenu() {
        BoxLayout bLayout = new BoxLayout(this, BoxLayout.X_AXIS);
        this.setLayout(bLayout);
        JButton searchBttn = this.pesquisa();
        JButton profileBttn = this.perfil();
        JButton helpBttn = this.ajuda();
        JButton exitBttn = this.sair();
        this.add(searchBttn);
        this.add(profileBttn);
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
        return MenuFactory.wrap(new UserMenu());
    }
}
