/*
@author: Eduarda Gislon 
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;
import java.util.Optional;

public class App {

	private static Grafo grafo;

	static {
		grafo = new Grafo();
		Vertice a = new Vertice("A");
		Vertice b = new Vertice("B");
		Vertice c = new Vertice("C");
		Vertice d = new Vertice("D");
		Vertice e = new Vertice("E");
		Vertice f = new Vertice("F");
		Vertice g = new Vertice("G");
		Vertice h = new Vertice("H");
		Vertice i = new Vertice("I");
		Vertice j = new Vertice("J");

		// arestas do vertice A
		a.addAresta(new Aresta(a, b, 36));
		a.addAresta(new Aresta(a, i, 84));
		// arestas do vertice B
		b.addAresta(new Aresta(b, a, 36));
		b.addAresta(new Aresta(b, c, 7));
		b.addAresta(new Aresta(b, j, 13));
		// arestas do vertice C
		c.addAresta(new Aresta(c, b, 7));
		c.addAresta(new Aresta(c, d, 47));
		// arestas do vertice D
		d.addAresta(new Aresta(d, e, 8));
		d.addAresta(new Aresta(d, c, 47));
		// arestas do vertice E
		e.addAresta(new Aresta(e, d, 8));
		e.addAresta(new Aresta(e, f, 4));
		// arestas do vertice F
		f.addAresta(new Aresta(f, e, 4));
		f.addAresta(new Aresta(f, g, 62));
		f.addAresta(new Aresta(f, j, 28));
		// arestas do vertice G
		g.addAresta(new Aresta(g, f, 62));
		g.addAresta(new Aresta(g, h, 30));
		// arestas do vertice H
		h.addAresta(new Aresta(h, g, 30));
		h.addAresta(new Aresta(h, i, 45));
		h.addAresta(new Aresta(h, j, 74));
		// arestas do vertice I
		i.addAresta(new Aresta(i, a, 84));
		i.addAresta(new Aresta(i, h, 45));
		// arestas do vertice J
		j.addAresta(new Aresta(j, b, 13));
		j.addAresta(new Aresta(j, f, 28));
		j.addAresta(new Aresta(j, h, 74));

		grafo.addVertice(a);
		grafo.addVertice(b);
		grafo.addVertice(c);
		grafo.addVertice(d);
		grafo.addVertice(e);
		grafo.addVertice(f);
		grafo.addVertice(g);
		grafo.addVertice(h);
		grafo.addVertice(i);
		grafo.addVertice(j);
	}

	public static void main(String[] args) {
		CarteiroChines carteiroChines = new CarteiroChines();
		carteiroChines.matrizCusto(grafo);
		System.out.print("\nCiclo Eureliano: ");
		grafo.fleury(grafo.getVertice("A"));
	}
}

class Grafo {

	private List<Vertice> vertices;

	public Grafo() {
		vertices = new ArrayList<Vertice>();
	}

	public List<Vertice> getVertices() {
		return vertices;
	}

	public void setVertices(List<Vertice> vertices) {
		this.vertices = vertices;
	}

	public void addVertice(Vertice vertice) {
		this.vertices.add(vertice);
	}

	public int size() {
		return vertices.size();
	}

	public Vertice getVertice(String vertice) {
		Optional<Vertice> v = vertices.stream().filter(x -> x.getNome().equals(vertice)).findAny();
		return v.get();
	}

	public void fleury(Vertice inicio) {
		Stack<Vertice> caminho = new Stack<>();
		List<Vertice> resultado = new ArrayList<>();

		int aux = 0;

		caminho.push(inicio);

		while (!caminho.isEmpty()) {
			Vertice atual = caminho.peek();

			if (!atual.getArestas().isEmpty()) {
				var proximo = atual.getArestas().get(0).getDestino();

				// Remove a aresta entre o vértice atual e o próximo
				atual.getArestas().remove(0);
				for(Aresta aresta : proximo.getArestas()) {
					aux++;
					if (aresta.getDestino().equals(atual)) {
						proximo.getArestas().remove(aux-1);
						break;
					}
				}
				aux = 0;

				caminho.push(proximo);
			} else {
				resultado.add(caminho.pop());
			}
		}

		// Imprime o caminho euleriano
		for (Vertice v : resultado) {
			System.out.print(v + "  ");
		}
	}
}

class Vertice implements Comparable<Vertice> {

	private String nome;
	private int distancia;
	private Vertice pai;
	private boolean visitado;
	private List<Aresta> arestas;

	public Vertice() {
		super();
		arestas = new ArrayList<Aresta>();
	}

	public Vertice(String nome) {
		super();
		this.nome = nome;
		arestas = new ArrayList<Aresta>();
	}

	public Vertice(Vertice v) {
		this.setNome(v.getNome());
		this.setArestas(v.arestas);
		this.setDistancia(v.getDistancia());
		this.setPai(v.getPai());
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Aresta> getArestas() {
		return arestas;
	}

	public void setArestas(List<Aresta> arestas) {
		this.arestas = arestas;
	}

	public void addAresta(Aresta aresta) {
		this.arestas.add(aresta);
	}

	public int getDistancia() {
		return distancia;
	}

	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}

	public Vertice getPai() {
		return pai;
	}

	public void setPai(Vertice pai) {
		this.pai = pai;
	}

	public boolean isVisitado() {
		return visitado;
	}

	public void visitado() {
		this.visitado = true;
	}

	public int compareTo(Vertice vertice) {
		if (this.getDistancia() < vertice.getDistancia())
			return -1;
		else if (this.getDistancia() == vertice.getDistancia())
			return 0;
		return 1;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertice other = (Vertice) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nome;
	}

}

class NameComparator implements Comparator<Vertice> {
	public int compare(Vertice v1, Vertice v2) {
		return v1.getNome().compareTo(v2.getNome());
	}
}

class Aresta {

	private Vertice origem;
	private Vertice destino;
	private int custo;
	private boolean visitado;

	public Aresta(Vertice origem, Vertice destino, int custo) {
		super();
		this.origem = origem;
		this.destino = destino;
		this.custo = custo;
	}

	public Vertice getOrigem() {
		return origem;
	}

	public void setOrigem(Vertice origem) {
		this.origem = origem;
	}

	public Vertice getDestino() {
		return destino;
	}

	public void setDestino(Vertice destino) {
		this.destino = destino;
	}

	public int getCusto() {
		return custo;
	}

	public void setCusto(int custo) {
		this.custo = custo;
	}

	public boolean isVisitado() {
		return visitado;
	}

	public void visitado() {
		this.visitado = true;
	}
}

class CarteiroChines {

	private Dijkstra dijkstra;

	public CarteiroChines() {
		super();
		dijkstra = new Dijkstra();
	}

	protected List<Vertice> grauImpar(Grafo grafo) {
		List<Vertice> impares = new ArrayList<>();
		for (Vertice vertice : grafo.getVertices())
			if (vertice.getArestas().size() % 2 > 0)
				impares.add(vertice);
		return impares;
	}

	public void matrizCusto(Grafo grafo) {
		List<Vertice> impares = grauImpar(grafo);
		List<Vertice> listMenorCaminho = new ArrayList<>();
		List<Vertice> ordenadoListMenorCaminho = new ArrayList<>();

		int aux = 0;
		Vertice[][] matrizD = new Vertice[impares.size()][impares.size()];

		System.out.println("Resultados do Dijkstra");

		for (Vertice origin : impares) {

			listMenorCaminho = dijkstra.menorCaminho(grafo, origin);
			System.err.println("\nVertice:" + origin.getNome());

			ordenadoListMenorCaminho = listMenorCaminho;
			Collections.sort(ordenadoListMenorCaminho, new NameComparator());

			this.imprimeValoresDijkstra(ordenadoListMenorCaminho, origin);

			// Seta matriz D
			for (Vertice v : ordenadoListMenorCaminho) {
				for (int x = 0; x < impares.size(); x++) {
					if (v.equals(impares.get(x))) {
						Vertice copia = new Vertice(v);
						matrizD[aux][x] = copia;
					}
				}
			}
			aux++;
		}

		this.imprimeMatrizD(matrizD, impares);

		// SETA MENORES DISTANCIAS E REMOVE DA MATRIZ D
		listMenorCaminho.clear();

		while (listMenorCaminho.size() != impares.size()) {
			ordenadoListMenorCaminho = menorDistancia(matrizD);

			for (Vertice v : ordenadoListMenorCaminho) {
				Vertice copia = new Vertice(v);
				listMenorCaminho.add(copia);
				for (int j = 0; j < matrizD.length; j++) {
					for (int i = 0; i < matrizD.length; i++) {
						if (v.equals(matrizD[j][i])) {
							matrizD[j][i] = null;
							matrizD[i][j] = null;
						}
					}
				}
			}
		}

		// CRIA ARESTAS
		Vertice pai = null;
		for (int i = 0; i < listMenorCaminho.size(); i++) {
			if (listMenorCaminho.get(i + 1) == null) {
				break;
			}
			Vertice origem = listMenorCaminho.get(i + 1);
			pai = criaAresta(listMenorCaminho.get(i));
			while (!pai.equals(origem)) {
				pai = criaAresta(pai);
			}
			i++;
		}

		// SOMATORIO ARESTAS
		this.somaCustosArestas(grafo);
	}

	public void somaCustosArestas(Grafo grafo) {
		int somatorio = 0;
		for (Vertice v1 : grafo.getVertices()) {
			for (Aresta aresta : v1.getArestas()) {
				somatorio += aresta.getCusto();
				aresta.isVisitado();
			}
		}
		System.out.println("\nO valor total de Custo é " + somatorio / 2);
	}

	public Vertice criaAresta(Vertice destino) {
		for (Aresta aresta : destino.getArestas()) {
			if (aresta.getDestino().equals(destino.getPai())) {
				Vertice pai = destino.getPai();
				destino.addAresta(new Aresta(destino, pai, aresta.getCusto()));
				pai.addAresta(new Aresta(pai, destino, aresta.getCusto()));
				return pai;
			}
		}

		return null;
	}

	public void imprimeValoresDijkstra(List<Vertice> listMenorCaminho, Vertice origin) {
		for (Vertice v : listMenorCaminho) {
			System.out.println(
					"\nDistancia: " + v.getDistancia() + " entre [" + origin.getNome() + " e " + v.getNome() + "]");
			if (v.getPai() != null) {
				System.out.println("Pai do vertice " + v.getNome() + " é [" + v.getPai().getNome() + "]");
			} else {
				System.out.println("Pai do vertice " + v.getNome() + " é [nil]");
			}
		}
	}

	public void imprimeMatrizD(Vertice[][] matrizD, List<Vertice> impares) {
		System.out.println("\n----Matriz de Distância ----");
		System.out.print("\t\t");
		for (int l = 0; l < matrizD.length; l++) {
			System.out.print(impares.get(l).getNome() + "\t");
		}

		for (int j = 0; j < matrizD.length; j++) {
			System.out.println("\t\t");
			System.out.print(impares.get(j).getNome() + "\t\t");
			for (int i = 0; i < matrizD.length; i++) {
				System.out.print(matrizD[j][i].getDistancia() + "\t");
			}
		}
		System.out.print("\n");
	}

	public List<Vertice> menorDistancia(Vertice[][] matrizD) {
		int menorDistancia = 99999;
		Vertice v1 = new Vertice();
		Vertice v2 = new Vertice();
		List<Vertice> verticeDistancia = new ArrayList<>();

		for (int j = 0; j < matrizD.length; j++) {
			for (int i = 0; i < matrizD.length; i++) {
				if (matrizD[j][i] != null && matrizD[i][j] != null) {
					if (menorDistancia > matrizD[j][i].getDistancia() && matrizD[j][i].getPai() != null) {
						menorDistancia = matrizD[j][i].getDistancia();
						v1 = matrizD[j][i];
						v2 = matrizD[i][j];
					}
				}
			}

		}

		verticeDistancia.add(v1);
		verticeDistancia.add(v2);
		System.out.println("\nA menor distância do conjunto par de Vertices: " + v1.getNome() + " e " + v2.getNome()
		+ " é " + v1.getDistancia());
		return verticeDistancia;
	}

}

class Dijkstra {

	private List<Vertice> menorCaminho = new ArrayList<>();
	private List<Vertice> naoVisitados = new ArrayList<>();
	private List<Aresta> arestas = new ArrayList<>();
	private Vertice atual;
	private Vertice vizinho;

	public List<Vertice> menorCaminho(Grafo grafo, Vertice origin) {
		menorCaminho.clear();
		naoVisitados.clear();
		origin.setPai(null);

		for (Vertice vertice : grafo.getVertices()) {
			if (vertice.equals(origin))
				vertice.setDistancia(0);
			else
				vertice.setDistancia(999999);
			naoVisitados.add(vertice);
		}

		Collections.sort(naoVisitados);

		while (!naoVisitados.isEmpty()) {
			atual = naoVisitados.get(0);
			for (int x = 0; x < atual.getArestas().size(); x++) {
				arestas = atual.getArestas();
				vizinho = arestas.get(x).getDestino();
				if (!vizinho.isVisitado()) {
					if (vizinho.getDistancia() > (atual.getDistancia() + arestas.get(x).getCusto())) {
						Vertice copia = new Vertice(atual);
						vizinho.setDistancia(copia.getDistancia() + arestas.get(x).getCusto());
						vizinho.setPai(copia);
					}
				}
			}
			Vertice copia = new Vertice(atual);
			menorCaminho.add(copia);
			naoVisitados.remove(0);
			Collections.sort(naoVisitados);
		}

		return menorCaminho;
	}
}
