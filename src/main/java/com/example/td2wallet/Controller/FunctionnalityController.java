package com.example.td2wallet.Controller;

import com.example.td2wallet.Entity.Account;
import com.example.td2wallet.Entity.AccountDate;
import com.example.td2wallet.Entity.Transaction;
import com.example.td2wallet.functionnality.Functionnality;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class FunctionnalityController {
    private final Functionnality functionnality;

    public FunctionnalityController(Functionnality functionnality) {
        this.functionnality = functionnality;
    }

    @PostMapping("/maketransaction")
    public Account makeTransaction(@RequestBody Transaction transaction){
        return functionnality.makeTransaction(transaction);
    }
    @GetMapping("/getbydate")
    public AccountDate getByDate(
            @RequestParam String transaction_date,
            @RequestParam int account_id
            ) {
        return functionnality.getByDate(transaction_date, account_id);
    }




}
