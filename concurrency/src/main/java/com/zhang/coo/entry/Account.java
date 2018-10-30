package com.zhang.coo.entry;

import java.io.Serializable;

/**
 * Created by aa on 2018/10/21.
 */
public class Account implements Serializable {

    private Integer id;
    private Double ant;
    private Double score;
    private Long version;

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", ant=" + ant +
                ", score=" + score +
                ", version=" + version +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getAnt() {
        return ant;
    }

    public void setAnt(Double ant) {
        this.ant = ant;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
