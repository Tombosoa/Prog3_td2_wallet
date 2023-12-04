package com.example.td2wallet.Controller;

import com.example.td2wallet.Entity.Transaction;
import com.example.td2wallet.Operation.TransactionOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class TransactionController {
    private final TransactionOperation transactionOperation;

    @Autowired
    public TransactionController(TransactionOperation transactionOperation) {
        this.transactionOperation = transactionOperation;
    }

     @GetMapping("/transactions")
    public List<Transaction> getTransactions(){
        return transactionOperation.findAll();
    }

    @GetMapping("/transaction/{id}")
    public Transaction getOneTransaction(@PathVariable("id") int id){
        return transactionOperation.getOne(id);
    }

    @PostMapping("/transaction")
    public Transaction newTransaction(@RequestBody Transaction transaction){
        return transactionOperation.save(transaction);
    }

    @PutMapping("/transaction")
    public Transaction updateTransaction(@RequestBody Transaction transaction){
        return transactionOperation.update(transaction);
    }

    @DeleteMapping("/transaction/{uid}")
    public void deleteTransaction(@PathVariable("uid") int id){
        transactionOperation.deleteTransaction(id);
    }
}
