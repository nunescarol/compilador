/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visitor;

import AST.*;

/**
 *
 * @author Usuario
 */
public interface Visitor {
    public void visitAtribuicao(Atribuicao a);
    
    public void visitComando(Comando c);
    public void visitComandoAtribuicao(ComandoAtribuicao ca);
    public void visitComandoCondicional(ComandoCondicional cc);
    public void visitComandoIterativo(ComandoIterativo ci);
    public void visitComandoSequencial(ComandoSequencial cs);
    
    public void visitCondicional(Condicional c);
    public void visitCondicionalCE(CondicionalCE cce);
    public void visitCondicionalSE(CondicionalSE cse);
    
    public void visitCorpo(Corpo c);
    
    public void visitDeclaracao(Declaracao d);
    public void visitDeclaracaoSequencial(DeclaracaoSequencial dec_seq);
    public void visitDeclaracaoSimples(DeclaracaoSimples dec_simp);
    
    public void visitExpressao(Expressao e);
    public void visitExpressaoSequencial(ExpressaoSequencial ex_seq);
    public void visitExpressaoUnica(ExpressaoUnica ex_unica);
    
    public void visitExpressaoSimples(ExpressaoSimples es);
    public void visitExpressaoSimplesSequencial(ExpressaoSimplesSequencial exp_simp_seq);
    public void visitExpressaoSimplesUnica(ExpressaoSimplesUnica exp_simp_unica);
    
    public void visitFator(Fator f);
    public void visitFatorExpressao(FatorExpressao f_exp);
    public void visitFatorLit(FatorLit f_lit);
    public void visitFatorVariavel(FatorVariavel f_var);
    
    public void visitIterativo(Iterativo it);
    
    public void visitListaDeIds(ListaDeIds l);
    public void visitListaDeIdsSequencial(ListaDeIdsSequencial lista_seq);
    public void visitListaDeIdsSimples(ListaDeIdsSimples lista_simp);
    
    public void visitLiteral(Literal lit);
    
    public void visitPrograma(Programa p);
    
    public void visitSeletor(Seletor s);
    public void visitSeletorSequencial(SeletorSequencial sel_seq);
    public void visitSeletorSimples(SeletorSimples sel_simp);
    
    public void visitTermo(Termo t);
    public void visitTermoUnico(TermoUnico termo_unico);
    public void visitTermoSequencial(TermoSequencial termo_seq);
    
    public void visitTipo(Tipo tipo);
    public void visitTipoAgregado(TipoAgregado tipo_a);
    public void visitTipoSimples(TipoSimples tipo_s);
    
    public void visitVariavel(Variavel var);
    
}
