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
public class DeclaracaoSequencial extends Declaracao{
    public Declaracao declaracao1, declaracao2;

    public DeclaracaoSequencial(Declaracao declaracao1, Declaracao declaracao2) {
        this.declaracao1 = declaracao1;
        this.declaracao2 = declaracao2;
    }
    
    public void visit(Visitor v){
        v.visitDeclaracaoSequencial(this);
    }
    
}
