package Service.BookService;

import Model.Book;
import Repository.BookRepository.BookRepository;

import java.util.List;
import java.util.Set;

public class BookService
{
    private final BookRepository bookRepository = new BookRepository();

    public void addBook(String name, String author, Integer numberOfItems)
    {
        Book book = new Book()
                .setName(name)
                .setAuthor(author)
                .setItemsAvailable(numberOfItems);

        bookRepository.addBookToDatabase(book);

    }

    public void updateItemsForBooks(Set<Book> books)
    {
        bookRepository.updateItemsForBooks(books);
    }


    public List<Book> getAllBooks()
    {
        return bookRepository.getBooks();
    }
}
