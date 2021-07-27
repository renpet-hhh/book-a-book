package view.components;

import java.awt.Cursor;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;

import view.Margin;
import view.components.fixed.LimitedJTextField;
import view.pages.Home;

public class ForgotPassword {


    final static int SPACEBETWEENBUTTONANDFIELD = 10;
    final static int SPACEBETWEENLABELANDFIELD = 10;
    final static int MINFIELDWIDTH = 200;
    final static int MAXFIELDWIDTH = 300;
    final static int DIALOGMARGIN = 20;

    public static JButton getButton(JFrame frame) {
        JButton forgotPasswordBttn = new JButton("Esqueceu a senha?");
        forgotPasswordBttn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        forgotPasswordBttn.setBorderPainted(false);
        forgotPasswordBttn.setBackground(Home.HEADERRIGHTCOLOR);
        forgotPasswordBttn.setForeground(Home.FORGOTPASSWORDCOLOR);
        forgotPasswordBttn.addActionListener(e -> ForgotPassword.show(frame));
        return forgotPasswordBttn;
    }

    public static void show(JFrame frame) {
        String title = "Esqueceu a senha?";
        String message = "Digite um email no campo abaixo para receber instruções sobre mudança de senha";
        LimitedJTextField field = new LimitedJTextField(MINFIELDWIDTH, MAXFIELDWIDTH);
        JButton send = new JButton("Enviar");
        JDialog dialog = new JDialog(frame, title);
        Label label = new Label(message);
        JComponent wrapper = Box.createVerticalBox();
        label.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        field.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        send.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        wrapper.add(label);
        wrapper.add(Margin.rigidVertical(SPACEBETWEENLABELANDFIELD));
        wrapper.add(field);
        wrapper.add(Margin.rigidVertical(SPACEBETWEENBUTTONANDFIELD));
        wrapper.add(send);
        dialog.add(Margin.horizontal(Margin.vertical(wrapper, DIALOGMARGIN), DIALOGMARGIN));
        dialog.pack();
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }
}
