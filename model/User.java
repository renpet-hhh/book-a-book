package model;

public class User {

    /** Representa um usuário */

    private String encryptedPassword; // não armazenamos a senha, apenas sua versão criptografada
    private UserData data;

    public UserData getData() { return this.data; }
    /* Indica se a senha encriptografada passada como argumento é igual à senha encriptografada desse usuário */
    public boolean comparePassword(String password) {
        return Crypto.crypt(password).equals(this.encryptedPassword);
    }

    public User(UserData data, String encryptedPassword) {
        this.data = data;
        this.encryptedPassword = encryptedPassword;
    }
    /** Retorna o nível de privilégio desse usuário.
     * 
     * 1 - Usuário comum
     * 2 - Administrador
     */
    public final static int GUESTPRIVILEGE = 0;
    public final static int USERPRIVILEGE = 1;
    public final static int ADMINPRIVILEGE = 2;
    public int getPrivilege() {
        return User.USERPRIVILEGE;
    }
}