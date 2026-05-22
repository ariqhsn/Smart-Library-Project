public interface LibraryADT {

    void addBook(String isbn, String title, String author);
 
    void searchBook(String isbn);
 
    void borrowBook(String isbn);

    void returnBook();

    void viewLatestHistory();

    void displayCatalogue();
}
 