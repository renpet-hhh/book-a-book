package view.pages;

import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JTextField;

import framework.App;
import framework.Page;
import helpers.Margin;
import controller.commands.DisplayPopupCmd;
import controller.commands.NavigateCmd;
import controller.commands.TryLoginCmd;
import controller.handlers.FieldHandler;
import view.components.Button;
import view.components.ForgotPassword;
import view.components.Label;
import view.components.base.MenuFactory;
import view.components.fixed.FixedJTextField;
import view.pages.guest.SearchBooksGuest;
import view.pages.user.Profile;
import view.pages.user.SearchBooksUser;

public class Home extends Page {
    
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
    final static int SPACEBETWEENTOPHEADERBUTTONS = 5;
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
        Label label = new Label(img);
        Box wrapper = Box.createVerticalBox();
        wrapper.add(Box.createVerticalGlue());
        wrapper.add(Margin.rigidVertical(LOGOMARGIN));
        wrapper.add(Margin.horizontal(label, LOGOMARGIN));
        wrapper.add(Margin.rigidVertical(LOGOMARGIN));
        wrapper.add(Box.createVerticalGlue());
        wrapper.setOpaque(true);
        wrapper.setBackground(LOGOCOLOR);
        return wrapper;
    }
    public static int getHeaderLeftWidth() {
        return headerLeft().getWidth();
    }

    /**
     * Constrói o header direito.
     * 
     * - Para construir o header direito da página inicial, especifique isHomePage == true e username == ""
     * - Para construir o header direito da página de um user, especifique isHomePage == false e username do user
     * - Para construir o header direito da página de um guest, especifique isHomePage == false e username == ""
     */
    public static JComponent headerRight(App app, JComponent pane, boolean isHomePage, String username) {
        int height = LOGOHEIGHT + 2 * LOGOMARGIN;
        Box component = Box.createVerticalBox();
        Box top = Box.createHorizontalBox();
        if (!isHomePage) {
            ActionListener homeHandler = e -> app.control().invoke(new NavigateCmd(new Home()));
            Button homeButton = new Button("Início", homeHandler, LABELCOLOR, HEADERRIGHTCOLOR);
            top.add(Margin.rigidHorizontal(LEFTMARGINHEADERTOP));
            top.add(homeButton);
        }
        ActionListener aboutHandler = e -> app.control().invoke(new NavigateCmd(new About()));
        Button aboutButton = new Button("Sobre", aboutHandler, LABELCOLOR, HEADERRIGHTCOLOR);
        //novo
        ActionListener helpHandler = e -> app.control().invoke(new NavigateCmd(new Help()));
        Button helpButton = new Button("Ajuda", helpHandler, LABELCOLOR, HEADERRIGHTCOLOR);
        //novo
        top.add(Box.createHorizontalGlue());
        if (!isHomePage && username.length() > 0) {
            ActionListener libraryHandler = e -> app.control().invoke(new NavigateCmd(new SearchBooksUser()));
            Button libraryButton = new Button("Acessar a Biblioteca", libraryHandler, LABELCOLOR, HEADERRIGHTCOLOR);
            top.add(libraryButton);
            top.add(Margin.rigidHorizontal(SPACEBETWEENTOPHEADERBUTTONS));
        }
        top.add(aboutButton);
        top.add(Margin.rigidHorizontal(SPACEBETWEENTOPHEADERBUTTONS));
        top.add(helpButton);
        top.add(Margin.rigidHorizontal(RIGHTMARGINHELP));
        top.setMaximumSize(new Dimension(Integer.MAX_VALUE, height / 2 - 2 * HEADERTOPMARGIN));
        Box bottom = Box.createHorizontalBox();
        if (isHomePage) {
            bottom.add(Box.createHorizontalGlue());
            Button forgotPasswordBttn = ForgotPassword.getButton(pane);
            bottom.add(Margin.horizontal(forgotPasswordBttn, FORGOTPASSWORDMARGIN));
            // o texto padrão desses campos está assim só por enquanto, para facilitar os testes
            JTextField emailField = new FixedJTextField(USERNAMEFIELDWIDTH, "Matrícula");
            JTextField passwordField = new FixedJTextField(PASSWORDFIELDWIDTH, "Senha");
            Button enter = new Button("Entrar", null, LABELCOLOR, HEADERRIGHTCOLOR);
            List<JTextField> loginFields = new ArrayList<>();
            loginFields.add(emailField);
            loginFields.add(passwordField);
            // vamos observar os campos Email e Senha
            // temos acesso à eles quando botão Entrar for pressionado
            // o Obsever a seguir é um handler do botão Entrar
            FieldHandler observer = new FieldHandler(loginFields, f -> {
                String matricula = f.get(0).getText();
                String password = f.get(1).getText();
                int matriculaInt = -1;
                try {
                    matriculaInt = Integer.parseInt(matricula);
                } catch (NumberFormatException e) {
                    app.control().invoke(new DisplayPopupCmd("Matrícula deve ser um número. Recebido: " + matricula));
                    return;
                };
                // quando o botão Entrar for pressionado, tentamos fazer login
                app.control().invoke(new TryLoginCmd(matriculaInt, password));
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
                Label welcomeLabel = new Label("Bem vindo, " + username, Profile.HEADERWELCOMECOLOR, null, Profile.WELCOMEFONT);
                Button logoutBttn = MenuFactory.exitButton(app);
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


    /**
     * Constrói o header.
     * 
     * - Para construir o header direito da página inicial, especifique isHomePage == true e username == ""
     * - Para construir o header direito da página de um user, especifique isHomePage == false e username do user
     * - Para construir o header direito da página de um guest, especifique isHomePage == false e username == ""
     */
    public static JComponent header(App app, JComponent pane, boolean isHomePage, String username) {
        Box component = Box.createHorizontalBox();
        JComponent left = Home.headerLeft();
        JComponent right = Home.headerRight(app, pane, isHomePage, username);
        component.add(left);
        component.add(right);
        component.setMinimumSize(new Dimension(left.getWidth(), 0));
        component.setMaximumSize(new Dimension(Integer.MAX_VALUE, left.getPreferredSize().height));
        component.setOpaque(true);
        component.setBackground(HEADERRIGHTCOLOR);
        component.setMinimumSize(new Dimension(component.getMinimumSize().width, left.getMinimumSize().height));
        return component;
    }

    public static JComponent mainContent(App app) {
        JComponent component = Box.createVerticalBox();
        Label mainText = new Label("Olá olá lorem ipsum pssum lorem ");
        ActionListener enterAsGuestHandler = e -> app.control().invoke(new NavigateCmd(new SearchBooksGuest()));
        Button enterAsGuestBttn = new Button("Acessar a biblioteca sem conta", enterAsGuestHandler);
        component.add(Margin.rigidVertical(20));
        component.add(mainText);
        component.add(Margin.rigidVertical(20));
        component.add(enterAsGuestBttn);
        component.add(Margin.rigidVertical(100));
        return component;
    }

    public static JComponent mainWrapper(App app) {
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
        component.add(Home.mainContent(app));
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
    public JComponent paint() {
        JComponent pane = Box.createVerticalBox();
        JComponent header = Home.header(app, pane, true, "");
        JComponent foot = Home.foot();
        JComponent mainWrapper = Home.mainWrapper(app);
        pane.add(header);
        pane.add(mainWrapper);
        pane.add(foot);
        return pane;
    }

}
