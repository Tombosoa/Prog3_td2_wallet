package com.example.td2wallet.Controller;

import com.example.td2wallet.Entity.*;
import com.example.td2wallet.functionnality.Functionnality;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

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
    public ResponseTransfer makeTransfer(
            @RequestParam double amount,
            @RequestParam int id_account_deb,
            @RequestParam int id_account_cred
    ){
        return functionnality.makeTransfer(amount, id_account_deb, id_account_cred);
    }

    @PostMapping("/newtransfer")
    public NewResponseTransfer makeTransfer(
            @RequestParam double amount,
            @RequestParam int id_account,
            @RequestParam int category_id,
            @RequestParam int subcategory_id,
            @RequestParam String action
    ){
        return functionnality.makeNewTransfer(amount, id_account, category_id, subcategory_id, action);
    }

    @GetMapping("/gettodaybalance")
    public AccountDate getTodayBalance(
            @RequestParam int account_id
    ) {
        return functionnality.getTodayBalance(account_id);
    }

    @GetMapping("/getcurrencyactual")
    public double getCurrencyActual() throws SQLException {
        return functionnality.getCurrencyActual();
    }

    @GetMapping("/allcurrency")
    public double getAllCurrency(@RequestParam String action) throws SQLException {
        return functionnality.getAllCurrency(action);
    }

    @GetMapping("/gettotaltransac")
    public AccountHistory getTotalTransac(
            @RequestParam int account_id,
            @RequestParam String first_date,
            @RequestParam String last_date
    ){
        return functionnality.getTotalTransac(account_id, first_date, last_date);
    }

    @GetMapping("/gettotaltransaccat")
    public List<SQLCresp> getTotalTransacCat(
            @RequestParam int account_id,
            @RequestParam String first_date,
            @RequestParam String last_date
    ){
        return functionnality.getTotalTransacCat(account_id, first_date, last_date);
    }

    @GetMapping("/getsqlfunction")
    public SQLresp executeFunction(
            @RequestParam int account_id,
            @RequestParam String first_date,
            @RequestParam String last_date
    ) throws SQLException {
        return functionnality.executeFunction(account_id, first_date, last_date);
    }

    @GetMapping("/getsqlfunctionc")
    public List<SQLCresp> executeFunctionC(
            @RequestParam int account_id,
            @RequestParam String first_date,
            @RequestParam String last_date
    ) throws SQLException {
        return functionnality.executeFunctionC(account_id, first_date, last_date);
    }
}
