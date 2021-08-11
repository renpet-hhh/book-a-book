package controller.commands;

import javax.swing.JOptionPane;

import framework.App;
import framework.Command;
import model.Admin;
import model.Crypto;
import model.Login;
import model.User;
import model.UserData;

public class RegisterUserCmd implements Command {

    private User user;

    public RegisterUserCmd(UserData data, String password, boolean isAdmin) {
        User user;
        if (isAdmin) {
            user = new Admin(data, Crypto.crypt(password));
        } else {
            user = new User(data, Crypto.crypt(password));
        }
        this.user = user;
    }

    @Override
    public String log() {
        return "RegisterUserCmd: " + this.user.getData().getEmail();
    }

    @Override
    public void execute() {
        App app = App.get();
        Login login = app.getLogin();
        try {
            login.addUser(this.user);
            String okMessage = "Usuário cadastrado com sucesso";
            app.control().invoke(new DisplayPopupCmd(okMessage, JOptionPane.INFORMATION_MESSAGE));
        } catch (RuntimeException e) {
            app.control().invoke(new DisplayPopupCmd(e.getMessage(), JOptionPane.ERROR_MESSAGE));
        }
    }
    
}
