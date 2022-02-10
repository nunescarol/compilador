/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import AST.*;
import static javafx.application.Platform.exit;


/**
 *
 * @author Usuario
 */
public class Parser1 {
    
    private Token currentToken;
    private Scanner scanner;
    private int indicador_p, indicador_s;

    public Parser1(String nomeArquivo) throws IOException {
        this.scanner = new Scanner(nomeArquivo);
        this.indicador_p=0;
        this.indicador_s=0;
    }
  
        
	
	private void accept(byte expectedKind) throws IOException{
            //System.out.println("entrou no accept: "+ currentToken.spelling);
            if(currentToken.kind == expectedKind)
                    currentToken = scanner.scan();
            else{
                this.comunicaErro(expectedKind);
            }
	}
	
	private void acceptIt() throws IOException{
		currentToken = scanner.scan();
	}
	
	public Programa parse() throws IOException{
            currentToken = scanner.scan();
            Programa programa = parsePrograma();
            if(currentToken.kind !=Token.EOT){
                this.comunicaErro(Token.EOT);
            }
            return programa;
	}
	
	private void comunicaErro(byte expectedKind){
            System.out.println("Erro na linha: "+this.scanner.linha);
            System.out.println("Esperava-se: "+Token.getSpellings()[expectedKind]);
            System.out.println("E teve: "+currentToken.spelling);
            
            System.exit(0);
            //System.out.println("Token encontrado: "+this.currentToken.kind);
	}

	//<atribuição> ::= <variável> := <expressão> 

	private Atribuicao parseAtribuicao() throws IOException{
            Atribuicao atribuicao;
            Variavel variavel = parseVariavel();
            accept(Token.BECOMES);
            Expressao expressao = parseExpressao();
            atribuicao = new Atribuicao(variavel, expressao);
            return atribuicao;
	}

	//<comando> ::= <atribuição> | <condicional> | <iterativo> | begin (<comando>;)* end
        //<comando-composto> ::= begin <lista-de-comandos> end
        //<lista-de-comandos> ::= (<comando>;)*
	private Comando parseComando() throws IOException{

            Comando comando = null;
            switch(currentToken.kind){
                case Token.IDENTIFIER:

                    Atribuicao atribuicao = parseAtribuicao();
                    comando = new ComandoAtribuicao(atribuicao);
                    //accept(Token.SEMICOLON);
                    break;
                case Token.IF:
                    Condicional condicional = parseCondicional();
                    comando = new ComandoCondicional(condicional);
                    break;
                case Token.WHILE:
                    Iterativo iterativo = parseIterativo();
                    comando = new ComandoIterativo(iterativo);
                    break;
                case Token.BEGIN:
                    comando = parseComandoComposto();
//                    comando = parseComando();
//                    while(currentToken.kind == Token.SEMICOLON){
//                        acceptIt();
//                        Comando comando2 = parseComando();
//                        comando = new ComandoSequencial(comando, comando2);
//                    }
//                    accept(Token.END);
//                    break;
                default:
                    this.comunicaErro(currentToken.kind);
                    exit();
            }
            return comando;
        }

	//<comando-composto> ::= begin <lista-de-comandos> end
        //<lista-de-comandos> ::= (<comando>;)*        
	private Comando parseComandoComposto() throws IOException{
            Comando comando=null;
            if(currentToken.kind ==Token.BEGIN){
                acceptIt();
                comando = parseComando();
                while(currentToken.kind == Token.SEMICOLON){
                    acceptIt();
                    if(currentToken.kind==Token.END)
                        break;
                    Comando comando2 = parseComando();
                    comando = new ComandoSequencial(comando, comando2);
                }
                accept(Token.END);
            }
            else{
                this.comunicaErro(currentToken.kind);
            }
            return comando;
	}

	//<condicional> ::= if<expressão> then <comando> (else <comando>|<vazio>)

	private Condicional parseCondicional() throws IOException{
            CondicionalSE condicional = null;
            if(currentToken.kind  == Token.IF){
                acceptIt();
                Expressao expressao = parseExpressao();
                accept(Token.THEN);
                Comando comando = parseComando();
                condicional = new CondicionalSE(expressao, comando);
                if(currentToken.kind  == Token.ELSE){
                    acceptIt();
                    comando = parseComando();
                    condicional = new CondicionalCE(comando, condicional.expressao, condicional.comando);
                }
            }
            return condicional;
        }

