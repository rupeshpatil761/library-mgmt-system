package com.library.service.contract;

import com.library.service.bean.Book;
import com.library.service.bean.BookCategory;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.net.URI;

@ExtendWith(SpringExtension.class)
//@SpringBootTest(classes = LibraryServiceApplication.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@DirtiesContext
// To use this: run "clean build publishToMavenLocal" on producer side.
@AutoConfigureStubRunner(stubsMode = StubRunnerProperties.StubsMode.LOCAL, ids = "com.rupesh.library:book-service:+:stubs:9092")
//@AutoConfigureStubRunner( stubsMode = StubRunnerProperties.StubsMode.REMOTE, repositoryRoot = "stubs://file://C:/Users/Rupesh_Patil/Downloads/JPOP%20Workspace/library-mgmt-system/book-service/build/libs/", ids = "com.rupesh.library:book-service")
public class LibraryServiceContractTest {

   /* @Autowired
    private MockMvc mockMvc;*/

    @Test
    public void validate_get_book_by_id_contract() throws Exception {

        ResponseEntity<Book> result = new TestRestTemplate().exchange(RequestEntity
                .get(URI.create("http://localhost:9092/books/12"))
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .build(), Book.class);

        BDDAssertions.then(result.getStatusCode().value()).isEqualTo(200);
        BDDAssertions.then(result.getBody().getId()).isEqualTo(12);
        BDDAssertions.then(result.getBody().getAuthor()).isEqualTo("Author-1");
        BDDAssertions.then(result.getBody().getName()).isEqualTo("test thriller book 1");
        BDDAssertions.then(result.getBody().getCategory()).isEqualTo(BookCategory.THRILLER);
        BDDAssertions.then(result.getBody().getDescription()).isEqualTo("test thriller book 1 desc");

        /**
         * You are using mockMvc to connect to a WireMock instance. That won't work. Change mockMvc on the consumer side to a restTemplate
         */
        /*mockMvc.perform(MockMvcRequestBuilders.get("/books/12").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect()
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is("test thriller book 1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", is("test thriller book 1 desc")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author", is("Author-1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.category", is("THRILLER")));*/
    }
}
