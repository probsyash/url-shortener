package com.yash.urlshortener.service;

import org.springframework.stereotype.Service;
import com.yash.urlshortener.Url;
import com.yash.urlshortener.repository.UrlRepository;
import java.util.Optional;

@Service
public class UrlService {

    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }



    public String shortenUrl(String originalUrl) {
        originalUrl = originalUrl.trim();
        Url url = new Url(originalUrl);
        long id = urlRepository.save(url).getId();
        String shortCode = encodeBase62(id);
        url.setShortCode(shortCode);
        urlRepository.save(url);
        return shortCode;
    }

    public String getOriginalUrl(String shortCode) {
        Optional<Url> urlOptional = urlRepository.findByShortCode(shortCode);
        return urlOptional.map(Url::getOriginalUrl).orElse(null);
    }

    private static String encodeBase62(long number) {
        final String characters = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        while (number > 0) {
            sb.append(characters.charAt((int) (number % characters.length())));
            number /= characters.length();
        }
        return sb.reverse().toString();
    }

}