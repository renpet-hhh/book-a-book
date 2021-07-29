package view.pages;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTextField;

import model.App;
import model.commands.LoginCmd;
import model.commands.NavigateCmd;
import model.commands.TryLoginCmd;
import model.handlers.FieldObserver;
import view.components.ForgotPassword;
import view.components.Label;
import view.components.base.MenuFactory;
import view.Margin;
import view.Page;
import view.components.fixed.FixedJTextField;
import view.pages.admin.SearchBooks;

public class Home implements Page {
    
    /** Desenha a página inicial da aplicação.
     * 
     * "header" é a parte de cima da página e inclui "headerLeft" e "headerRight"
     * 
     * "headerLeft" é onde está a logo da UFC
     * 
     * "headerRight" é a parte do header que não é a logo da UFC, e portanto inclui
     * uma parte "top" e uma parte "bottom". Em "top" há os botões "Sobre" e "Ajuda",
     * colocados bem à direita por meio de cola (glue, veja BoxLayout). Em "bottom"
     * há os campos "Email" e "Senha", além do botão "Entrar".
     * 
     * "mainContent" é o conteúdo central da página, que contém a descrição da aplicação e 
     * o botão de "Acessar como convidado".
     * 
     * "mainWrapper" serve para aplicar espaçamentos em mainContent
     * 
     * "foot" é a barra no final da página que possui "Termos de Serviço" e "Política de Privacidade".
     */

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
    final static int SPACEBETWEENABOUTANDHELP = 5;
    final static int RIGHTMARGINHELP = 25;
    final static int LEFTMARGINHEADERTOP = 15;
    final static int LEFTMARGINHEADERBOTTOM = 15;
    final static int RIGHTMARGINHEADERBOTTOM = 15;

    final static Color LEFTRIGHTCOLOR = new Color(225, 225, 225);
    final static Color LOGOCOLOR = new Color(187, 187, 187);
    public final static Color HEADERRIGHTCOLOR = new Color(66, 66, 69);
    final static Color FOOTCOLOR = HEADERRIGHTCOLOR;
    final static Color LABELCOLOR = new Color(255, 255, 255);
    public final static Color FORGOTPASSWORDCOLOR = new Color(138, 185, 240);

