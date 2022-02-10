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
public class ComandoAtribuicao extends Comando{
    public Atribuicao atribuicao;

    public ComandoAtribuicao(Atribuicao atribuicao) {
        this.atribuicao = atribuicao;
    }
    
    public void visit(Visitor v){
        v.visitComandoAtribuicao(this);
    }
}
