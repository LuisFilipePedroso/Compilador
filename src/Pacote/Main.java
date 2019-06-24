package Pacote;
import java.io.Reader;
import java.io.StringReader;
import java.io.IOException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Lexico lexico = new Lexico();
        Sintatico sintatico = new Sintatico();
        Semantico semantico = new Semantico();
        Scanner scan = new Scanner(System.in);
        System.out.println("Digite o cod:");
        String txt=scan.nextLine();
        while(!txt.equalsIgnoreCase("exit")){
            try
            {
                //String x="fun nome(){}";
                Reader a = new StringReader(txt);
                lexico.setInput(a);
                sintatico.parse(lexico, semantico);
                System.out.println("Entrada aceita");
            }
            catch ( LexicalError e )
            {
                System.out.println("Erro lexico: "+e.getMessage()+" na posição: "+e.getPosition());
                //Trada erros léxicos
            }
            catch ( SyntaticError e )
            {
                System.out.println("Erro sintático: "+e.getMessage()+" na posição: "+e.getPosition());
                //Trada erros sintáticos
            }
            catch ( SemanticError e )
            {
                System.out.println("Erro semântico: "+e.getMessage()+" na posição: "+e.getPosition());
                //Trada erros semânticos
            }
            catch (IOException e)
            {
                System.out.println("io");
            }
            System.out.println("Digite o cod:");
            txt=scan.nextLine();
        }
    }
}