package com.example.testt.Service;

import com.example.testt.Model.Book;
import com.example.testt.Model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BookService {
    ArrayList<Book> books =new ArrayList<>();

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void addBook (Book book){
         books.add(book);
    }



    public boolean updateBook (String id , Book book){
        for (int i = 0; i < books.size(); i++) {
            if(books.get(i).getId().equalsIgnoreCase(id)){
                books.set(i,book);
                return true;
            }
        }return false;
    }

    public boolean deleteBook (String id ){
        for(Book b:books){
            if (b.getId().equalsIgnoreCase(id)){
                books.remove(b);
                return true;
            }
        }return false;
    }


    public Book bookByName (String name){
        for (Book b:books){
            if(b.getName().equalsIgnoreCase(name)){
                return b;
            }
        }return null;
    }


public ArrayList<Book> bookByCategory (String category ){
        ArrayList<Book> byCategory = new ArrayList<>();
        for (Book b:books){
            if (b.getCategory().equalsIgnoreCase(category )){
                byCategory.add(b);
            }return byCategory;
        }return null;
}


public ArrayList<Book> bookByNumberOfPages (int number_of_pages){
        ArrayList<Book> byNumber_of_pages =new ArrayList<>();
        for (Book b:books){
            if (b.getNumber_of_pages()>=number_of_pages){
                byNumber_of_pages.add(b);
            }return byNumber_of_pages;
        }return null;
}

public int changeBookStatus (String id , String requstedId ,User user) {
    User requested;
    for (Book b : books) {
        if (user.getId().equalsIgnoreCase(requstedId) && user.getRole().equalsIgnoreCase("libraian")) {
            return 1;}
            if (b.isAvailable()==false){
                return 2;
            }
            if (b.isAvailable()==true){
                b.setAvailable(false);
                return 3;
            }
        }

    return 4;
}

}
