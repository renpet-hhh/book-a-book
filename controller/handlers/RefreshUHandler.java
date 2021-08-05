package controller.handlers;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import framework.App;
import model.Login;
import model.RefreshableBU;
import model.User;
import controller.commands.DisplayPopupCmd;

public class RefreshUHandler implements ActionListener {
    
    private RefreshableBU component;
    private JTextField field;
    public RefreshUHandler(RefreshableBU component, JTextField field) {
        this.component = component;
        this.field = field;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        App app = App.get();
        String matricula = this.field.getText();
        /* Obrigatoriedade dos campos */
        if (matricula.length() == 0) {
            app.control().invoke(new DisplayPopupCmd("Campo Matrícula Usuário é obrigatório", JOptionPane.ERROR_MESSAGE));
            return;
        }
        /* Interpretação dos campos */
        int matriculaInt;
        try {
            matriculaInt = Integer.parseInt(matricula);
            if (matriculaInt < 0) { throw new NumberFormatException(); }
        } catch (NumberFormatException e) {
            app.control().invoke(new DisplayPopupCmd("Matrícula deve ser um número não negativo. Recebido: " + matricula, JOptionPane.ERROR_MESSAGE));
            return;
        }
        Login login = app.getLogin();
        User user = login.getUser(matriculaInt);
        this.component.refresh(app, null, user);
    }

  
}
