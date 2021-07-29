package model.commands;

import java.util.Arrays;

import model.App;
import model.Command;
import model.Login;
import view.pages.Home;

public class LogoutCmd implements Command {

    @Override
    public void execute() {
        App app = App.get();
        Login login = app.getLogin();
        login.logout();
        app.invoke(new NavigateCmd(new Home()));
    }
    
    @Override
    public String log() {
        Object data[] = new Object[] {};
        return Arrays.toString(data);
    }
}
