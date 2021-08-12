

package controller.commands;

import framework.Command;
import model.Emprestimo;

public class ChangeSettings {
    

    public static Command prazoEmprestimo(int days) {
        Command cmd = new Command(){
            @Override
            public void execute() {
                Emprestimo.changeExpireLimit(days);
            }
            @Override
            public String log() {
                return "PrazoEmprestimo: " + days;
            }
        };
        return cmd;
    }

    public static Command maxQuantityEmprestimo(int quantity) {
        Command cmd = new Command(){
            @Override
            public void execute() {
                Emprestimo.changeMaxQuantity(quantity);
            }
            @Override
            public String log() {
                return "MaxQuantityEmprestimo: " + quantity;
            }
        };
        return cmd;
    }

    public static Command multa(double amount) {
        Command cmd = new Command(){
            @Override
            public void execute() {
                Emprestimo.changeMulta(amount);
            }
            @Override
            public String log() {
                return "Multa: " + amount;
            }
        };
        return cmd;
    }
}
