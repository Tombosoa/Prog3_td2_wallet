package com.example.td2wallet.Controller;

import com.example.td2wallet.Entity.Account;
import com.example.td2wallet.Entity.AccountDate;
import com.example.td2wallet.Entity.Transaction;
import com.example.td2wallet.Entity.TransferHistory;
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

@PostMapping("/transfer")
    public TransferHistory makeTransfer(
            @RequestParam double amount,
            @RequestParam int id_account_deb,
            @RequestParam int id_account_cred
    ){
        return functionnality.makeTransfer(amount, id_account_deb, id_account_cred);
    }

    @GetMapping("/gettodaybalance")
    public AccountDate getTodayBalance(
            @RequestParam int account_id
    ) {
        return functionnality.getTodayBalance(account_id);
    }

}
