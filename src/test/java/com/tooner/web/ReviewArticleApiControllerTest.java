package com.tooner.web;

import com.tooner.domain.reviewarticle.ReviewArticle;
import com.tooner.domain.reviewarticle.ReviewArticleRepository;
import com.tooner.web.dto.ReviewArticleSaveRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReviewArticleApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ReviewArticleRepository reviewArticleRepository;

    @After
    public void tearDown() throws Exception {
        reviewArticleRepository.deleteAll();
    }

    @Test
    public void ReviewArticle_등록된다() throws Exception {
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";
        Integer rating = 3;
        ReviewArticleSaveRequestDto requestDto = ReviewArticleSaveRequestDto.builder()
                .title(title)
                .content(content)
                .rating(rating)
                .build();

        String url = "http://localhost:" + port + "/api/reviews";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<ReviewArticle> all = reviewArticleRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
        assertThat(all.get(0).getRating()).isEqualTo(rating);
    }
}
