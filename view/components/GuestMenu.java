package view.components;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JMenuBar;

import view.components.base.MenuFactory;

public class GuestMenu extends JMenuBar {

    /** Define como construir o menu de um convidado (Guest) */

    private Button pesquisa() {
        Button button = MenuFactory.createButton("Pesquisa Bibliográfica");
        return button;
    }

    private Button ajuda() {
        Button button = MenuFactory.helpButton();
        return button;
    }

    private Button sair() {
        Button button = MenuFactory.exitButton();
        return button;
    }

    public GuestMenu() {
        BoxLayout bLayout = new BoxLayout(this, BoxLayout.X_AXIS);
        this.setLayout(bLayout);
        Button searchBttn = this.pesquisa();
        Button helpBttn = this.ajuda();
        Button exitBttn = this.sair();
        this.add(searchBttn);
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
        return MenuFactory.wrap(new GuestMenu());
    }
}
