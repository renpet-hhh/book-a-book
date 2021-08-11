package view.components;

import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.Color;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

import controller.commands.DereserveBookCmd;
import framework.App;
import framework.View;
import helpers.Margin;
import model.Book;
import model.Emprestimo;
import model.User;
import view.components.layout.PackLayout;

public class EmprestimoItem extends View {

    final static int LEFTCONTENTMARGIN = 10;
    final static int VERTICALCONTENTMARGIN = 6;
    final static int ICONVMARGIN = 6;
    final static int ICONHMARGIN = 3;

    final static Color LABELCOLOR = new Color(240, 240, 240);
    final static Color EXPIREDATECOLOR = new Color(230, 230, 230);
    final static Color BGCOLOR = new Color(66, 66, 69);
    final static Color DELETEBGCOLOR = new Color(231, 75, 75);
    final static Color INFOBGCOLOR = new Color(196, 196, 196);

    private Book book;
    private User user;
    private LocalDateTime expireDate = null;

    public EmprestimoItem(App model, Book book, User user) {
        super(model);
        this.book = book;
        this.user = user;
    }
    public EmprestimoItem(App model, Emprestimo emprestimo) {
        super(model);
        this.book = emprestimo.getBook();
        this.user = emprestimo.getUser();
        this.expireDate = emprestimo.getExpireDate();
    }


    @Override
    public JComponent paint() {
        JComponent component = PackLayout.createHorizontalBox();
        component.add(this.mainContent(this.book));
        // só há botão de deletar se isso é uma reserva
        if (this.expireDate == null) component.add(this.deleteButton());
        component.add(this.infoButton());
        return component;
    }

    private String getTextFromBook(Book book) {
        return book.getTitle() + "; " + book.getAuthors().get(0);
    }

    private Label mainLabel;
    public JComponent mainContent(Book book) {
        JComponent content = Box.createHorizontalBox();
        String labelText = this.getTextFromBook(book);
        this.mainLabel = new Label(labelText);
        this.mainLabel.setForeground(LABELCOLOR);
        content.add(Margin.rigidHorizontal(LEFTCONTENTMARGIN));
        content.add(Margin.glueVertical(this.mainLabel));
        content.add(Box.createHorizontalGlue());
        content.setBackground(BGCOLOR);
        content.setOpaque(true);
        if (this.expireDate == null) return content;
        JComponent wrapper = Box.createVerticalBox();
        Label expireLabel = new Label("Prazo: " + this.expireDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), EXPIREDATECOLOR);
        wrapper.add(content);
        wrapper.add(Margin.glueLeft(Margin.horizontalRight(expireLabel, 10)));
        wrapper.setBackground(BGCOLOR);
        wrapper.setOpaque(true);
        return wrapper;
    }

    public JComponent deleteButton() {
        ImageIcon img = new ImageIcon("./images/outline_delete_black_24dp.png");
        ActionListener deleteHandler = e -> {
            this.model.control().invoke(new DereserveBookCmd(this.book, this.user));
        };
        Button icon = new Button(img, deleteHandler, DELETEBGCOLOR);
        JComponent wrapper = Margin.vertical(Margin.horizontal(icon, ICONHMARGIN), ICONVMARGIN);
        wrapper.setBackground(DELETEBGCOLOR);
        wrapper.setOpaque(true);
        return wrapper;
    }

    public JComponent infoButton() {
        ImageIcon img = new ImageIcon("./images/outline_info_black_24dp.png");
        ActionListener infoHandler = e -> {
            BookResult bk = new BookResult(model, book);
            bk.paint();
            bk.popupBookData();
        };
        Button icon = new Button(img, infoHandler, INFOBGCOLOR);
        JComponent wrapper = Margin.vertical(Margin.horizontal(icon, ICONHMARGIN), ICONVMARGIN);
        wrapper.setBackground(INFOBGCOLOR);
        wrapper.setOpaque(true);
        return wrapper;
    }

  
    
}
