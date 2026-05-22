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

    // Person 2
    // SEARCH (Person 2)
    public Book search(String isbnInput) {

        // ISBN VALIDATION
        if (isbnInput == null || isbnInput.trim().isEmpty()) {
            System.out.println("[Error] ISBN cannot be empty.");
            return null;
        }

        isbnInput = isbnInput.trim();

        if (!isbnInput.matches("\\d+")) {
            System.out.println("[Error] ISBN must contain numbers only.");
            return null;
        }

        int isbn = Integer.parseInt(isbnInput);

        if (isbn <= 0) {
            System.out.println("[Error] ISBN must be a positive number.");
            return null;
        }

        // Perform recursive search
        Book result = searchRec(root, isbn);

        if (result == null) {
            System.out.println("[Not Found] No book with ISBN " + isbn + " in catalogue.");
        }

        return result;
    }

    // Recursive search helper
    private Book searchRec(Book node, int isbn) {

        if (node == null || node.getIsbn() == isbn) {
            return node;
        }

        // Go LEFT if isbn is smaller
        if (isbn < node.getIsbn()) {
            return searchRec(node.left, isbn);
        }

        // Go RIGHT if isbn is larger
        return searchRec(node.right, isbn);
    }

    // DISPLAY ALL (Person 2)
    public void displayAll() {

        if (root == null) {
            System.out.println("[Info] Catalogue is empty.");
            return;
        }

        System.out.println("\n===== Book Catalogue (sorted by ISBN) =====");
        displayInOrder(root);
        System.out.println("===========================================\n");
    }

    // Recursive in-order traversal: LEFT -> ROOT -> RIGHT
    // This prints books in ascending ISBN order
    private void displayInOrder(Book node) {

        if (node == null) {
            return;
        }

        displayInOrder(node.left);   // Visit left subtree first
        node.displayInfo();          // Print current node
        displayInOrder(node.right);  // Visit right subtree last
    }

    // DELETE (Person 2)
    public void delete(String isbnInput) {

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

        int isbn = Integer.parseInt(isbnInput);

        if (isbn <= 0) {
            System.out.println("[Error] ISBN must be a positive number.");
            return;
        }

        // Check if book exists first
        if (searchRec(root, isbn) == null) {
            System.out.println("[Not Found] No book with ISBN " + isbn + " in catalogue.");
            return;
        }

        root = deleteRec(root, isbn);
        System.out.println("[Success] Book with ISBN " + isbn + " removed from catalogue.");
    }

    // Recursive delete helper — handles 3 BST cases
    private Book deleteRec(Book node, int isbn) {

        // Base case: node not found
        if (node == null) {
            return null;
        }

        // Navigate to the target node
        if (isbn < node.getIsbn()) {
            node.left = deleteRec(node.left, isbn);

        } else if (isbn > node.getIsbn()) {
            node.right = deleteRec(node.right, isbn);

        } else {
            // TARGET FOUND — handle 3 cases:

            // Case 1: No children (leaf node) — just remove it
            if (node.left == null && node.right == null) {
                return null;
            }

            // Case 2a: Only right child — replace with right child
            if (node.left == null) {
                return node.right;
            }

            // Case 2b: Only left child — replace with left child
            if (node.right == null) {
                return node.left;
            }

            // Case 3: Two children — replace with in-order successor
            Book successor = findMin(node.right);
            node.right = deleteRec(node.right, successor.getIsbn());

            Book replacement = new Book(successor.getIsbn(), successor.getTitle(), successor.getAuthor());
            replacement.left  = node.left;
            replacement.right = node.right;
            return replacement;
        }

        return node;
    }

    // Helper: find the minimum node (leftmost) in a subtree
    private Book findMin(Book node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
}