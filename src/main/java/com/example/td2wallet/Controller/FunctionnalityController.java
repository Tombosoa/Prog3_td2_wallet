package com.example.td2wallet.Controller;

import com.example.td2wallet.Entity.Account;
import com.example.td2wallet.Entity.Transaction;
import com.example.td2wallet.functionnality.Functionnality;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
