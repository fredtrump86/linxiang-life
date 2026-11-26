package com.linxiang.service;

import com.linxiang.analytics.BehaviorEvent;
import java.util.List;
import java.util.Map;

/** 业务处理服务。 */
public interface IBehaviorAggregationService {
  void accept(BehaviorEvent event);

  List<Map<String, Object>> ranking(int minutes, int limit);
}
