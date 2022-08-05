package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.beans.Book;
import com.example.demo.service.BookService;



@RestController
public class BookRestController {
	@Autowired
	private BookService bookService;
	
	@PostMapping(value="/addBook", consumes = {"application/json"})
	public ResponseEntity<String> addBook(@RequestBody Book book){
		String msg = null;
		boolean isSaved = bookService.saveBook(book);
		if(isSaved) {
			msg = "Book Saved";
			return new ResponseEntity<String>(msg,HttpStatus.CREATED);
		}else {
			msg = "Failed to save";
			return new ResponseEntity<String>(msg,HttpStatus.BAD_REQUEST);
		}
	}
	
}

