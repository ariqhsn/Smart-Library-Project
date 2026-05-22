import java.util.Scanner;

public class SmartLibrary implements LibraryADT {

    private final BookBST     catalogue = new BookBST();
    private final BorrowStack history   = new BorrowStack();


    @Override
    public void addBook(String isbn, String title, String author) {
        catalogue.insert(isbn, title, author);
    }

    @Override
    public void searchBook(String isbn) {
        Book b = catalogue.search(isbn);
        if (b != null) {
            System.out.println("\n[Found] Book details:");
            b.displayInfo();
        }
    }

    @Override
    public void borrowBook(String isbn) {
        Book b = catalogue.search(isbn);
        if (b == null) return;

        history.push(b);
        catalogue.delete(isbn);
    }

    @Override
    public void returnBook() {
        Book returned = history.pop();
        if (returned == null) return;

        catalogue.insert(
            String.valueOf(returned.getIsbn()),
            returned.getTitle(),
            returned.getAuthor()
        );
    }

    @Override
    public void viewLatestHistory() {
        history.displayHistory();
    }

    @Override
    public void displayCatalogue() {
        catalogue.displayAll();
    }

    public void runMenu() {
        Scanner sc = new Scanner(System.in);

        System.out.println("============================================");
        System.out.println("    Welcome to the Smart Library System     ");
        System.out.println("============================================");

        while (true) {
            printMenu();
            int choice = readMenuChoice(sc);

            // Exit option
            if (choice == 7) {
                System.out.println("\n[Goodbye] Thank you for using Smart Library!");
                break;
            }

            handleChoice(choice, sc);
        }

        sc.close();
    }

    private void printMenu() {
        System.out.println("\n------- Smart Library Menu -------");
        System.out.println("  1. Add Book");
        System.out.println("  2. Search Book           (BST O(log n))");
        System.out.println("  3. Borrow Book           (BST -> Stack)");
        System.out.println("  4. Return Last Book      (Stack -> BST, LIFO)");
        System.out.println("  5. View Borrowing History");
        System.out.println("  6. Display Full Catalogue");
        System.out.println("  7. Exit");
        System.out.println("----------------------------------");
    }

    private int readMenuChoice(Scanner sc) {
        System.out.print("Choice: ");
        String input = sc.nextLine().trim();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;   // sentinel = invalid input
        }
    }

    private void handleChoice(int choice, Scanner sc) {
        switch (choice) {

            case 1: {
                System.out.print("Enter ISBN   : ");
                String isbn   = sc.nextLine();
                System.out.print("Enter Title  : ");
                String title  = sc.nextLine();
                System.out.print("Enter Author : ");
                String author = sc.nextLine();
                addBook(isbn, title, author);
                break;
            }

            case 2: {
                System.out.print("Enter ISBN to search: ");
                String isbn = sc.nextLine();
                searchBook(isbn);
                break;
            }

            case 3: {
                System.out.print("Enter ISBN to borrow: ");
                String isbn = sc.nextLine();
                borrowBook(isbn);
                break;
            }

            case 4: {
                returnBook();
                break;
            }

            case 5: {
                viewLatestHistory();
                break;
            }

            case 6: {
                displayCatalogue();
                break;
            }

            default:
                System.out.println("[Error] Invalid option. Please enter a number from 1 to 7.");
        }
    }
}