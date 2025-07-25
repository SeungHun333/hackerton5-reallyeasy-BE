package com.reallyeasy.cineView.domain.movie.service;

import com.reallyeasy.cineView.domain.favoriteMovie.repository.FavoriteMovieRepository;
import com.reallyeasy.cineView.domain.movie.dto.response.MovieResponse;
import com.reallyeasy.cineView.domain.movie.dto.response.MovieWithReviewResponse;
import com.reallyeasy.cineView.domain.movie.entity.Movie;
import com.reallyeasy.cineView.domain.movie.repository.MovieRepository;
import com.reallyeasy.cineView.domain.review.dto.response.ReviewResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final FavoriteMovieRepository favoriteMovieRepository;

    public Page<MovieResponse> getMovies(Pageable pageable) {
        return movieRepository.findAll(pageable)
                .map(MovieResponse::toDto);
    }

    public MovieWithReviewResponse getMovieById(Long movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("영화를 찾을 수 없습니다."));

        List<ReviewResponse> reviews = movie.getReviews().stream()
                .map(ReviewResponse::toDto)
                .toList();

        return new MovieWithReviewResponse(
                movie.getId(),
                movie.getTmdbId(),
                movie.getTitle(),
                movie.getOriginalLanguage(),
                movie.getOverview(),
                movie.getPosterPath(),
                movie.getReleaseDate(),
                reviews
        );
    }

    public Page<MovieResponse> getFavoriteMovies(Long userId, Pageable pageable) {
        Page<Movie> movies = favoriteMovieRepository.findMoviesByUserId(userId, pageable);
        return movies.map(MovieResponse::toDto);
    }
}
