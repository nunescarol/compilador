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
public class Variavel extends AST{
    public Token identificador;
    public Seletor seletor;

    public Variavel(Token identificador, Seletor seletor) {
        this.identificador = identificador;
        this.seletor = seletor;
    }
    
    public void visit(Visitor v){
        v.visitVariavel(this);
    }
}