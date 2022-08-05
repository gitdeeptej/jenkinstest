package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.demo.beans.Book;
import com.example.demo.controller.BookRestController;
import com.example.demo.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;


@WebMvcTest(value = BookRestController.class)
public class BookRestControllerTest {
	@MockBean
	private BookService bookService;
	@Autowired
	MockMvc mockMvc;
	@Test
	public void testAddBook01() throws Exception{ 
		when(bookService.saveBook(ArgumentMatchers.any())).thenReturn(true);
		
		Book b = new Book(1,"java",450.00);
		ObjectMapper mapper = new ObjectMapper();
		String bookJson = mapper.writeValueAsString(b);
		
		MockHttpServletRequestBuilder reqBuilder =  MockMvcRequestBuilders.post("/addBook")
				  .contentType(MediaType.APPLICATION_JSON)
				  .content(bookJson);
		
		ResultActions perform = mockMvc.perform(reqBuilder);
		MvcResult andReturn = perform.andReturn();
		MockHttpServletResponse response = andReturn.getResponse();
		int status = response.getStatus();
		assertEquals(201, status); 
	}
	@Test
	public void testAddBook02() throws Exception{ 
		when(bookService.saveBook(ArgumentMatchers.any())).thenReturn(false);
		
		Book b = new Book(1,"java",450.00);
		ObjectMapper mapper = new ObjectMapper();
		String bookJson = mapper.writeValueAsString(b);
		
		MockHttpServletRequestBuilder reqBuilder =  MockMvcRequestBuilders.post("/addBook")
				  .contentType(MediaType.APPLICATION_JSON)
				  .content(bookJson);
		
		ResultActions perform = mockMvc.perform(reqBuilder);
		MvcResult andReturn = perform.andReturn();
		MockHttpServletResponse response = andReturn.getResponse();
		int status = response.getStatus();
		assertEquals(400, status); 
	}
	
}

