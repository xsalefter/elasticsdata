package com.xsalefter.elasticsdata.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;

public class Grade implements Serializable {

    private static final long serialVersionUID = -9042324097016242766L;

    @Column(name = "date")
    private Date date;

    @Column(name = "grade")
    private String grade;

    @Column(name = "score")
    private Integer score;

    public Grade() {
    }

    public Grade(Date date, String grade, Integer score) {
        this.date = date;
        this.grade = grade;
        this.score = score;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Grade [date=").append(date).append(", grade=").append(grade).append(", score=").append(score)
                .append("]");
        return builder.toString();
    }
}
