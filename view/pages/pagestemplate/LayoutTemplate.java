package view.pages.pagestemplate;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;

import view.components.Label;
import view.components.base.MenuFactory;
import view.Margin;

public class LayoutTemplate {

    /** Template para construção do layout padrão:
     * 
     * O layout padrão é basicamente a barra de menu seguida do caminho (exemplo: Pesquisa >> Usuários),
     * seguido do conteúdo (parte principal e central de uma página), seguido de um barra final (foot) com informações de Termos de Serviço por exemplo.
     */

    final static int BOTTOMMARGINMAINWRAPPER = 80;
    final static int LEFTRIGHTMARGINWRAPPER = MenuFactory.WRAPPERHORIZONTALMARGIN;


    public static JComponent pathComponent(String text) {
        /** Constrói um component para exibir o caminho (exemplo: Pesquisa >> Usuários) */
        JComponent component = Box.createHorizontalBox();
        Label label = new Label(text);
        component.add(Margin.rigidHorizontal(LEFTRIGHTMARGINWRAPPER));
        component.add(label);
        component.add(Box.createHorizontalGlue());
        return component;
    }

    public static void build(JFrame frame, JComponent menubar, JComponent content, String path) {
        /** frame é o componente ao qual será construído o template, de acordo com
         * o que foi fornecido comoo argumento (menubar, content, path).
         */
        BoxLayout bLayout = new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS);
        frame.setLayout(bLayout);
        frame.add(menubar);
        frame.add(pathComponent(path));
        frame.add(content);
        frame.add(Margin.rigidVertical(BOTTOMMARGINMAINWRAPPER));
    }
    
}
