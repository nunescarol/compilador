/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador;

import AST.*;

import visitor.Visitor;

/**
 *
 * @author Usuario
 */
public class Printer implements Visitor{
    int i=0;
    
    private void indent(String nodeInfo, int indentLevel){
    	for(int x=0; x<=indentLevel/2;x++){
        	System.out.print("|  ");
            System.out.print("  ");
        }
    	System.out.println(nodeInfo);
    }

    @Override
    public void visitAtribuicao(Atribuicao a) {
        i++;
        indent("Atribuicao", i);
        if(a!=null){
            if(a.expressao!=null){
                i++;
                a.expressao.visit(this);
                i--;
            }
            if(a.variavel!=null){
                i++;
                a.variavel.visit(this);
                i--;
            }
        }
        i--;
    }
    
    @Override
    public void visitComando(Comando c) {
        i++;
        indent("Comando", i);
        if(c!=null){
            if(c instanceof ComandoAtribuicao){
                i++;
                ((ComandoAtribuicao)c).visit(this);
                i--;
            }else
                if(c instanceof ComandoCondicional){
                    i++;
                    ((ComandoCondicional)c).visit(this);
                    i--;
                }else
                    if(c instanceof ComandoIterativo){
                        i++;
                        ((ComandoIterativo)c).visit(this);
                        i--;
                    }else{
                        i++;
                        ((ComandoSequencial)c).visit(this);
                        i--;
                    }
        }
        i--;
    }

    @Override
    public void visitComandoAtribuicao(ComandoAtribuicao ca) {
        i++;
        indent("ComandoAtribuicao", i);
        if(ca!=null)
            if(ca.atribuicao!=null){
                i++;
                ca.atribuicao.visit(this);
                i--;
            }
        i--;
    }

    @Override
    public void visitComandoCondicional(ComandoCondicional cc) {
        i++;
        indent("ComandoCondicional", i);
        if(cc!=null)
            if(cc.condicional!=null){
                i++;
                cc.condicional.visit(this);
                i--;
            }
        i--;
    }

    @Override
    public void visitComandoIterativo(ComandoIterativo ci) {
        i++;
        indent("ComandoIterativo", i);
        if(ci!=null)
            if(ci.iterativo!=null){
                i++;
                ci.iterativo.visit(this);
                i--;
            }
        i--;
    }

    @Override
    public void visitComandoSequencial(ComandoSequencial cs) {
        i++;
        indent("Comando Sequencial", i);
        if(cs!=null){
            if(cs.comando1!=null){
                i++;
                cs.comando1.visit(this);
                i--;
            }
            if(cs.comando2!=null){
                i++;
                cs.comando2.visit(this);
                i--;
            }
        }
        i--;
    }
    
    
    @Override
    public void visitCondicional(Condicional c) {
        i++;
        indent("Condicional", i);
        if(c!=null){
            if(c instanceof CondicionalSE){
                i++;
                ((CondicionalSE)c).visit(this);
                i--;
            }
            else{
                i++;
                ((CondicionalCE)c).visit(this);
                i--;
            }
        }
    }

    @Override
    public void visitCondicionalCE(CondicionalCE cce) {
        i++;
        indent("Condicional com ELSE", i);
        if(cce!=null)
            if(cce.comando2!=null){
                i++;
                cce.comando2.visit(this);
                i--;
            }
        i--;
    }

    @Override
    public void visitCondicionalSE(CondicionalSE cse) {
        i++;
        indent("Condicional sem else", i);
        if(cse!=null){
            if(cse.expressao!=null){
                i++;
                cse.expressao.visit(this);
                i--;
            }
            if(cse.comando!=null){
                i++;
                cse.comando.visit(this);
                i--;
            }
        }
        i--;
    }

    @Override
    public void visitCorpo(Corpo c) {
        i++;
        indent("Corpo", i);
        if(c!=null){
            if(c.declaracao!=null){
                i++;
                c.declaracao.visit(this);
                i--;
            }
            if(c.comando!=null){
                i++;
                c.comando.visit(this);
                i--;
            }
        }
        i--;
    }
    
    
    @Override
    public void visitDeclaracao(Declaracao d) {
        i++;
        indent("Declaracao", i);
        if(d!=null){
            if(d instanceof DeclaracaoSimples){
                i++;
                ((DeclaracaoSimples)d).visit(this);
                i--;
            }else{
                i++;
                ((DeclaracaoSequencial)d).visit(this);
                i--;
            }
        }
        i--;
    }

    @Override
    public void visitDeclaracaoSequencial(DeclaracaoSequencial dec_seq) {
        i++;
        indent("Declaracao Sequencial", i);
        if(dec_seq!=null){
            if(dec_seq.declaracao1!=null){
                i++;
                dec_seq.declaracao1.visit(this);
                i--;
            }
            if(dec_seq.declaracao2!=null){
                i++;
                dec_seq.declaracao2.visit(this);
                i--;
            }
        }
        
        i--;
    }

