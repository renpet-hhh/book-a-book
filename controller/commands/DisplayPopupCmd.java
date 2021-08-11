package controller.commands;

import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import framework.App;
import framework.Command;

/** Comando concreto.
 * 
 * Exibe um popup de erro.
 */
public class DisplayPopupCmd implements Command {

    JFrame frame;
    String message, title;
    int messageType;

    private final static String defaultTitle = "";
    private final static int defaultMessageType = JOptionPane.INFORMATION_MESSAGE;

    public DisplayPopupCmd(JFrame frame, String message, String title, int messageType) {
        this.frame = frame;
        this.message = message;
        this.title = title;
        this.messageType = messageType;
    }
    public DisplayPopupCmd(String message, int messageType) {
        this(App.get().getFrame(), message, DisplayPopupCmd.defaultTitle, messageType);
    }
    public DisplayPopupCmd(String message) {
        this(App.get().getFrame(), message, DisplayPopupCmd.defaultTitle, DisplayPopupCmd.defaultMessageType);
    }

    @Override
    public void execute() {
        if (!App.get().shouldIgnorePopup() || this.messageType == JOptionPane.ERROR_MESSAGE) {
            JOptionPane.showMessageDialog(this.frame, this.message, title, this.messageType);
        }
    }
    
    @Override
    public String log() {
        Object data[] = new Object[] {this.frame, this.message, this.title, this.messageType};
        return Arrays.toString(data);
    }
}
