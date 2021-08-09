package controller.commands;

import java.util.List;

import framework.App;
import framework.Command;
import model.Book;
import model.User;

public class RefreshCmd implements Command {
    
    String changeID;
    Object[] args;
    public RefreshCmd(String changeID, Object ...args) {
        this.changeID = changeID;
        this.args = args;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void execute() {
        App app = App.get();
        switch (this.changeID) {
            case "UserShow":
                app.setUserShow((User)args[0]);
                break;
            case "BookShow":
                app.setBookShow((Book)args[0]);
                break;
            case "UserListShow":
                app.setUserListShow((List<User>)args[0]);
                break;
            case "BookListShow":
                app.setBookListShow((List<Book>)args[0]);
                break;
            case "CLEAR":
                app.setUserShow(null);
                app.setBookShow(null);
                app.setUserListShow(null);
                app.setBookListShow(null);
                break;
            default:
                // notifica a View mais externa,
                // que por sua vez notificará seus filhos
                app.getCurrentPage().refresh(this.changeID, this.args);
        }
    }

    @Override
    public String log() {
        return "RefreshCmd: " + this.changeID;
    }
    
}
