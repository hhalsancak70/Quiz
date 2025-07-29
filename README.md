# Veri Yapıları ve Algoritmalar Projesi

Bu proje, temel veri yapıları ve algoritmaların Java implementasyonlarını içeren bir eğitim/öğrenim projesidir.

## 📚 İçerik

Proje aşağıdaki temel algoritma ve veri yapılarını içermektedir:

### Graf Algoritmaları
- **BFS (Breadth-First Search)**
  - Grafta seviye seviye arama algoritması
  - Konum: `BFS/BFS.java`

- **DFS (Depth-First Search)**
  - Grafta derinlemesine arama algoritması
  - Konum: `DFS/DFSExample.java`

- **Dijkstra Algoritması**
  - En kısa yol bulma algoritması
  - Konum: `DijkstraExample/DijkstraExample.java`

- **Topolojik Sıralama**
  - Yönlü graflarda düğümlerin sıralanması
  - Konum: `DirectedGraphTopologicalSort/DirectedGraphTopologicalSort.java`

### Ağaç Yapıları
- **AVL Ağacı**
  - Dengeli ikili arama ağacı
  - Konum: `Quiz/AVLTree.java`

- **İkili Arama Ağacı (BST)**
  - Temel ikili arama ağacı implementasyonu
  - Konum: `Quiz/BinarySearchTree.java`

- **Kırmızı-Siyah Ağaç**
  - Dengeli ikili arama ağacı
  - Konum: `Quiz/RedBlackTree.java`

### Graf Örneği
- **GraphExample**
  - Ağırlıklı ve yönlü graf implementasyonu
  - Havaalanları arası rota örneği
  - Konum: `Quiz/GraphExample.java`

## 🚀 Başlangıç

### Gereksinimler
- Java Development Kit (JDK) 11 veya üzeri
- Java IDE (Eclipse, IntelliJ IDEA, vb.)

### Kurulum
1. Projeyi klonlayın:
```bash
git clone [repo-url]
```

2. IDE'nizde projeyi açın

3. Modül yapılandırmasının doğru olduğundan emin olun (`module-info.java`)

## 💡 Kullanım Örnekleri

### Graf Örneği Çalıştırma
```java
public static void main(String[] args) {
    Graph graph = new Graph();

    // Düğüm ekleme
    graph.addVertex("ORD");
    graph.addVertex("DFW");
    graph.addVertex("SFO");
    graph.addVertex("LAX");

    // Kenar ekleme
    graph.addEdge("ORD", "DFW", 802);
    graph.addEdge("ORD", "SFO", 1843);
    graph.addEdge("DFW", "LAX", 1233);
    graph.addEdge("SFO", "LAX", 337);

    // Grafı yazdır
    graph.printGraph();
}
```

## 📝 Notlar
- Her algoritma kendi paketinde bulunmaktadır
- Implementasyonlar eğitim amaçlıdır
- Gerçek dünya senaryoları için optimize edilebilir

## 🤝 Katkıda Bulunma
1. Fork edin
2. Feature branch oluşturun (`git checkout -b feature/AmazingFeature`)
3. Değişikliklerinizi commit edin (`git commit -m 'Add some AmazingFeature'`)
4. Branch'inizi push edin (`git push origin feature/AmazingFeature`)
5. Pull Request oluşturun

## 📜 Lisans
Bu proje eğitim amaçlıdır.