
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
	char caracter;
	int numeroDeVeces = 0;
	static ArrayList<String> contenidoArchivo;
	static HashMap<String, Integer> contadorPalabras;

	public static void main(String[] args) {
		leerArchivo();

		contarPalabras();

		mostrarResultado();
	}

	private static void mostrarResultado() {
		// TODO Auto-generated method stub
		

	}

	private static void contarPalabras() {

		contadorPalabras = new HashMap<String, Integer>();

		for (String cont : contenidoArchivo) {
			String[] palabras = identificarPalabra(cont);
			contarPalabras(palabras);
		}
		

	}

	private static void contarPalabras(String[] palabras) {
			for (String p : palabras) {
				if(contadorPalabras.get(p)==null) {
					contadorPalabras.put(p, 1);
				}
				else {
					int contador=contadorPalabras.get(p)+1;
					contadorPalabras.put(p, contador);
				}
			}

	}

	private static String[] identificarPalabra(String contenido) {
		ArrayList<String> palabras = new ArrayList<String>();
		int inicio = 0;
		for (int i = 0; i < contenido.length(); i++) {
			String palabra = "";
			if (contenido.charAt(i) == ' ') {
				if (i > 0) {
					palabra = contenido.substring(inicio, i);
					inicio = i + 1;
					palabras.add(palabra);
				}
			}
		}

		return palabras.toArray(new String[0]);
	}

	public static void leerArchivo() {
		// Creo un archivo de texto con el objeto File
		File file = new File("C:\\Users\\7mo tecnica\\Documents\\Franzofeo.txt");

		contenidoArchivo = new ArrayList<String>();
		// Defino la referencia de un Scanner
		Scanner sc;

		try {
			// Creo el Scanner para leer el archivo identificado en file
			sc = new Scanner(file);

			// hasNextLine() devuelve un valor booleano, indicando si existe o
			// no una siguiente línea a leer
			while (sc.hasNextLine()) {
				String linea = sc.nextLine();
				contenidoArchivo.add(linea);
				// System.out.println(linea);

			}

			// close() cierra la lectura del archivo
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static int contarCaracteres(String cadena, char caracter) {
		int posicion, contador = 0;

		// se busca la primera vez que aparece
		posicion = cadena.indexOf(caracter);
		while (posicion != -1) { // mientras se encuentre el caracter
			contador++; // se cuenta
			// se sigue buscando a partir de la posición siguiente a la encontrada
			posicion = cadena.indexOf(caracter, posicion + 1);
		}
		return contador;
	}

	// // Otra alternativa, es utilizar un BufferedReader
	// BufferedReader br = null;
	//
	// try {
	// // Creo el objeto en base al file, pero previamente lo tengo que
	// // leer a través de un FileReader
	// br = new BufferedReader(new FileReader(file));
	//
	// // readLine() lee la siguiente línea, en caso que no haya nada para
	// // leer devuelve null
	// String st;
	// while ((st = br.readLine()) != null)
	// System.out.println(st);
	//
	// // cierro el archivo
	// br.close();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
}
