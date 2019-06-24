package Pacote;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TabelaSimbolos {
    private List<Simbolo> tabela;
    public TabelaSimbolos() {
        tabela = new ArrayList<>();
    }
    public void add(Simbolo s){
        tabela.add(s);
        //System.out.println(s);
    }
    public void addUso(Simbolo s,int linha){
        s = pesquisaSimbolo(s);
        s.addUso(linha);
    }
    public Simbolo pesquisaSimbolo(Simbolo s){
        for (Iterator<Simbolo> iterator = tabela.iterator(); iterator.hasNext();) {
            Simbolo next = iterator.next();
            if((next.getLexema().equals(s.getLexema()))&&(next.getEscopo()<=s.getEscopo())){
                return next;
            }
        }
        return null;
    }
    public List<Simbolo> getTabela() {
        return tabela;
    }
    public void setTabela(List<Simbolo> tabela) {
        this.tabela = tabela;
    }
    @Override
    public String toString() {
        return "TabelaSimbolos{" + "tabela=" + tabela + '}';
    }
}