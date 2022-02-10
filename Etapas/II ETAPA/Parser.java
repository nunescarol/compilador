/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testelexico;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


/**
 *
 * @author Usuario
 */
public class Parser {
    
    private Token currentToken;
    private Scanner scanner;
    private int indicador_p, indicador_s;

    public Parser(String nomeArquivo) throws IOException {
        this.scanner = new Scanner(nomeArquivo);
        this.indicador_p=0;
        this.indicador_s=0;
    }
  
        
	
	private void accept(byte expectedKind) throws IOException{
		if(currentToken.kind == expectedKind)
			currentToken = scanner.scan();
                else{
                    this.comunicaErro();
                    //System.out.println("Token esperado: "+expectedKind);
                }
	}
	
	private void acceptIt() throws IOException{
		currentToken = scanner.scan();
	}
	
	public void parse() throws IOException{
		currentToken = scanner.scan();
		parsePrograma();
		if(currentToken.kind !=Token.EOT){
			this.comunicaErro();
                }
	}
	
	private void comunicaErro(){
		System.out.println("Erro na linha: "+this.scanner.linha);
                System.out.println("Erro do tipo: "+this.indicador_p+", "+this.indicador_s);
                //System.out.println("Token encontrado: "+this.currentToken.kind);
	}

	//<atribuição> ::= <variável> := <expressão> 

	private void parseAtribuicao() throws IOException{
            indicador_p = 1;
		if(currentToken.kind ==  Token.IDENTIFIER){
			acceptIt();
			accept(Token.BECOMES);
			parseExpressao();
		}else{
			this.comunicaErro();
                }
	}

	//<comando> ::= 	<atribuição> | <condicional> | <iterativo> 	| <comando-composto>

	//<comando> ::= 	<id> <seletor> := <expressão>  | if <expressão> then <comando> ( else <comando> | <vazio> ) | while <expressão> do <comando>	| begin <lista-de-comandos> end	

	private void parseComando() throws IOException{
            System.out.println("Comando");
            indicador_p = 2;
		switch(currentToken.kind){
			case Token.IDENTIFIER:
//				acceptIt();
//				accept(Token.BECOMES);
//				parseExpressao();
//				break;
                                parseAtribuicao();
                            break;
			case Token.IF:
//				acceptIt();
//				parseExpressao();
//				accept(Token.THEN);
//				parseComando();
//				if(currentToken.kind  == Token.ELSE){
//					acceptIt();
//					parseComando();
//				}
                            parseCondicional();
				break;
			case Token.WHILE:
//				acceptIt();
//				parseExpressao();
//				accept(Token.DO);
//				parseComando();
                            parseIterativo();
				break;
			case Token.BEGIN:
//				acceptIt();
//				parseListaComandos();
//				accept(Token.END);
                            parseComandoComposto();
				break;
			default:
				this.comunicaErro();
	}
        }

	//<comando-composto> ::= 	begin <lista-de-comandos> end
	private void parseComandoComposto() throws IOException{
            System.out.println("ComandoComposto");
            indicador_p = 3;
                //System.out.println(this.currentToken.spelling);
		if(currentToken.kind ==Token.BEGIN){
			acceptIt();
			parseListaComandos();
			accept(Token.END);
		}
                else{
                    this.comunicaErro();
                }
	}

	//<condicional> ::= if<expressão> then <comando> (else <comando>|<vazio>)

	private void parseCondicional() throws IOException{
            System.out.println("Condicional");
            indicador_p = 4;
		if(currentToken.kind  == Token.IF){
			acceptIt();
			parseExpressao();
			accept(Token.THEN);
			parseComando();
			if(currentToken.kind  == Token.ELSE){
				acceptIt();
				parseComando();
			}
                }
        }

	//<corpo> ::= <declarações> <comando-composto>
	private void parseCorpo () throws IOException{
            System.out.println("Corpo");
            indicador_p = 5;
		parseDeclaracoes();
		parseComandoComposto();
	}

