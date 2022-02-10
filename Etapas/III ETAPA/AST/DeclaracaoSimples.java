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
public class DeclaracaoSimples extends Declaracao{
    public ListaDeIds lista_ids;
    public Tipo tipo;

    public DeclaracaoSimples(ListaDeIds lista_ids, Tipo tipo) {
        this.lista_ids = lista_ids;
        this.tipo = tipo;
    }
    
    public void visit(Visitor v){
        v.visitDeclaracaoSimples(this);
    }
}
