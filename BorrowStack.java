public class BorrowStack {
 
    //LinkedList node
    private class Node {
        Book data;   // The book stored in this node
        Node next;   // Pointer to the node below it in the stack
 
        Node(Book data) {
            this.data = data;
            this.next = null;
        }
    }
    
    // STACK STATE
    private Node top; 
    private int size;  
 
    // Constructor
    public BorrowStack() {
        this.top  = null;
        this.size = 0;
    }

    // Called when a student borrows a book
    public void push(Book book) {
 
        // Validation: don't push null
        if (book == null) {
            System.out.println("[Error] Cannot add a null book to history.");
            return;
        }

        // Create a new node and link it above the current top
        Node newNode = new Node(book);
        newNode.next = top;   // new node points DOWN to old top
        top = newNode;        // top now points to the new node
        size++;
 
        System.out.println("[History] \"" + book.getTitle() + "\" added to borrowing history.");
    }
 
    // Called when a student returns a book (undo last borrow)
    public Book pop() {
 
        if (isEmpty()) {
            System.out.println("[Error] Borrowing history is empty. Nothing to remove.");
            return null;
        }
 
        Book removedBook = top.data;  // Save the book to return it
        top = top.next;               // Move top down to the next node
        size--;
 
        System.out.println("[History] \"" + removedBook.getTitle() + "\" removed from borrowing history.");
        return removedBook;
    }
    // look at the top book WITHOUT removing it
    public Book peek() {
 
        if (isEmpty()) {
            System.out.println("[Error] Borrowing history is empty.");
            return null;
        }
 
        return top.data;
    }
 
    //Check if the stack has no books
    public boolean isEmpty() {
        return top == null;
    }

    // Return how many books are in the stack
    public int getSize() {
        return size;
    }
 
    public void displayHistory() {
 
        if (isEmpty()) {
            System.out.println("[Info] Borrowing history is empty.");
            return;
        }
 
        System.out.println("\n Borrowing History (Most Recent First)");
 
        Node current = top;   // Start from the top (most recent)
        int position = 1;
 
        while (current != null) {
            System.out.println("  " + position + ". " + current.data.toString());
            current = current.next;   // Move down the linked list
            position++;
        }
    }
 
    //Empty the entire history
    public void clear() {
        top  = null;
        size = 0;
        System.out.println("[Info] Borrowing history has been cleared.");
    }
}