package controller;

/** Enumeração de "eventos" de refresh.
 * O modelo notifica as views sobre diversos tipos de mudanças no estado do model.
 * Para que as views possam escolher como reagir a cada tipo de mudança,
 * é enviado com cada notificação uma chave que identifica o tipo de alteração realizada.
 */
public enum RefreshID {
    UserContext, // usuário do contexto mudou
    BookContext, // livro do contexto mudou
    UserListContext, // lista de usuários do contexto mudou
    BookListContext, // lista de livros do contexto mudou
    AuthorAddBook, // autor foi adicionado a um livro
    BookTotal, // quantidade total de algum livro foi modificada
    BookAvailable, // quantidade disponível de algum livro foi modificada
    BookReserved, // quantidade reservada de algum livro foi modificada
    LibraryAddBook, // um livro foi adicionado à biblioteca
    LoginUserChanged, // usuário logado mudou
    LoginIncrementMatricula, // número atual de matrícula foi incrementado
    LoginAddUser, // novo usuário foi cadastrado
    UserReserveBook, // livro foi reservado
    UserUnreserveBook, // reserva foi removida
    UserEmprestar, // empréstimo realizado
    UserDevolver, // devolução realizada
    SettingsChanged, // alguma configuração mudou
    CLEAR, // Contexto foi limpo.
    INIT, // Componente será montado a seguir.
    MOUNT; // Componente foi montado (posições calculadas). Chance de alterar dados que dependem das posições dos componentes na tela.
}
