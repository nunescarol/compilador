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
public class ExpressaoSimplesSequencial extends ExpressaoSimples {
    public Token operadorAd;
    public ExpressaoSimples esquerda, direita;

    public ExpressaoSimplesSequencial(Token operadorAd, ExpressaoSimples esquerda, ExpressaoSimples direita) {
        this.operadorAd = operadorAd;
        this.esquerda = esquerda;
        this.direita = direita;
    }
    
    public void visit(Visitor v){
        v.visitExpressaoSimplesSequencial(this);
    }
    
}
