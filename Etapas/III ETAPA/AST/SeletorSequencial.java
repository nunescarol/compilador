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
public class SeletorSequencial extends Seletor{
    public Seletor seletor;
    public Expressao expressao;

    public SeletorSequencial(Seletor seletor, Expressao expressao) {
        this.seletor = seletor;
        this.expressao = expressao;
    }
    
    public void visit(Visitor v){
        v.visitSeletorSequencial(this);
    }
    
}
