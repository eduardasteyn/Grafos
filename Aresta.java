
/**
 * 
 * @author Eduarda Jeniffer Steilein Gislon: 15/09/2023 
 *
 */

public class Aresta {
    private int valor;
    private Vertice origem;
    private Vertice destino;

    public Aresta(int key, Vertice origem, Vertice destino) {
        this.setValor(key);
        this.setOrigem(origem);
        this.setDestino(destino);

        origem.getArestas().add(this);
        destino.getArestas().add(this);
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
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

    public String toString() {
        return "Valor: " + this.getValor() + " | origem: " + this.getOrigem().getValor() + " destino: " + this.getDestino().getValor();
    }
}
