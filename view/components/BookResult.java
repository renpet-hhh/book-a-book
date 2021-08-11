package view.components;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.Color;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;

import framework.App;
import helpers.Margin;
import model.Book;
import view.components.layout.PackLayout;
import view.components.layout.StretchLayout;

public class BookResult extends JComponent {

    /** 
     * SearchUsersResults exibe uma lista de BookResult.
     * 
     * É composto por alguns dados do usuário à esquerda e dois botões à direita.
     * Os botões são "Abrir registro" e "Editar".
     */

    final static int SPACEBETWEENBOTTOMLABELS = 20;
    final static int BOTTOMLABELSRIGHTMARGIN = 25;
    final static int SPACEBETWEENBUTTONS = 15;
    final static int BUTTONSVERTICALMARGIN = 18;
    final static int BUTTONSHORIZONTALMARGIN = 25;
    final static int INSIDEVERTICALMARGINLEFTCONTENT = 10;
    final static int INSIDEHORIZONTALMARGINLEFTCONTENT = 10;

    final static Color WRAPPERBUTTONSGRAY = new Color(209, 209, 209);
    final static Color BUTTONBGGRAY = new Color(181, 181, 181);
    final static Color BUTTONLABELCOLOR = new Color(0, 0, 0);
    final static Color LEFTBACKGROUNDCOLOR = new Color(255, 255, 255);

    /* Constantes para o POPUP */
    final static int TOPMARGIN = 40;
    final static int BOTTOMMARGIN = 40;
    final static int LEFTMARGIN = 30;
    final static int RIGHTMARGIN = 30;

    private JCheckBox checkbox = null;

    public BookResult(App app, Book book) {
        this(app, book, true, false, null);
    }
    public BookResult(App app, Book book, boolean editable, boolean selectable) {
        this(app, book, editable, selectable, null);
    }
    public BookResult(App app, Book book, boolean editable, boolean selectable, ActionListener checkboxHandler) {
        BoxLayout layout = new BoxLayout(this, BoxLayout.X_AXIS);
        this.setLayout(layout);
        if (selectable) {
            this.checkbox = new JCheckBox();
            if (checkboxHandler != null) this.checkbox.addActionListener(checkboxHandler);
            this.add(this.checkbox);
        }
        JComponent bookRegister = StretchLayout.createHorizontalBox();
        JComponent left = this.left(book);
        JComponent right = this.right(app, book, editable);
        bookRegister.add(left);
        bookRegister.add(right);
        this.add(bookRegister);
    }

    public JCheckBox getCheckBox() { return this.checkbox; }


    private JComponent left(Book book) {
        JComponent component = PackLayout.createVerticalBox();
        Label title = new Label("Título: " + book.getTitle());
        Label author = new Label("Autor: " + book.getAuthors().get(0));
        Label year = new Label("Ano: " + book.getYearOfPublishment());
        component.add(Margin.rigidVertical(INSIDEVERTICALMARGINLEFTCONTENT));
        component.add(Margin.glueRight(title));
        component.add(Margin.glueRight(author));
        component.add(Margin.glueRight(year));
        component.add(Box.createVerticalGlue());
        component.add(this.bottom(book));
        component.add(Margin.rigidVertical(INSIDEVERTICALMARGINLEFTCONTENT));
        component.setOpaque(true);
        component.setBackground(LEFTBACKGROUNDCOLOR);
        JComponent wrapper = Box.createHorizontalBox();
        wrapper.add(Margin.rigidHorizontal(INSIDEHORIZONTALMARGINLEFTCONTENT));
        wrapper.add(component);
        wrapper.add(Box.createHorizontalGlue());
        wrapper.setOpaque(true);
        wrapper.setBackground(LEFTBACKGROUNDCOLOR);
        return wrapper;
    }

