// BOOK BST
public class BookBST {

    // Root node of the BST
    private Book root;

    // Constructor
    public BookBST() {
        this.root = null;
    }

    // Insert a book into the BST
    public void insert(String isbnInput, String title, String author) {

        // ISBN VALIDATION
        if (isbnInput == null || isbnInput.trim().isEmpty()) {
            System.out.println("[Error] ISBN cannot be empty.");
            return;
        }

        isbnInput = isbnInput.trim();

        if (!isbnInput.matches("\\d+")) {
            System.out.println("[Error] ISBN must contain numbers only.");
            return;
        }

        // Convert String to int
        int isbn = Integer.parseInt(isbnInput);

        if (isbn <= 0) {
            System.out.println("[Error] ISBN must be a positive number.");
            return;
        }

        // TITLE VALIDATION
        if (title == null || title.trim().isEmpty()) {
            System.out.println("[Error] Title cannot be empty.");
            return;
        }

        title = title.trim();

        // AUTHOR VALIDATION
        if (author == null || author.trim().isEmpty()) {
            System.out.println("[Error] Author cannot be empty.");
            return;
        }

        author = author.trim();

        if (author.matches(".*\\d.*")) {
            System.out.println("[Error] Author name cannot contain numbers.");
            return;
        }

        // Insert into BST
        root = insertRec(root, isbn, title, author);
    }

    // Recursive insert helper
    private Book insertRec(Book node, int isbn, String title, String author) {

        // Base case: empty spot found
        if (node == null) {
            System.out.println("[Success] Book \"" + title + "\" added to catalogue.");
            return new Book(isbn, title, author);
        }

        // Duplicate ISBN check
        if (isbn == node.getIsbn()) {
            System.out.println("[Warning] ISBN " + isbn + " already exists in catalogue.");
            return node;
        }

        // Go LEFT if isbn is smaller
        if (isbn < node.getIsbn()) {
            node.left = insertRec(node.left, isbn, title, author);
        }

        // Go RIGHT if isbn is larger
        else {
            node.right = insertRec(node.right, isbn, title, author);
        }

        return node;
    }

    // Getter for root
    public Book getRoot() {
        return root;
    }

    // Setter for root
    public void setRoot(Book root) {
        this.root = root;
    }
}