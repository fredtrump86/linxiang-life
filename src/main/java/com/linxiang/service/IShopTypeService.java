package com.linxiang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linxiang.dto.Result;
import com.linxiang.entity.ShopType;

/**
 * 服务类
 *
 * @author 虎哥
 * @since 2021-12-22
 */
public interface IShopTypeService extends IService<ShopType> {

  Result queryTypeList();
}
