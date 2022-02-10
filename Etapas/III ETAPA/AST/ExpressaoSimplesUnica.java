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
public class ExpressaoSimplesUnica extends ExpressaoSimples{
    public Termo termo;

    public ExpressaoSimplesUnica(Termo termo) {
        this.termo = termo;
    }
    
    public void visit(Visitor v){
        v.visitExpressaoSimplesUnica(this);
    }
}
