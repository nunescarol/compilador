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
public class CondicionalSE extends Condicional{
    public Expressao expressao;
    public Comando comando;

    public CondicionalSE(Expressao expressao, Comando comando) {
        this.expressao = expressao;
        this.comando = comando;
    }
    
    public void visit(Visitor v){
        v.visitCondicionalSE(this);
    }
}