	//<corpo> ::= <declarações> <comando-composto>
	private Corpo parseCorpo () throws IOException{
            Corpo corpo;
            Declaracao declaracoes = parseDeclaracoes();
            Comando comando = parseComandoComposto();
            corpo = new Corpo(declaracoes, comando);
            return corpo;
	}

	//<declaração> ::=  var <lista-de-ids> : <tipo>	;
	private Declaracao parseDeclaracao() throws IOException{

            Declaracao declaracao = null;
            if(currentToken.kind == Token.VAR){
       
                acceptIt();
                ListaDeIds lista = parseListaId();
            
                accept(Token.COLON);
           
                Tipo tipo = parseTipo();
                accept(Token.SEMICOLON);
                declaracao = new DeclaracaoSimples(lista, tipo);
            }
            return declaracao;
	}	
		
	//<declaração-de-variável> ::= var <lista-de-ids> : <tipo>	OBSOLETA
	/*private void parseDeclaracaoVariavel(){
		if(currentToken == Token.VAR){
			acceptIt();
			parseListaId();
			accept(Token.COLON);
			parseTipo();
		}
	}*/

	//<declarações> ::= (<declaração>;)*
	private Declaracao parseDeclaracoes() throws IOException{
            Declaracao declaracao=null;
   
            declaracao = parseDeclaracao();
            while(currentToken.kind == Token.VAR){
           
                Declaracao declaracao2 = parseDeclaracao();
                declaracao = new DeclaracaoSequencial(declaracao, declaracao2);
            }
            return declaracao;
	}

	//<expressão> ::= <expressão-simples> (<op-rel> <expressão-simples>)*
	private Expressao parseExpressao() throws IOException{
            Expressao expressao;

            ExpressaoSimples expressaoSimples = parseExpressaoSimples();
      
            
            expressao = new ExpressaoUnica(expressaoSimples);
            while(currentToken.kind ==  Token.OPERATORREL){
                Token operadorRel;
                operadorRel = new Token(currentToken.kind, currentToken.spelling);
                acceptIt();
                expressaoSimples = parseExpressaoSimples();
           
                expressao = new ExpressaoSequencial(operadorRel, expressao, expressaoSimples);
            }
            return expressao;
	}
		
	//<expressão-simples> ::= <termo>(<op-ad> <termo>)*
	private ExpressaoSimples parseExpressaoSimples () throws IOException{
            ExpressaoSimples expressaoSimples;
            Termo termo = parseTermo();
            expressaoSimples = new ExpressaoSimplesUnica(termo);
		while(currentToken.kind == Token.OPERATORAD){
                    Token operadorAd = new Token(currentToken.kind, currentToken.spelling);
                    acceptIt();
  
                    termo = parseTermo();

                    expressaoSimples = new ExpressaoSimplesSequencial(operadorAd, expressaoSimples, termo);
		}
    
            return expressaoSimples;
	}
		
	//<fator> ::= <variável> | <literal> 	| "(" <expressão> ")" 
	//<fator> ::= <id> <seletor>|<bool-lit>| <int-lit> | <float-lit> | "(" <expressão> ")" 	
	private Fator parseFator() throws IOException{
            Fator fator = null;
            switch(currentToken.kind){
                case Token.IDENTIFIER:
                    Variavel variavel = parseVariavel();
                    fator = new FatorVariavel(variavel);
                    break;
                case Token.BOOLEAN: case Token.FLOATLITERAL: case Token.INTLITERAL:
                    Literal literal = parseLiteral();
                    fator = new FatorLit(literal);
                    break;
                case Token.LPAREN:
                    acceptIt();
                    Expressao expressao = parseExpressao();
                    fator = new FatorExpressao(expressao);
                    accept(Token.RPAREN);
                    break;
                default:
//                    this.comunicaErro();
                    exit();
            }
            return fator;
    }

	//<iterativo> ::= while <expressão> do <comando>
	private Iterativo parseIterativo() throws IOException{
            Iterativo iterativo;
            indicador_p = 11;
		accept(Token.WHILE);
		Expressao expressao = parseExpressao();
		accept(Token.DO);
		Comando comando = parseComando();
                iterativo = new Iterativo(expressao, comando);
                return iterativo;
	}

