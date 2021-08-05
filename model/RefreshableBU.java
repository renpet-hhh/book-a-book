package model;

import framework.App;

/* BU indica Book e User */
public interface RefreshableBU {
    /** Atualiza a informação dos componentes sobre um livro e um usuário */
    void refresh(App app, Book book, User user);
}
