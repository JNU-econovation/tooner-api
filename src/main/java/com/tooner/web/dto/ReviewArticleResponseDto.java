package com.tooner.web.dto;

import com.tooner.domain.reviewarticle.ReviewArticle;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewArticleResponseDto {
    private Long id;
    private String title;
    private String content;
    private Integer rating;

    public ReviewArticleResponseDto(ReviewArticle entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.rating = entity.getRating();
    }
}
