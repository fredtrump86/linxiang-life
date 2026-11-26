package com.linxiang.service;

import com.linxiang.messaging.BusinessEvent;

/** 业务处理服务。 */
public interface IBusinessEventService {
  void consume(BusinessEvent event, String group);

  boolean recordConsumptionFailure(
      BusinessEvent event,
      String group,
      String brokerId,
      String payload,
      RuntimeException failure,
      int deliveries);
}
