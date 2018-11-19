package algoritmo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class Grafo {
	private List<Nodo> nodos = new ArrayList<>();
	private int cantidadDeNodos;
	private MatrizAdyacencia matrizDeCostos;
	private PriorityQueue<Arista> colaCostoArista = new PriorityQueue<>();
	static final int INFINITO = 2147483647;

	public Grafo(int cantidadDeNodos) {
		for (int i = 0; i < cantidadDeNodos; i++) {
			nodos.add(new Nodo(i));
		}
		this.matrizDeCostos = new MatrizAdyacencia(cantidadDeNodos);
		this.cantidadDeNodos = cantidadDeNodos;
	}

	public void set(int fila, int columna, int peso) {
		this.matrizDeCostos.setValor(fila, columna, peso);
	}

	public int[] calcularDijkstra(int nodoSolicitado) {
		int[] costosDelNodoSolicitadoAN = new int[this.cantidadDeNodos];
		List<Integer> listaDeAdyacentes = new LinkedList<>();
		// Cargo la cola con el nodo solicitado
		int nodoW, costoNormal, costoConNodoIntermedio;

		
		for (int j = 0; j < this.cantidadDeNodos; j++) {
			if(j != nodoSolicitado)	//no quiero cargar el costo para llegar a sigo mismo
				this.colaCostoArista
						.add(new Arista(this.matrizDeCostos.getValor(nodoSolicitado, j), nodoSolicitado, j));
		}

		for (int i = 0; i < this.cantidadDeNodos; i++) {
			costosDelNodoSolicitadoAN[i] = this.matrizDeCostos.getValor(nodoSolicitado, i);
		}

		while (!this.colaCostoArista.isEmpty()) {
			//saco el de menor costo y lo llamo
			nodoW = this.colaCostoArista.poll().nodoDestino;

			// Cargo los adyacentes
			for (int i = 0; i < this.cantidadDeNodos; i++) {
				if (this.matrizDeCostos.getValor(nodoW, i) != INFINITO) {
					listaDeAdyacentes.add(i);
				}
			}

			for (Integer i : listaDeAdyacentes) {
				costoNormal = costosDelNodoSolicitadoAN[i];
				costoConNodoIntermedio = costosDelNodoSolicitadoAN[nodoW] + this.matrizDeCostos.getValor(nodoW, i);

				if (costoConNodoIntermedio < costoNormal) {
					costosDelNodoSolicitadoAN[i] = costoConNodoIntermedio;
					
					colaCostoArista.remove(new Arista(costoNormal, nodoSolicitado, i));
					colaCostoArista.add(new Arista(costoConNodoIntermedio, nodoSolicitado, i));
				}
				
			}
			listaDeAdyacentes.clear();

		}

		return costosDelNodoSolicitadoAN;
	}
}
