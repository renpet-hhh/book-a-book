package model.commands;

import model.App;
import model.Command;
import view.Page;

public class NavigateCmd implements Command {


    private Page page;

    public NavigateCmd(Page page) {
        this.page = page;
    }

    @Override
    public void execute() {
        App app = App.get();
        app.getFrame().setTitle(this.page.getTitle());
        app.navigate(this.page);
    }

    @Override
    public String log() {
        return "NavigateCmd: " + this.page.getTitle(); 
    }
}
