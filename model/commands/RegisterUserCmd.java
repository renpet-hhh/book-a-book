package model.commands;

import javax.swing.JOptionPane;

import model.App;
import model.Command;
import model.Crypto;
import model.Login;
import model.User;

public class RegisterUserCmd implements Command {

    private User user;

    public RegisterUserCmd(String username, String password) {
        User user = new User(username, Crypto.crypt(password));
        this.user = user;
    }

    @Override
    public String log() {
        return "RegisterUserCmd: " + this.user.getName();
    }

    @Override
    public void execute() {
        App app = App.get();
        Login login = app.getLogin();
        try {
            login.addUser(this.user);
            String okMessage = "Usuário cadastrado com sucesso";
            app.invoke(new DisplayPopupCmd(okMessage, JOptionPane.INFORMATION_MESSAGE));
        } catch (RuntimeException e) {
            app.invoke(new DisplayPopupCmd(e.getMessage(), JOptionPane.ERROR_MESSAGE));
        }
    }
    
}
