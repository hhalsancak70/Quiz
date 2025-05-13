package Quiz;

import java.util.*;

//Vertex (Düğüm) sınıfı
class Vertex {
	String label;

	Vertex(String label) {
		this.label = label;
	}

	@Override
	public String toString() {
		return label;
	}
}

//Edge (Kenar) sınıfı
class Edge {
	Vertex from;
	Vertex to;
	int weight; // Mesafe, maliyet gibi değerler

	Edge(Vertex from, Vertex to, int weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}

	@Override
	public String toString() {
		return from + " -> " + to + " (" + weight + ")";
	}
}

//Graph (Graf) sınıfı - Adjacency List ile
class Graph {
	private Map<Vertex, List<Edge>> adjList = new HashMap<>();

	// Düğüm ekleme
	public void addVertex(String label) {
		adjList.putIfAbsent(new Vertex(label), new ArrayList<>());
	}

	// Kenar ekleme (directed edge)
	public void addEdge(String fromLabel, String toLabel, int weight) {
		Vertex from = getVertex(fromLabel);
		Vertex to = getVertex(toLabel);
		if (from == null || to == null)
			return;
		adjList.get(from).add(new Edge(from, to, weight));
	}

	// Yardımcı: Etiketle Vertex bulma
	private Vertex getVertex(String label) {
		for (Vertex v : adjList.keySet()) {
			if (v.label.equals(label))
				return v;
		}
		return null;
	}

	// Grafı yazdırma (Adjacency List gösterimi)
	public void printGraph() {
		for (Vertex v : adjList.keySet()) {
			System.out.print(v + " -> ");
			List<Edge> edges = adjList.get(v);
			for (Edge e : edges) {
				System.out.print(e.to.label + "(" + e.weight + ") ");
			}
			System.out.println();
		}
	}
}

//Test
public class GraphExample {
	public static void main(String[] args) {
		Graph graph = new Graph();

		// Vertex ekle
		graph.addVertex("ORD");
		graph.addVertex("DFW");
		graph.addVertex("SFO");
		graph.addVertex("LAX");

		// Edge ekle (Directed edges)
		graph.addEdge("ORD", "DFW", 802);
		graph.addEdge("ORD", "SFO", 1843);
		graph.addEdge("DFW", "LAX", 1233);
		graph.addEdge("SFO", "LAX", 337);

		// Grafı yazdır
		graph.printGraph();
	}
}
