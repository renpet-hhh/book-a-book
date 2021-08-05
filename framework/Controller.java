package framework;

import helpers.Logger;

public class Controller {

    private Logger logger;
    public Controller(Logger logger) {
        this.logger = logger;
    }

    /* Design Pattern: Invoker | https://pt.wikipedia.org/wiki/Command */
    /* Invoca comandos */
    public void invoke(Command cmd) {
        this.logger.log(cmd);
        cmd.execute();
    }
}
