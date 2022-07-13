package com.subbiah.app.FetchApi.Models;

import java.time.LocalDateTime;
import javax.persistence.*;

@Entity
public class PointsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String payer;
    @Column
    private int transactionPoints;
    @Column
    private LocalDateTime transactionDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPayer(String payer) {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public int getTransactionPoints() {
        return transactionPoints;
    }

    public void setTransactionPoints(int transactionPoints) {
        this.transactionPoints = transactionPoints;
    }
    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }
    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public int compareTo(PointsEntity pe) {
        return getTransactionDate().compareTo(pe.getTransactionDate());
    }
}
