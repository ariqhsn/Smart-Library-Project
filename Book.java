public class Book {
    // Book data attributes (data buat buku2)
    int isbn;
    String title;
    String author;
    
    // References for the Binary Search Tree (BST) nodes
    Book left;
    Book right;

    // Constructor to initialize a new book node
    public Book(int isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.left = null;
        this.right = null;
    }
} //dbuwuhdw