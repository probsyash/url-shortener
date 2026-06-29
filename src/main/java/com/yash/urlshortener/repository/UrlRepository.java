package com.yash.urlshortener.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.yash.urlshortener.Url;

public interface UrlRepository extends JpaRepository<Url, Long> {
    Optional<Url> findByShortCode(String shortCode);
    
}
