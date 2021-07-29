package view.pages.user;

import java.awt.Color;
import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.App;
import model.User;
import model.UserData;
import view.Margin;
import view.Page;
import view.components.Label;
import view.pages.Home;
import view.pages.pagestemplate.LayoutTemplate;

public class Profile implements Page {

    public final static String TITLE = "Perfil";
    @Override
    public String getTitle() { return TITLE; }

    @Override
    public void paint(JFrame frame) {
        App app = App.get();
        User user = app.getLogin().getUser();
        UserData data = user.getData();
        JComponent menubar = Home.header(frame, false, data.name);
        String path = "Perfil";
        // Página incompleta por enquanto!
        JComponent content = this.mainWrapper();
        LayoutTemplate.build(frame, menubar, content, path);
    }

    public JComponent mainContent() {
        App app = App.get();
        User user = app.getLogin().getUser();
        UserData data = user.getData();
        JComponent content = Box.createVerticalBox();
        JComponent profileData = Margin.glueRight(new Label("Dados pessoais"));
        JComponent emailLabel = Margin.glueRight(new Label("Email: " + data.email));
        JComponent contactLabel = Margin.glueRight(new Label("Contato: " + data.contact));
        profileData.setBackground(Color.blue);
        content.add(profileData);
        content.add(emailLabel);
        content.add(contactLabel);
        JComponent wrapper = Box.createHorizontalBox();
        wrapper.add(Margin.rigidHorizontal(10));
        wrapper.add(content);
        wrapper.add(Box.createHorizontalGlue());
        return wrapper;
    }
    
    public JComponent mainWrapper() {
        JComponent wrapper = Box.createHorizontalBox();
        wrapper.add(this.leftMenu());
        wrapper.add(this.mainContent());
        return wrapper;
    }

    public JComponent leftMenu() {
        return new JPanel();
    }
    
}
