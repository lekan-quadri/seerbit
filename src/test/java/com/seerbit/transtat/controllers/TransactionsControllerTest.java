package com.seerbit.transtat.controllers;

import com.seerbit.transtat.models.Transaction;
import com.seerbit.transtat.models.TransactionStatistics;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.*;

class TransactionsControllerTest {

    TransactionsController controller = new TransactionsController();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    @Test
    void addTransactionSuccess() {
        Date now = new Date();
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Transaction t = new Transaction("500.344",sdf.format(now));
        ResponseEntity entity = controller.addTransaction(t);
        assertEquals(201,entity.getStatusCode().value());
    }

    @Test
    void addOlderTransactionSuccess() {
        Transaction t = new Transaction("500.344","2022-05-15T11:59:51.312Z");
        ResponseEntity entity = controller.addTransaction(t);
        assertEquals(204,entity.getStatusCode().value());
    }

    @Test
    void addTransactionFutureDate() {
        Transaction t = new Transaction("500.344","2023-05-15T11:59:51.312Z");
        ResponseEntity entity = controller.addTransaction(t);
        assertEquals(422,entity.getStatusCode().value());
    }

    @Test
    void addTransactionNotParsableField() {
        Transaction t = new Transaction("Abc01","2023-05-15T11:59:51.312Z");
        ResponseEntity entity = controller.addTransaction(t);
        assertEquals(422,entity.getStatusCode().value());
    }

    @Test
    void getTransactions() {
        Date now = new Date();
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Transaction t1 = new Transaction("500.344",sdf.format(now));
        Transaction t2 = new Transaction("5000.379",sdf.format(now));
        Transaction t3 = new Transaction("100.644","2022-05-15T11:59:51.312Z");
        Transaction t4 = new Transaction("500.094",sdf.format(now));
        Transaction t5 = new Transaction("700.1126","2022-05-15T13:45:51.312Z");
        controller.addTransaction(t1);
        controller.addTransaction(t2);
        controller.addTransaction(t3);
        controller.addTransaction(t4);
        controller.addTransaction(t5);
        TransactionStatistics statistics = controller.getTransactions();
        TransactionStatistics ts = new TransactionStatistics("6000.81","2000.27","5000.38","500.09",3);
        assertEquals(ts.toString(),statistics.toString());
    }

    @Test
    void deleteTransactions() {
        Date now = new Date();
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Transaction t = new Transaction("500.344",sdf.format(now));
        controller.addTransaction(t);
        ResponseEntity entity = controller.deleteTransactions();

        assertEquals(0,controller.getTransactionsList().size());
        assertEquals(204, entity.getStatusCode().value());
    }
}