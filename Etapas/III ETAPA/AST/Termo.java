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
public abstract class Termo extends ExpressaoSimples{
    public abstract void visit(Visitor v);
}
