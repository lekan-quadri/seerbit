package com.seerbit.transtat.models;

public class TransactionStatistics {

    private String sum;
    private String avg;
    private String max;
    private String min;
    private long count;

    public TransactionStatistics() {
    }

    public TransactionStatistics(String sum, String avg, String max, String min, long count) {
        this.sum = sum;
        this.avg = avg;
        this.max = max;
        this.min = min;
        this.count = count;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getAvg() {
        return avg;
    }

    public void setAvg(String avg) {
        this.avg = avg;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "TransactionStatistics{" +
                "sum='" + sum + '\'' +
                ", avg='" + avg + '\'' +
                ", max='" + max + '\'' +
                ", min='" + min + '\'' +
                ", count=" + count +
                '}';
    }
}
