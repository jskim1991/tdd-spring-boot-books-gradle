package io.tanzu.labs.tddspringbootbooks;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;

import javax.transaction.Transactional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureDataJpa
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class TddSpringBootBooksApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookJpaRepository jpaRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void test_getAll_returnsOk() throws Exception {
        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
        ;
    }

    @Test
    void test_getAll_returnsEmptyArray() throws Exception {
        mockMvc.perform(get("/books"))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty())
        ;
    }

    @Test
    void test_getAll_returnsSingleBook() throws Exception {
        jpaRepository.save(new BookEntity("Book1"));


        mockMvc.perform(get("/books"))
                .andExpect(jsonPath("$[0].id", equalTo(1)))
                .andExpect(jsonPath("$[0].name", equalTo("Book1")))
        ;
    }

    @Test
    void test_getBook_returnsSingleBook() throws Exception {
        jpaRepository.save(new BookEntity("Book1"));


        mockMvc.perform(get("/books/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.name", equalTo("Book1")))
        ;
    }

    @Test
    void test_addBook_returnsCreated() throws Exception {
        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isCreated());
    }

    @Test
    void test_addBook_returnsBookAdded() throws Exception {
        String json = new ObjectMapper().writeValueAsString(new NewBook("Book1"));


        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.name", equalTo("Book1")));
    }

    @Test
    void test_addBook_persistsToDB() throws Exception {
        String json = new ObjectMapper().writeValueAsString(new NewBook("Book1"));


        mockMvc.perform(post("/books")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON));


        assertThat(jpaRepository.findAll().size(), equalTo(1));
        BookEntity actualBook = jpaRepository.findById(1).get();
        assertThat(actualBook.getName(), equalTo("Book1"));
    }

    @Test
    void test_updatedBook_returnsUpdatedBook() throws Exception {
        jpaRepository.save(new BookEntity("Book1"));
        String json = new ObjectMapper().writeValueAsString(new UpdateBook("Updated Book1"));


        mockMvc.perform(put("/books/1")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.name", equalTo("Updated Book1")))
        ;
    }

    @Test
    void test_updateBook_persistsToDB() throws Exception {
        jpaRepository.save(new BookEntity("Book1"));
        String json = new ObjectMapper().writeValueAsString(new UpdateBook("Updated Book1"));


        mockMvc.perform(put("/books/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json));

        BookEntity actualBook = jpaRepository.findById(1).get();
        assertThat(actualBook.getName(), equalTo("Updated Book1"));
    }

    @Test
    void test_getBookWhenEmpty_throwsException() throws Exception {
        NestedServletException e = assertThrows(NestedServletException.class, () ->
                mockMvc.perform(get("/books/999"))
        );
        RuntimeException innerException = (RuntimeException) e.getCause();
        assertThat(innerException.getClass(), equalTo(RuntimeException.class));
        assertThat(innerException.getMessage(), equalTo("No such book for id 999"));
    }

    @Test
    public void test_updateBookWhenEmpty_throwsException() {
        NestedServletException e = assertThrows(NestedServletException.class, () ->
                mockMvc.perform(put("/books/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
        );
        RuntimeException innerException = (RuntimeException) e.getCause();
        assertThat(innerException.getClass(), equalTo(RuntimeException.class));
        assertThat(innerException.getMessage(), equalTo("No such book for id 999"));
    }

    @Test
    void test_deleteBookWhenEmpty_throwsException() {
        NestedServletException e = assertThrows(NestedServletException.class, () ->
                mockMvc.perform(delete("/books/999"))
        );
        RuntimeException innerException = (RuntimeException) e.getCause();
        assertThat(innerException.getClass(), equalTo(RuntimeException.class));
        assertThat(innerException.getMessage(), equalTo("No such book for id 999"));
    }

    @Test
    void test_deleteBook_deletesFromDB() throws Exception {
        jpaRepository.save(new BookEntity("Book1"));


        mockMvc.perform(delete("/books/1"));


        assertThat(jpaRepository.findById(1).isEmpty(), equalTo(true));
    }
}
