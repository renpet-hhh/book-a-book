package view.pages.admin;
import java.awt.Color;

import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

import model.Book;
import model.RefreshableBU;
import model.User;
import model.UserData;
import model.handlers.RefreshBUHandler;
import view.Margin;
import view.Page;
import view.components.AdminMenu;
import view.components.Label;
import view.components.base.MenuFactory;
import view.pages.pagestemplate.SearchContentTemplate;
import view.pages.pagestemplate.LayoutTemplate;

public class EmprestimosEDevolucoes implements Page, RefreshableBU {
    
    public final static String TITLE = "Empréstimos e Devoluções";
    @Override
    public String getTitle() { return TITLE; }

    final static int INFOVERTICALCONTENTMARGIN = 50;
    final static int INFOCONTENTLEFTMARGIN = 50;
    final static int SPACEBETWEENLABELS = 3;

    final static Color INFOBGCOLOR = new Color(196, 196, 196);

    private JLabel title = new Label("");
    private JLabel authors = new Label("");
    private JLabel publishmentdate = new Label("");
    private JLabel username = new Label("");
    private JLabel status = new Label("");
    private JLabel pending = new Label("");
    private JComponent infoComponent = this.info();
    private JButton rentButton;


    @Override
    public void paint(JFrame frame) {
        BoxLayout bLayout = new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS);
        frame.setLayout(bLayout);
        JComponent menubar = AdminMenu.withWrapper();
        /* Top */
        String[] labelsText = new String[] {
            "Cód. do Livro:", "Matrícula Usuário:"
        };
        String[] topButtonText = new String[] {"Buscar"};
        SearchContentTemplate inputTemplate = new SearchContentTemplate(labelsText, topButtonText, null, false, -1, false);
        JComponent searchContent = inputTemplate.build();
        ActionListener refreshHandler = new RefreshBUHandler(this, inputTemplate.getTextFields());
        ActionListener[] searchHandlers = new ActionListener[] {refreshHandler};
        inputTemplate.setHandlers(searchHandlers);
        /* Bottom */
        String[] bottomButtonsText = new String[] {"Cancelar", "Emprestar/Devolver"};
        ActionListener cancelHandler = e -> this.refresh(null, null);
        ActionListener[] bottomHandlers = new ActionListener[] {cancelHandler, null};
        SearchContentTemplate buttonsTemplate = new SearchContentTemplate(new String[0], bottomButtonsText, bottomHandlers, false);
        JComponent buttons = buttonsTemplate.build();
        this.rentButton = buttonsTemplate.getButtons()[1];
        frame.add(menubar);
        frame.add(LayoutTemplate.pathComponent("Circulação >> Empréstimos e Devoluções"));
        frame.add(searchContent);
        frame.add(this.infoComponent);
        frame.add(buttons);
        this.refresh(null, null);
    }

    private void addLabels(JComponent component) {
        component.add(Margin.rigidVertical(INFOVERTICALCONTENTMARGIN));
        component.add(this.title);
        component.add(Margin.rigidVertical(SPACEBETWEENLABELS));
        component.add(this.authors);
        component.add(Margin.rigidVertical(SPACEBETWEENLABELS));
        component.add(this.publishmentdate);
        component.add(Margin.rigidVertical(SPACEBETWEENLABELS));
        component.add(this.username);
        component.add(Margin.rigidVertical(SPACEBETWEENLABELS));
        component.add(this.status);
        component.add(Margin.rigidVertical(SPACEBETWEENLABELS));
        component.add(this.pending);
    }
    

    private JComponent info() {
        JComponent wrapper2 = Box.createHorizontalBox();
        JComponent wrapper1 = Box.createHorizontalBox();
        JComponent content = Box.createVerticalBox();
        addLabels(content);
        content.add(Margin.rigidVertical(INFOVERTICALCONTENTMARGIN));
        wrapper1.add(Margin.rigidHorizontal(INFOCONTENTLEFTMARGIN));
        wrapper1.add(content);
        wrapper1.add(Box.createHorizontalGlue());
        wrapper1.setOpaque(true);
        wrapper1.setBackground(INFOBGCOLOR);
        wrapper2.add(Margin.rigidHorizontal(MenuFactory.WRAPPERHORIZONTALMARGIN));
        wrapper2.add(wrapper1);
        wrapper2.add(Margin.rigidHorizontal(MenuFactory.WRAPPERHORIZONTALMARGIN));
        return wrapper2;
    }
    public void refresh(Book book, User user) {
        String titleText = "Título: ";
        String authorsText = "Autor(es): ";
        String yearText = "Data de Publicação: ";
        String userText = "Usuário: ";
        String statusText = "Situação:";
        String pendingText = "Devoluções pendentes:";
        if (book != null) {
            titleText += book.getTitle();
            authorsText += String.join(", ", book.getAuthors());
            yearText += book.getYearOfPublishment();
        } else {
            titleText += "NÃO ENCONTRADO";
        }
        if (user != null) {
            UserData data = user.getData();
            userText += data.getName();
        } else {
            userText += "NÃO ENCONTRADO";
        }
        this.title.setText(titleText);
        this.authors.setText(authorsText);
        this.publishmentdate.setText(yearText);
        this.username.setText(userText);
        this.status.setText(statusText);
        this.pending.setText(pendingText);
        this.rentButton.setEnabled(book != null && user != null);
    }
}
