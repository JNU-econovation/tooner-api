package com.tooner.domain.reviewarticle;

import com.tooner.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class
ReviewArticle extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private Integer rating;

    //추후 필드 추가

    @Builder
    public ReviewArticle(String title, String content, Integer rating) {
        this.title = title;
        this.content = content;
        this.rating = rating;
    }

    public void update(String title, String content, Integer rating) {
        this.title = title;
        this.content = content;
        this.rating = rating;
    }
}
