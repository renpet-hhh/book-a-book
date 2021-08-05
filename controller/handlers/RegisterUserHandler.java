package controller.handlers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.function.Consumer;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import framework.App;
import model.UserData;
import controller.commands.DisplayPopupCmd;
import controller.commands.RegisterUserCmd;

public class RegisterUserHandler extends FieldHandler {

    /** Define as restrições para que um cadastro de usuário seja aceito.
     * 
     * Por exemplo, impõe regras para o formato de data (dd/MM/yyyy),
     * proíbe campos vazios, faz confirmação de senha, etc.
     * @param fields - Campos de texto (componentes) em ORDEM:
     * 
     * 0 - Nome Completo
     * 1 - Data de nascimento
     * 2 - Documento
     * 3 - Endereço
     * 4 - Email
     * 5 - Contato
     * 6 - Senha
     * 7 - Confirmação de senha 
     */

    private boolean isAdmin;
    public RegisterUserHandler(List<JTextField> fields, boolean isAdmin) {
        super(fields, null);
        this.isAdmin = isAdmin;
        this.setHandler(this.h);
    }

    private Consumer<List<JTextField>> h = new Consumer<List<JTextField>>() {
        @Override
        public void accept(List<JTextField> f) {
            App app = App.get();
            String username = f.get(0).getText();
            if (username.length() == 0) {
                app.control().invoke(new DisplayPopupCmd("Campo de nome é obrigatório", JOptionPane.ERROR_MESSAGE));
                return;
            }
            String birthdateStr = f.get(1).getText();
            String document = f.get(2).getText();
            String address = f.get(3).getText();
            String email = f.get(4).getText();
            if (email.length() == 0) {
                app.control().invoke(new DisplayPopupCmd("Campo de email é obrigatório", JOptionPane.ERROR_MESSAGE));
                return;
            }
            String contact = f.get(5).getText();
            String password = f.get(6).getText();
            if (password.length() < 6) {
                app.control().invoke(new DisplayPopupCmd("Senha deve ter no mínimo 6 caracteres", JOptionPane.ERROR_MESSAGE));
                return;
            }
            String confirmPassword = f.get(7).getText();
            if (!password.equals(confirmPassword)) {
                app.control().invoke(new DisplayPopupCmd("Senhas diferentes", JOptionPane.ERROR_MESSAGE));
                return;
            }
            LocalDate birthdate;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            try {
                birthdate = LocalDate.parse(birthdateStr, formatter);
            } catch (DateTimeParseException e) {
                app.control().invoke(new DisplayPopupCmd("Data deve estar no formato dd/MM/yyyy", JOptionPane.ERROR_MESSAGE));
                return;
            }
            UserData data = new UserData(username, address, contact, email, document, birthdate);
            // o botão Cadastrar foi pressionado! vamos tentar cadastrar o usuário
            // mensagens de erro e confirmação são de responsabilidade do próprio comando abaixo
            app.control().invoke(new RegisterUserCmd(data, password, RegisterUserHandler.this.isAdmin));
        }
    };

}
