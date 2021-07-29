package model;

public class Crypto {

    private static String SECRET = "BIGSECRET";
    

    /* Aplica criptografia ao argumento */
    public static String crypt(String x) {
        // usaremos SECRET aqui dentro como chave da criptografia
        // por enquanto não há criptografia, é só um teste
        return x + Crypto.SECRET.hashCode();
    }
}
