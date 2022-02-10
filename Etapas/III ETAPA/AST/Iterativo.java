/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST;

import visitor.Visitor;

/**
 *
 * @author Usuario
 */
public class Iterativo extends AST{
    public Expressao expressao;
    public Comando comando;
    
    public Iterativo(Expressao expressao, Comando ccomando) {
        this.expressao = expressao;
        this.comando = comando;
    }
    
    public void visit(Visitor v){
        v.visitIterativo(this);
    }
}
