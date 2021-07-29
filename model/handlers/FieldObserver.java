package model.handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.function.Consumer;

import javax.swing.JTextField;

public class FieldObserver implements ActionListener {

    private List<JTextField> fields;
    private Consumer<List<JTextField>> handler;
    public FieldObserver(List<JTextField> fields, Consumer<List<JTextField>> handler) {
        this.fields = fields;
        this.handler = handler;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        handler.accept(this.fields);
    }
    
}
