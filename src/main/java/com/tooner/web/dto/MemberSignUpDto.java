package com.tooner.web.dto;

import com.tooner.domain.Gender;
import com.tooner.domain.keyword.Keyword;
import com.tooner.domain.member.Member;
import com.tooner.domain.member.Role;
import com.tooner.domain.reviewarticle.ReviewArticle;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

@Getter
@NoArgsConstructor
public class MemberSignUpDto {
    private String userName;
    private String password;
    private String alias;
    private String profileImage;
    private String bio;
    private Integer age;
    private Gender gender;
    private Role role;
    private List<Keyword> keywords;
    private List<ReviewArticle> reviewArticles;

    @Builder
    public MemberSignUpDto(String userName, String password, String alias, String profileImage, String bio, Integer age, Gender gender, Role role, List<Keyword> keywords, List<ReviewArticle> reviewArticles) {
        this.userName = userName;
        this.password = password;
        this.alias = alias;
        this.profileImage = profileImage;
        this.bio = bio;
        this.age = age;
        this.gender = gender;
        this.role = role;
        this.keywords = keywords;
        this.reviewArticles = reviewArticles;
    }
}
