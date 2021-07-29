package model;

import java.util.HashMap;
import java.util.Map;

import model.commands.DisplayPopupCmd;
import model.commands.LoginCmd;

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

    /* Base de dados */
    private Map<String, User> users = new HashMap<String, User>();
    
    public void addUser(User user) {
        String username = user.getName();
        if (users.containsKey(username)) {
            throw new RuntimeException("Usuário já está cadastrado");
        }
        users.put(username, user);
    }

    /* Funções para salvar e carregar a base de dados */
    public void loadUserDB(String filename) {
        return;
    }
    public void saveUserDB(String filename) {
        return;
    }


    /** Valida um usuário.
       -1 - Acesso não permitido.
        0 - Usuário não cadastrado.
        1 - Usuário comum.
        2 - Admin
    */
    private int validate(String email, String password) {
        User u = this.users.getOrDefault(email, null);
        if (u == null) return 0;
        if (!u.comparePassword(password)) return -1;
        return u.getPrivilege();
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
            return;
        }
        app.invoke(new DisplayPopupCmd("Email e/ou senha incorretos"));
    }

    public void logout() {
        if (!this.isLoggedIn) {
            return;
        }
        this.username = null;
        this.isLoggedIn = false;
        this.isAdmin = false;
    }

}
