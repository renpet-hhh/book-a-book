package view.pages.admin;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import controller.RefreshID;
import controller.commands.DisplayPopupCmd;
import controller.commands.EmprestarCmd;
import controller.commands.RefreshCmd;
import controller.handlers.RefreshReservaHandler;
import framework.Page;
import helpers.Margin;
import model.Book;
import model.User;
import model.UserData;
import view.components.AdminMenu;
import view.components.BookResult;
import view.components.Label;
import view.components.base.MenuFactory;
import view.pages.pagestemplate.LayoutTemplate;
import view.pages.pagestemplate.SearchContentTemplate;

public class Reservations extends Page {
    
    public final static String TITLE = "Reservas";
    @Override
    public String getTitle() { return TITLE; }

    final static int INFOVERTICALCONTENTMARGIN = 50;
    final static int INFOCONTENTLEFTMARGIN = 50;
    final static int SPACEBETWEENLABELS = 3;
    final static int SPACEBETWEENBOOKRESULTS = 8;
    final static int SCROLLTOPMARGIN = 15;
    final static int SCROLLBOTTOMMARGIN = 10;

    final static Color INFOBGCOLOR = new Color(196, 196, 196);

    private JComponent reservedBooksList;

    private JLabel username = new Label("");
    private JLabel status = new Label("");
    private JLabel pending = new Label("");
    private JLabel reserves = new Label("");
    private JButton rentButton;
    private JScrollPane scrollPane;
    private JComponent infoComponent;
    private List<JCheckBox> checkBoxes;

