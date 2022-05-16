package com.seerbit.transtat.models;

public class Transaction {

    private String amount;
    private String timestamp;

    public Transaction() {
    }

    public Transaction(String amount, String timestamp) {
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "{" +
                "\"amount\":\"" + amount + "\"," +
                "\"timestamp\":\"" + timestamp + "\"" +
                "}";
    }
}
