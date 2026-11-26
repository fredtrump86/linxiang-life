package com.linxiang.service.impl;

import com.linxiang.mapper.OperationAuditMapper;
import com.linxiang.service.IOperationAuditService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class OperationAuditServiceImpl implements IOperationAuditService {
  @Resource
  private OperationAuditMapper operationAuditMapper;

  @Override
  public void recordAudit(Long userId, String method, String path, Integer httpStatus) {
    operationAuditMapper.insertAudit(userId, method, path, httpStatus);
  }
}
