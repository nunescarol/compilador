/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testelexico;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Usuario
 */
/*public class CopyCharacters {
    public static void main(String[] args) throws IOException {

        FileReader inputStream = null;
        FileWriter outputStream = null;

        try {
            inputStream = new FileReader("xanadu.txt");
            outputStream = new FileWriter("characteroutput.txt");

            int c;
            while ((c = inputStream.read()) != -1) {
                outputStream.write(c);
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }
}*/

public class Scanner {
	
	//private char currentChar = inputStream.read() //first source character;
        private char currentChar;
	private byte currentKind;
	private StringBuffer currentSpelling;
	int linha, coluna;
	FileReader inputStream = null;
	
	public Scanner(String arquivo) throws FileNotFoundException, IOException{
		super();
		this.inputStream = new FileReader(arquivo);
		this.currentChar = (char)inputStream.read();
		this.linha = 1;
		this.coluna = 1;
	}
	
	private void take (char expectedChar) throws IOException{
		if(currentChar == expectedChar){
			currentSpelling.append(currentChar);
			if(currentChar == '\n'){
				coluna=1;
				linha++;
			}else
				coluna++;
			currentChar = (char)inputStream.read(); //next
		}else
			System.out.println("tu eh burro eh porra linha: "+linha+"coluna: "+coluna);
	}
	
	private void takeIt() throws IOException{
            int i=0;
		currentSpelling.append(currentChar);
		if(currentChar == '\n'){
			coluna=1;
			linha++;
		}else{
			coluna++;
                }
		i = inputStream.read(); //next
                
                currentChar = (char)i;
                if( i== -1)
                    currentChar = '\000';
                    
	}
        
	
	private boolean isDigit(char c){
            switch(c){
		case '0': case '1': case '2': case '3': case '4': case '5': case '6': case '7': case '8': case '9':
		return true;
		default: 
		return false;
            }
	}
	
	private boolean isLetter(char c){
            switch(c){
		case 'a': case 'b': case 'c': case 'd': case 'e': case 'f': case 'g': case 'h': case 'i': case 'j': case 'k': case 'l': case 'm': case 'n': case 'o': case 'p': case 'q': case 'r': case 's': case 't': case 'u': case 'v': case 'w': case 'x': case 'y': case 'z': 
		return true;
		default: 
		return false;
            }
	}
	
	private boolean isGraphic(char c){
            switch(c){
		case '0': case '1': case '2': case '3': case '4': case '5': case '6': case '7': case '8': case '9': 
		case 'a': case 'b': case 'c': case 'd': case 'e': case 'f': case 'g': case 'h': case 'i': case 'j': case 'k': case 'l': case 'm': case 'n': case 'o': case 'p': case 'q': case 'r': case 's': case 't': case 'u': case 'v': case 'w': case 'x': case 'y': case 'z': 
		case '!': case '@': case '#': case '$': case '%': case '~': case '*': case '(': case ')': case '-': case '_': case '+': case '=': case '´': case '`': case '[': case '{': case 'ª': case '}': case ']': case 'º': case '<': case '>': case ',': case '.': case ';': case '/': case ':': case '?': case '\\': case '|': case '\'': case '"': 
		return true;
		default: 
		return false;
            }

	}
	
