package Pacote;
import java.util.ArrayList;
import java.util.List;
public class Simbolo {
    private int escopo;
    private String lexema;
    private int id;
    private int inicio;
    private int fim;
    private int declaracao;
    private List<Integer> uso;
    public Simbolo() {
        uso = new ArrayList<>();
    }
    public Simbolo(String lexema,int escopo, int id, int declaracao) {
        this.escopo = escopo;
        this.lexema = lexema;
        this.id = id;
        this.declaracao = declaracao;
        uso = new ArrayList<>();
    }
    public Simbolo(int escopo, String lexema, int id, int inicio) {
        this.escopo = escopo;
        this.lexema = lexema;
        this.id = id;
        this.inicio = inicio;
        uso = new ArrayList<>();
    }
    public void addUso(Integer linha){
        uso.add(linha);
    }
    public int getEscopo() {
        return escopo;
    }
    public void setEscopo(int escopo) {
        this.escopo = escopo;
    }
    public String getLexema() {
        return lexema;
    }
    public void setLexema(String lexema) {
        this.lexema = lexema;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getInicio() {
        return inicio;
    }
    public void setInicio(int inicio) {
        this.inicio = inicio;
    }
    public int getFim() {
        return fim;
    }
    public void setFim(int fim) {
        this.fim = fim;
    }
    public int getDeclaracao() {
        return declaracao;
    }
    public void setDeclaracao(int declaracao) {
        this.declaracao = declaracao;
    }
    public List<Integer> getUso() {
        return uso;
    }
    public void setUso(List<Integer> uso) {
        this.uso = uso;
    }
    @Override
    public String toString() {
        return "Simbolo{" + "escopo=" + escopo + ", lexema=" + lexema + ", id=" + id + ", inicio=" + inicio + ", fim=" + fim + ", declaracao=" + declaracao + ", uso=" + uso + '}';
    }
}