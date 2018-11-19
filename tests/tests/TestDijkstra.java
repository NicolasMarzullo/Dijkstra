package tests;
import org.junit.Test;

import algoritmo.Grafo;

public class TestDijkstra {

	@Test
	public void testExplicacionClase() {
		Grafo g = new Grafo(5);
		g.set(0, 1, 4);
		g.set(0, 3, 1);
		g.set(0, 4, 6);
		g.set(1, 2, 1);
		g.set(2, 4, 2);
		g.set(3, 2, 2);
		g.set(3, 4, 7);

		int resultado[] = g.calcularDijkstra(0);

		System.out.println("RESULTADO!!");
		
		for (int i = 0; i < 5; i++) {
			System.out.print(resultado[i] + "\t");
		}
	}
	
	@Test
	public void testGrafoInternet() {
		Grafo g = new Grafo(5);
		g.set(0, 1, 7);
		g.set(0, 3, 2);
		
		g.set(1, 2, 1);
		g.set(1, 3, 2);
		
		g.set(2, 4, 5);
		
		g.set(3, 1, 3);
		g.set(3, 4, 5);
		g.set(3, 2, 8);
		
		g.set(4, 2, 4);
		
		int resultado[] = g.calcularDijkstra(0);

		System.out.println("RESULTADO!!");
		
		for (int i = 0; i < 5; i++) {
			System.out.print(resultado[i] + "\t");
		}
	}

}
