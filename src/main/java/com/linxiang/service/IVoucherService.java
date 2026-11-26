package com.linxiang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linxiang.dto.Result;
import com.linxiang.entity.Voucher;

/**
 * 服务类
 *
 * @author 虎哥
 * @since 2021-12-22
 */
public interface IVoucherService extends IService<Voucher> {

  Result queryVoucherOfShop(Long shopId);

  void addSeckillVoucher(Voucher voucher);

  Result capabilities();

  Result createSeckillVoucher(Voucher voucher);

  Result createVoucher(Voucher voucher);
}
