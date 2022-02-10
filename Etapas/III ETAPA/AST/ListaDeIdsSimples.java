/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST;
import compilador.Token;
import visitor.Visitor;
/**
 *
 * @author Usuario
 */
public class ListaDeIdsSimples extends ListaDeIds{
    public Token identificador;

    public Token getIdentificador() {
        return identificador;
    }

    public ListaDeIdsSimples(Token identificador) {
        this.identificador = identificador;
    }
    
    public void visit(Visitor v){
        v.visitListaDeIdsSimples(this);
    }
}
