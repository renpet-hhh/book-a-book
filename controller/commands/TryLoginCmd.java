package controller.commands;

import framework.App;
import framework.Command;
import model.Login;

public class TryLoginCmd implements Command {

    /** Tenta realizar login de usuario, mostrando erro caso usuário esteja logado
     * ou caso matrícula ou senha estejam inseridas erradas
     */

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
        // para mais informações, veja tryLogin
        login.tryLogin(this.matricula, this.password);
    }
    
}