    public static JComponent headerLeft() {
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

    public static JComponent headerRight(JFrame frame, boolean isHomePage, String username) {
        App app = App.get();
        int height = LOGOHEIGHT + 2 * LOGOMARGIN;
        Box component = Box.createVerticalBox();
        Box top = Box.createHorizontalBox();
        if (!isHomePage) {
            JButton homeButton = new JButton("Início");
            homeButton.setForeground(LABELCOLOR);
            homeButton.setBackground(HEADERRIGHTCOLOR);
            homeButton.setBorderPainted(false);
            homeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            homeButton.addActionListener(e -> app.invoke(new NavigateCmd(new Home())));
            top.add(Margin.rigidHorizontal(LEFTMARGINHEADERTOP));
            top.add(homeButton);
        }
        JButton aboutButton = new JButton("Sobre");
        aboutButton.setForeground(LABELCOLOR);
        aboutButton.setBackground(HEADERRIGHTCOLOR);
        aboutButton.setBorderPainted(false);
        aboutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        aboutButton.addActionListener(e -> app.invoke(new NavigateCmd(new About())));
        top.add(Box.createHorizontalGlue());
        top.add(aboutButton);
        top.add(Margin.rigidHorizontal(SPACEBETWEENABOUTANDHELP));
        top.add(new Label("Ajuda", LABELCOLOR));
        top.add(Margin.rigidHorizontal(RIGHTMARGINHELP));
        top.setMaximumSize(new Dimension(Integer.MAX_VALUE, height / 2 - 2 * HEADERTOPMARGIN));
        Box bottom = Box.createHorizontalBox();
        if (isHomePage) {
            bottom.add(Box.createHorizontalGlue());
            JButton forgotPasswordBttn = ForgotPassword.getButton(frame);
            bottom.add(Margin.horizontal(forgotPasswordBttn, FORGOTPASSWORDMARGIN));
            // o texto padrão desses campos está assim só por enquanto, para facilitar os testes
            JTextField emailField = new FixedJTextField(USERNAMEFIELDWIDTH, "example@gmail.com");
            JTextField passwordField = new FixedJTextField(PASSWORDFIELDWIDTH, "aaaaaa");
            JButton enter = new JButton("Entrar");
            enter.setForeground(LABELCOLOR);
            enter.setBorderPainted(false);
            enter.setBackground(HEADERRIGHTCOLOR);
            enter.setCursor(new Cursor(Cursor.HAND_CURSOR));
            List<JTextField> loginFields = new ArrayList<>();
            loginFields.add(emailField);
            loginFields.add(passwordField);
            // vamos observar os campos Email e Senha
            // temos acesso à eles quando botão Entrar for pressionado
            // o Obsever a seguir é um handler do botão Entrar
            FieldObserver observer = new FieldObserver(loginFields, f -> {
                String email = f.get(0).getText();
                String password = f.get(1).getText();
                // quando o botão Entrar for pressionado, tentamos fazer login
                app.invoke(new TryLoginCmd(email, password));
            });
            enter.addActionListener(observer);
            bottom.add(emailField);
            bottom.add(Margin.rigidHorizontal(INPUTFIELDSMARGINBETWEEN));
            bottom.add(passwordField);
            bottom.add(Margin.rigidHorizontal(ENTERLEFTMARGIN));
            bottom.add(enter);
            bottom.add(Margin.rigidHorizontal(ENTERRIGHTMARGIN));
            bottom.setMaximumSize(new Dimension(Integer.MAX_VALUE, height / 2 - 2 * HEADERBOTTOMMARGIN));
        } else {
            if (username.length() > 0) {
                Label welcomeLabel = new Label("Bem vindo, " + username, LABELCOLOR);
                JButton logoutBttn = MenuFactory.exitButton();
                bottom.add(Margin.rigidHorizontal(LEFTMARGINHEADERBOTTOM));
                bottom.add(welcomeLabel);
                bottom.add(Box.createHorizontalGlue());
                bottom.add(logoutBttn);
                bottom.add(Margin.rigidHorizontal(RIGHTMARGINHEADERBOTTOM));
            }
        }
        component.add(Margin.vertical(top, HEADERTOPMARGIN));
        component.add(Margin.vertical(bottom, HEADERBOTTOMMARGIN));
        component.add(Box.createVerticalGlue());
        component.setAlignmentY(JComponent.CENTER_ALIGNMENT);
        return component;
    }


    public static JComponent header(JFrame frame, boolean isHomePage, String username) {
        Box component = Box.createHorizontalBox();
        JComponent left = Home.headerLeft();
        JComponent right = Home.headerRight(frame, isHomePage, username);
        component.add(left);
        component.add(right);
        component.setMinimumSize(new Dimension(left.getWidth(), 0));
        component.setMaximumSize(new Dimension(Integer.MAX_VALUE, left.getPreferredSize().height));
        component.setOpaque(true);
        component.setBackground(HEADERRIGHTCOLOR);
        return component;
    }

    public static JComponent mainContent() {
        JComponent component = Box.createVerticalBox();
        Label mainText = new Label("Olá olá lorem ipsum pssum lorem ");
        JButton enterAsGuestBttn = new JButton("Acessar a biblioteca sem conta");
        /* o botão deve levar até Pesquisa para Guest, mas enquanto não temos sistema de login implementado,
        vou fazer com que o botão para uma página de Admin */
        enterAsGuestBttn.addActionListener(e -> App.get().invoke(new LoginCmd()));
        component.add(Margin.rigidVertical(20));
        component.add(mainText);
        component.add(Margin.rigidVertical(20));
        component.add(enterAsGuestBttn);
        component.add(Margin.rigidVertical(100));
        return component;
    }

    public static JComponent mainWrapper() {
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
        component.add(Home.mainContent());
        component.add(Margin.rigidHorizontal(15));
        component.add(Box.createHorizontalGlue());
        component.add(right);
        return component;
    }

    public static JComponent foot() {
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
        JComponent header = Home.header(frame, true, "");
        JComponent foot = Home.foot();
        JComponent mainWrapper = Home.mainWrapper();
        frame.add(header);
        frame.add(mainWrapper);
        frame.add(foot);
    }

}
