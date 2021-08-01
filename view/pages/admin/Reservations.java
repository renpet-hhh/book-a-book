package view.pages.admin;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import model.Book;
import model.RefreshableBU;
import model.User;
import model.UserData;
import model.handlers.ClearHandler;
import model.handlers.RefreshUHandler;
import view.Margin;
import view.Page;
import view.components.AdminMenu;
import view.components.BookResult;
import view.components.Label;
import view.components.base.MenuFactory;
import view.pages.pagestemplate.SearchContentTemplate;
import view.pages.pagestemplate.LayoutTemplate;

public class Reservations implements Page, RefreshableBU {
    
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
    private JButton rentButton;
    private JScrollPane scrollPane;
    private JComponent infoComponent;
    private List<JCheckBox> checkBoxes;

    @Override
    public void paint(JFrame frame) {
        /* Inicialização */
        this.reservedBooksList = Box.createVerticalBox();
        this.checkBoxes = new ArrayList<>();

        this.scrollPane = new JScrollPane(this.reservedBooksList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        this.scrollPane.setBorder(BorderFactory.createEmptyBorder());

        this.infoComponent = this.info();

        /* Layout */
        BoxLayout bLayout = new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS);
        frame.setLayout(bLayout);
        JComponent menubar = AdminMenu.withWrapper();
        /* Top */
        String[] labelsText = new String[] {
            "Matrícula Usuário:"
        };
        String[] topButtonText = new String[] {"Buscar"};
        SearchContentTemplate searchTemplate = new SearchContentTemplate(labelsText, topButtonText, false, false);
        JComponent searchContent = searchTemplate.build();
        ActionListener searchHandler = new RefreshUHandler(this, searchTemplate.getTextFields().get(0));
        ActionListener[] searchHandlers = new ActionListener[] {searchHandler};
        searchTemplate.setHandlers(searchHandlers);
        /* Bottom */
        String[] bottomButtonsText = new String[] {"Cancelar", "Emprestar"};
        ActionListener cancelHandler = new ClearHandler<>(searchTemplate.getClearableFields());
        ActionListener[] handlers = new ActionListener[] {cancelHandler, null};
        SearchContentTemplate buttonsTemplate = new SearchContentTemplate(new String[0], bottomButtonsText, handlers, false, -1, true);
        JComponent buttons = buttonsTemplate.build();
        this.rentButton = buttonsTemplate.getButtons()[1];
        frame.add(menubar);
        frame.add(LayoutTemplate.pathComponent("Circulação >> Reservas"));
        frame.add(searchContent);
        frame.add(this.infoComponent);
        frame.add(buttons);
        this.refresh(null, null);
    }

    private void addLabels(JComponent component) {
        component.add(Margin.rigidVertical(INFOVERTICALCONTENTMARGIN));
        component.add(Margin.glueRight(this.username));
        component.add(Margin.rigidVertical(SPACEBETWEENLABELS));
        component.add(Margin.glueRight(this.status));
        component.add(Margin.rigidVertical(SPACEBETWEENLABELS));
        component.add(Margin.glueRight(this.pending));   
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
    public void refresh(Book book, User user) {
        String userText = "Usuário: ";
        String statusText = "Situação: ";
        String pendingText = "Devoluções pendentes: ";
        if (user != null) {
            ActionListener checkboxHandler = e -> this.refreshForCheckBoxInteraction(user);
            this.reservedBooksList.removeAll();
            this.checkBoxes.clear();
            UserData data = user.getData();
            userText += data.getName();
            for (Book reservedBook : data.getReservedBooks()) {
                BookResult bookResult = new BookResult(reservedBook, false, true, checkboxHandler);
                this.checkBoxes.add(bookResult.getCheckBox());
                this.reservedBooksList.add(bookResult);
                this.reservedBooksList.add(Margin.rigidVertical(SPACEBETWEENBOOKRESULTS));
            }
            this.reservedBooksList.validate();
        } else {
            userText += "NÃO ENCONTRADO";
        }
        this.username.setText(userText);
        this.status.setText(statusText);
        this.pending.setText(pendingText);
        this.scrollPane.setVisible(user != null);
        this.refreshForCheckBoxInteraction(user);
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
