package com.tooner.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewArticleUpdateRequestDto {
    private String title;
    private String content;
    private Integer rating;

    @Builder
    public ReviewArticleUpdateRequestDto(String title, String content, Integer rating) {
        this.title = title;
        this.content = content;
        this.rating = rating;
    }

}
