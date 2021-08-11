package controller.commands;

import java.util.List;

import controller.RefreshID;
import framework.App;
import framework.Command;
import model.Book;
import model.User;

public class RefreshCmd implements Command {
    
    RefreshID changeID;
    Object[] args;
    public RefreshCmd(RefreshID changeID, Object ...args) {
        this.changeID = changeID;
        this.args = args;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void execute() {
        App app = App.get();
        switch (this.changeID) {
            case UserContext:
                app.setUserContext((User)args[0]);
                break;
            case BookContext:
                app.setBookContext((Book)args[0]);
                break;
            case UserListContext:
                app.setUserListContext((List<User>)args[0]);
                break;
            case BookListContext:
                app.setBookListContext((List<Book>)args[0]);
                break;
            case CLEAR:
                app.setUserContext(null);
                app.setBookContext(null);
                app.setUserListContext(null);
                app.setBookListContext(null);
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
