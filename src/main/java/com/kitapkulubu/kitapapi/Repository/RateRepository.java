package com.kitapkulubu.kitapapi.Repository;

import com.kitapkulubu.kitapapi.model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RateRepository extends JpaRepository<Rate, Long> {
    List<Rate> findByBookId(Long bookId);
}