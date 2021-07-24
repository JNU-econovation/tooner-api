package com.tooner.domain.member;

import com.tooner.domain.BaseTimeEntity;
import com.tooner.domain.Gender;
import com.tooner.domain.keyword.Keyword;
import com.tooner.domain.reviewarticle.ReviewArticle;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = true)
    @OneToMany(mappedBy = "member")
    private List<Keyword> keywords;

    @Column(nullable = true)
    private List<ReviewArticle> reviewArticles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return userName;
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
