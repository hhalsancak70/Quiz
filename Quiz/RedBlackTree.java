package Quiz;

//Red-Black Tree Implementation (2-3-4 Tree Temsili)
public class RedBlackTree {

	private static final boolean RED = true;
	private static final boolean BLACK = false;

	// Red-Black Tree Node tanımı
	static class Node {
		int key;
		Node left, right, parent;
		boolean color;

		Node(int key) {
			this.key = key;
			this.color = RED; // Yeni eklenen node her zaman KIRMIZI başlar
		}
	}

	private Node root;

	// Yardımcı: Sol döndürme
	private void leftRotate(Node x) {
		Node y = x.right;
		x.right = y.left;
		if (y.left != null)
			y.left.parent = x;
		y.parent = x.parent;

		if (x.parent == null)
			root = y;
		else if (x == x.parent.left)
			x.parent.left = y;
		else
			x.parent.right = y;

		y.left = x;
		x.parent = y;
	}

	// Yardımcı: Sağ döndürme
	private void rightRotate(Node y) {
		Node x = y.left;
		y.left = x.right;
		if (x.right != null)
			x.right.parent = y;
		x.parent = y.parent;

		if (y.parent == null)
			root = x;
		else if (y == y.parent.left)
			y.parent.left = x;
		else
			y.parent.right = x;

		x.right = y;
		y.parent = x;
	}

	// RB ağacına ekleme işlemi
	public void insert(int key) {
		Node z = new Node(key);
		Node y = null;
		Node x = root;

		// 1. Normal BST ekleme işlemi
		while (x != null) {
			y = x;
			if (z.key < x.key)
				x = x.left;
			else
				x = x.right;
		}
		z.parent = y;

		if (y == null)
			root = z;
		else if (z.key < y.key)
			y.left = z;
		else
			y.right = z;

		// 2. Double Red düzeltme
		insertFixup(z);
	}

	// Double Red (parent ve child ikisi de kırmızı) ihlalini düzeltme
	private void insertFixup(Node z) {
		while (z.parent != null && z.parent.color == RED) {
			if (z.parent == z.parent.parent.left) {
				Node y = z.parent.parent.right; // amca düğüm (uncle)
				if (y != null && y.color == RED) {
					// Case 1: Recolor (split of 4-node)
					z.parent.color = BLACK;
					y.color = BLACK;
					z.parent.parent.color = RED;
					z = z.parent.parent;
				} else {
					if (z == z.parent.right) {
						// Case 2: Left Rotation
						z = z.parent;
						leftRotate(z);
					}
					// Case 3: Right Rotation
					z.parent.color = BLACK;
					z.parent.parent.color = RED;
					rightRotate(z.parent.parent);
				}
			} else {
				// Sağ taraf için simetrik işlemler
				Node y = z.parent.parent.left;
				if (y != null && y.color == RED) {
					z.parent.color = BLACK;
					y.color = BLACK;
					z.parent.parent.color = RED;
					z = z.parent.parent;
				} else {
					if (z == z.parent.left) {
						z = z.parent;
						rightRotate(z);
					}
					z.parent.color = BLACK;
					z.parent.parent.color = RED;
					leftRotate(z.parent.parent);
				}
			}
		}
		root.color = BLACK; // root her zaman siyah
	}

	// Ağacı inorder olarak yazdırır (küçükten büyüğe)
	public void inorderTraversal(Node node) {
		if (node != null) {
			inorderTraversal(node.left);
			System.out.print(node.key + " ");
			inorderTraversal(node.right);
		}
	}

	public void printTree() {
		inorderTraversal(root);
		System.out.println();
	}

	// Test
	public static void main(String[] args) {
		RedBlackTree tree = new RedBlackTree();
		int[] keys = { 10, 20, 30, 15, 25, 5, 1 };

		for (int key : keys) {
			tree.insert(key);
		}

		System.out.println("Red-Black Tree (Inorder Traversal):");
		tree.printTree();
	}
}
