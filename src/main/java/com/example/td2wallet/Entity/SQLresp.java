package com.example.td2wallet.Entity;

import java.math.BigDecimal;

public class SQLresp {
    private int account_id;
    private BigDecimal debit;
    private BigDecimal credit;

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public BigDecimal getDebit() {
        return debit;
    }

    public void setDebit(BigDecimal debit) {
        this.debit = debit;
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }

    public SQLresp(int account_id, BigDecimal debit, BigDecimal credit) {
        this.account_id = account_id;
        this.debit = debit;
        this.credit = credit;
    }

    @Override
    public String toString() {
        return "SQLresp{" +
                "account_id=" + account_id +
                ", debit=" + debit +
                ", credit=" + credit +
                '}';
    }
}
