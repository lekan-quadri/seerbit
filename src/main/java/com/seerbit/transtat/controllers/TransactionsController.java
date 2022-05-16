package com.seerbit.transtat.controllers;

import com.seerbit.transtat.models.Transaction;
import com.seerbit.transtat.models.TransactionStatistics;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@Validated
@RequestMapping("/transaction")
public class TransactionsController {

    private List<Transaction> transactions = new ArrayList<>();
    private TransactionStatistics statistics = new TransactionStatistics();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    @PostMapping
    public ResponseEntity addTransaction(@Valid @RequestBody Transaction transaction){
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date now = new Date();
        try {
            Date date = sdf.parse(transaction.getTimestamp());
            BigDecimal amount = new BigDecimal(transaction.getAmount());
            transaction.setAmount(amount.toString());
            if(date.after(now))
                return new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
            if(now.getTime() - date.getTime() > 30000) {
                transactions.add(transaction);
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        transactions.add(transaction);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/statistics")
    public TransactionStatistics getTransactions(){

        statistics.setSum(transactions.stream().filter(this::test).map(x -> new BigDecimal(x.getAmount()).setScale(2,RoundingMode.HALF_UP)).reduce(BigDecimal.ZERO, BigDecimal::add).toString());
        statistics.setMax(transactions.stream().filter(this::test).map(x -> new BigDecimal(x.getAmount()).setScale(2,RoundingMode.HALF_UP)).max(Comparator.naturalOrder()).get().toString());
        statistics.setMin(transactions.stream().filter(this::test).map(x -> new BigDecimal(x.getAmount()).setScale(2,RoundingMode.HALF_UP)).min(Comparator.naturalOrder()).get().toString());
        statistics.setCount(transactions.stream().filter(this::test).count());
        BigDecimal average = new BigDecimal(statistics.getSum()).divide(new BigDecimal(statistics.getCount()));
        statistics.setAvg(average.setScale(2,RoundingMode.HALF_UP).toString());

        return statistics;
    }

    @DeleteMapping
    public ResponseEntity deleteTransactions(){
        transactions = new ArrayList<>();
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    private boolean test(Transaction t) {
        boolean check = false;
        Date now = new Date();
        try {
            check = now.getTime() - sdf.parse(t.getTimestamp()).getTime() <= 30000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return check;
    }

    public List<Transaction> getTransactionsList(){
        return transactions;
    }

}
