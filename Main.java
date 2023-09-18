import java.util.ArrayList;
import java.util.Collections;

/**
 * 
 * @author Eduarda Jeniffer Steilein Gislon: 15/09/2023 
 *
 */

public class Main {

	public static String tipoDoGrafo(int[][] matrizAdj) {
		Grafo g = new Grafo(matrizAdj);
		if (g.getVertices().isEmpty()) {
			return "Não é um grafo!!!";
		} else {
			return " O Grafo é " +
					"\n " + ((g.eDirigido()) ? "Dirigido" : "Não Dirigido") +
					((g.eSimples()) ? ", Simples" : ", Multigrafo") +
					((g.eRegular()) ? ", Regular" : "") +
					((g.eCompleto()) ? ", Completo" : "") +
					((g.eNulo()) ? ", Nulo" : "") +
					((g.eBipartido()) ? ", Bipartido" : "");
		}
	}

	public static String arestasDoGrafo(int[][] matrizAdj) {
		Grafo g = new Grafo(matrizAdj);
		ArrayList<Aresta> arestas = g.getArestas();  
		StringBuilder strBuilder = new StringBuilder("\n Quantidade de Arestas " + "\n Grafo possui " + arestas.size() + " arestas");

		if (!arestas.isEmpty()) {
			for (Aresta a : arestas) {
				strBuilder.append("\n ").append(a.toString());
			}
		}

		return strBuilder.toString();
	}

	public static String grausDoVertice(int[][] matrizAdj) {
		Grafo g = new Grafo(matrizAdj);
		ArrayList<Vertice> vertices = g.getVertices();
		ArrayList<Integer> graus = new ArrayList<>();

		StringBuilder strBuilder = new StringBuilder("\n Grau dos Vertices ");

		if (!vertices.isEmpty()) {
			for (Vertice v : vertices) {
				strBuilder.append("\n Vertice ").append(v.getValor()).append(", grau ").append(v.getGrau());
				graus.add(v.getGrau());
			}

			graus.sort(Collections.reverseOrder());
			strBuilder.append("\n\n Conjunto de Graus: ");

			for (Integer grau : graus) {
				strBuilder.append(grau).append(", ");
			}
		} else {
			strBuilder.append("\n Grafo não possui vértices.");
		}

		return strBuilder.toString();
	}

	public static void main(String[] args) {
		//  Não-dirigido, Simples
		int[][] NDS = {
				{0, 1, 1, 1},
				{1, 0, 1, 0},
				{1, 1, 0, 1},
				{1, 0, 1, 0}};

		System.out.println(tipoDoGrafo(NDS));
		System.out.println(arestasDoGrafo(NDS));
		System.out.println(grausDoVertice(NDS));
	}
}