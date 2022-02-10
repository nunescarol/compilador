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
public class Programa extends AST{
    public Token identificador;
    public Corpo corpo;

    public Programa(Token identificador, Corpo corpo) {
        this.identificador = identificador;
        this.corpo = corpo;
    }
    
    public void visit(Visitor v){
        v.visitPrograma(this);
    }
	
}
