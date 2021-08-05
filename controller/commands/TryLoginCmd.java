package controller.commands;

import framework.App;
import framework.Command;
import model.Login;

public class TryLoginCmd implements Command {

    private int matricula;
    private String password;

    public TryLoginCmd(int matricula, String password) {
        this.matricula = matricula;
        this.password = password;
    }

    @Override
    public String log() {
        return "TryLoginCmd: " + this.matricula;
    }

    @Override
    public void execute() {
        App app = App.get();
        Login login = app.getLogin();
        login.tryLogin(this.matricula, this.password);
    }
    
}
