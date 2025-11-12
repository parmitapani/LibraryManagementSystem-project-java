import java.util.Scanner;
import java.util.List;

public class LibraryManagementSystem {
    public static void main(String[] args) {
        DatabaseHelper.initDatabase();

        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                System.out.println("\n1. Add Book\n2. Display Books\n3. Borrow Book\n4. Return Book\n5. Exit");
                int choice = sc.nextInt();
                sc.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter title: ");
                        String title = sc.nextLine();
                        System.out.print("Enter author: ");
                        String author = sc.nextLine();
                        DatabaseHelper.addBook(title, author);
                        break;
                    case 2:
                        List<Book> books = DatabaseHelper.getAllBooks();
                        for (Book b : books) {
                            System.out.println(b.title + " by " + b.author + " - " + (b.isAvailable ? "Available" : "Borrowed"));
                        }
                        break;
                    case 3:
                        System.out.print("Enter title to borrow: ");
                        title = sc.nextLine();
                        if (DatabaseHelper.borrowBook(title)) {
                            System.out.println("Book borrowed!");
                        } else {
                            System.out.println("Book not available or not found.");
                        }
                        break;
                    case 4:
                        System.out.print("Enter title to return: ");
                        title = sc.nextLine();
                        if (DatabaseHelper.returnBook(title)) {
                            System.out.println("Book returned!");
                        } else {
                            System.out.println("Book not found or not borrowed.");
                        }
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }
            }
        }
    }
}
