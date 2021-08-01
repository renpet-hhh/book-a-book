package view.components;

import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.awt.Color;

import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;

import model.App;
import model.User;
import model.UserData;
import view.Margin;
import view.components.layout.PackLayout;
import view.components.layout.StretchLayout;

public class UserResult extends JComponent {

    /** 
     * SearchUsersResults exibe uma lista de UserResult.
     * 
     * É composto por alguns dados do usuário à esquerda e dois botões à direita.
     * Os botões são "Abrir registro" e "Editar".
     */

    final static int SPACEBETWEENBOTTOMLABELS = 20;
    final static int BOTTOMLABELSRIGHTMARGIN = 25;
    final static int SPACEBETWEENBUTTONS = 15;
    final static int BUTTONSVERTICALMARGIN = 18;
    final static int BUTTONSHORIZONTALMARGIN = 25;
    final static int INSIDEVERTICALMARGINLEFTCONTENT = 10;
    final static int INSIDEHORIZONTALMARGINLEFTCONTENT = 10;

    final static Color WRAPPERBUTTONSGRAY = new Color(209, 209, 209);
    final static Color BUTTONBGGRAY = new Color(181, 181, 181);
    final static Color BUTTONLABELCOLOR = new Color(0, 0, 0);
    final static Color LEFTBACKGROUNDCOLOR = new Color(255, 255, 255);

    /* Constantes para o POPUP */
    final static int TOPMARGIN = 40;
    final static int BOTTOMMARGIN = 40;
    final static int LEFTMARGIN = 30;
    final static int RIGHTMARGIN = 30;

    public UserResult(User user) {
        PackLayout layout = new PackLayout(this, PackLayout.X_AXIS);
        this.setLayout(layout);
        JComponent left = this.left(user);
        JComponent right = this.right(user);
        this.add(left);
        this.add(right);
    }


    private JComponent left(User user) {
        UserData data = user.getData();
        JComponent component = PackLayout.createVerticalBox();
        Label name = new Label("Nome: " + data.getName());
        Label status = new Label("Situação: OK");
        Label rent = new Label("Livros emprestados: 0");
        Label reserved = new Label("Livros reservados: 0");
        JComponent bottom = Box.createHorizontalBox();
        bottom.add(rent);
        bottom.add(Margin.rigidHorizontal(SPACEBETWEENBOTTOMLABELS));
        bottom.add(reserved);
        bottom.add(Margin.rigidHorizontal(BOTTOMLABELSRIGHTMARGIN));
        component.add(Margin.rigidVertical(INSIDEVERTICALMARGINLEFTCONTENT));
        component.add(Margin.glueRight(name));
        component.add(Margin.glueRight(status));
        component.add(Box.createVerticalGlue());
        component.add(bottom);
        component.add(Margin.rigidVertical(INSIDEVERTICALMARGINLEFTCONTENT));
        component.setOpaque(true);
        component.setBackground(LEFTBACKGROUNDCOLOR);
        JComponent wrapper = Box.createHorizontalBox();
        wrapper.add(Margin.rigidHorizontal(INSIDEHORIZONTALMARGINLEFTCONTENT));
        wrapper.add(component);
        wrapper.add(Box.createHorizontalGlue());
        wrapper.setOpaque(true);
        wrapper.setBackground(LEFTBACKGROUNDCOLOR);
        return wrapper;
    }

    private JComponent right(User user) {
        JComponent component = StretchLayout.createVerticalBox();
        ActionListener viewHandler = e -> {
            this.popupUserData(user);
        };
        Button view = new Button("Abrir registro", viewHandler, BUTTONLABELCOLOR, BUTTONBGGRAY);
        Button edit = new Button("Editar", null, BUTTONLABELCOLOR, BUTTONBGGRAY);
        component.add(Margin.rigidVertical(BUTTONSVERTICALMARGIN));
        component.add(view);
        component.add(Margin.rigidVertical(SPACEBETWEENBUTTONS));
        component.add(edit);
        component.add(Margin.rigidVertical(BUTTONSVERTICALMARGIN));
        component.setOpaque(true);
        component.setBackground(WRAPPERBUTTONSGRAY);
        JComponent wrapper = Margin.horizontal(component, BUTTONSHORIZONTALMARGIN);
        wrapper.setOpaque(true);
        wrapper.setBackground(WRAPPERBUTTONSGRAY);
        return wrapper;
    }

    public void popupUserData(User user) {
        this.popupUserData(App.get().getFrame(), user);
    }
    public void popupUserData(JFrame frame, User user) {
        UserData data = user.getData();
        JComponent component = Box.createVerticalBox();
        Label name = new Label("Nome: " + data.getName());
        Label matricula = new Label("Matrícula: " + data.getMatricula());
        Label birth = new Label("Data de nascimento: " + data.getBirthdate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        Label address = new Label("Endereço: "+ data.getAddress());
        Label email = new Label("Email: " + data.getEmail());
        Label contact = new Label("Contato: " + data.getContact());
        Label status = new Label("Situação: OK");
        component.add(Margin.rigidVertical(TOPMARGIN));
        component.add(name);
        component.add(matricula);
        component.add(birth);
        component.add(address);
        component.add(email);
        component.add(contact);
        component.add(Margin.rigidVertical(3));
        component.add(status);
        component.add(Margin.rigidVertical(3));
        component.add(Margin.rigidVertical(BOTTOMMARGIN));
        JComponent wrapper = Box.createHorizontalBox();
        wrapper.add(Margin.rigidHorizontal(LEFTMARGIN));
        wrapper.add(component);
        wrapper.add(Margin.rigidHorizontal(RIGHTMARGIN));
        JDialog dialog = new JDialog(frame);
        dialog.add(wrapper);
        dialog.pack();
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }
}
