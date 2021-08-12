package controller.handlers;

import java.util.function.Consumer;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class NotifyTextChangeListener implements DocumentListener {

    private Consumer<Void> handler;
    public NotifyTextChangeListener(Consumer<Void> handler) {
        this.handler = handler;
    } 

    @Override
    public void insertUpdate(DocumentEvent e) {
        this.handler.accept(null);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        this.handler.accept(null);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        this.handler.accept(null);
    }
    
}
