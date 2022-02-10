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
public class Corpo extends AST{
    public Declaracao declaracao;
    public Comando comando;

    public Corpo(Declaracao declaracao, Comando comando) {
        this.declaracao = declaracao;
        this.comando = comando;
    }
    
    public void visit(Visitor v){
        v.visitCorpo(this);
    }
        
}
