package Service.BookService;

import Model.Book;
import Repository.BookRepository.BookRepository;

import java.util.List;

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


    public List<Book> getAllBooks()
    {
        return bookRepository.getBooks();
    }
}