    @Override
    public void visitDeclaracaoSimples(DeclaracaoSimples dec_simp) {
        i++;
        indent("Declaracao Simples", i);
        if(dec_simp!=null){
            if(dec_simp.lista_ids!=null){
                i++;
                dec_simp.lista_ids.visit(this);
                i--;
            }
            if(dec_simp.tipo!=null){
                i++;
                dec_simp.tipo.visit(this);
                i--;
            }
        }
        i--;
    }
    
    
    @Override
    public void visitExpressao(Expressao e) {
        i++;
        indent("Expressao", i);
        if(e!=null){
            if(e instanceof ExpressaoUnica){
                i++;
                ((ExpressaoUnica)e).visit(this);
                i--;
            }
            else{
                i++;
                ((ExpressaoSequencial)e).visit(this);
                i--;
            }
        }
        i--;
    }

    @Override
    public void visitExpressaoSequencial(ExpressaoSequencial ex_seq) {
        i++;
        indent("Expressao Sequencial", i);
        if(ex_seq!=null){
            if(ex_seq.operadorRel!=null){
                i++;
                indent(ex_seq.operadorRel.spelling, i+1);
                i--;
            }
            if(ex_seq.esquerda!=null){
                i++;
                ex_seq.esquerda.visit(this);
                i--;
            }
            if(ex_seq.direita!=null){
                i++;
                ex_seq.direita.visit(this);
                i--;
            }
        }
        i--;
    }

    @Override
    public void visitExpressaoUnica(ExpressaoUnica ex_unica) {
        i++;
        indent("Expressao Unica", i);
        if(ex_unica!=null)
            if(ex_unica.expressaoSimples!=null){
                i++;
                ex_unica.expressaoSimples.visit(this);
                i--;
            }
        i--;
    }
    
    
    @Override
    public void visitExpressaoSimples(ExpressaoSimples es) {
        i++;
        indent("Expressao Simples", i);
        if(es!=null){
            if(es instanceof ExpressaoSimplesUnica){
                i++;
                ((ExpressaoSimplesUnica)es).visit(this);
                i--;
            }
            else{
                i++;
                ((ExpressaoSimplesSequencial)es).visit(this);
                i--;
            }
        }
        i--;
    }

    @Override
    public void visitExpressaoSimplesSequencial(ExpressaoSimplesSequencial exp_simp_seq) {
        i++;
        indent("Expressao Simples Sequencial", i);
        if(exp_simp_seq!=null){
            i++;
            if(exp_simp_seq.operadorAd!=null)
                indent(exp_simp_seq.operadorAd.spelling, i+1);
            if(exp_simp_seq.esquerda!=null)
                exp_simp_seq.esquerda.visit(this);
            if(exp_simp_seq.direita!=null)
                exp_simp_seq.direita.visit(this);
            i--;
        }
    }

    @Override
    public void visitExpressaoSimplesUnica(ExpressaoSimplesUnica exp_simp_unica) {
        i++;
        if(exp_simp_unica!=null){
            i++;
            exp_simp_unica.termo.visit(this);
            i--;
        }
        i--;
    }
    
    
    @Override
    public void visitFator(Fator f) {
        i++;
        indent("Fator", i);
        if(f!=null){
            i++;
            if(f instanceof FatorExpressao)
                ((FatorExpressao)f).visit(this);
            else
                if(f instanceof FatorLit)
                    ((FatorLit)f).visit(this);
                else
                    ((FatorVariavel)f).visit(this);
            i--;
        }
        i--;
    }

    @Override
    public void visitFatorExpressao(FatorExpressao f_exp) {
        i++;
        indent("Fator Expressao", i);
        if(f_exp!=null){
            i++;
            f_exp.expressao.visit(this);
            i--;
        }
        i--;
    }

    @Override
    public void visitFatorLit(FatorLit f_lit) {
        i++;
        indent("Fator Literal", i);
        if(f_lit!=null){
            i++;
            f_lit.literal.visit(this);
            i--;
        }
        i--;
    }

    @Override
    public void visitFatorVariavel(FatorVariavel f_var) {
        i++;
        indent("Fator Variavel", i);
        if(f_var!=null){
            i++;
            f_var.variavel.visit(this);
            i--;
        }
        i--;
    }

    @Override
    public void visitIterativo(Iterativo it) {
        i++;
        indent("Iterativo", i);
        if(it!=null){
            i++;
            if(it.expressao!=null)
                it.expressao.visit(this);
            if(it.comando!=null)
                it.comando.visit(this);
            i--;
        }
    }
    
    
    @Override
    public void visitListaDeIds(ListaDeIds l) {
        i++;
        indent("Lista de Ids", i);
        if(l!=null){
            i++;
            if(l instanceof ListaDeIdsSimples)
                ((ListaDeIdsSimples)l).visit(this);
            else
                ((ListaDeIdsSequencial)l).visit(this);
            i--;
        }
    }

