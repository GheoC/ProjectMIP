package Service.BookOrderService;

import Model.Book;
import Model.BookOrder;
import Model.User;
import Repository.BookOrderRepository.BookOrderRepository;

import java.util.List;
import java.util.Set;

public class BookOrderService
{
    private final BookOrderRepository bookOrderRepository = new BookOrderRepository();

    public void lendBooksToUser(User user, Set<Book> books, Integer period)
    {
        bookOrderRepository.addBookOrders(user, books, period);

    }

    public List<BookOrder> findBookOrder(Integer id)
    {

        return bookOrderRepository.findBookOrderByUserId(id);
    }

    public void receiveBooksFromUser(List<BookOrder> bookOrders)
    {
        bookOrderRepository.receiveBooks(bookOrders);


    }

}
