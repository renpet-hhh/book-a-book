package view.components;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.border.Border;

import framework.App;
import framework.View;
import helpers.Margin;
import model.Book;
import model.Emprestimo;
import model.Relatorio;
import model.Reports;
import model.User;
import view.components.layout.PackLayout;

public class ReportList extends View {

    private final static Color BGCOLOR = new Color(220, 220, 220);
    private final static Color TITLECOLOR = new Color(10, 10, 10);
    private final static Color DESCRIPTIONCOLOR = TITLECOLOR;
    private final static Color ITEMBORDERCOLOR = new Color(10, 10, 10);

    final static int VPADDING = 8;
    final static int HPADDING = 4;
    final static Border ITEMBORDER = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(ITEMBORDERCOLOR, 1), BorderFactory.createEmptyBorder(VPADDING, HPADDING, VPADDING, HPADDING));
    
    final static int SPACEBETWEENITEMS = 20;
    final static int SPACEBETWEENLABELANDREGISTER = 5;
    final static Dimension minPopupDimension = new Dimension(200, 200);
    final static int windowPadding = 30;
    final static int RIGHTITEMPADDING = 100;
    
    
    
    private Reports reports;
    private Reports.Type type;
    private Dimension prefScrollDimension = new Dimension(480, 300);
    private LocalDate from, to;

    public ReportList(App app, Reports.Type type, LocalDate from, LocalDate to) {
        this(app, type, from, to, null);
    }
    public ReportList(App app, Reports.Type type, LocalDate from, LocalDate to, Dimension prefDim) {
        super(app);
        this.reports = app.getReports();
        this.type = type;
        if (prefDim != null) this.prefScrollDimension = prefDim;
        this.from = from != null ? from : LocalDate.MIN;
        this.to = to != null ? to : LocalDate.MAX;
    }

    private ActionListener popupUserRegister(User registrador, User newUser) {
        JFrame frame = new JFrame();
        String label1 = "Admin que cadastrou o usuário: ";
        String label2 = "Usuário cadastrado: ";
        
        UserResult uItem = null;
        if (registrador != null) {
            uItem = new UserResult(this.model, registrador, false);
            uItem.setFrame(frame);
        }

        UserResult uItem2 = new UserResult(this.model, newUser, false);
        uItem2.setFrame(frame);
    
        return this.popup(frame, label1, label2, uItem == null ? null : uItem.paint(), uItem2.paint());
    }
    private ActionListener popupBookRegister(Book book, User user) {
        JFrame frame = new JFrame();
        String label1 = "Admin que cadastrou o livro: ";
        String label2 = "Registro do livro: ";
    
        UserResult uItem = new UserResult(this.model, user, false);
        uItem.setFrame(frame);

        EmprestimoItem eItem = new EmprestimoItem(this.model, book, user);
        eItem.setFrame(frame);
        eItem.setShouldBeDeletable(false);
    
        return this.popup(frame, label1, label2, uItem.paint(), eItem.paint());
    }
    private ActionListener popupEmprestimo(Emprestimo emprestimo) {
        User user = emprestimo.getUser();
        JFrame frame = new JFrame();
        String label1 = "Registro do usuário associado a este empréstimo: ";
        String label2 = "Registro do empréstimo: ";
    
        UserResult uItem = new UserResult(this.model, user, false);
        uItem.setFrame(frame);

        EmprestimoItem eItem = new EmprestimoItem(this.model, emprestimo);
        eItem.setFrame(frame);
    
        return this.popup(frame, label1, label2, uItem.paint(), eItem.paint());
    }
    private ActionListener popup(JFrame frame, String label1, String label2, JComponent view1, JComponent view2) {
        return e -> {
            JComponent results = Box.createVerticalBox();
            Label uLabel = new Label(label1);
            Label bLabel = new Label(label2);

            results.add(Box.createVerticalGlue());
            if (view1 != null) {
                results.add(Margin.glueRight(uLabel));
                results.add(Margin.rigidVertical(SPACEBETWEENLABELANDREGISTER));
                results.add(view1);
                results.add(Margin.rigidVertical(SPACEBETWEENITEMS));
            }
            if (view2 != null) {
                results.add(Margin.glueRight(bLabel));
                results.add(Margin.rigidVertical(SPACEBETWEENLABELANDREGISTER));
                results.add(view2);
            }
            results.add(Box.createVerticalGlue());
            results.setMinimumSize(minPopupDimension);
        
            frame.add(Margin.vertical(Margin.horizontal(results, windowPadding), windowPadding));
            frame.setLocationRelativeTo(null);
            frame.pack();
            frame.setVisible(true);
        };
    }



    private <T> List<Relatorio<T>> filteredCopy(List<Relatorio<T>> list) {
        List<Relatorio<T>> l = new ArrayList<>();
        for (Relatorio<T> r : list) {
            LocalDate date = r.getDate().toLocalDate();
            if (date.compareTo(this.from) >= 0 && date.compareTo(this.to) <= 0) {
                l.add(r);
            }
        }
        return l;
    }

    @Override
    public JComponent paint() {
        JComponent component = Box.createVerticalBox();
        boolean isEmprestimo = this.type == Reports.Type.EMPRESTIMO;
        boolean isBookRegister = this.type == Reports.Type.BOOK_REGISTER;
        boolean isDevolucao = this.type == Reports.Type.DEVOLUCAO;
        if (isEmprestimo || isDevolucao) {
            List<Relatorio<Emprestimo>> list = this.filteredCopy(isEmprestimo ? this.reports.getEmprestimos() : this.reports.getDevolucoes());
           
            if (list.size() == 0) {
                String msg = isEmprestimo ? "Não há empréstimos registrados" : "Não há devoluções registradas";
                Label emptyLabel = new Label(msg);
                component.add(emptyLabel);
                return component;
            }
            for (Relatorio<Emprestimo> r : list) {
                Emprestimo emprestimo = r.getReport();
                String username = isEmprestimo ? "Empréstimo realizado por: " : "Devolução feita por: ";
                String bookTitle = isEmprestimo ? "Livro emprestado: " : "Livro devolvido: ";
                username += r.getReport().getUser().getData().getName();
                bookTitle += r.getReport().getBook().getTitle();
                component.add(this.reportItem(username, bookTitle, r.getDate(), this.popupEmprestimo(emprestimo)));
                component.add(Margin.rigidVertical(SPACEBETWEENITEMS));
            }
        } else if (isBookRegister) {
            List<Relatorio<Book>> list = this.filteredCopy(this.reports.getBooksRegister());
            if (list.size() == 0) {
                String msg = "Não há cadastro de livro registrado";
                Label emptyLabel = new Label(msg);
                component.add(emptyLabel);
                return component;
            }
            for (Relatorio<Book> r : list) {
                Book book = r.getReport();
                User user = (User)(r.getData())[0];
                String username = "Admin que cadastrou o livro: " + user.getData().getName();
                String bookTitle = "Livro cadastrado: " + book.getTitle();
                component.add(this.reportItem(username, bookTitle, r.getDate(), this.popupBookRegister(book, user)));
                component.add(Margin.rigidVertical(SPACEBETWEENITEMS));
            }
        } else if (this.type == Reports.Type.USER_REGISTER) {
            List<Relatorio<User>> list = this.filteredCopy(this.reports.getUsersRegister());
            if (list.size() == 0) {
                String msg = "Não há registro de cadastro de usuário";
                Label emptyLabel = new Label(msg);
                component.add(emptyLabel);
                return component;
            }
            for (Relatorio<User> r : list) {
                User user = r.getReport(); // novo usuário cadastrado
                User admin = (User)r.getData()[0]; // admin que cadastrou o usuário
                String newUserName = "Novo usuário cadastrado: " + user.getData().getName();
                String adminUserName = "Admin que fez o cadastrado: " + (admin == null ? "Usuário pré-cadastrado" : admin.getData().getName());
                
                component.add(this.reportItem(adminUserName, newUserName, r.getDate(), this.popupUserRegister(admin, user)));
                component.add(Margin.rigidVertical(SPACEBETWEENITEMS));
            }
        }
        ScrollPane scrollPane = new ScrollPane(component);
        scrollPane.setPreferredSize(this.prefScrollDimension);
        return scrollPane;
    }

    private JComponent reportItem(String username, String bookTitle, LocalDateTime date, ActionListener openPopup) {
        JComponent content = Box.createVerticalBox();
        Label userLabel = new Label(username, TITLECOLOR);
        Label bookLabel = new Label(bookTitle, DESCRIPTIONCOLOR);
        Label dateLabel = new Label("Data: " + date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        content.add(Margin.glueRight(Margin.horizontalRight(userLabel, RIGHTITEMPADDING)));
        content.add(Margin.glueRight(Margin.horizontalRight(bookLabel, RIGHTITEMPADDING)));
        content.add(Margin.glueRight(Margin.horizontalRight(dateLabel, RIGHTITEMPADDING)));
        content.setOpaque(true);
        content.setBackground(BGCOLOR);
        content.setBorder(ITEMBORDER);
        JComponent wrapper = PackLayout.createHorizontalBox();
        JComponent infoButton = new InfoButton(openPopup);
        infoButton.setMinimumSize(infoButton.getPreferredSize());
        wrapper.add(content);
        wrapper.add(Margin.glueVertical(infoButton));
        wrapper.setBackground(InfoButton.DEFAULTBGCOLOR);
        wrapper.setOpaque(true);
        return wrapper;
    }
}
