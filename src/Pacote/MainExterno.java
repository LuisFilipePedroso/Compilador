package Pacote;

import Framework.Framework;
import java.io.Reader;
import java.io.StringReader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MainExterno {

    public static void main(String[] args) {
        Lexico lexico = new Lexico();
        Sintatico sintatico = new Sintatico();
        Semantico semantico = new Semantico();
        String txt = read("src/Arquivos/teste.txt");
        Reader a = new StringReader(txt);

        try {
            Framework myFramework = new Framework(txt, "Pacote");
            myFramework.run();
        } catch (Exception e) {
            System.out.println(e);
        }
//        try {
//            lexico.setInput(a);
//            sintatico.parse(lexico, semantico);
//
//            Framework myFramework = new Framework(txt, "Pacote");
//            myFramework.run();
//        } catch (LexicalError e) {
//            System.out.println("Erro lexico: " + e.getMessage() + " na posição: " + e.getPosition());
//            //Trada erros léxicos
//        } catch (SyntaticError e) {
//            System.out.println("Erro sintático: " + e.getMessage() + " na posição: " + e.getPosition());
//            //Trada erros sintáticos
//        } catch (SemanticError e) {
//            System.out.println("Erro semântico: " + e.getMessage() + " na posição: " + e.getPosition());
//            //Trada erros semânticos
//        } catch (IOException e) {
//            System.out.println("io");
//        }
    }

    public static String read(String caminho) {
        String txt = "";
        try {
            FileReader file = new FileReader(caminho);
            BufferedReader br = new BufferedReader(file);
            String s;
            while ((s = br.readLine()) != null) {
                txt += s;
            }
            file.close();
        } catch (IOException f) {
            f.printStackTrace();
        }
        return txt;
    }
}