    @Override
    public void visitListaDeIdsSequencial(ListaDeIdsSequencial lista_seq) {
        i++;
        indent("Lista de Ids Sequencial", i);
        if(lista_seq!=null){
            i++;
            if(lista_seq.esquerda!=null)
                lista_seq.esquerda.visit(this);
            if(lista_seq.direita!=null)
                lista_seq.direita.visit(this);
            i--;
        }
        i--;
    }

    @Override
    public void visitListaDeIdsSimples(ListaDeIdsSimples lista_simp) {
        i++;
        indent("Lista de Ids Simples", i);
        if(lista_simp!=null)
            if(lista_simp.identificador!=null){
                i++;
                indent(lista_simp.identificador.spelling, i+1);
                i--;
            }
        i--;
    }

    @Override
    public void visitLiteral(Literal lit) {
        i++;
        indent("Literal", i);
        if(lit!=null)
            if(lit.literal!=null){
                i++;
                indent(lit.literal.spelling, i);
                i--;
            }
        i--;
    }

    @Override
    public void visitPrograma(Programa p) {
        i++;
        indent("Programa", i);
        if(p!=null){
            i++;
            if(p.identificador!=null)
                indent(p.identificador.spelling, i+1);
            if(p.corpo!=null){
                //System.out.println("entruo no corpo");
                p.corpo.visit(this);
            }
            i--;
        }
        i--;
    }
    
    @Override
    public void visitSeletor(Seletor s) {
        i++;
        indent("Seletor", i);
        if(s!=null){
            i++;
            if(s instanceof SeletorSequencial)
                ((SeletorSequencial)s).visit(this);
            else
                ((SeletorSimples)s).visit(this);
            i--;
        }
        i--;
    }

    @Override
    public void visitSeletorSequencial(SeletorSequencial sel_seq) {
        i++;
        indent("Seletor sequencial", i);
        if(sel_seq!=null){
            i++;
            if(sel_seq.seletor!=null)
                sel_seq.seletor.visit(this);
            if(sel_seq.expressao!=null)
                sel_seq.expressao.visit(this);
            i--;
        }
        i--;
    }

    @Override
    public void visitSeletorSimples(SeletorSimples sel_simp) {
        i++;
        indent("Seletor simples", i);
        if(sel_simp!=null)
            if(sel_simp.expressao!=null){
                i++;
                sel_simp.expressao.visit(this);
                i--;
            }
        i--;
    }
    
    

    @Override
    public void visitTermo(Termo t) {
        i++;
        indent("Termo", i);
        if(t!=null){
            i++;
            if(t instanceof TermoUnico)
                ((TermoUnico)t).visit(this);
            else
                ((TermoSequencial)t).visit(this);
            i--;
        }
        i--;
    }

    @Override
    public void visitTermoUnico(TermoUnico termo_unico) {
        i++;
        indent("Termo Unico", i);
        if(termo_unico!=null)
            if(termo_unico.fator!=null){
                i++;
                termo_unico.fator.visit(this);
                i--;
            }
        i--;
    }

    @Override
    public void visitTermoSequencial(TermoSequencial termo_seq) {
        i++;
        indent("Termo Sequencial", i);
        if(termo_seq!=null){
            i++;
            if(termo_seq.operadorMul!=null)
               indent(termo_seq.operadorMul.spelling, i+1);
            if(termo_seq.esquerda!=null)
                termo_seq.esquerda.visit(this);
            if(termo_seq.direita!=null)
                termo_seq.direita.visit(this);
            i--;
        }
        i--;
    }
    
    
    @Override
    public void visitTipo(Tipo tipo) {
        i++;
        indent("Tipo", i);
        if(tipo!=null){
            i++;
            if(tipo instanceof TipoAgregado)
                ((TipoAgregado)tipo).visit(this);
            else
                ((TipoSimples)tipo).visit(this);
            i--;
        }
        i--;
    }

    @Override
    public void visitTipoAgregado(TipoAgregado tipo_a) {
        i++;
        indent("Tipo agregado", i);
        if(tipo_a!=null){
            i++;
            if(tipo_a.literal1!=null)
                tipo_a.literal1.visit(this);
            if(tipo_a.literal2!=null)
                tipo_a.literal1.visit(this);
            if(tipo_a.tipo!=null)
                tipo_a.tipo.visit(this);
            i--;
        }
        i--;
    }

    @Override
    public void visitTipoSimples(TipoSimples tipo_s) {
        i++;
        indent("Tipo simples", i);
        if(tipo_s!=null)
            if(tipo_s.tipo!=null){
                indent(tipo_s.tipo.spelling, i);
            }
        i--;
    }

    @Override
    public void visitVariavel(Variavel var) {
        i++;
        indent("Variavel", i);
        if(var!=null){
            i++;
            if(var.identificador!=null)
                indent(var.identificador.spelling, i);
            if(var.seletor!=null)
                var.seletor.visit(this);
            i--;
        }
        i--;
    }
    
    public void print(Programa programa){
        System.out.println("---Iniciando impressão da árvore");
        programa.visit(this);
    }









    
}
