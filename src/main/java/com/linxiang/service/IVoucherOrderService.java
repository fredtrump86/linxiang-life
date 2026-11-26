package com.linxiang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linxiang.dto.Result;
import com.linxiang.entity.VoucherOrder;

/**
 * 服务类
 *
 * @author 虎哥
 * @since 2021-12-22
 */
public interface IVoucherOrderService extends IService<VoucherOrder> {

  /** 条件扣库存与保存订单在同一事务内完成；用户锁覆盖事务提交。 */
  void persistSeckill(VoucherOrder order);

  Result seckillVoucher(Long voucherId);

  Result orderOrdinaryVoucher(Long voucherId);

  Result queryMyOrder(Long orderId);

  Result queryMyVoucherOrder(Long voucherId);

  Result cancelUnpaidOrdinaryOrder(Long orderId);

  Result payMockOrder(Long orderId, Long expectedPayCents);

  Result queryMyOrders(Integer page, boolean heldOnly);
}
