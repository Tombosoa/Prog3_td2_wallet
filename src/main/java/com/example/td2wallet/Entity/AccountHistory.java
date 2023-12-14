package com.example.td2wallet.Entity;

public class AccountHistory {
    public int account_id;
    public double credit;
    public double debit;

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public double getDebit() {
        return debit;
    }

    public void setDebit(double debit) {
        this.debit = debit;
    }

    public AccountHistory() {
    }

    public AccountHistory(int account_id, double credit, double debit) {
        this.account_id = account_id;
        this.credit = credit;
        this.debit = debit;
    }

    @Override
    public String toString() {
        return "AccountHistory{" +
                "account_id=" + account_id +
                ", credit=" + credit +
                ", debit=" + debit +
                '}';
    }
}
