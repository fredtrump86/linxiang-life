package com.linxiang.service.impl;

import com.linxiang.mapper.BehaviorMapper;
import com.linxiang.service.IBehaviorFailureService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class BehaviorFailureServiceImpl implements IBehaviorFailureService {
  @Resource
  private BehaviorMapper behaviorMapper;

  @Override
  public void saveFailure(
      String topic, Integer partitionId, Long messageOffset, String payload, String reason) {
    behaviorMapper.insertFailure(topic, partitionId, messageOffset, payload, reason);
  }
}
