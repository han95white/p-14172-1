package com.back.domain.post.postComment.controller;

import com.back.domain.post.post.controller.ApiV1PostController;
import com.back.domain.post.post.entity.Post;
import com.back.domain.post.post.service.PostService;
import com.back.domain.post.postComment.entity.PostComment;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ApiV1PostCommentControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private PostService postService;

    @Test
    @DisplayName("댓글 단건조회")
    void t10() throws Exception {
        int postId = 1;
        int id = 1;

        ResultActions resultActions = mvc
                .perform(
                        get("/api/v1/posts/1/comments/" + id)
                )
                .andDo(print());

        Post post = postService.findById(postId).get();
        PostComment postComment = post.findCommentById(id).get();

        resultActions
                .andExpect(handler().handlerType(ApiV1PostCommentController.class))
                .andExpect(handler().methodName("getItem"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(postComment.getId()))
                .andExpect(jsonPath("$.createDate").value(Matchers.startsWith(postComment.getCreateDate().toString().substring(0, 20))))
                .andExpect(jsonPath("$.modifyDate").value(Matchers.startsWith(postComment.getModifyDate().toString().substring(0, 20))))
                .andExpect(jsonPath("$.content").value(postComment.getContent()));
    }

//    @Test
//    @DisplayName("글 단건조회, 404")
//    void t11() throws Exception {
//        int postId = Integer.MAX_VALUE;
//        int id = Integer.MAX_VALUE;
//
//        ResultActions resultActions = mvc
//                .perform(
//                        get("/api/v1/posts/" + id)
//                )
//                .andDo(print());
//
//        resultActions
//                .andExpect(handler().handlerType(ApiV1PostController.class))
//                .andExpect(handler().methodName("getItem"))
//                .andExpect(status().isNotFound())
//                .andExpect(jsonPath("$.resultCode").value("404-1"))
//                .andExpect(jsonPath("$.msg").value("해당 데이터가 존재하지 않습니다."));
//    }

}