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
		int nodoW, costoDirecto, costoConNodoIntermedio;

		//Cargo los costos de las aristas en la cola de prioridad
		for (int j = 0; j < this.cantidadDeNodos; j++) {
			if (j != nodoSolicitado) // no quiero cargar el costo para llegar a sigo mismo
				this.colaCostoArista
						.add(new Arista(this.matrizDeCostos.getValor(nodoSolicitado, j), nodoSolicitado, j));
		}

		//Cargo el vector de costos
		for (int i = 0; i < this.cantidadDeNodos; i++) {
			costosDelNodoSolicitadoAN[i] = this.matrizDeCostos.getValor(nodoSolicitado, i);
		}

		//Mientras queden elementos en la cola por analizar. (Conjunto v-s)
		while (!this.colaCostoArista.isEmpty()) {
			
			//La cola me saca el mejor.
			nodoW = this.colaCostoArista.poll().nodoDestino;

			// Cargo los adyacentes. Sé que un nodo no es adyacente con otro si tiene un infinito.
			for (int i = 0; i < this.cantidadDeNodos; i++) {
				if (this.matrizDeCostos.getValor(nodoW, i) != INFINITO) {
					listaDeAdyacentes.add(i);
				}
			}

			for (Integer i : listaDeAdyacentes) {
				
				//Calculo los costos yendo directamente al nodo o pasando por un intermedio a ver cual es mejor
				costoDirecto = costosDelNodoSolicitadoAN[i];
				costoConNodoIntermedio = costosDelNodoSolicitadoAN[nodoW] + this.matrizDeCostos.getValor(nodoW, i);

				if (costoConNodoIntermedio < costoDirecto) {
					costosDelNodoSolicitadoAN[i] = costoConNodoIntermedio;

					//Esto es para actualizar la cola, ya que solo se puede actualizar eliminando y volviendo a agregar el elemento
					colaCostoArista.remove(new Arista(costoDirecto, nodoSolicitado, i));
					colaCostoArista.add(new Arista(costoConNodoIntermedio, nodoSolicitado, i));
				}

			}
			//No olvidar de limpiar los adyacentes!!
			listaDeAdyacentes.clear();

		}

		return costosDelNodoSolicitadoAN;
	}
}
