package com.seerbit.transtat.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seerbit.transtat.models.Transaction;
import com.seerbit.transtat.models.TransactionStatistics;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TransactionsController.class)
class TransactionsControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    @Test
    void addTransaction() throws Exception {
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date now = new Date();

        mvc.perform(post("/transaction")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new Transaction("500.344",sdf.format(now))))
                .accept(MediaType.APPLICATION_JSON))
       .andExpect(status().is(201));
    }

//    @Test
//    void getTransactions() throws Exception {
//
//        Date now = new Date();
//        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
//
//
//        RequestBuilder builder = MockMvcRequestBuilders.get("/transaction/statistics");
//
//        TransactionStatistics ts = new TransactionStatistics("6000.81","2000.27","5000.38","500.09",3);
//
//        mvc.perform(builder).andExpect(content().string(ts.toString()));
//    }
//
//    @Test
//    void deleteTransactions() {
//        RequestBuilder builder = MockMvcRequestBuilders.delete("/transaction");
//    }
}