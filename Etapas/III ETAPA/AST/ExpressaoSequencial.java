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
public class ExpressaoSequencial extends Expressao{
    public Token operadorRel;
    public Expressao esquerda, direita;

    public ExpressaoSequencial(Token operadorRel, Expressao esquerda, Expressao direita) {
        this.operadorRel = operadorRel;
        this.esquerda = esquerda;
        this.direita = direita;
    }
    
    public void visit(Visitor v){
        v.visitExpressaoSequencial(this);
    }
}
