package model;

public class TryLoginCmd implements Command {

    private String email, password;

    public TryLoginCmd(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public String log() {
        return "TryLoginCmd: " + this.email;
    }

    @Override
    public void execute() {
        App app = App.get();
        Login login = app.getLogin();
        login.login(this.email, this.password);
    }
    
}