	private byte scanToken() throws IOException{
            switch(currentChar){
		case 'b': case 'c': case 'd': case 'e': case 'g': case 'h': case 'i': case 'j': case 'k': case 'l': case 'm': case 'n': case 'p': case 'q': case 'r': case 's': case 'u': case 'v': case 'w': case 'x': case 'y': case 'z': 
			takeIt();
		while(isLetter(currentChar) || isDigit(currentChar))
			takeIt();
		return Token.IDENTIFIER;
		
		case 'a': 
			takeIt();
		if(currentChar == 'n'){
			takeIt();
			if(currentChar == 'd'){
				takeIt();
				if(currentChar == ' '){
					return Token.OPERATORMUL;
				}
			}
		}
		while(isLetter(currentChar) || isDigit(currentChar))
			takeIt();
		return Token.IDENTIFIER;
		
		case 'o': 
			takeIt();
		if(currentChar == 'r'){
			takeIt();
			if(currentChar == ' '){
				return Token.OPERATORAD;
			}
		}
		while(isLetter(currentChar) || isDigit(currentChar))
			takeIt();
		return Token.IDENTIFIER;
                    
                case 't':
                    takeIt();
                    if(currentChar == 'r'){
                        takeIt();
                        if(currentChar == 'u'){
                            takeIt();
                            if(currentChar == 'e'){
                                takeIt();
                                if(currentChar == ' ')
                                    return Token.BOOLLIT;
                            }
                        }
                    }
                    while(isLetter(currentChar) || isDigit(currentChar))
                            takeIt();
                    return Token.IDENTIFIER;
                        
                case 'f':
                    takeIt();
                    if(currentChar == 'a'){
                        takeIt();
                        if(currentChar == 'l'){
                            takeIt();
                            if(currentChar == 's'){
                                takeIt();
                                if(currentChar == 'e'){
                                    takeIt();
                                    if(currentChar == ' ')
                                        return Token.BOOLLIT;
                                }
                            }
                        }
                    }
                    while(isLetter(currentChar) || isDigit(currentChar))
                            takeIt();
                    return Token.IDENTIFIER;
		
		case '0': case '1': case '2': case '3': case '4': case '5': case '6': case '7': case '8': case '9':
		takeIt();
		while(isDigit(currentChar))
			takeIt();
		if(currentChar == '.'){
			takeIt();
                        if(currentChar == '.'){
                            takeIt();
                            return Token.PONTOVETOR;
                        }else
                            while(isDigit(currentChar))
                                    takeIt();
                        return Token.FLOATLITERAL;
		}
		return Token.INTLITERAL;

		case '.':
		takeIt();
		if(isDigit(currentChar)){
			while(isDigit(currentChar))
				takeIt();
			return Token.FLOATLITERAL;
		}
		else if(currentChar=='.'){
			takeIt();
			return Token.PONTOVETOR;
		}else
                    return Token.FULLSTOP;
		
		
		
		case '+': case '-': 
			takeIt();
			return Token.OPERATORAD;
                    
                case '*': case '/':
                    takeIt();
                    return Token.OPERATORMUL;
                
                case '=':
                    takeIt();
                    return Token.OPERATORREL;
			
		case '<':
			takeIt();
			if(currentChar == '=' || currentChar == '>')
				takeIt();
			return Token.OPERATORREL;
			
		case '>':
			takeIt();
			if(currentChar == '=')
				takeIt();
			return Token.OPERATORREL;
		
		case ';':
			takeIt();
			return Token.SEMICOLON;
		
		case ':':
			takeIt();
			if (currentChar == '='){
				takeIt();
				return Token.BECOMES;
			}
			else
				return Token.COLON;
		
		case '[':
			takeIt();
			return Token.LBRACK;
		
		case ']':
			takeIt();
			return Token.RBRACK;
			
		case '(':
			takeIt();
			return Token.LPAREN;
			
		case ')':
			takeIt();
			return Token.RPAREN;
			
		case ',':
			takeIt();
			return Token.COMMA;
		
		case '\000':
			inputStream.close();
			return Token.EOT;
			
		default:
			return -2;
		}
        }
	

	private void scanSeparator() throws IOException{
		switch(currentChar){
			case '!': {
				takeIt();
				while(isGraphic(currentChar))
					takeIt();
				take('\n');
			}
			break;
			
			case ' ': case '\n': case '\r':
				takeIt();
				break;
		}
	}
	
	public Token scan() throws IOException{
		while (currentChar == '!' || currentChar == ' ' || currentChar == '\n' || currentChar == '\r'){
			scanSeparator();
                }
		currentSpelling =  new StringBuffer("");
		currentKind = scanToken();
		return new Token(currentKind, currentSpelling.toString());
	}
	
}