	//<declaração> ::=  var <lista-de-ids> : <tipo>	
	private void parseDeclaracao() throws IOException{
            System.out.println("Declaracao");
            indicador_p = 6;
		if(currentToken.kind == Token.VAR){
			acceptIt();
			parseListaId();
			accept(Token.COLON);
			parseTipo();
		}
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
	private void parseDeclaracoes() throws IOException{
            System.out.println("Declaracoes");
            indicador_p = 7;
		while(currentToken.kind == Token.VAR){
			parseDeclaracao();
                        accept(Token.SEMICOLON);
		}
	}

	//<expressão> ::= <expressão-simples> (<op-rel> <expressão-simples>)*
	private void parseExpressao() throws IOException{
            System.out.println("Expressao");
            indicador_p = 8;
		parseExpressaoSimples();
		while(currentToken.kind ==  Token.OPERATORREL){
			acceptIt();
			parseExpressaoSimples();
		}
		/*if(currentToken.kind !=  Token.OPERATORREL){
			this.comunicaErro();
                }*/
	}
		
	//<expressão-simples> ::= <termo>(<op-ad> <termo>)*
	private void parseExpressaoSimples () throws IOException{
            System.out.println("ExpressaoSimples");
            indicador_p = 9;
		parseTermo();
		while(currentToken.kind == Token.OPERATORAD){
			acceptIt();
			parseTermo();
		}
		/*if(currentToken.kind != Token.OPERATORAD){
			this.comunicaErro();
                }*/
	}
		
	//<fator> ::= <variável> | <literal> 	| "(" <expressão> ")" 
	//<fator> ::= <id> <seletor>|<bool-lit>| <int-lit> | <float-lit> | "(" <expressão> ")" 	
	private void parseFator() throws IOException{
            System.out.println("Fator");
            indicador_p = 10;
		switch(currentToken.kind){
			case Token.IDENTIFIER:
                                this.indicador_s=1;
				acceptIt();
				parseSeletor();
				break;
			case Token.BOOLEAN: case Token.FLOATLITERAL: case Token.INTLITERAL:
                                acceptIt();
				break;
			case Token.LPAREN:
                            this.indicador_s=3;
				acceptIt();
				parseExpressao();
				accept(Token.RPAREN);
				break;
			default:
				this.comunicaErro();
	}
        }

	//<iterativo> ::= while <expressão> do <comando>
	private void parseIterativo() throws IOException{
            System.out.println("Iterativo");
            indicador_p = 11;
		accept(Token.WHILE);
		parseExpressao();
		accept(Token.DO);
		parseComando();
	}

	//<lista-de-comandos> ::= (<comando>;)*
	// <lista-de-comandos> ::= (<id> <seletor> := <expressão> | if <expressão> then <comando> ( else <comando> | <vazio> ) | while <expressão> do <comando>	| begin <lista-de-comandos> end	)*
	private void parseListaComandos() throws IOException{
            System.out.println("ListaComandos");
            indicador_p = 12;
		while((currentToken.kind == Token.IF) || (currentToken.kind == Token.IDENTIFIER) || currentToken.kind == Token.WHILE || currentToken.kind == Token.BEGIN){
                    parseComando();
                    accept(Token.SEMICOLON);
                        
		}
	}

//<lista-de-ids> ::= <id>(,<id>)*
	private void parseListaId () throws IOException{
            System.out.println("ListaIds");
            indicador_p = 13;
		accept(Token.IDENTIFIER);
		while(currentToken.kind == Token.COMMA){
			acceptIt();
			accept(Token.IDENTIFIER);
		}
		/*if(currentToken.kind != Token.COMMA){
			this.comunicaErro();
                }*/
	}
			


	private void parseLiteral () throws IOException{
            System.out.println("Literal");
            indicador_p = 14;
		switch(currentToken.kind){	
			case Token.BOOLEAN: case Token.FLOATLITERAL: case Token.INTLITERAL:
				acceptIt();
				break;
			default:
				this.comunicaErro();
		}
	}

	//<programa> ::= program<id>;<corpo>.
	private void parsePrograma () throws IOException{
            System.out.println("Programa");
            indicador_p = 15;
		accept(Token.PROGRAM);
		accept(Token.IDENTIFIER);
		accept(Token.SEMICOLON);
		parseCorpo();
                accept(Token.FULLSTOP);
	}

	//<seletor> ::= ("[" <expressão> "]")*
	private void parseSeletor() throws IOException{
            System.out.println("Seletor");
            indicador_p = 16;
            if(this.currentToken.kind == Token.LBRACK){
                acceptIt();
                parseExpressao();
                accept(Token.RBRACK);
            }
	}

	//<termo> ::= <fator> (<op-mul> <fator>)*
	private void parseTermo () throws IOException{
             System.out.println("Termo");
            indicador_p = 17;
		parseFator();
		while(currentToken.kind == Token.OPERATORMUL){
			acceptIt();
			parseFator();
		}
		/*if(currentToken.kind != Token.OPERATORMUL){
			this.comunicaErro();
                }*/
		
	}

	//<tipo> ::= array [ <literal> .. <literal> ] of <tipo> | integer | real | boolean
	private void parseTipo() throws IOException{
            System.out.println("Tipo");
            indicador_p = 18;
		switch(currentToken.kind){
			case Token.ARRAY:
				acceptIt();
				accept(Token.LBRACK);
				parseLiteral();
				accept(Token.PONTOVETOR);
				accept(Token.RBRACK);
				accept(Token.OF);
				parseTipo();
				break;
			case Token.INTEGER: case Token.REAL: case Token.BOOLEAN:
				acceptIt();
				break;
			default:
				this.comunicaErro();
	}
        }


	//private void parseTipoAgregado ();


	//private void parseTipoSimples ();


	private void parseVariavel() throws IOException{
            System.out.println("Variavel");
            indicador_p = 19;
		accept(Token.IDENTIFIER);
		parseSeletor();
	}
    
}
