package BFS;

import java.util.*;

//Vertex sınıfı - düğüm etiketini tutar (ör: A, B, C)
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

//Graph sınıfı - Adjacency List ile temsil edilir
class Graph {
	private Map<Vertex, List<Vertex>> adjList = new HashMap<>();

	// Düğüm ekleme
	public void addVertex(String label) {
		adjList.putIfAbsent(new Vertex(label), new ArrayList<>());
	}

	// Kenar ekleme (yönsüz graph için çift taraflı)
	public void addEdge(String fromLabel, String toLabel) {
		Vertex from = getVertex(fromLabel);
		Vertex to = getVertex(toLabel);
		if (from == null || to == null)
			return;

		adjList.get(from).add(to);
		adjList.get(to).add(from);
	}

	// Yardımcı metot - Vertex bulma (etikete göre)
	private Vertex getVertex(String label) {
		for (Vertex v : adjList.keySet()) {
			if (v.label.equals(label))
				return v;
		}
		return null;
	}

	// BFS Traversal - slayttaki BFS(G, s) algoritmasına uygun
	public void bfs(String startLabel) {
		Vertex start = getVertex(startLabel);
		if (start == null) {
			System.out.println("Starting vertex not found.");
			return;
		}

		// Vertex etiketleri: UNEXPLORED, VISITED
		Map<Vertex, String> vertexLabel = new HashMap<>();

		// Edge etiketleri: UNEXPLORED, DISCOVERY, CROSS
		Map<String, String> edgeLabel = new HashMap<>();

		// Tüm vertex'leri UNEXPLORED olarak başlatıyoruz
		for (Vertex v : adjList.keySet()) {
			vertexLabel.put(v, "UNEXPLORED");
		}

		// BFS sırası (Queue ile seviyeli tarama yapılır)
		Queue<Vertex> queue = new LinkedList<>();

		// Başlangıç vertex işaretlenir ve kuyrukta sıraya alınır
		vertexLabel.put(start, "VISITED");
		queue.add(start);

		System.out.println("BFS traversal starting from " + start + ":");

		// Kuyruk boşalana kadar devam eden BFS döngüsü
		while (!queue.isEmpty()) {
			Vertex current = queue.poll(); // FIFO - ilk vertex alınır
			System.out.print(current + " "); // Mevcut vertex'i ziyaret ediyoruz

			// Komşuları kontrol ederiz
			for (Vertex neighbor : adjList.get(current)) {
				// Kenar kimliği (string) oluşturulur (küçük->büyük sıralı şekilde)
				String edgeKey = current.label.compareTo(neighbor.label) < 0 ? current.label + "-" + neighbor.label
						: neighbor.label + "-" + current.label;

				// Eğer kenar daha önce incelenmediyse
				if (!edgeLabel.containsKey(edgeKey)) {
					// Eğer komşu daha önce ziyaret edilmemişse (discovery edge)
					if (vertexLabel.get(neighbor).equals("UNEXPLORED")) {
						System.out.println("\nDiscovery edge: " + current + " -> " + neighbor);
						edgeLabel.put(edgeKey, "DISCOVERY");

						vertexLabel.put(neighbor, "VISITED");
						queue.add(neighbor);
					} else {
						// Komşu zaten ziyaret edilmişse (cross edge)
						System.out.println("\nCross edge: " + current + " -> " + neighbor);
						edgeLabel.put(edgeKey, "CROSS");
					}
				}
			}
		}
	}
}

//Test sınıfı
public class BFS {
	public static void main(String[] args) {
		Graph graph = new Graph();

		// Vertex'ler slayttaki gibi eklenir
		graph.addVertex("A");
		graph.addVertex("B");
		graph.addVertex("C");
		graph.addVertex("D");
		graph.addVertex("E");
		graph.addVertex("F");
		graph.addVertex("G");
		graph.addVertex("H");
		graph.addVertex("I");

		// Edge'ler slayttaki örneğe göre
		graph.addEdge("A", "B");
		graph.addEdge("A", "C");
		graph.addEdge("A", "E");
		graph.addEdge("B", "D");
		graph.addEdge("C", "F");
		graph.addEdge("E", "G");
		graph.addEdge("E", "H");
		graph.addEdge("G", "I");

		// BFS başlatılır (A'dan)
		graph.bfs("A");
	}
}
