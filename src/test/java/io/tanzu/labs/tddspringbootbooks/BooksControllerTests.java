package io.tanzu.labs.tddspringbootbooks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BooksControllerTests {

    private MockMvc mockMvc;
    private StubBookRepository stubBookRepository;

    @BeforeEach
    public void setup() {
        stubBookRepository = new StubBookRepository();


        mockMvc = MockMvcBuilders
                .standaloneSetup(new BooksController(stubBookRepository))
                .build();
    }

    @Test
    public void test_getAll_returnsOk() throws Exception {
        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
        ;
    }

    @Test
    public void test_getAll_returnsEmptyArray() throws Exception {
        mockMvc.perform(get("/books"))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty())
        ;
    }

    @Test
    public void test_getAll_returnsSingleBook() throws Exception {
        stubBookRepository.setData(Collections.singletonList(new Book(1, "Book1")));


        mockMvc.perform(get("/books"))
                .andExpect(jsonPath("$[0].name", equalTo("Book1")))
        ;
    }

    @Test
    public void test_getBook_returnsEmptyArray() throws Exception {
        mockMvc.perform(get("/books/1"))
                .andExpect(status().isOk())
        ;
    }

    @Test
    public void test_getBook_returnsSingleBook() throws Exception {
        stubBookRepository.setGetBook_returnValue(new Book(1, "Book1"));


        mockMvc.perform(get("/books/1"))
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.name", equalTo("Book1")))
        ;
    }

    @Test
    public void test_getBook_returnsCorrectId() throws Exception {
        SpyBookRepository spyBookRepository = new SpyBookRepository();
        MockMvc mockMvc = MockMvcBuilders
                .standaloneSetup(new BooksController(spyBookRepository))
                .build();


        mockMvc.perform(get("/books/111"));


        assertThat(spyBookRepository.getGetBook_argument_id(), equalTo(111));
    }

    @Test
    public void test_getAllFromDB_returnsList() throws Exception {
        FakeBookRepository fakeBookRepository = new FakeBookRepository();
        fakeBookRepository.add(new NewBook("Book1"));
        fakeBookRepository.add(new NewBook("Book2"));
        MockMvc mockMvc = MockMvcBuilders
                .standaloneSetup(new BooksController(fakeBookRepository))
                .build();


        mockMvc.perform(get("/books"))
                .andExpect(jsonPath("$[0].id", equalTo(1)))
                .andExpect(jsonPath("$[0].name", equalTo("Book1")))
                .andExpect(jsonPath("$[1].id", equalTo(2)))
                .andExpect(jsonPath("$[1].name", equalTo("Book2")))
        ;
    }

    @Test
    public void test_getBookFromDB_returnsSingleBook() throws Exception {
        FakeBookRepository fakeBookRepository = new FakeBookRepository();
        fakeBookRepository.add(new NewBook("Book1"));


        MockMvc mockMvc = MockMvcBuilders
                .standaloneSetup(new BooksController(fakeBookRepository))
                .build();


        mockMvc.perform(get("/books/1"))
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.name", equalTo("Book1")))
        ;
    }

    @Test
    public void test_updateBook_returnsOk() throws Exception {
        mockMvc.perform(put("/books/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    void test_updatedBook_returnsUpdatedBook() throws Exception {
        stubBookRepository.setUpdateBook_returnValue(new Book(1, "Updated Book1"));


        mockMvc.perform(put("/books/1")
                .content("{}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.name", equalTo("Updated Book1")))
        ;
    }

    @Test
    void test_updateBook_updatesBookWithCorrectInputs() throws Exception {
        SpyBookRepository spyBookRepository = new SpyBookRepository();
        mockMvc = MockMvcBuilders
                .standaloneSetup(new BooksController(spyBookRepository))
                .build();

        String json = "{\n" +
                "  \"name\": \"Updated Book1\"\n" +
                "}";


        mockMvc.perform(put("/books/999")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        );


        assertThat(spyBookRepository.getUpdateBook_argument_id(), equalTo(999));

        UpdateBook expectedUpdateBook = new UpdateBook("Updated Book1");
        assertThat(spyBookRepository.getUpdateBook_argument_updateBook(), equalTo(expectedUpdateBook));
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
        stubBookRepository.setAddBook_returnValue(new Book(1, "Book1"));


        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.name", equalTo("Book1")));
    }

    @Test
    void test_addBook_addsBookWithCorrectInputs() throws Exception {
        SpyBookRepository spyBookRepository = new SpyBookRepository();
        mockMvc = MockMvcBuilders
                .standaloneSetup(new BooksController(spyBookRepository))
                .build();

        String json = "{\n" +
                "  \"name\": \"Book1\"\n" +
                "}";

        mockMvc.perform(post("/books")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON));


        NewBook expectedNewBook = new NewBook("Book1");
        assertThat(spyBookRepository.getAddBook_argument_book(), equalTo(expectedNewBook));
    }

    @Test
    void test_deleteBook_returnsNoContent() throws Exception {
        mockMvc.perform(delete("/books/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void test_deleteBook_deletesWithCorrectId() throws Exception {
        SpyBookRepository spyBookRepository = new SpyBookRepository();
        mockMvc = MockMvcBuilders
                .standaloneSetup(new BooksController(spyBookRepository))
                .build();


        mockMvc.perform(delete("/books/999"));


        assertThat(spyBookRepository.getDeleteBook_argument_id(), equalTo(999));
    }
}
