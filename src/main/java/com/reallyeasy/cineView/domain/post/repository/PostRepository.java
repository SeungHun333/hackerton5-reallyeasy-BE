package com.reallyeasy.cineView.domain.post.repository;

import com.reallyeasy.cineView.common.enums.Category;
import com.reallyeasy.cineView.domain.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findByCategory(Category category, Pageable pageable);
}
