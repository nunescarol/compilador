public class Token {
	public byte kind;
	public String spelling;
        
        public Token(){
            this.kind = -1;
            this.spelling = "-1";
        }
        
        public final static byte 
                IDENTIFIER = 0,
		INTLITERAL = 1,
		OPERATORAD = 2,
		OPERATORMUL = 3,
		OPERATORREL = 4,
		FLOATLITERAL = 5,
                BOOLLIT = 6,
		
		
		
		
		ARRAY = 7, 
		BEGIN = 8,
		BOOLEAN = 9,
		DO = 10,
		ELSE = 11,
		END = 12,
		FALSE = 13,
		IF = 14,
		INTEGER = 15,
		PROGRAM = 16,
		OF = 17,
		REAL = 18,
		THEN = 19,
		TRUE = 20,
		WHILE = 21,
		VAR = 22,
		
		BECOMES = 23, 			// :=
		COMMA = 24,			// ,
		COLON = 25,			// :
		LBRACK = 26,			// [
		RBRACK = 27,			// ]
		LPAREN = 28, 			// (
		RPAREN = 29, 			// )
		SEMICOLON = 30, 		// ;
                FULLSTOP = 31,                  //.
		PONTOVETOR = 32,                //..
		
		EOT = 33;
		
	private final static String[] spellings = {"<id>", "<int-lit>", "<op-ad>", "<op-mul>", "<op-rel>", "<float-lit>", "<bool-lit>", "array", "begin", "boolean", "do", "else", "end", "false", "if", "integer", "program", "of", "real", "then", "true", "while","var", ":=", ",", ":", "[", "]", "(", ")", ";","." ,".." , "<eot>"};
	
	public Token (byte kind, String spelling){
		this.kind = kind;
		this.spelling = spelling;
		
		if(kind == IDENTIFIER)
			for(int k = ARRAY; k<=VAR;k++)
				if(spelling.equals(spellings[k])){
					this.kind = (byte) k;
					break;
				}
                if(kind==EOT)
                    this.spelling = "<eot>";
	}
	
}
	
	