	//<lista-de-comandos> ::= (<comando>;)*
	// <lista-de-comandos> ::= (<id> <seletor> := <expressão> | if <expressão> then <comando> ( else <comando> | <vazio> ) | while <expressão> do <comando>	| begin <lista-de-comandos> end	)*
	private Comando parseListaComandos() throws IOException{
            Comando comando=null;
            if((currentToken.kind == Token.IF) || (currentToken.kind == Token.IDENTIFIER) || currentToken.kind == Token.WHILE || currentToken.kind == Token.BEGIN){
                comando = parseComando();
            }
            else
                exit();
            while((currentToken.kind == Token.IF) || (currentToken.kind == Token.IDENTIFIER) || currentToken.kind == Token.WHILE || currentToken.kind == Token.BEGIN){
                Comando comando2 = parseComando();
                comando = new ComandoSequencial(comando, comando2);
                accept(Token.SEMICOLON);
            }
            return comando;
	}

//<lista-de-ids> ::= <id>(,<id>)*
	private ListaDeIds parseListaId () throws IOException{

            ListaDeIds lista = new ListaDeIdsSimples(currentToken);
            accept(Token.IDENTIFIER);
            while(currentToken.kind == Token.COMMA){
                acceptIt();
                ListaDeIds lista2 = parseListaId();
                lista = new ListaDeIdsSequencial(lista, lista2);
                //accept(Token.IDENTIFIER);
           
            }
         
            return lista;
	}
			


	private Literal parseLiteral () throws IOException{
            Literal literal =null;
            switch(currentToken.kind){	
                case Token.BOOLEAN: case Token.FLOATLITERAL: case Token.INTLITERAL:
                    literal = new Literal(currentToken);
                    acceptIt();
                    break;
                default:
//                    this.comunicaErro();
            }
            return literal;
	}

	//<programa> ::= program<id>;<corpo>.
	private Programa parsePrograma () throws IOException{

            Programa programa;
            Token identificador;
            accept(Token.PROGRAM);
            identificador = new Token(currentToken.kind, currentToken.spelling);
            accept(Token.IDENTIFIER);
            accept(Token.SEMICOLON);
 
            Corpo corpo = parseCorpo();
            accept(Token.FULLSTOP);
            programa  = new Programa(identificador, corpo);
            return programa;
	}

	//<seletor> ::= ("[" <expressão> "]")*
	private Seletor parseSeletor() throws IOException{
            Seletor seletor=null;
            if(this.currentToken.kind == Token.LBRACK){
                acceptIt();
                Expressao expressao = parseExpressao();
                seletor = new SeletorSimples(expressao);
                accept(Token.RBRACK);
            }
            else
                exit();
            while(this.currentToken.kind == Token.LBRACK){
                acceptIt();
                Expressao expressao = parseExpressao();
                seletor = new SeletorSequencial(seletor, expressao);
                accept(Token.RBRACK);
            }
            return seletor;
	}

	//<termo> ::= <fator> (<op-mul> <fator>)*
	private Termo parseTermo () throws IOException{
            Termo termo ;
            Fator fator = parseFator();
            termo = new TermoUnico(fator);
         
            while(currentToken.kind == Token.OPERATORMUL){
               
                Token operadorMul = currentToken;
                acceptIt();
                fator = parseFator();
                Termo termo2 = new TermoUnico(fator);
                termo = new TermoSequencial(operadorMul, termo, termo2);
            }
            return termo;
	}

	//<tipo> ::= array [ <literal> .. <literal> ] of <tipo> | integer | real | boolean
	private Tipo parseTipo() throws IOException{
            Tipo tipo=null;
            switch(currentToken.kind){
                case Token.ARRAY:
                    acceptIt();
                    accept(Token.LBRACK);
                    Literal literal1 = parseLiteral();
                    accept(Token.PONTOVETOR);
                    Literal literal2 = parseLiteral();
                    accept(Token.RBRACK);
                    accept(Token.OF);
                    Tipo tipo2 = parseTipo();
                    tipo = new TipoAgregado(literal1, literal2, tipo);
                    break;
                case Token.INTEGER: case Token.REAL: case Token.BOOLEAN:
                    tipo = new TipoSimples(currentToken);
                    acceptIt();
                    break;
                default:
//                    this.comunicaErro();
                    exit();
            }
            return tipo;
        }


	//private void parseTipoAgregado ();
	//private void parseTipoSimples ();


	private Variavel parseVariavel() throws IOException{
            Variavel variavel;
            Token identificador = currentToken;

            accept(Token.IDENTIFIER);
            Seletor seletor = parseSeletor();
            variavel = new Variavel(identificador, seletor);
            return variavel;
	}
    
}
