package model;

import java.time.LocalDate;

public class UserData {
    /* Struct de dados. Não define comportamento, então todos os campos são públicos */
    public String name, address, contact, email, document;
    public LocalDate birthdate;
}