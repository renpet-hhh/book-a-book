package model;

public class User {

    private String name, encryptedPassword;

    public String getName() { return this.name; }
    /* Indica se a senha encriptografada passada como argumento é igual à senha encriptografada desse usuário */
    public boolean comparePassword(String password) {
        return Crypto.crypt(password).equals(this.encryptedPassword);
    }

    public User(String name, String encryptedPassword) {
        this.name = name;
        this.encryptedPassword = encryptedPassword;
    }
    /* Retorna o nível de privilégio desse usuário */
    public int getPrivilege() {
        return 1;
    }
}