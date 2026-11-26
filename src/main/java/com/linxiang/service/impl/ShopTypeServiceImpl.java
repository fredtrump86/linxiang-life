package com.linxiang.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linxiang.dto.Result;
import com.linxiang.entity.ShopType;
import com.linxiang.mapper.ShopTypeMapper;
import com.linxiang.service.IShopTypeService;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Service
public class ShopTypeServiceImpl extends ServiceImpl<ShopTypeMapper, ShopType>
    implements IShopTypeService {

  @Override
  public Result queryTypeList() {
    List<ShopType> typeList = query().orderByAsc("sort").list();
    return Result.ok(typeList);
  }
}
