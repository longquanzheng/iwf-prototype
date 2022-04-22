package com.indeed.iwf.demo;

public class Subscription {
    public int getPeriodsInSubscription() {
        return 10;
    }

    public int getBillingPeriodInSeconds() {
        return 3600 * 24 * 7;
    }

    public int getBillingPeriodCharge() {
        return 100;
    }

    public void setBillingPeriodCharge(int newAmount) {
    }
}
