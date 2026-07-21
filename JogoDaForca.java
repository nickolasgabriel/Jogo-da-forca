package cursoHoraDeCodar.arrays;

import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class JogoDaForca {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Escolha a palavra a ser adivinhada: ");
        String palavraSecreta = scanner.next();
        limparConsole();

        String[] letrasPalavrasSecreta= palavraSecreta.split("");

        String[] palavraDescoberta = new String[letrasPalavrasSecreta.length];
        Arrays.fill(palavraDescoberta, "_");

        ArrayList<String> listaLetrasEscolhidas = new ArrayList<>();

        String letraEscolhida;
        byte vidas = 6, contador = 0;
        boolean isTerminou = false;

        do {
            System.out.println(listaLetrasEscolhidas);
            System.out.println("Vidas: " + vidas);
            mostrarPalavraDescoberta(palavraDescoberta);

            letraEscolhida = escolherLetra(scanner, listaLetrasEscolhidas);
            listaLetrasEscolhidas.add(letraEscolhida);
            vidas = verificarLetraNaPalavra(letraEscolhida, letrasPalavrasSecreta, palavraDescoberta, vidas, listaLetrasEscolhidas);

            contador = 0;
            for (int i = 0; i < palavraDescoberta.length; i++) {
                if (palavraDescoberta[i].equals("_")){
                    contador++;
                    break;
                }
            }
            if (contador == 0 || vidas == 0){
                isTerminou = true;
            }
        }while (!isTerminou);

        limparConsole();
        if (contador == 0){
            System.out.println("PARABÉNS!!! Você ganhou.");
        }else{
            System.out.println("Você perdeu!");
            System.out.println("A palavra era " + palavraSecreta);
        }

        scanner.close();
    }

    public static String escolherLetra(Scanner scanner, ArrayList listaLetrasEscolhidas){
        System.out.print("\nDigite uma letra: ");
        String letraEscolhida = scanner.nextLine();

        while (letraEscolhida.length() != 1 || listaLetrasEscolhidas.contains(letraEscolhida)){
            System.out.print("Erro! digite uma letra: ");
            letraEscolhida = scanner.nextLine();
        }
        return letraEscolhida;
    }

    public static byte verificarLetraNaPalavra(String letraEscolhida, String[] letrasPalavraSecreta, String[] palavraDescoberta, byte vidas, ArrayList<String> listaLetrasEscolhidas){
        byte letraTemNaPalavra = 0;
        for (int i = 0; i < letrasPalavraSecreta.length; i++) {
            if (letraEscolhida.equalsIgnoreCase(letrasPalavraSecreta[i])){
                palavraDescoberta[i] = letraEscolhida;
                letraTemNaPalavra++;
            }
    }
        if (letraTemNaPalavra == 0){
            System.out.println("Não tem a letra " + letraEscolhida);
            return vidas -= 1;
        }
        return vidas;
    }

    public static void mostrarPalavraDescoberta(String[] palavraDescoberta){
        for (String i : palavraDescoberta){
            System.out.print(i + " ");
        }
    }

    public static void limparConsole() {
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
    }
}
