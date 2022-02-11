package Model;


import javax.persistence.*;

@Entity
@Table(name = "book")
public class Book
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "items_available")
    private int itemsAvailable;


    public String getName() {
        return name;
    }

    public Book setName(String name) {
        this.name = name;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public Book setAuthor(String author) {
        this.author = author;
        return this;
    }

    public int getItemsAvailable() {
        return itemsAvailable;
    }

    public Book setItemsAvailable(int itemsAvailable) {
        this.itemsAvailable = itemsAvailable;
        return this;
    }
}
