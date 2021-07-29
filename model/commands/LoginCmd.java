package model.commands;

import java.util.Arrays;

import model.App;
import model.Command;
import model.Login;
import view.pages.admin.SearchBooks;
import view.pages.user.Profile;

public class LoginCmd implements Command {

    private String username;
    private boolean isAdmin;

    public LoginCmd(String username, boolean isAdmin) {
        this.username = username;
        this.isAdmin = isAdmin;
    }

    @Override
    public void execute() {
        App app = App.get();
        Login login = app.getLogin();
        login.setUsername(this.username);
        login.setIsLoggedIn(true);
        login.setIsAdmin(this.isAdmin);
        if (!this.isAdmin) {
            app.invoke(new NavigateCmd(new Profile()));
        } else {
            app.invoke(new NavigateCmd(new SearchBooks()));
        }
    }

    @Override
    public String log() {
        Object data[] = new Object[] {this.username, this.isAdmin};
        return Arrays.toString(data);
    }
}
