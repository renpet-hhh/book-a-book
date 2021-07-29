package model;

import java.util.HashMap;
import java.util.Map;

import model.commands.DisplayPopupCmd;
import model.commands.LoginCmd;

public class Login {
    
    /* Estado de login */
    private User user; // estado inicial
    private boolean isLoggedIn = false;
    private boolean isAdmin = false;
    /* Getters */
    public User getUser() { return this.user; }
    public boolean isLoggedIn() { return this.isLoggedIn; }
    public boolean isAdmin() { return this.isAdmin; }
    /* Setters */
    public void setUser(User user) { this.user = user; }
    public void setIsLoggedIn(boolean isLoggedIn) { this.isLoggedIn = isLoggedIn; }
    public void setIsAdmin(boolean isAdmin) { this.isAdmin = isAdmin; }

    /* Base de dados */
    private Map<String, User> users = new HashMap<String, User>();
    
    public void addUser(User user) {
        String email = user.getData().email;
        if (users.containsKey(email)) {
            throw new RuntimeException("Usuário já está cadastrado");
        }
        users.put(email, user);
    }

    /* Funções para salvar e carregar a base de dados */
    public void loadUserDB(String filename) {
        return;
    }
    public void saveUserDB(String filename) {
        return;
    }


    /** Retorna um usuário, se a senha está correta */
    private User validate(String email, String password) {
        User u = this.users.getOrDefault(email, null);
        if (u == null) return null;
        if (!u.comparePassword(password)) return null;
        return u;
    }

    public void login(String username, String password) {
        App app = App.get();
        if (this.isLoggedIn) {
            Command displayErr = new DisplayPopupCmd("Usuário já está logado");
            app.invoke(displayErr);
            return;
        }
        User u = validate(username, password);
        if (u != null) {
            Command loginCmd = new LoginCmd(u);
            app.invoke(loginCmd);
            return;
        }
        app.invoke(new DisplayPopupCmd("Email e/ou senha incorretos"));
    }

    public void logout() {
        if (!this.isLoggedIn) {
            return;
        }
        this.user = null;
        this.isLoggedIn = false;
        this.isAdmin = false;
    }

}
