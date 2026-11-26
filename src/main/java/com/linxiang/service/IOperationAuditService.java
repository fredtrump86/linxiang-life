package com.linxiang.service;

public interface IOperationAuditService {
  void recordAudit(Long userId, String method, String path, Integer httpStatus);
}
