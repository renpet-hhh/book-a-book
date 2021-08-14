package controller.commands;

import javax.swing.JOptionPane;

import framework.App;
import framework.Command;
import model.Admin;
import model.Crypto;
import model.Login;
import model.Reports;
import model.User;
import model.UserData;

public class RegisterUserCmd implements Command {

    private User user;
    private User registrador;
    public RegisterUserCmd(UserData data, String password, boolean isAdmin) {
        this(data, password, isAdmin, App.get().getLogin().getUser());
    }
    /** registrador é o administrador que está cadastrando esse usuário */
    public RegisterUserCmd(UserData data, String password, boolean isAdmin, User registrador) {
        User user;
        if (isAdmin) {
            user = new Admin(data, Crypto.crypt(password));
        } else {
            user = new User(data, Crypto.crypt(password));
        }
        this.user = user;
        this.registrador = registrador;
    }

    @Override
    public String log() {
        return "RegisterUserCmd: " + this.user.getData().getEmail();
    }

    @Override
    public void execute() {
        App app = App.get();
        Login login = app.getLogin();
        String okMessage = "Usuário cadastrado com sucesso";
        try {
            login.addUser(this.user);
        } catch (RuntimeException e) {
            app.control().invoke(new DisplayPopupCmd(e.getMessage(), JOptionPane.ERROR_MESSAGE));
            return;
        }
        app.control().invoke(new ReportCmd<>(user, Reports.Type.USER_REGISTER, registrador));
        app.control().invoke(new DisplayPopupCmd(okMessage, JOptionPane.INFORMATION_MESSAGE));
    }
    
}
