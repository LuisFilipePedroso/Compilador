package Pacote;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
public class Sintatico implements Constants{
    private Stack stack = new Stack();
    private Token currentToken;
    private Token previousToken;
    private Lexico scanner;
    private Semantico semanticAnalyser;
    private int[][][] PARSER_TABLE;
    public void parse(Lexico scanner, Semantico semanticAnalyser) throws LexicalError, SyntaticError, SemanticError, IOException, Pacote.SemanticError{
        parseTableCons();
        this.scanner = scanner;
        this.semanticAnalyser = semanticAnalyser;
        stack.clear();
        stack.push(new Integer(0));
        TabelaSimbolos tabela = parseTabelaSimbolos();
        currentToken = scanner.nextToken();
        List<Token> lista = new ArrayList<>();
        do{
            if(currentToken!=null){
                if ((!lista.contains(currentToken))&&(!currentToken.getLexeme().equalsIgnoreCase("$"))) {
                    lista.add(currentToken);
                    //System.out.println(currentToken);
                }
            }
        }while (!step());
        semanticAnalyser.verf(lista, tabela);
    }
    private boolean step() throws LexicalError, SyntaticError, SemanticError{
        if (currentToken == null)
        {
            int pos = 0;
            if (previousToken != null)
                pos = previousToken.getPosition()+previousToken.getLexeme().length();

            currentToken = new Token(DOLLAR, "$", pos);
        }

        int token = currentToken.getId();
        int state = ((Integer)stack.peek()).intValue();

        int[] cmd = PARSER_TABLE[state][token-1];

        switch (cmd[0])
        {
            case 0:
                stack.push(new Integer(cmd[1]));
                previousToken = currentToken;
                currentToken = scanner.nextToken();
                return false;

            case 1:
                int[] prod = PRODUCTIONS[cmd[1]];

                for (int i=0; i<prod[1]; i++)
                    stack.pop();

                int oldState = ((Integer)stack.peek()).intValue();
                stack.push(new Integer(PARSER_TABLE[oldState][prod[0]-1][1]));
                return false;

            case 2:
                int action = FIRST_SEMANTIC_ACTION + cmd[1] - 1;
                stack.push(new Integer(PARSER_TABLE[state][action][1]));
                semanticAnalyser.executeAction(cmd[1], previousToken);
                return false;

            case 3:
                return true;

            case 5:
                //System.out.println(currentToken.getLexeme());
                throw new SyntaticError(PARSER_ERROR[state], currentToken.getPosition());
        }
        return false;
    }
    public void parseTableCons() throws IOException{
        String caminho="src/Arquivos/tabela.txt";
        String[] tabela = read(caminho).replaceAll("\\{|\\}|\\s*", "").split(",");
        int linhas=contlinha(caminho);
        int colunas=(tabela.length/linhas)/2;
        PARSER_TABLE=new int[linhas][colunas][2];
        int index=0;
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                for (int k = 0; k < 2; k++) {
                    PARSER_TABLE[i][j][k]=Integer.parseInt(tabela[index]);
                    index++;
                }
            }
        }
    }
    public String read(String caminho) {
        String txt = "";
        try {
            FileReader file = new FileReader(caminho);
            BufferedReader br = new BufferedReader(file);
            String s;
            while((s=br.readLine())!=null){
                txt+=s;
            }
            file.close();
        } catch (IOException f) {
            f.printStackTrace();
        }
        return txt;
    }
    public int contlinha(String caminho) throws IOException{
        File arquivoLeitura = new File(caminho);
        LineNumberReader linhaLeitura;
        linhaLeitura = new LineNumberReader(new FileReader(arquivoLeitura));
        linhaLeitura.skip(arquivoLeitura.length());
        int qtdLinha = linhaLeitura.getLineNumber();
        return qtdLinha;
    }
    public TabelaSimbolos parseTabelaSimbolos() throws LexicalError, SyntaticError, SemanticError{
        Reader a = new StringReader(scanner.getinput());
        Lexico l = new Lexico(a);
        Token t = null;
        TabelaSimbolos tabela = new TabelaSimbolos();
        int linha=1;
        Integer escopo=0;
        Stack<Integer> pilha = new Stack<>();
        pilha.push(escopo);
        while ( (t = l.nextToken()) != null ){
            switch(t.getId()){
                case t_abreChave:
                    escopo++;
                    pilha.push(escopo);
                    break;
                case t_fechaChave:
                    pilha.pop();
                    break;
                case t_int:
                    t = l.nextToken();
                    if (t.getId()==t_var) {
                        tabela.add(new Simbolo(t.getLexeme(), pilha.peek(), t_int, linha));
                    }else{
                        throw new SyntaticError("Esperava uma variavel", t.getPosition());
                    }
                    if('\n'==l.nextChar()){
                        linha++;
                    }
                    break;
                case t_double:
                    t = l.nextToken();
                    if (t.getId()==t_var) {
                        tabela.add(new Simbolo(t.getLexeme(), pilha.peek(), t_double, linha));
                    }else{
                        throw new SyntaticError("Esperava uma variavel", t.getPosition());
                    }
                    if('\n'==l.nextChar()){
                        linha++;
                    }
                    break;
                case t_float:
                    t = l.nextToken();
                    if (t.getId()==t_var) {
                        tabela.add(new Simbolo(t.getLexeme(), pilha.peek(), t_float, linha));
                    }else{
                        throw new SyntaticError("Esperava uma variavel", t.getPosition());
                    }
                    if('\n'==l.nextChar()){
                        linha++;
                    }
                    break;
                case t_str:
                    t = l.nextToken();
                    if (t.getId()==t_var) {
                        tabela.add(new Simbolo(t.getLexeme(), pilha.peek(), t_str, linha));
                    }else{
                        throw new SyntaticError("Esperava uma variavel", t.getPosition());
                    }
                    if('\n'==l.nextChar()){
                        linha++;
                    }
                    break;  
                case t_void:
                    t = l.nextToken();
                    if (t.getId()==t_var) {
                        tabela.add(new Simbolo(t.getLexeme(), pilha.peek(), t_void, linha));
                    }else if(t.getId()==t_main){
                        tabela.add(new Simbolo(t.getLexeme(), pilha.peek(), t_main, linha));
                    }else{
                        throw new SyntaticError("Esperava uma variavel", t.getPosition());
                    }
                    //procurar {
                    if('\n'==l.nextChar()){
                        linha++;
                    }
                    break;
                case t_fun:
                    t = l.nextToken();
                    if (t.getId()==t_var) {
                        tabela.add(new Simbolo(t.getLexeme(), pilha.peek(), t_fun, linha));
                    }else{
                        throw new SyntaticError("Esperava uma variavel", t.getPosition());
                    }
                    //procurar {
                    if('\n'==l.nextChar()){
                        linha++;
                    }
                    break;
                /*
                    case if: ... */    
                case t_var:
                    if (null==tabela.pesquisaSimbolo(new Simbolo(t.getLexeme(), escopo, 0, 0))) {
                        //System.out.println(t.getLexeme());
                        throw new SemanticError("Variável não declarada", t.getPosition());
                    }else{
                        tabela.addUso(new Simbolo(t.getLexeme(), escopo, 0, 0), linha);
                    }
                    break;
            }
            if("\n".equals(l.nextChar())){
                linha++;
            }
            //System.out.println("\'"+l.nextString()+"\'");
        }
        return tabela;
    }
}