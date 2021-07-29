package model.commands;

import model.App;
import model.Command;
import model.Login;
import view.Page;
import view.pages.Home;
import view.pages.user.Profile;

public class NavigateCmd implements Command {


    private Page page;

    public NavigateCmd(Page page) {
        this.page = page;
    }

    @Override
    public void execute() {
        App app = App.get();
        Login login = app.getLogin();
        String destPageName = app.getCurrentPage().getClass().getSimpleName();
        String currPageName = this.page.getClass().getSimpleName();
        if (destPageName.equals(currPageName)) {
            return; // não fazemos nada quando já estamos na página de destino
        }
        if (this.page instanceof Home && login.isLoggedIn()) {
            this.page = new Profile();
        }
        app.getFrame().setTitle(this.page.getTitle());
        app.navigate(this.page);
    }

    @Override
    public String log() {
        return "NavigateCmd: " + this.page.getTitle(); 
    }
}
