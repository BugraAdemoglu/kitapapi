
package com.kitapkulubu.kitapapi.controller;

import com.kitapkulubu.kitapapi.model.Rate;
import com.kitapkulubu.kitapapi.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books/{bookId}/rates")
@CrossOrigin
public class RateController {

    @Autowired
    private RateService rateService;

    @PostMapping
    public ResponseEntity<Rate> addRate(@PathVariable Long bookId, @RequestBody Rate rate) {
        return rateService.addRateToBook(bookId, rate)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Rate> getRates(@PathVariable Long bookId) {
        return rateService.getRatesByBookId(bookId);
    }
}