package com.tooner.web;

import com.tooner.domain.reviewarticle.ReviewArticle;
import com.tooner.domain.reviewarticle.ReviewArticleRepository;
import com.tooner.web.dto.ReviewArticleResponseDto;
import com.tooner.web.dto.ReviewArticleSaveRequestDto;
import com.tooner.web.dto.ReviewArticleUpdateRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.ws.Response;
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

    @Test
    public void ReviewArticle_수정된다() throws Exception {
        //given
        ReviewArticle savedReviewArticle = reviewArticleRepository.save(ReviewArticle.builder()
        .title("title")
        .content("content")
        .rating(3)
        .build());

        Long updateId = savedReviewArticle.getId();
        String expectedTitle = "title2";
        String expectedContent = "content2";
        Integer expectedRating = 1;

        ReviewArticleUpdateRequestDto requestDto = ReviewArticleUpdateRequestDto.builder()
                .title(expectedTitle)
                .content(expectedContent)
                .rating(expectedRating)
                .build();

        String url = "http://localhost:" + port + "/api/reviews/" + updateId;

        HttpEntity<ReviewArticleUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        //when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<ReviewArticle> all = reviewArticleRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);
        assertThat(all.get(0).getRating()).isEqualTo(expectedRating);
    }

    @Test
    public void ReviewArticle_조회된다() throws Exception {
        //given
        ReviewArticle savedReviewArticle = reviewArticleRepository.save(ReviewArticle.builder()
        .title("title")
        .content("content")
        .rating(3)
        .build());

        Long savedId = savedReviewArticle.getId();
        String savedTitle = savedReviewArticle.getTitle();
        String savedContent = savedReviewArticle.getContent();
        Integer savedRating = savedReviewArticle.getRating();

        String url = "http://localhost:" + port + "/api/reviews/" + savedId;

        //when
        ResponseEntity<ReviewArticleResponseDto> responseEntity = restTemplate.getForEntity(url, ReviewArticleResponseDto.class);


        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().getId()).isEqualTo(savedId);

        List<ReviewArticle> all = reviewArticleRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(savedTitle);
        assertThat(all.get(0).getContent()).isEqualTo(savedContent);
        assertThat(all.get(0).getRating()).isEqualTo(savedRating);
    }

    @Test
    public void ReviewArticle_삭제된다() throws Exception {
        //given
        ReviewArticle savedReviewArticle = reviewArticleRepository.save(ReviewArticle.builder()
                .title("title")
                .content("content")
                .rating(3)
                .build());

        Long savedId = savedReviewArticle.getId();
        String savedTitle = savedReviewArticle.getTitle();
        String savedContent = savedReviewArticle.getContent();
        Integer savedRating = savedReviewArticle.getRating();

        String url = "http://localhost:" + port + "/api/reviews/" + savedId;

        HttpEntity<ReviewArticle> requestEntity = new HttpEntity<>(savedReviewArticle);

        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Long.class, savedId);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

    }
}
