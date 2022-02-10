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
public class Literal extends AST{
    public Token literal;

    public Literal(Token literal) {
        this.literal = literal;
    }
    
    public void visit(Visitor v){
        v.visitLiteral(this);
    }
}