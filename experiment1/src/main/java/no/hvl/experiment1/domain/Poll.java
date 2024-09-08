package no.hvl.experiment1.domain;

import java.time.Instant;

public class Poll {

    private long id;

    private String question;
    private String publishedAt;;
    private String validUntil;


    public Poll(String Question,String PublishedAt,String ValidUntil) {
        this.question = Question;
        this.publishedAt = PublishedAt;
        this.validUntil = ValidUntil;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }



    //Get / set
    public String getQuestion() {
        return question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }
    public String getPublishedAt() {
        return publishedAt;
    }
    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }
    public String getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(String validUntil) {
        this.validUntil = validUntil;
    }

}
