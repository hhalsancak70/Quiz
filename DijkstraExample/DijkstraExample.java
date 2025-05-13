package DijkstraExample;

import java.util.*;

//Vertex sınıfı - düğüm etiketini tutar
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

//Edge sınıfı - komşu ve kenar ağırlığını tutar
class Edge {
	Vertex target;
	int weight;

	Edge(Vertex target, int weight) {
		this.target = target;
		this.weight = weight;
	}
}

//Weighted Graph (ağırlıklı graf) - Dijkstra için Adjacency List ile temsil edilir
class WeightedGraph {
	private Map<Vertex, List<Edge>> adjList = new HashMap<>();

	// Vertex ekleme
	public void addVertex(String label) {
		adjList.putIfAbsent(new Vertex(label), new ArrayList<>());
	}

	// Kenar ekleme (Yönsüz graph için iki yönlü ekleme yapılır)
	public void addEdge(String fromLabel, String toLabel, int weight) {
		Vertex from = getVertex(fromLabel);
		Vertex to = getVertex(toLabel);
		if (from == null || to == null)
			return;

		adjList.get(from).add(new Edge(to, weight));
		adjList.get(to).add(new Edge(from, weight)); // Yönsüz graph
	}

	// Etiketle Vertex bulma
	private Vertex getVertex(String label) {
		for (Vertex v : adjList.keySet()) {
			if (v.label.equals(label))
				return v;
		}
		return null;
	}

	// Dijkstra Algoritması - slayttaki mantıkla birebir
	public void dijkstra(String startLabel) {
		Vertex start = getVertex(startLabel);
		if (start == null) {
			System.out.println("Start vertex not found.");
			return;
		}

		// Mesafe tabloları (d(x))
		Map<Vertex, Integer> distances = new HashMap<>();
		// Önceki düğümleri tutarak yolun yeniden yapılandırılması için
		Map<Vertex, Vertex> previous = new HashMap<>();

		// Tüm vertex'lerin mesafesini sonsuz başlatıyoruz (∞)
		for (Vertex v : adjList.keySet()) {
			distances.put(v, Integer.MAX_VALUE);
			previous.put(v, null);
		}

		// Başlangıç vertex'inin mesafesi 0
		distances.put(start, 0);

		// Öncelik sırasına göre vertex seçmek için PriorityQueue kullanılır
		PriorityQueue<Vertex> pq = new PriorityQueue<>(Comparator.comparingInt(distances::get));
		pq.add(start);

		// "Cloud" büyüdükçe yeni vertex'leri ekleyip etiketleri güncelleyeceğiz
		while (!pq.isEmpty()) {
			Vertex current = pq.poll(); // Mesafesi en küçük vertex alınır
			int currentDistance = distances.get(current);

			// Komşuları keşfet (incidentEdges slayttaki gibi)
			for (Edge edge : adjList.get(current)) {
				Vertex neighbor = edge.target;

				// Kenar relaksasyonu - d(z) = min{ d(z), d(u) + weight(u,z) }
				int newDist = currentDistance + edge.weight;

				if (newDist < distances.get(neighbor)) {
					// Daha kısa yol bulundu
					distances.put(neighbor, newDist);
					previous.put(neighbor, current);

					// PriorityQueue güncellenir (basitçe tekrar ekliyoruz)
					pq.remove(neighbor); // O(n) ama küçük n için kabul edilebilir
					pq.add(neighbor);
				}
			}
		}

		// Sonuçları yazdır
		System.out.println("Shortest distances from " + start + ":");
		for (Vertex v : adjList.keySet()) {
			String path = reconstructPath(start, v, previous);
			System.out.println(start + " -> " + v + " = " + distances.get(v) + " | Path: " + path);
		}
	}

	// En kısa yolu yeniden yapılandırmak için (previous pointers kullanarak)
	private String reconstructPath(Vertex start, Vertex end, Map<Vertex, Vertex> previous) {
		List<String> path = new ArrayList<>();
		for (Vertex at = end; at != null; at = previous.get(at)) {
			path.add(at.label);
		}
		Collections.reverse(path);
		return String.join(" -> ", path);
	}
}

//Test sınıfı
public class DijkstraExample {
	public static void main(String[] args) {
		WeightedGraph graph = new WeightedGraph();

		// Vertex'ler eklenir (slayttaki gibi örnekleme)
		graph.addVertex("A");
		graph.addVertex("B");
		graph.addVertex("C");
		graph.addVertex("D");
		graph.addVertex("E");
		graph.addVertex("F");

		// Edge'ler eklenir (slaytta olduğu gibi ağırlıklarla)
		graph.addEdge("A", "B", 4);
		graph.addEdge("A", "C", 2);
		graph.addEdge("B", "C", 1);
		graph.addEdge("B", "D", 5);
		graph.addEdge("C", "D", 8);
		graph.addEdge("C", "E", 10);
		graph.addEdge("D", "E", 2);
		graph.addEdge("D", "F", 6);
		graph.addEdge("E", "F", 3);

		// Dijkstra başlatılır (A'dan)
		graph.dijkstra("A");
	}
}
