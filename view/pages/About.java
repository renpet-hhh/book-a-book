package view.pages;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;

import view.Margin;
import view.Page;
import view.components.Label;

public class About implements Page {

    public final static String TITLE = "Sobre";
    @Override
    public String getTitle() { return TITLE; }

    final static int TOPMARGINCONTENT = 20;
    final static int MAINTEXTMARGIN = 10;
    final static int CONTACTBELOWMARGIN = 10;
    final static int SPACEBETWEENEMAILS = 4;
    final static int BOTTOMMARGINCONTENT = 20;
    
    @Override
    public void paint(JFrame frame) {
        BoxLayout bLayout = new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS);
        frame.setLayout(bLayout);
        JComponent component = Box.createVerticalBox();
        component.add(Home.header(frame, false));
        JComponent wrap1 = Box.createHorizontalBox();
        wrap1.add(Margin.rigidHorizontal(70));
        wrap1.add(About.mainContent());
        wrap1.add(Box.createHorizontalGlue());
        component.add(wrap1);
        component.add(Home.foot());
        frame.add(component);
    }

    public static JComponent mainContent() {
        JComponent component = Box.createVerticalBox();
        Label sobre = new Label("Sobre");
        Label content = new Label("<html>Sistema desenvolvido para se integrar à Biblioteca Universitária,<br/> possibilitando a pesquisa de livros disponíveis na biblioteca,<br/> além de permitir reserva, empréstimo e devolução de livros.</html>");
        component.add(Margin.rigidVertical(TOPMARGINCONTENT));
        component.add(sobre);
        component.add(Margin.rigidVertical(MAINTEXTMARGIN));
        component.add(content);
        component.add(Margin.rigidVertical(MAINTEXTMARGIN));
        component.add(Box.createVerticalGlue());
        Label contact = new Label("Contato");
        Label email1 = new Label("Administração - example1@gmail.com");
        Label email2 = new Label("TI - example2@gmail.com");
        component.add(contact);
        component.add(Margin.rigidVertical(CONTACTBELOWMARGIN));
        component.add(email1);
        component.add(Margin.rigidVertical(SPACEBETWEENEMAILS));
        component.add(email2);
        component.add(Margin.rigidVertical(BOTTOMMARGINCONTENT));
        return component;
    }


}
