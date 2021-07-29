package model.commands;

import java.util.Arrays;

import model.App;
import model.Command;
import model.Login;
import model.User;
import view.pages.admin.SearchBooks;
import view.pages.guest.SearchBooksGuest;
import view.pages.user.Profile;

public class LoginCmd implements Command {

    private User user;
    private int userCode;

    public LoginCmd(User user) {
        this.user = user;
        this.userCode = user.getPrivilege();
    }
    public LoginCmd() {
        this.user = null;
        this.userCode = 0; // convidado
    }

    @Override
    public void execute() {
        App app = App.get();
        Login login = app.getLogin();
        boolean isAdmin = this.userCode == 2;
        login.setUser(this.user);
        login.setIsLoggedIn(true);
        login.setIsAdmin(isAdmin);
        if (this.userCode == 1) {
            app.invoke(new NavigateCmd(new Profile()));
        } else if (this.userCode == 2) {
            app.invoke(new NavigateCmd(new SearchBooks()));
        } else {
            // enquanto ainda não implementamos a página do Guest
            app.invoke(new NavigateCmd(new SearchBooksGuest()));
        }
    }

    @Override
    public String log() {
        String email = this.user == null ? "" : this.user.getData().email;
        Object data[] = new Object[] {email, this.userCode};
        return Arrays.toString(data);
    }
}
