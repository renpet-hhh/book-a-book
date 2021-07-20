package model.commands;

import java.util.Arrays;

import model.App;
import model.Command;
import model.Login;

public class LogoutCmd implements Command {

    @Override
    public void execute() {
        Login login = App.get().getLogin();
        login.setUsername("");
        login.setIsLoggedIn(false);
        login.setIsAdmin(false);
    }
    
    @Override
    public String log() {
        Object data[] = new Object[] {};
        return Arrays.toString(data);
    }
}
