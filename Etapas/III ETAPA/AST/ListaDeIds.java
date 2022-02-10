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
public abstract class ListaDeIds {
    public abstract void visit(Visitor v);
    Token identificador;
    public Token getToken() {
        return this.identificador;
    }
    
}
