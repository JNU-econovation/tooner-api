package com.tooner.domain.vote;

import com.tooner.domain.reviewarticle.ReviewArticle;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@RequiredArgsConstructor
@Entity
public class DownVote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ReviewArticle reviewArticle;
}
