package Model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "book_order")
public class BookOrder
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Book book;

    private LocalDate localDate;

    private int lendingTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public int getLendingTime() {
        return lendingTime;
    }

    public void setLendingTime(int lendingTime) {
        this.lendingTime = lendingTime;
    }

    @Override
    public String toString() {
        return "BookOrder{" +
                "id=" + id +
                ", user=" + user +
                ", book=" + book +
                ", localDate=" + localDate +
                ", lendingTime=" + lendingTime +
                '}';
    }
}
