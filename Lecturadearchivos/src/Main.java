
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.Map.Entry;

import ipe.utils.OrderMode;
import ipe.utils.OrderedMap;

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
		System.out.println("::::::TODOS::::::");
		for (String string : contadorPalabras.keySet()) {
			System.out.println(string.toLowerCase());
		}

		System.out.println("::::::ORDENADOS::::::");
		LinkedHashMap<String, Integer> ContadorOrdenado = OrderedMap.sortMapByValue(contadorPalabras,
				OrderMode.DESCENDING);
		int cont = 1;
		Set<Entry<String, Integer>> entrySetSortedByValue = ContadorOrdenado.entrySet();
		for (Entry<String, Integer> mapping : entrySetSortedByValue) {
			if (cont < 11) {
				System.out.println(cont + "\t" + mapping.getKey() + "\t" + mapping.getValue());
				cont++;
			} else {
				break;
			}
		}
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
			String comparador = p.toLowerCase();
			// String comparador = p;
			if (contadorPalabras.get(comparador) == null) {
				contadorPalabras.put(comparador, 1);
			} else {
				int contador = contadorPalabras.get(comparador) + 1;
				contadorPalabras.put(comparador, contador);
			}
		}

	}

	private static String[] identificarPalabra(String contenido) {
		ArrayList<String> palabras = new ArrayList<String>();
		int inicio = 0;
		for (int i = 0; i < contenido.length(); i++) {
			String palabra = "";
			boolean separador = esSeparador(contenido.charAt(i));
			int separadorExtra = 0;
			if (separador || i + 1 == contenido.length()) {
				while (i+1 < contenido.length() && esSeparador(contenido.charAt(i+1))) {
					i++;
					separadorExtra++;
				}
				
				if (i > 0) {
					int fin;
					if (separador) {
						fin = i - separadorExtra;
					} else {
						fin = i + 1;
					}
					palabra = contenido.substring(inicio, fin);
					inicio = i + 1;
					palabras.add(palabra);
				}
			}
		}

		return palabras.toArray(new String[0]);
	}

	private static boolean esSeparador(char charAt) {
		if (charAt == ' ')
			return true;
		else if (charAt == '.')
			return true;
		else if (charAt == ',')
			return true;
		else if (charAt == '!')
			return true;
		else if (charAt == '?')
			return true;
		
		return false;
	}

	public static void leerArchivo() {
		// Creo un archivo de texto con el objeto File
		File file = new File("C:\\Users\\7mo tecnica\\Documents\\textocopia2.txt");

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
}
