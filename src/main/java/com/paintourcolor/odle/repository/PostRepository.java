package com.paintourcolor.odle.repository;

import com.paintourcolor.odle.entity.EmotionEnum;
import com.paintourcolor.odle.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAllByOrderByCreatedAtDesc(Pageable pageable);

    @Query("select post from Post post WHERE post.user.id = :userId")
    Page<Post> findAllByUserId(Long userId, Pageable pageable);

    @Query("select count(post) from Post post WHERE post.user.id = :userId")
    Long countByUserId(Long userId);

    @Query("SELECT m.id, m.title, m.singer, m.cover, COUNT(p.music.id) AS musicCount " +
            "FROM Music m " +
            "LEFT JOIN m.posts p " +
            "WHERE p.emotion = :emotion " +
            "AND p.createdAt BETWEEN :startDate AND :endDate " +
            "GROUP BY m.id " +
            "ORDER BY musicCount DESC")
    List<Object[]> findEmotionMusicIdsWithCountAndMusicInfo(@Param("emotion") EmotionEnum emotion, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}
