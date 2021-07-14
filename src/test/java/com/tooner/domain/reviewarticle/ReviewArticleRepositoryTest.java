package com.tooner.domain.reviewarticle;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReviewArticleRepositoryTest {

    @Autowired
    ReviewArticleRepository reviewArticleRepository;

    @After
    public void cleanup() {
        reviewArticleRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";
        Integer rating = 3;

        reviewArticleRepository.save(ReviewArticle.builder()
                .title(title)
                .content(content)
                .rating(rating)
                .build());

        //when
        List<ReviewArticle> reviewArticleList = reviewArticleRepository.findAll();

        //then
        ReviewArticle reviewArticle = reviewArticleList.get(0);
        assertThat(reviewArticle.getTitle()).isEqualTo(title);
        assertThat(reviewArticle.getContent()).isEqualTo(content);
        assertThat(reviewArticle.getRating()).isEqualTo(rating);
    }
}
