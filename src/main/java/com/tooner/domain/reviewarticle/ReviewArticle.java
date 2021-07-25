package com.tooner.domain.reviewarticle;

import com.tooner.domain.BaseTimeEntity;

import com.tooner.domain.member.Member;
import com.tooner.domain.vote.DownVote;
import com.tooner.domain.vote.UpVote;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class ReviewArticle extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private Integer rating;

    @OneToMany
    private List<UpVote> upvotes = new ArrayList<>();

    @OneToMany
    private List<DownVote> downvotes = new ArrayList<>();

    @ManyToOne
    private Member member;

    //private Webtoon webtoon;

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
