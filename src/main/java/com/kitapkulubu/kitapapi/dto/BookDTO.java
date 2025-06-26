package com.kitapkulubu.kitapapi.dto;

import com.kitapkulubu.kitapapi.model.Author;
import com.kitapkulubu.kitapapi.model.Comment;
import com.kitapkulubu.kitapapi.model.PublishHouse;
import com.kitapkulubu.kitapapi.model.Rate;
import jakarta.persistence.*;

import java.util.List;

public class BookDTO {

        private Long id;
        private String title;
        private String description;
        private int publicationYear;
        private Long authorId;
        private Long publishHouseId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getPublishHouseId() {
        return publishHouseId;
    }

    public void setPublishHouseId(Long publishHouseId) {
        this.publishHouseId = publishHouseId;
    }
}

