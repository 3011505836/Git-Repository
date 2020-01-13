package com.fh.service;

import com.fh.common.ServerResponse;
import com.fh.model.PayLog;

public interface PayLogService {
    void addPayLog(PayLog payLog);

    ServerResponse getPayMoney(String outTradeNo);

    void updatePayLog(PayLog payLog);
}
