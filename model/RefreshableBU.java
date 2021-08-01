package model;

/* BU indica Book e User */
public interface RefreshableBU {
    /** Atualiza a informação dos componentes sobre um livro e um usuário */
    void refresh(Book book, User user);
}
