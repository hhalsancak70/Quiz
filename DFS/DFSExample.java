package DFS;

import java.util.*;

//Vertex (düğüm) sınıfı
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

//Graph (graf) sınıfı - Adjacency List ile
class Graph {
	private Map<Vertex, List<Vertex>> adjList = new HashMap<>();

	// Düğüm ekleme
	public void addVertex(String label) {
		adjList.putIfAbsent(new Vertex(label), new ArrayList<>());
	}

	// Yönsüz kenar ekleme
	public void addEdge(String fromLabel, String toLabel) {
		Vertex from = getVertex(fromLabel);
		Vertex to = getVertex(toLabel);
		if (from == null || to == null)
			return;

		adjList.get(from).add(to);
		adjList.get(to).add(from); // Yönsüz graf olduğu için iki yönlü eklenir
	}

	// Yardımcı: Etiketle Vertex bulma
	private Vertex getVertex(String label) {
		for (Vertex v : adjList.keySet()) {
			if (v.label.equals(label))
				return v;
		}
		return null;
	}

	// DFS Traversal - Recursive
	public void dfs(String startLabel) {
		Vertex start = getVertex(startLabel);
		if (start == null)
			return;

		Set<Vertex> visited = new HashSet<>();
		System.out.println("DFS traversal starting from " + start + ":");
		dfsRecursive(start, visited);
	}

	// DFS Recursive helper function
	private void dfsRecursive(Vertex current, Set<Vertex> visited) {
		visited.add(current);
		System.out.print(current + " "); // Slayttaki "visit" işlemi

		// Tüm komşuları keşfet
		for (Vertex neighbor : adjList.get(current)) {
			if (!visited.contains(neighbor)) {
				// Discovery Edge
				System.out.println("\nDiscovery edge: " + current + " -> " + neighbor);
				dfsRecursive(neighbor, visited);
			} else {
				// Back Edge
				System.out.println("\nBack edge: " + current + " -> " + neighbor);
			}
		}
	}
}

//Test
public class DFSExample {
	public static void main(String[] args) {
		Graph graph = new Graph();

		// Vertex ekle
		graph.addVertex("A");
		graph.addVertex("B");
		graph.addVertex("C");
		graph.addVertex("D");
		graph.addVertex("E");
		graph.addVertex("F");
		graph.addVertex("G");

		// Edge ekle
		graph.addEdge("A", "B");
		graph.addEdge("A", "C");
		graph.addEdge("B", "D");
		graph.addEdge("C", "E");
		graph.addEdge("E", "F");
		graph.addEdge("E", "G");

		// DFS başlat
		graph.dfs("A");
	}
}
