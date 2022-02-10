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
public class Atribuicao extends AST{
    public Variavel variavel;
    public Expressao expressao;

    public Atribuicao(Variavel variavel, Expressao expressao) {
        this.variavel = variavel;
        this.expressao = expressao;
    }
    
    public void visit(Visitor v){
        v.visitAtribuicao(this);
    }
        
        
}
