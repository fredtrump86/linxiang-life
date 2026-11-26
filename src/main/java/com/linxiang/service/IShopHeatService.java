package com.linxiang.service;

import com.linxiang.dto.Result;

public interface IShopHeatService {
  void refresh();

  Result ranking(int windowMinutes, int limit);
}
