package Framework;

import Arquivos.Code;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Framework {

    private String code;
    private String pack;

    public Framework(String code, String pack) {
        this.code = code;
        this.pack = pack;
    }

    public void run() throws FileNotFoundException, UnsupportedEncodingException, IOException {
        //String test = this.code.replace("void main()", "public static void main(String[] args)");
        String test[] = this.code.split("  ");
        String part[] = null;
        String print = "";
        String fileName = "Code";

        PrintWriter writer = new PrintWriter("src/Arquivos/" + fileName + ".java", "UTF-8");
        String className = "public class " + fileName + " {";
        writer.println("package Arquivos;");

        writer.println(className);

        for (int i = 0; i < test.length; i++) {
            test[i] = test[i].replace("}", "\n}");
            test[i] = test[i].replace("void main()", "public static void main(String[] args)");
            if (test[i].contains("print")) {
                for (int j = 0; j < test.length; j++) {
                    if (!test[j].contains("System.out.")) {
                        test[j] = test[j].replace("print", "System.out.print");
                    }
                }
            } else if (test[i].contains("println")) {
                for (int j = 0; j < test.length; j++) {
                    if (!test[j].contains("System.out.")) {
                        test[j] = test[j].replace("print", "System.out.println");
                    }
                }
            }

            if (test[i].contains("elif")) {
                test[i] = test[i].replace("elif", "else if");
            }
            
            if(test[i].contains("fun")) {
                test[i] = test[i].replace("fun", "public static void");
            }
            
            writer.println(test[i]);
            //System.out.println(test[i]);
        }
        writer.println("}");
        writer.close();

        Code code = new Code();
        code.main(part);
    }
}
