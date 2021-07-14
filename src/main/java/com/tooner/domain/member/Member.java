package com.tooner.domain.member;

import com.tooner.domain.Gender;
import com.tooner.domain.reviewarticle.ReviewArticle;
import javassist.compiler.ast.Keyword;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String userName;

    @Column(length = 16, nullable = false)
    private String password;

    @Column(length = 30, nullable = false)
    private String alias;

    @Column(nullable = false)
    private String profileImage;

    @Lob
    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String bio;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private Gender gender;

    //@Column(nullable = false)
    //private List<Keyword> keywords;

    //
    //private List<ReviewArticle> reviewArticles;
}
