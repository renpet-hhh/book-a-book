package model;

import model.commands.DisplayPopupCmd;
import model.commands.LoginCmd;
import model.commands.LogoutCmd;

public class Login {
    
    /* Estado de login */
    private String username = ""; // estado inicial
    private boolean isLoggedIn = false;
    private boolean isAdmin = false;
    /* Getters */
    public String getUsername() { return this.username; }
    public boolean isLoggedIn() { return this.isLoggedIn; }
    public boolean isAdmin() { return this.isAdmin; }
    /* Setters */
    public void setUsername(String username) { this.username = username; }
    public void setIsLoggedIn(boolean isLoggedIn) { this.isLoggedIn = isLoggedIn; }
    public void setIsAdmin(boolean isAdmin) { this.isAdmin = isAdmin; }

    /** Valida um usuário.
        0 - Usuário não cadastrado.
        1 - Usuário comum.
        2 - Admin
    */
    private int validate(String username, String password) {
        return 1;
    }

    public void login(String username, String password) {
        App app = App.get();
        if (this.isLoggedIn) {
            Command displayErr = new DisplayPopupCmd("Usuário já está logado");
            app.invoke(displayErr);
            return;
        }
        int code = validate(username, password);
        if (code > 0) {
            Command loginCmd = new LoginCmd(username, code == 2);
            app.invoke(loginCmd);
        }
    }

    public void logout() {
        if (!this.isLoggedIn) {
            return;
        }
        this.username = null;
        this.isLoggedIn = false;
        this.isAdmin = false;
        App app = App.get();
        Command logoutCmd = new LogoutCmd();
        app.invoke(logoutCmd);
    }

}
