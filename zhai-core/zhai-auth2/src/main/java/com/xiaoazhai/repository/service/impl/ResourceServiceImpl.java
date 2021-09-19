package com.xiaoazhai.repository.service.impl;

import com.xiaoazhai.repository.entity.Resource;
import com.xiaoazhai.repository.mapper.ResourceMapper;
import com.xiaoazhai.repository.service.ResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhai
 * @since 2021-09-19
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {

}
