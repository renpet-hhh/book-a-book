package model.commands;

import java.util.Arrays;

import model.App;
import model.Command;
import model.Login;
import model.User;
import view.pages.admin.SearchBooks;
import view.pages.user.Profile;

public class LoginCmd implements Command {

    private User user;
    private boolean isAdmin;

    public LoginCmd(User user) {
        this.user = user;
        this.isAdmin = user.getPrivilege() == 2;
    }

    @Override
    public void execute() {
        App app = App.get();
        Login login = app.getLogin();
        login.setUser(this.user);
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
        Object data[] = new Object[] {this.user.getData().email, this.isAdmin};
        return Arrays.toString(data);
    }
}
