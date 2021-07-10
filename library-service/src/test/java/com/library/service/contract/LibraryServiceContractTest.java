package com.library.service.contract;

import com.library.service.LibraryServiceApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.is;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = LibraryServiceApplication.class)
@AutoConfigureMockMvc
// To use this: run "clean build publishToMavenLocal" on producer side.
@AutoConfigureStubRunner(stubsMode = StubRunnerProperties.StubsMode.LOCAL, ids = "com.rupesh.library:book-service:+:stubs:9092")
//@AutoConfigureStubRunner( stubsMode = StubRunnerProperties.StubsMode.REMOTE, repositoryRoot = "stubs://file://C:/Users/Rupesh_Patil/Downloads/JPOP%20Workspace/library-mgmt-system/book-service/build/libs/", ids = "com.rupesh.library:book-service")
public class LibraryServiceContractTest {

    /*@Autowired
    WebApplicationContext wac;*/

    @Autowired
    private MockMvc mockMvc;

    /*@BeforeEach
    public void before() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).dispatchOptions(true).build();
    }*/

    @Test
    public void validate_get_book_by_id_contract() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books/12").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(12)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is("test thriller book 1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", is("test thriller book 1 desc")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author", is("Author-1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.category", is("THRILLER")));
    }
}
