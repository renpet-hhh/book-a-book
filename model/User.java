package model;

public class User {

    /** Representa um usuário */

    private String name, encryptedPassword; // não armazenamos a senha, apenas sua versão criptografada

    public String getName() { return this.name; }
    /* Indica se a senha encriptografada passada como argumento é igual à senha encriptografada desse usuário */
    public boolean comparePassword(String password) {
        return Crypto.crypt(password).equals(this.encryptedPassword);
    }

    public User(String name, String encryptedPassword) {
        this.name = name;
        this.encryptedPassword = encryptedPassword;
    }
    /** Retorna o nível de privilégio desse usuário.
     * 
     * 1 - Usuário comum
     * 2 - Administrador
     */
    public int getPrivilege() {
        return 1;
    }
}