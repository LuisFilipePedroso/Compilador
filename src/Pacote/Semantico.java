package Pacote;
import java.util.List;
public class Semantico implements Constants{
    public void executeAction(int action, Token token)	throws SemanticError
    {
        //System.out.println("Ação #"+action+", Token: "+token);
    }
    public void verf(List<Token> lista, TabelaSimbolos tabela) throws Pacote.SemanticError{
        if (lista.isEmpty()) {
            return;
        }
        int index=0;
        Token atual;
        do {
            atual=lista.get(index);
            switch(atual.getId()){
                case t_int:
                    while ((atual.getId()!=t_semicolon)) {                        
                        index++;
                        atual=lista.get(index);
                        if((atual.getId()==t_doubleNumber)||(atual.getId()==t_valStr)){
                            throw new SemanticError("Valor inválido para variavel int", atual.getPosition());
                        }
                    }
                    break;
                case t_str:
                    while (atual.getId()!=t_semicolon) {                        
                        index++;
                        atual=lista.get(index);
                        if((atual.getId()==t_number)||(atual.getId()==t_doubleNumber)){
                            throw new SemanticError("Valor inválido para variavel String", atual.getPosition());
                        }
                    }
                    break;
                case t_double:
                    while (atual.getId()!=t_semicolon) {                        
                        index++;
                        atual=lista.get(index);
                        if((atual.getId()==t_number)||(atual.getId()==t_valStr)){
                            throw new SemanticError("Valor inválido para variavel double", atual.getPosition());
                        }
                    }
                    break;
                case t_float:
                    while (atual.getId()!=t_semicolon) {                        
                        index++;
                        atual=lista.get(index);
                        if((atual.getId()==t_number)||(atual.getId()==t_valStr)){
                            throw new SemanticError("Valor inválido para variavel float", atual.getPosition());
                        }
                    }
                    break;
                case t_var:
                    int tipo=0;
                    switch(tipo){
                        case t_int:
                            while ((atual.getId()!=t_semicolon)) {                        
                                index++;
                                atual=lista.get(index);
                                if((atual.getId()==t_doubleNumber)||(atual.getId()==t_valStr)){
                                    throw new SemanticError("Valor inválido para variavel int", atual.getPosition());
                                }
                            }
                            break;
                        case t_str:
                            while (atual.getId()!=t_semicolon) {                        
                                index++;
                                atual=lista.get(index);
                                if((atual.getId()==t_number)||(atual.getId()==t_doubleNumber)){
                                    throw new SemanticError("Valor inválido para variavel String", atual.getPosition());
                                }
                            }
                            break;
                        case t_double:
                            while (atual.getId()!=t_semicolon) {                        
                                index++;
                                atual=lista.get(index);
                                if((atual.getId()==t_number)||(atual.getId()==t_valStr)){
                                    throw new SemanticError("Valor inválido para variavel double", atual.getPosition());
                                }
                            }
                            break;
                        case t_float:
                            while (atual.getId()!=t_semicolon) {                        
                                index++;
                                atual=lista.get(index);
                                if((atual.getId()==t_number)||(atual.getId()==t_valStr)){
                                    throw new SemanticError("Valor inválido para variavel double", atual.getPosition());
                                }
                            }
                            break;
                    }    
                    break;
                    //case if,while,elif,for
            }
            index++;
        } while (index<lista.size());
    }
}    