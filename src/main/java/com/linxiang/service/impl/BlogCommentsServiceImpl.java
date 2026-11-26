package com.linxiang.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linxiang.entity.BlogComments;
import com.linxiang.mapper.BlogCommentsMapper;
import com.linxiang.service.IBlogCommentsService;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Service
public class BlogCommentsServiceImpl extends ServiceImpl<BlogCommentsMapper, BlogComments>
    implements IBlogCommentsService {}
