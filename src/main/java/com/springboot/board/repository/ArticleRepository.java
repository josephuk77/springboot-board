package com.springboot.board.repository;

import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import com.springboot.board.domain.Article;
import com.springboot.board.domain.QArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ArticleRepository extends
        JpaRepository<Article, Long>,
        QuerydslPredicateExecutor<Article>,
        QuerydslBinderCustomizer<QArticle> {


    // QuerydslBinderCustomizer 인터페이스를 구현하고 customize() 메서드를 오버라이드
    @Override
    default void customize(QuerydslBindings bindings, QArticle root){
        bindings.excludeUnlistedProperties(true);
        bindings.including(root.title, root.content, root.hashtag, root.createdAt, root.createdBy);
        bindings.bind(root.title).first(StringExpression::containsIgnoreCase); // 대소문자 구분 없이 검색
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase); // 대소문자 구분 없이 검색
        bindings.bind(root.hashtag).first(StringExpression::containsIgnoreCase); // 대소문자 구분 없이 검색
        bindings.bind(root.createdAt).first(DateTimeExpression::eq); // 날짜로 검색
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase); // 대소문자 구분 없이 검색
    }

}
