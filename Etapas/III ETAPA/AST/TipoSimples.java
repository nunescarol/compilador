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
public class TipoSimples extends Tipo{
    public Token tipo;

    public TipoSimples(Token tipo) {
        this.tipo = tipo;
    }
    
    public void visit(Visitor v){
        v.visitTipoSimples(this);
    }
}
