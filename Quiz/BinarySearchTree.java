package Quiz;

//Binary Search Tree uygulaması - temel işlemler: arama, ekleme, silme
public class BinarySearchTree {

	// Ağaç düğümünü temsil eden iç sınıf
	static class Node {
		int key; // Düğümün anahtar değeri
		Node left, right; // Sol ve sağ çocuk düğümleri

		// Yapıcı metot - yeni düğüm oluşturur
		Node(int item) {
			key = item;
			left = right = null;
		}
	}

	Node root; // Ağacın kök düğümü

	// Ağacı başlatmak için yapıcı metot
	BinarySearchTree() {
		root = null;
	}

	// ----------------------------
	// 1. ARAMA (SEARCH)
	// ----------------------------
	// Verilen anahtar değeri arar
	Node search(Node root, int key) {
		// Ağacın sonuna gelindi ama bulunamadıysa null döner
		if (root == null || root.key == key)
			return root;

		// Anahtar mevcut düğümden küçükse sol alt ağaçta arar
		if (key < root.key)
			return search(root.left, key);

		// Anahtar mevcut düğümden büyükse sağ alt ağaçta arar
		return search(root.right, key);
	}

	// ----------------------------
	// 2. EKLEME (INSERTION)
	// ----------------------------
	// Anahtar değeri ağaca ekler (recursive)
	Node insert(Node root, int key) {
		// Eğer yerleşecek boş yer bulunduysa yeni düğüm oluşturulur
		if (root == null) {
			root = new Node(key);
			return root;
		}

		// Anahtar küçükse sol alt ağaca eklenir
		if (key < root.key)
			root.left = insert(root.left, key);

		// Anahtar büyükse sağ alt ağaca eklenir
		else if (key > root.key)
			root.right = insert(root.right, key);

		// Aynı anahtar zaten varsa eklenmez (varsayım)
		return root;
	}

	// Ağaca dışarıdan erişim için ekleme metodu
	void insert(int key) {
		root = insert(root, key);
	}

	// ----------------------------
	// 3. SİLME (DELETION)
	// ----------------------------
	// Verilen anahtar değerli düğümü ağaçtan siler
	Node delete(Node root, int key) {
		// Eğer ağaç boşsa null döner
		if (root == null)
			return root;

		// Anahtar küçükse sol alt ağaçta arama yapılır
		if (key < root.key)
			root.left = delete(root.left, key);

		// Anahtar büyükse sağ alt ağaçta arama yapılır
		else if (key > root.key)
			root.right = delete(root.right, key);

		// Anahtar bulundu
		else {
			// 1 veya hiç çocuğu varsa:
			if (root.left == null)
				return root.right;
			else if (root.right == null)
				return root.left;

			// 2 çocuğu varsa:
			// Sağ alt ağaçtaki en küçük değeri bulur (inorder successor)
			root.key = minValue(root.right);

			// Bulunan düğümü sağ alt ağaçtan siler
			root.right = delete(root.right, root.key);
		}

		return root;
	}

	// Sağ alt ağaçtaki en küçük anahtar değerini bulur
	int minValue(Node root) {
		int minv = root.key;
		while (root.left != null) {
			minv = root.left.key;
			root = root.left;
		}
		return minv;
	}

	// Ağaca dışarıdan erişim için silme metodu
	void delete(int key) {
		root = delete(root, key);
	}

	// ----------------------------
	// 4. INORDER TRAVERSAL (KÜÇÜKTEN BÜYÜĞE YAZDIRMA)
	// ----------------------------
	void inorder(Node root) {
		if (root != null) {
			inorder(root.left);
			System.out.print(root.key + " ");
			inorder(root.right);
		}
	}

	// Dışarıdan erişim için gezinti metodu
	void printInorder() {
		inorder(root);
		System.out.println();
	}

	// ----------------------------
	// 5. ÖRNEK KULLANIM
	// ----------------------------
	public static void main(String[] args) {
		BinarySearchTree tree = new BinarySearchTree();

		// Ekleme işlemleri
		tree.insert(6);
		tree.insert(9);
		tree.insert(2);
		tree.insert(4);
		tree.insert(1);
		tree.insert(8);
		tree.insert(5);

		System.out.println("Inorder traversal after insertions:");
		tree.printInorder(); // Küçükten büyüğe sıralı yazdırır

		// Arama örneği
		System.out.println("Search for 4: " + (tree.search(tree.root, 4) != null ? "Found" : "Not Found"));

		// Silme işlemi
		tree.delete(4);
		System.out.println("Inorder traversal after deleting 4:");
		tree.printInorder();

		// İki çocuklu düğüm silme örneği
		tree.delete(2);
		System.out.println("Inorder traversal after deleting 2:");
		tree.printInorder();
	}
}
