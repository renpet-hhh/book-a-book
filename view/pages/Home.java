package view.pages;

import java.awt.Color;
import java.awt.Image;
import java.awt.Dimension;
// import java.awt.image.BufferedImage;
// import java.io.File;

// import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTextField;

import model.App;
import model.commands.NavigateCmd;
import view.components.ForgotPassword;
import view.components.Label;
import view.Margin;
import view.Page;
import view.components.fixed.FixedJTextField;
import view.pages.admin.SearchBooks;

public class Home implements Page {

    public final static String TITLE = "Página Inicial";
    @Override
    public String getTitle() { return TITLE; }

    final static int LOGOHEIGHT = 100;
    final static int LOGOMARGIN = 20;
    final static int HEADERTOPMARGIN = 20;
    final static int HEADERBOTTOMMARGIN = 20;
    final static int USERNAMEFIELDWIDTH = 140;
    final static int PASSWORDFIELDWIDTH = 140;
    final static int FORGOTPASSWORDMARGIN = 10;
    final static int ENTERLEFTMARGIN = 10;
    final static int ENTERRIGHTMARGIN = 100;
    final static int INPUTFIELDSMARGINBETWEEN = 2;
    final static int FOOTMARGIN = 10;
    final static int FOOTSPACEBETWEEN = 20;
    final static int MAINCONTENTLEFTRIGHTWIDTH = 191;

    final static Color LEFTRIGHTCOLOR = new Color(225, 225, 225);
    final static Color LOGOCOLOR = new Color(187, 187, 187);
    public final static Color HEADERRIGHTCOLOR = new Color(66, 66, 69);
    final static Color FOOTCOLOR = HEADERRIGHTCOLOR;
    final static Color LABELCOLOR = new Color(255, 255, 255);
    public final static Color FORGOTPASSWORDCOLOR = new Color(138, 185, 240);

