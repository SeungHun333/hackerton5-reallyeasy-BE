package com.reallyeasy.cineView.domain.comment.repository;

import com.reallyeasy.cineView.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Comment findByIdAndDeletedAtIsNull(Long commentId);
    List<Comment> findAllByPostIdAndDeletedAtIsNull(Long postId);
    List<Comment> findAllByUserIdAndDeletedAtIsNull(Long userId);
}
