// BOOK MODEL
public class Book {

    // Attributes
    private int isbn;
    private String title;
    private String author;

    // BST pointers
    Book left;
    Book right;

    // Constructor
    public Book(int isbn, String title, String author) {
        this.isbn   = isbn;
        this.title  = title;
        this.author = author;
        this.left   = null;
        this.right  = null;
    }

    // Getters
    public int getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    // Setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    // Display method
    public void displayInfo() {
        System.out.println("+.........................+");
        System.out.println("  ISBN   : " + isbn);
        System.out.println("  Title  : " + title);
        System.out.println("  Author : " + author);
        System.out.println("+.........................+");
    }

    @Override
    public String toString() {
        return "[ISBN: " + isbn + "] " + title + " by " + author;
    }
}