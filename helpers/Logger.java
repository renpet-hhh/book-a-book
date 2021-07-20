package helpers;

import java.util.ArrayList;
import java.util.List;

import model.Command;

public class Logger {
    List<String> commandHistory;
    boolean shouldLog;
    
    public Logger(boolean shouldLog) {
        this.commandHistory = new ArrayList<String>();
        this.shouldLog = shouldLog;
    }

    public void log(Command cmd) {
        if (!shouldLog) return;
        commandHistory.add(cmd.log());
    }

    public void dump() {
        System.out.println("[Comandos]");
        for (String cmd : commandHistory) {
            System.out.println(cmd);
        }
    }
}
