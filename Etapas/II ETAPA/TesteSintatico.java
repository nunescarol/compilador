/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testelexico;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class TesteSintatico {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        String nome = "codigofonte2.txt";
        Token currentToken, EOT = new Token();
        Parser parser = new Parser(nome);
        
        parser.parse();
    
    }
    
}