    public JComponent headerLeft() {
        ImageIcon originalImg = new ImageIcon("./images/logoufc.png");
        Image image = originalImg.getImage(); // transform it 
        Image newimg = image.getScaledInstance(-1, LOGOHEIGHT,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        ImageIcon img = new ImageIcon(newimg);  // transform it back
        Label jlabel = new Label(img);
        Box wrapper = Margin.vertical(Margin.horizontal(jlabel, LOGOMARGIN), LOGOMARGIN);
        wrapper.setOpaque(true);
        wrapper.setBackground(LOGOCOLOR);
        return wrapper;
    }

    public JComponent headerRight(JFrame frame) {
        int height = LOGOHEIGHT + 2 * LOGOMARGIN;
        Box component = Box.createVerticalBox();
        Box top = Box.createHorizontalBox();
        top.add(Box.createHorizontalGlue());
        top.add(new Label("Sobre", LABELCOLOR));
        top.add(Margin.rigidHorizontal(5));
        top.add(new Label("Ajuda", LABELCOLOR));
        top.add(Margin.rigidHorizontal(25));
        top.setMaximumSize(new Dimension(Integer.MAX_VALUE, height / 2 - 2 * HEADERTOPMARGIN));
        Box bottom = Box.createHorizontalBox();
        bottom.add(Box.createHorizontalGlue());
        JButton forgotPasswordBttn = ForgotPassword.getButton(frame);
        bottom.add(Margin.horizontal(forgotPasswordBttn, FORGOTPASSWORDMARGIN));
        JTextField username = new FixedJTextField(USERNAMEFIELDWIDTH, "Nome de usuário");
        bottom.add(username);
        bottom.add(Margin.rigidHorizontal(INPUTFIELDSMARGINBETWEEN));
        bottom.add(new FixedJTextField(PASSWORDFIELDWIDTH, "Senha"));
        bottom.add(Margin.rigidHorizontal(ENTERLEFTMARGIN));
        bottom.add(new Label("Entrar", LABELCOLOR));
        bottom.add(Margin.rigidHorizontal(ENTERRIGHTMARGIN));
        bottom.setMaximumSize(new Dimension(Integer.MAX_VALUE, height / 2 - 2 * HEADERBOTTOMMARGIN));
        component.add(Margin.vertical(top, HEADERTOPMARGIN));
        component.add(Margin.vertical(bottom, HEADERBOTTOMMARGIN));
        component.add(Box.createVerticalGlue());
        component.setAlignmentY(JComponent.CENTER_ALIGNMENT);
        return component;
    }


    public JComponent header(JFrame frame) {
        Box component = Box.createHorizontalBox();
        JComponent left = this.headerLeft();
        JComponent right = this.headerRight(frame);
        component.add(left);
        component.add(right);
        component.setMinimumSize(new Dimension(left.getWidth(), 0));
        component.setMaximumSize(new Dimension(Integer.MAX_VALUE, left.getPreferredSize().height));
        component.setOpaque(true);
        component.setBackground(HEADERRIGHTCOLOR);
        return component;
    }

    public JComponent mainContent() {
        JComponent component = Box.createVerticalBox();
        Label mainText = new Label("Olá olá lorem ipsum pssum lorem ");
        JButton enterAsGuestBttn = new JButton("Acessar a biblioteca sem conta");
        /* o botão deve levar até Pesquisa para Guest, mas enquanto não temos sistema de login implementado,
        vou fazer com que o botão para uma página de Admin */
        enterAsGuestBttn.addActionListener(e -> App.get().invoke(new NavigateCmd(new SearchBooks())));
        component.add(Margin.rigidVertical(20));
        component.add(mainText);
        component.add(Margin.rigidVertical(20));
        component.add(enterAsGuestBttn);
        component.add(Margin.rigidVertical(100));
        return component;
    }

    public JComponent mainWrapper() {
        JComponent component = Box.createHorizontalBox();
        Box.Filler left = new Box.Filler(new Dimension(MAINCONTENTLEFTRIGHTWIDTH, 0), new Dimension(MAINCONTENTLEFTRIGHTWIDTH, 0), new Dimension(MAINCONTENTLEFTRIGHTWIDTH, Integer.MAX_VALUE));
        Box.Filler right = new Box.Filler(new Dimension(MAINCONTENTLEFTRIGHTWIDTH, 0), new Dimension(MAINCONTENTLEFTRIGHTWIDTH, 0), new Dimension(MAINCONTENTLEFTRIGHTWIDTH, Integer.MAX_VALUE));
        left.setOpaque(true);
        left.setBackground(LEFTRIGHTCOLOR); 
        right.setOpaque(true);
        right.setBackground(LEFTRIGHTCOLOR);
        component.add(left);
        component.add(Box.createHorizontalGlue());
        component.add(Margin.rigidHorizontal(15));
        component.add(this.mainContent());
        component.add(Margin.rigidHorizontal(15));
        component.add(Box.createHorizontalGlue());
        component.add(right);
        return component;
    }

    public JComponent foot() {
        Box component = Box.createHorizontalBox();
        Label terms = new Label("Termos de Serviço", LABELCOLOR);
        Label privacy = new Label("Política de Privacidade", LABELCOLOR);
        component.add(terms);
        component.add(Margin.rigidHorizontal(FOOTSPACEBETWEEN));
        component.add(privacy);
        component.add(Box.createHorizontalGlue());
        Box wrapper = Margin.horizontal(Margin.vertical(component, FOOTMARGIN), FOOTMARGIN);
        wrapper.setOpaque(true);
        wrapper.setBackground(FOOTCOLOR);
        return wrapper;
    }

    @Override
    public void paint(JFrame frame) {
        BoxLayout bLayout = new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS);
        frame.setLayout(bLayout);
        frame.setTitle("Página inicial");
        JComponent header = this.header(frame);
        JComponent foot = this.foot();
        JComponent mainWrapper = this.mainWrapper();
        frame.add(header);
        frame.add(mainWrapper);
        frame.add(foot);
    }

}
