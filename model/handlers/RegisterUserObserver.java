package model.handlers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.function.Consumer;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.App;
import model.UserData;
import model.commands.DisplayPopupCmd;
import model.commands.RegisterUserCmd;

public class RegisterUserObserver extends FieldObserver {

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

    public RegisterUserObserver(List<JTextField> fields) {
        super(fields, null);
        this.setHandler(RegisterUserObserver.h);
    }

    private static Consumer<List<JTextField>> h = new Consumer<List<JTextField>>() {
        @Override
        public void accept(List<JTextField> f) {
            App app = App.get();
            String username = f.get(0).getText();
            if (username.length() == 0) {
                app.invoke(new DisplayPopupCmd("Campo de nome é obrigatório", JOptionPane.ERROR_MESSAGE));
                return;
            }
            String birthdateStr = f.get(1).getText();
            String document = f.get(2).getText();
            String address = f.get(3).getText();
            String email = f.get(4).getText();
            if (email.length() == 0) {
                app.invoke(new DisplayPopupCmd("Campo de email é obrigatório", JOptionPane.ERROR_MESSAGE));
                return;
            }
            String contact = f.get(5).getText();
            String password = f.get(6).getText();
            if (password.length() < 6) {
                app.invoke(new DisplayPopupCmd("Senha deve ter no mínimo 6 caracteres", JOptionPane.ERROR_MESSAGE));
                return;
            }
            String confirmPassword = f.get(7).getText();
            if (!password.equals(confirmPassword)) {
                app.invoke(new DisplayPopupCmd("Senhas diferentes", JOptionPane.ERROR_MESSAGE));
                return;
            }
            UserData data = new UserData();
            data.name = username;
            data.document = document;
            data.address = address;
            data.email = email;
            data.contact = contact;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate ld;
            try {
                ld = LocalDate.parse(birthdateStr, formatter);
            } catch (DateTimeParseException e) {
                app.invoke(new DisplayPopupCmd("Data deve estar no formato dd/MM/yyyy", JOptionPane.ERROR_MESSAGE));
                return;
            }
            data.birthdate = ld;
            // o botão Cadastrar foi pressionado! vamos tentar cadastrar o usuário
            // mensagens de erro e confirmação são de responsabilidade do próprio comando abaixo
            app.invoke(new RegisterUserCmd(data, password));
        }
    };

}
