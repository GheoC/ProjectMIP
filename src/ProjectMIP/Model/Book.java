package Model;


import javafx.scene.control.CheckBox;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private int id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "items_available")
    private int itemsAvailable;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private Set<BookOrder> bookOrders = new HashSet<>();

    @Transient
    private CheckBox selected = new CheckBox();

    public int getId() {
        return id;
    }

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


    public CheckBox getSelected() {
        return selected;
    }

    public void setSelected(CheckBox selected) {
        this.selected = selected;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", itemsAvailable=" + itemsAvailable +
                '}';
    }
}
