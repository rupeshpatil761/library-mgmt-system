package com.book.service.cloud.contract;

import com.book.service.BookServiceApplication;
import com.book.service.controller.BookController;
import com.book.service.model.Book;
import com.book.service.model.BookCategory;
import com.book.service.service.BookServiceImpl;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMessageVerifier
public class BaseContractTest {

    @MockBean
    private BookServiceImpl bookService;

    @Autowired
    private BookController bookController;

    @Before
    public void setup(){

        // Skip the rest assure security config
        RestAssuredMockMvc.config = RestAssuredMockMvc.config().mockMvcConfig(
               RestAssuredMockMvc.config.getMockMvcConfig().dontAutomaticallyApplySpringSecurityMockMvcConfigurer());

        //set REST endpoint for test - exposing all endpoints
        RestAssuredMockMvc.standaloneSetup(bookController);

        // Mock business layer
        Mockito.when(bookService.getBookById(Mockito.anyLong())).thenReturn(getMockBookData());

    }

    private Book getMockBookData(){
        Book book = new Book();
        book.setId(12l);
        book.setDescription("test thriller book 1 desc");
        book.setAuthor("Author-1");
        book.setName("test thriller book 1");
        book.setCategory(BookCategory.THRILLER);
        return book;
    }

    /*@Test
    public void contextLoads() {
    }*/
}
