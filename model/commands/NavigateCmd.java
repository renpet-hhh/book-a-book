package model.commands;

import model.App;
import model.Command;
import view.pages.Home;
import view.pages.Search;

public class NavigateCmd implements Command {

    /* O título da página, utilizado como código para navegação */
    public static final String PAGINAINICIAL = "Página Inicial";
    public static final String PESQUISABIBLIOGRAFICA = "Pesquisa Bibliográfica";

    private String pageTitle;

    public NavigateCmd(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    @Override
    public void execute() {
        App app = App.get();
        app.getFrame().setTitle(this.pageTitle);
        switch (this.pageTitle) {
            case PAGINAINICIAL:
                app.navigate(new Home());
                break;
            case PESQUISABIBLIOGRAFICA:
                app.navigate(new Search());
                break;
            default:
                break;
        }
    }

    @Override
    public String log() {
        return "NavigateCmd: " + this.pageTitle; 
    }
}
