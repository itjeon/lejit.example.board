package lejit.example.board.api.boards;

import com.fasterxml.jackson.databind.ObjectMapper;
import lejit.example.board.domain.Board;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
@AutoConfigureMockMvc
public class BoardTest {

    private Log logger = LogFactory.getLog(BoardTest.class);

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() throws Exception {
        Board board1 = Board.builder()
                .title("기초데이터 제목1")
                .content("기초데이터 내용1")
                .build();

        Board board2 = Board.builder()
                .title("기초데이터 제목2")
                .content("기초데이터 내용2")
                .build();

        mockMvc.perform(post("/boards")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(board1)))
        ;

        mockMvc.perform(post("/boards")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(board2)))
        ;
    }

    @Test
    void 게시글을_등록할_수_있다() throws Exception {
        Board board = Board.builder()
                .title("제목")
                .content("내용")
                .build();

        MvcResult result = mockMvc.perform(post("/boards")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(board)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value(board.getTitle()))
                .andReturn();
        ;
    }

    @Test
    void 게시글을_조회할_수_있다() throws Exception {
        mockMvc.perform(get("/boards"))
                .andDo(print())
                .andExpect(jsonPath("$._embedded.boards.length()").value(2))
        ;
    }


}
