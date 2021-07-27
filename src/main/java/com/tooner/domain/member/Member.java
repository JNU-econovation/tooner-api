package com.tooner.domain.member;

import com.tooner.domain.BaseTimeEntity;
import com.tooner.domain.Gender;
import com.tooner.domain.keyword.Keyword;
import com.tooner.domain.reviewarticle.ReviewArticle;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Member extends BaseTimeEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String userName;

    @Column(length = 80, nullable = false)
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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = true)
    @OneToMany
    private List<Keyword> keywords;

    @Column(nullable = true)
    @OneToMany
    private List<ReviewArticle> reviewArticles;

    @Builder
    public Member(Long id, String userName, String password, String alias, String profileImage, String bio, Integer age, Gender gender, Role role, List<Keyword> keywords, List<ReviewArticle> reviewArticles) {
        this.id = id;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(this.role.name()));
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
