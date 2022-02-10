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
public class TipoAgregado extends Tipo{
    public Literal literal1, literal2;
    public Tipo tipo;

    public TipoAgregado(Literal literal1, Literal literal2, Tipo tipo) {
        this.literal1 = literal1;
        this.literal2 = literal2;
        this.tipo = tipo;
    }
    
    public void visit(Visitor v){
        v.visitTipoAgregado(this);
    }
}
