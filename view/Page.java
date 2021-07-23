package view;

import javax.swing.JFrame;

public interface Page {

    String getTitle();
    void paint(JFrame frame);
    
}
