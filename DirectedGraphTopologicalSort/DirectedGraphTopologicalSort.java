package DirectedGraphTopologicalSort;

import java.util.*;

//Vertex sınıfı - düğüm etiketini saklar (ör: A, B, C gibi)
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

//Directed Graph sınıfı - Adjacency List ile yönlü kenarları tutar
class Digraph {
	private Map<Vertex, List<Vertex>> adjList = new HashMap<>();

	// Vertex ekleme
	public void addVertex(String label) {
		adjList.putIfAbsent(new Vertex(label), new ArrayList<>());
	}

	// Yönlü kenar ekleme (from -> to)
	public void addEdge(String fromLabel, String toLabel) {
		Vertex from = getVertex(fromLabel);
		Vertex to = getVertex(toLabel);
		if (from == null || to == null)
			return;

		adjList.get(from).add(to);
	}

	// Yardımcı: Etikete göre Vertex bulur
	private Vertex getVertex(String label) {
		for (Vertex v : adjList.keySet()) {
			if (v.label.equals(label))
				return v;
		}
		return null;
	}

	// DFS tabanlı Topological Sort algoritması
	public void topologicalSort() {
		// Ziyaret edilen düğümleri takip eden küme
		Set<Vertex> visited = new HashSet<>();

		// Topolojik sıralama sonucu için Stack kullanılır
		Deque<Vertex> stack = new ArrayDeque<>();

		// Tüm düğümler için DFS başlatılır (disconnected graph olabilir)
		for (Vertex v : adjList.keySet()) {
			if (!visited.contains(v)) {
				dfsTopological(v, visited, stack);
			}
		}

		// Sonuç yazdırılır: stack'deki vertex'ler ters sırada sıralanır
		System.out.println("Topological Sort Order:");
		while (!stack.isEmpty()) {
			System.out.print(stack.pop() + " ");
		}
	}

	// Yardımcı DFS fonksiyonu - Topological Sort için
	private void dfsTopological(Vertex current, Set<Vertex> visited, Deque<Vertex> stack) {
		visited.add(current); // Bu vertex'i ziyaret ettik

		// Bu vertex'in tüm outgoing (giden) kenarları işlenir
		for (Vertex neighbor : adjList.get(current)) {
			if (!visited.contains(neighbor)) {
				// Eğer komşu düğüm henüz keşfedilmemişse DFS devam eder
				dfsTopological(neighbor, visited, stack);
			}
			// Eğer neighbor zaten ziyaret edilmişse:
			// forward edge, cross edge ayrımı slaytta anlatıldığı gibi yapılabilir (isteğe
			// bağlı)
		}

		// Bu vertex'e giden yollar tamamen bittiğinde (post-order)
		// onu topological order'a dahil etmek için stack'e ekliyoruz
		stack.push(current);
	}
}

//Test sınıfı
public class DirectedGraphTopologicalSort {
	public static void main(String[] args) {
		Digraph digraph = new Digraph();

		// Slaytta geçen örneğe benzer şekilde vertex'ler eklenir
		digraph.addVertex("A");
		digraph.addVertex("B");
		digraph.addVertex("C");
		digraph.addVertex("D");
		digraph.addVertex("E");

		// Yönlü kenarlar (directed edges) eklenir
		digraph.addEdge("A", "C");
		digraph.addEdge("B", "C");
		digraph.addEdge("C", "D");
		digraph.addEdge("D", "E");

		// Topological Sort işlemi başlatılır
		digraph.topologicalSort();
	}
}
