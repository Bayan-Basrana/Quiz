package com.example.testt.Controller;

import com.example.testt.ApiResponse.ApiResponse;
import com.example.testt.Model.Book;
import com.example.testt.Model.User;
import com.example.testt.Service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/getBook")
    public ResponseEntity getBook(){
        ArrayList<Book> books =bookService.getBooks();
        return ResponseEntity.status(200).body(books);
    }

    @PostMapping("/addBook")
    public ResponseEntity addBook (@RequestBody @Valid Book book , Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        bookService.addBook(book);
        return ResponseEntity.status(200).body(new ApiResponse("book added successfully"));
    }

    @PutMapping("/updateBook/{id}")
    public ResponseEntity updateUser (@PathVariable String id , @RequestBody @Valid Book book, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        boolean isUpdated = bookService.updateBook(id ,book);
        if (isUpdated){
            bookService.updateBook(id,book);
            return ResponseEntity.status(200).body(new ApiResponse("book updated successfully"));
        }else return ResponseEntity.status(400).body(new ApiResponse("id not found"));
    }


    @DeleteMapping("/deleteBook/{id}")
    public ResponseEntity deleteBook (@PathVariable String id ){
        boolean isDeleted = bookService.deleteBook(id);
        if (isDeleted){
            return ResponseEntity.status(200).body(new ApiResponse("book deleted successfully"));
        }else return ResponseEntity.status(400).body(new ApiResponse("id not found"));
    }


    @GetMapping("/bookByName/{name}")
    public ResponseEntity bookByName (@PathVariable String name){
        if (bookService.bookByName(name)!= null){
            return ResponseEntity.status(200).body(bookService.bookByName(name));
        }return ResponseEntity.status(400).body(new ApiResponse("no book have this name"));
    }
@GetMapping("/bookByCategory/{category}")
    public ResponseEntity bookByCategory (@PathVariable String category ){
        ArrayList<Book> byCategory = bookService.bookByCategory(category );
        if (bookService.bookByCategory(category )!= null){
            return ResponseEntity.status(200).body(byCategory);
        }return ResponseEntity.status(400).body(new ApiResponse(" no book have this category "));
    }

@GetMapping("/byNumber_of_pages/{number_of_pages}")
    public ResponseEntity bookByNumberOfPages (@PathVariable int number_of_pages){
        ArrayList<Book> byNumber_of_pages = bookService.bookByNumberOfPages(number_of_pages);

        if (bookService.bookByNumberOfPages(number_of_pages)!=null){
            return ResponseEntity.status(200).body(byNumber_of_pages);
        }return ResponseEntity.status(400).body(new ApiResponse(" no book have this number of pages or above"));
    }



    
public  ResponseEntity changeBookStatus (@PathVariable String id , @PathVariable String requstedId , User user){
        if (bookService.changeBookStatus(id ,requstedId, user)==1){
            return ResponseEntity.status(400).body("Only the librarian can change the status of the book");
        }
    if (bookService.changeBookStatus(id ,requstedId, user)==2) {
        return ResponseEntity.status(400).body("book is alredy unavailable ");
    }
    if (bookService.changeBookStatus(id ,requstedId, user)==3) {
        return ResponseEntity.status(200).body("successfully ");
    }
    return  ResponseEntity.status(400).body(new ApiResponse("id not found"));

    }



}
