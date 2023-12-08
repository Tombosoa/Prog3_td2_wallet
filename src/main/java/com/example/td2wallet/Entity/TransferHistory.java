package com.example.td2wallet.Entity;

import java.time.OffsetDateTime;

public class TransferHistory {
    private int id;
    private int id_transactionDeb;
    private int id_transactionCred;
    private OffsetDateTime transfer_date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_transactionDeb() {
        return id_transactionDeb;
    }

    public void setId_transactionDeb(int id_transactionDeb) {
        this.id_transactionDeb = id_transactionDeb;
    }

    public int getId_transactionCred() {
        return id_transactionCred;
    }

    public void setId_transactionCred(int id_transactionCred) {
        this.id_transactionCred = id_transactionCred;
    }

    public OffsetDateTime getTransfer_date() {
        return transfer_date;
    }

    public void setTransfer_date(OffsetDateTime transfer_date) {
        this.transfer_date = transfer_date;
    }

    public TransferHistory(int id, int id_transactionDeb, int id_transactionCred, OffsetDateTime transfer_date) {
        this.id = id;
        this.id_transactionDeb = id_transactionDeb;
        this.id_transactionCred = id_transactionCred;
        this.transfer_date = transfer_date;
    }

    public TransferHistory() {
    }

    public TransferHistory(int id_transactionDeb, int id_transactionCred, OffsetDateTime transfer_date) {
        this.id_transactionDeb = id_transactionDeb;
        this.id_transactionCred = id_transactionCred;
        this.transfer_date = transfer_date;
    }

    public TransferHistory(int id_transactionDeb, int id_transactionCred) {
        this.id_transactionDeb = id_transactionDeb;
        this.id_transactionCred = id_transactionCred;
    }

    @Override
    public String toString() {
        return "TransferHistory{" +
                "id=" + id +
                ", id_transactionDeb=" + id_transactionDeb +
                ", id_transactionCred=" + id_transactionCred +
                ", transfer_date=" + transfer_date +
                '}';
    }
}