    private JComponent right(App app, Book book, boolean editable) {
        JComponent component = StretchLayout.createVerticalBox();
        ActionListener viewHandler = e -> {
            this.popupBookData(app, book);
        };
        Button view = new Button("Abrir registro", viewHandler, BUTTONLABELCOLOR, BUTTONBGGRAY);
        Button edit = new Button("Editar", null, BUTTONLABELCOLOR, BUTTONBGGRAY);
        component.add(Margin.rigidVertical(BUTTONSVERTICALMARGIN));
        component.add(view);
        if (editable) {
            component.add(Margin.rigidVertical(SPACEBETWEENBUTTONS));
            component.add(edit);
        } else {
            component.add(Margin.rigidVertical(0));
        }
        component.add(Margin.rigidVertical(BUTTONSVERTICALMARGIN));
        component.setOpaque(true);
        component.setBackground(WRAPPERBUTTONSGRAY);
        JComponent wrapper = Margin.horizontal(Margin.glueVertical(component), BUTTONSHORIZONTALMARGIN);
        wrapper.setOpaque(true);
        wrapper.setBackground(WRAPPERBUTTONSGRAY);
        return wrapper;
    }

    private JComponent bottom(Book book) {
        int total = book.getHowManyTotal();
        int available = book.getHowManyAvailable();
        int rent = book.getHowManyRented();
        int reserved = book.getHowManyReserved();
        Label totalLabel = new Label("Exemplares: " + total);
        Label availableLabel = new Label("Disponíveis: " + available);
        Label rentLabel = new Label("Emprestados: " + rent);
        Label reservedLabel = new Label("Reservados: " + reserved);
        JComponent bottom = Box.createHorizontalBox();
        bottom.add(totalLabel);
        bottom.add(Margin.rigidHorizontal(SPACEBETWEENBOTTOMLABELS));
        bottom.add(availableLabel);
        bottom.add(Margin.rigidHorizontal(SPACEBETWEENBOTTOMLABELS));
        bottom.add(rentLabel);
        bottom.add(Margin.rigidHorizontal(SPACEBETWEENBOTTOMLABELS));
        bottom.add(reservedLabel);
        bottom.add(Margin.rigidHorizontal(BOTTOMLABELSRIGHTMARGIN));
        return bottom;
    }
    
    private void popupBookData(App app, Book book) {
        this.popupBookData(app.getFrame(), book);
    }
    private void popupBookData(JFrame frame, Book book) {
        String title = book.getTitle();
        String subtitle = book.getSubtitle();
        List<String> authors = book.getAuthors();
        String author1 = "", author2 = "", author3 = "";
        /* ignoramos as exceções, pois nesse caso elas já estão tratadas (a String author correspondente será vazia "") */
        try { author1 = authors.get(0); } catch (IndexOutOfBoundsException e) {}
        try { author2 = authors.get(1); } catch (IndexOutOfBoundsException e) {}
        try { author3 = authors.get(2); } catch (IndexOutOfBoundsException e) {}
        String edition = book.getEdition();
        int year = book.getYearOfPublishment();
        String whereWasPublished = book.getWhereWasPublished();
        String isbn = book.getIsbn();;
        JComponent component = Box.createVerticalBox();
        Label titleLabel = new Label("Título: " + title);
        Label subtitleLabel = new Label("Subtítulo: " + subtitle);
        Label author1Label = new Label("Autor 1: " + author1);
        Label author2Label = new Label("Autor 2: " + author2);
        Label author3Label = new Label("Autor 3: " + author3);
        Label editionLabel = new Label("Edição: " + edition);
        Label yearLabel = new Label("Ano de publicação: " + year);
        Label whereLabel = new Label("Local de publicação: " + whereWasPublished);
        Label isbnLabel = new Label("ISBN: " + isbn);
        component.add(Margin.rigidVertical(TOPMARGIN));
        component.add(Margin.glueRight(titleLabel));
        component.add(Margin.glueRight(subtitleLabel));
        component.add(Margin.glueRight(author1Label));
        component.add(Margin.glueRight(author2Label));
        component.add(Margin.glueRight(author3Label));
        component.add(Margin.glueRight(editionLabel));
        component.add(Margin.glueRight(yearLabel));
        component.add(Margin.glueRight(whereLabel));
        component.add(Margin.glueRight(isbnLabel));
        component.add(Margin.rigidVertical(3));
        component.add(this.bottom(book));
        component.add(Margin.rigidVertical(3));
        component.add(Margin.rigidVertical(BOTTOMMARGIN));
        JComponent wrapper = Box.createHorizontalBox();
        wrapper.add(Margin.rigidHorizontal(LEFTMARGIN));
        wrapper.add(component);
        wrapper.add(Margin.rigidHorizontal(RIGHTMARGIN));
        JDialog dialog = new JDialog(frame);
        dialog.add(wrapper);
        dialog.pack();
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }

}
