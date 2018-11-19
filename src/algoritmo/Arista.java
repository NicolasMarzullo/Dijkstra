package algoritmo;

public class Arista implements Comparable<Arista>{

	 int costo;
	 int nodoOrigen;
	 int nodoDestino;
	
	
	public Arista(int costo, int nodoOrigen, int nodoDestino) {
		this.costo = costo;
		this.nodoOrigen = nodoOrigen;
		this.nodoDestino = nodoDestino;
	}


	@Override
	public int compareTo(Arista otra) {
		return this.costo - otra.costo;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + costo;
		result = prime * result + nodoDestino;
		result = prime * result + nodoOrigen;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Arista other = (Arista) obj;
		if (costo != other.costo)
			return false;
		if (nodoDestino != other.nodoDestino)
			return false;
		if (nodoOrigen != other.nodoOrigen)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Arista [costo=" + costo + ", nodoOrigen=" + nodoOrigen + ", nodoDestino=" + nodoDestino + "]";
	}
	
	
	
}
