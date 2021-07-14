package com.tooner.web.dto;

import com.tooner.domain.reviewarticle.ReviewArticle;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewArticleSaveRequestDto {
    private String title;
    private String content;
    private Integer rating;

    @Builder
    public ReviewArticleSaveRequestDto(String title, String content, Integer rating) {
        this.title = title;
        this.content = content;
        this.rating =rating;
    }

    public ReviewArticle toEntity() {
        return ReviewArticle.builder()
                .title(title)
                .content(content)
                .rating(rating)
                .build();
    }
}
