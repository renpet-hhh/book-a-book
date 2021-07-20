package model.commands;

import java.util.Arrays;

import model.App;
import model.Command;
import model.Login;

public class LoginCmd implements Command {

    String username;
    boolean isAdmin;

    public LoginCmd(String username, boolean isAdmin) {
        this.username = username;
        this.isAdmin = isAdmin;
    }

    @Override
    public void execute() {
        Login login = App.get().getLogin();
        login.setUsername(this.username);
        login.setIsLoggedIn(true);
        login.setIsAdmin(this.isAdmin);
    }

    @Override
    public String log() {
        Object data[] = new Object[] {this.username, this.isAdmin};
        return Arrays.toString(data);
    }
}
