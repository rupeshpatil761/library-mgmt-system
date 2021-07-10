package com.library.service.contract;

import com.library.service.LibraryServiceApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.is;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = LibraryServiceApplication.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@AutoConfigureStubRunner(stubsMode = StubRunnerProperties.StubsMode.LOCAL, ids = "com.rupesh.library:book-service:+:stubs:9092")
//@ActiveProfiles("test")
public class LibraryServiceContractTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void validate_get_book_by_id_contract() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books/12"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is("test thriller book 1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", is("test thriller book 1 desc")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author", is("Author-1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.category", is("THRILLER")));
    }
}
