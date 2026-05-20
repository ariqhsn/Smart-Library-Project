public class BookBST {
    // The root of the Binary Search Tree is kept private (Information Hiding)
    private Book root;

    // INSERTION LOGIC
    
    // Main insert method called by Member 4 in SmartLibrary.java
    public void insert(int isbn, String t, String a) {
        root = ins(root, isbn, t, a);
    }

    // Recursive helper method to insert a book based on its ISBN
    private Book ins(Book r, int i, String t, String a) {
        // Base Case: If the current node is null, create the new Book object here
        if (r == null) {
            return new Book(i, t, a);
        }
        
        // Recursive Step: If the new ISBN is smaller, navigate to the left subtree
        if (i < r.isbn) {
            r.left = ins(r.left, i, t, a);
        } 
        // Recursive Step: If the new ISBN is larger, navigate to the right subtree
        else if (i > r.isbn) {
            r.right = ins(r.right, i, t, a);
        }
        // If the ISBN already exists (duplicate), it is ignored in a standard BST
        
        return r;
    }

    // SEARCH LOGIC (bukan punya gw ini intinya)

    public Book search(int i) {
        return sea(root, i);
    }

    private Book sea(Book r, int i) {
        if (r == null || r.isbn == i) return r;
        return (i < r.isbn) ? sea(r.left, i) : sea(r.right, i);
    }
}