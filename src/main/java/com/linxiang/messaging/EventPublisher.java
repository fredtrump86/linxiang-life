package com.linxiang.messaging;

public interface EventPublisher {
  void publish(BusinessEvent event);
}