    @Override
    public JComponent paint() {
        JComponent pane = Box.createVerticalBox();
        /* Inicialização */
        this.reservedBooksList = Box.createVerticalBox();
        this.checkBoxes = new ArrayList<>();

        this.scrollPane = new JScrollPane(this.reservedBooksList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        this.scrollPane.setBorder(BorderFactory.createEmptyBorder());

        this.infoComponent = this.info();

        /* Layout */
        JComponent menubar = AdminMenu.withWrapper(app);
        /* Top */
        String[] labelsText = new String[] {
            "Matrícula Usuário:"
        };
        String[] topButtonText = new String[] {"Buscar"};
        SearchContentTemplate searchTemplate = new SearchContentTemplate(labelsText, topButtonText, false, false);
        JComponent searchContent = searchTemplate.build();
        ActionListener searchHandler = new RefreshReservaHandler(searchTemplate.getTextFields().get(0));
        ActionListener[] searchHandlers = new ActionListener[] {searchHandler};
        searchTemplate.setHandlers(searchHandlers);
        /* Bottom */
        String[] bottomButtonsText = new String[] {"Cancelar", "Emprestar"};
        ActionListener cancelHandler = e -> {
            this.app.control().invoke(new RefreshCmd(RefreshID.UserContext, (User)null));
            this.app.control().invoke(new RefreshCmd(RefreshID.BookListContext, (List<Book>)null));
        };
        ActionListener emprestarHandler = e -> {
            User user = this.app.getUserContext();
            if (user == null) return;
            List<Book> reservedBooks = user.getData().getReservedBooks();
            List<Book> listaDeLivrosParaEmprestar = new ArrayList<Book>();
            for (int i = 0; i < reservedBooks.size(); i++) {
                if (this.checkBoxes.get(i).isSelected()) {
                    Book b = reservedBooks.get(i);
                    listaDeLivrosParaEmprestar.add(b);
                    // não vamos emprestar imediatamente, pois isso alteraria a ordem
                    // dos livros reservados, vamos deixar para emprestar depois desse loop
                }
            }
            this.app.shouldIgnorePopup(true);
            for (Book b : listaDeLivrosParaEmprestar) {
                this.app.control().invoke(new EmprestarCmd(b, user));
            }
            this.app.shouldIgnorePopup(false);
            this.app.control().invoke(new RefreshCmd(RefreshID.BookListContext, user.getData().getReservedBooks()));
            this.app.control().invoke(new DisplayPopupCmd("Livro(s) emprestado(s) com sucesso"));
        };
        ActionListener[] handlers = new ActionListener[] {cancelHandler, emprestarHandler};
        SearchContentTemplate buttonsTemplate = new SearchContentTemplate(new String[0], bottomButtonsText, handlers, false, -1, true);
        JComponent buttons = buttonsTemplate.build();
        this.rentButton = buttonsTemplate.getButtons()[1];
        pane.add(menubar);
        pane.add(LayoutTemplate.pathComponent("Circulação >> Reservas"));
        pane.add(searchContent);
        pane.add(this.infoComponent);
        pane.add(buttons);
        this.app.control().invoke(new RefreshCmd(RefreshID.UserContext, (User)null));
        return pane;
    }

    private void addLabels(JComponent component) {
        component.add(Margin.rigidVertical(INFOVERTICALCONTENTMARGIN));
        component.add(Margin.glueRight(this.username));
        component.add(Margin.rigidVertical(SPACEBETWEENLABELS));
        component.add(Margin.glueRight(this.status));
        component.add(Margin.rigidVertical(SPACEBETWEENLABELS));
        component.add(Margin.glueRight(this.pending));   
        component.add(Margin.rigidVertical(SPACEBETWEENLABELS));
        component.add(Margin.glueRight(this.reserves));   
    }

    private JComponent info() {
        JComponent wrapper2 = Box.createHorizontalBox();
        JComponent wrapper1 = Box.createHorizontalBox();
        JComponent content = Box.createVerticalBox();
        addLabels(content);
        content.add(Margin.rigidVertical(SCROLLTOPMARGIN));
        content.add(this.scrollPane);
        content.add(Margin.rigidVertical(SCROLLBOTTOMMARGIN));
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

    @Override
    public void refresh(RefreshID changeID, Object ...args) {
        String userText = "Usuário: ";
        String statusText = "Situação: ";
        String pendingText = "Devoluções pendentes: ";
        String reservesText = "Reservas: ";
        User user = this.app.getUserContext();
        if (RefreshID.UserContext == changeID) {
            if (user != null) {
                UserData data = user.getData();
                userText += data.getName();
                statusText += user.status();
                pendingText += data.getEmprestimos().size();
            } else {
                userText += "NÃO ENCONTRADO";
                reservesText += "NÃO ENCONTRADO";
                this.scrollPane.setVisible(false);
            }
            this.refreshForCheckBoxInteraction(user);
            this.username.setText(userText);
            this.status.setText(statusText);
            this.pending.setText(pendingText);
            this.reserves.setText(reservesText);
        }
        if (RefreshID.BookListContext == changeID) {
            List<Book> bookList = this.app.getBookListContext();
            if (bookList != null) {
                this.checkBoxes.clear();
                this.reservedBooksList.removeAll();
                ActionListener checkboxHandler = e -> this.refreshForCheckBoxInteraction(user);
                for (Book reservedBook : bookList) {
                    BookResult bookResult = new BookResult(app, reservedBook, false, true, checkboxHandler);
                    this.checkBoxes.add(bookResult.getCheckBox());
                    this.reservedBooksList.add(bookResult);
                    this.reservedBooksList.add(Margin.rigidVertical(SPACEBETWEENBOOKRESULTS));
                }
                this.infoComponent.revalidate();
                this.refreshForCheckBoxInteraction(user);
            } else {
                reservesText += "NÃO ENCONTRADO";
            }
            this.scrollPane.setVisible(bookList != null && bookList.size() > 0);
        }
        super.refresh(changeID, args);
    }

    public void refreshForCheckBoxInteraction(User user) {
        boolean someCheckBoxIsSelected = false;
        for (JCheckBox check : this.checkBoxes) {
            if (check.isSelected()) {
                someCheckBoxIsSelected = true;
                break;
            }
        }
        this.rentButton.setEnabled(user != null && someCheckBoxIsSelected);
    }

    
}
