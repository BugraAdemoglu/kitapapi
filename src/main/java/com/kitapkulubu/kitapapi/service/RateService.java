package com.kitapkulubu.kitapapi.service;

import com.kitapkulubu.kitapapi.model.Book;
import com.kitapkulubu.kitapapi.model.Rate;
import com.kitapkulubu.kitapapi.Repository.BookRepository;
import com.kitapkulubu.kitapapi.Repository.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RateService {

    @Autowired
    private RateRepository rateRepository;

    @Autowired
    private BookRepository bookRepository;

    public Optional<Rate> addRateToBook(Long bookId, Rate rate) {
        Optional<Book> bookOpt = bookRepository.findById(bookId);
        if (bookOpt.isPresent()) {
            rate.setBook(bookOpt.get());
            return Optional.of(rateRepository.save(rate));
        }
        return Optional.empty();
    }

    public List<Rate> getRatesByBookId(Long bookId) {
        return rateRepository.findByBookId(bookId);
    }

    public double getAverageRatingForBook(Long bookId) {
        List<Rate> rates = rateRepository.findByBookId(bookId);
        if (rates.isEmpty()) return 0.0;
        return rates.stream().mapToDouble(Rate::getScore).average().orElse(0.0);
    }
}