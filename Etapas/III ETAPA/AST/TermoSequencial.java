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
public class TermoSequencial extends Termo{
    public Token operadorMul;
    public Termo esquerda, direita;

    public TermoSequencial(Token operadorMul, Termo esquerda, Termo direita) {
        this.operadorMul = operadorMul;
        this.esquerda = esquerda;
        this.direita = direita;
    }
    
    public void visit(Visitor v){
        v.visitTermoSequencial(this);
    }
}
