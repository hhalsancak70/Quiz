package Quiz;

//AVL Tree Uygulaması - Arama, Ekleme, Dengeleme (Rotasyonlar)
public class AVLTree {

	// AVL Ağacı düğümünü tanımlayan iç sınıf
	static class AVLNode {
		int key; // Düğümün anahtar değeri
		int height; // Düğümün yüksekliği (denge için gerekli)
		AVLNode left; // Sol çocuk
		AVLNode right; // Sağ çocuk

		AVLNode(int key) {
			this.key = key;
			this.height = 1; // Yeni düğümler başta yaprak olur, yüksekliği 1
		}
	}

	AVLNode root; // Ağacın kök düğümü

	// 1. Bir düğümün yüksekliğini hesaplar
	int height(AVLNode node) {
		if (node == null)
			return 0;
		return node.height;
	}

	// 2. İki sayının maksimumunu döndürür
	int max(int a, int b) {
		return (a > b) ? a : b;
	}

	// 3. Sağ Rotasyon işlemi (LL dengesizliği için)
	AVLNode rightRotate(AVLNode y) {
		AVLNode x = y.left;
		AVLNode T2 = x.right;

		// Rotasyon işlemi
		x.right = y;
		y.left = T2;

		// Yükseklikleri güncelle
		y.height = max(height(y.left), height(y.right)) + 1;
		x.height = max(height(x.left), height(x.right)) + 1;

		// Yeni kökü döndür
		return x;
	}

	// 4. Sol Rotasyon işlemi (RR dengesizliği için)
	AVLNode leftRotate(AVLNode x) {
		AVLNode y = x.right;
		AVLNode T2 = y.left;

		// Rotasyon işlemi
		y.left = x;
		x.right = T2;

		// Yükseklikleri güncelle
		x.height = max(height(x.left), height(x.right)) + 1;
		y.height = max(height(y.left), height(y.right)) + 1;

		// Yeni kökü döndür
		return y;
	}

	// 5. Denge Faktörü Hesaplama (sol yüksekliği - sağ yüksekliği)
	int getBalance(AVLNode node) {
		if (node == null)
			return 0;
		return height(node.left) - height(node.right);
	}

	// 6. AVL Ağacına Ekleme İşlemi (BST mantığıyla, ardından dengelenir)
	AVLNode insert(AVLNode node, int key) {
		// 1. Normal BST ekleme
		if (node == null)
			return new AVLNode(key);

		if (key < node.key)
			node.left = insert(node.left, key);
		else if (key > node.key)
			node.right = insert(node.right, key);
		else
			return node; // Aynı anahtar varsa eklenmez (AVL'de benzersiz anahtarlar olur)

		// 2. Yükseklik güncellemesi
		node.height = 1 + max(height(node.left), height(node.right));

		// 3. Denge faktörünü hesapla
		int balance = getBalance(node);

		// 4. Dengesizlik durumları ve rotasyonlar

		// Sol Sol (LL) durumu
		if (balance > 1 && key < node.left.key)
			return rightRotate(node);

		// Sağ Sağ (RR) durumu
		if (balance < -1 && key > node.right.key)
			return leftRotate(node);

		// Sol Sağ (LR) durumu
		if (balance > 1 && key > node.left.key) {
			node.left = leftRotate(node.left);
			return rightRotate(node);
		}

		// Sağ Sol (RL) durumu
		if (balance < -1 && key < node.right.key) {
			node.right = rightRotate(node.right);
			return leftRotate(node);
		}

		// Dengeliyse aynı node'u döndür
		return node;
	}

	// 7. AVL Ağacında Inorder Gezinti (Küçükten büyüğe yazdırır)
	void inorder(AVLNode node) {
		if (node != null) {
			inorder(node.left);
			System.out.print(node.key + " ");
			inorder(node.right);
		}
	}

	// 8. Dışarıdan erişim için ekleme metodu
	void insert(int key) {
		root = insert(root, key);
	}

	// 9. AVL Ağacının yüksekliğini döndürür
	int getHeight() {
		return height(root);
	}

	// 10. Main - Örnek Kullanım
	public static void main(String[] args) {
		AVLTree tree = new AVLTree();

		// Slaytta verilen örnek değerlere benzer şekilde ekleme
		int[] keys = { 10, 20, 30, 40, 50, 25 };

		for (int key : keys)
			tree.insert(key);

		System.out.println("AVL Ağacının Inorder sıralaması:");
		tree.inorder(tree.root);

		System.out.println("\nAVL Ağacının Yüksekliği: " + tree.getHeight());
	}
}
