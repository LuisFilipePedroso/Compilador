package Arquivos;
public class Code {
public static void soma(int a, int b) {

int c = a + b;

System.out.println("A soma é: " + c);
}public static void subtract(int a, int b) {

int c = a - b;

System.out.println("A subtração é: " + c);
}public static void mult(int a, int b) {

int c = a * b;

System.out.println("A multiplicação é: " + c);
}public static void main(String[] args) {

System.out.println("Adição");

soma(2, 4);

System.out.println("");

System.out.println("Subtração");

subtract(4, 2);

int i = 0;

System.out.println("");

System.out.println("Multiplicação");

while(i < 10) {



mult(i, i + 2);



 i+=1;


}
}
}
