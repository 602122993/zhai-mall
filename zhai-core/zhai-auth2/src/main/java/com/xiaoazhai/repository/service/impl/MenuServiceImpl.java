package com.xiaoazhai.repository.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoazhai.domain.entity.MenuEntity;
import com.xiaoazhai.repository.entity.Menu;
import com.xiaoazhai.repository.mapper.MenuMapper;
import com.xiaoazhai.repository.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoazhai.util.BeanUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhai
 * @since 2021-09-20
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Override
    public IPage<MenuEntity> queryMenuPage(Page page, Long parentId, String name) {
        return BeanUtil.copyPage(this.page(page, Wrappers.<Menu>lambdaQuery()
                .eq(Menu::getParentId,parentId)
                .orderByAsc(Menu::getSort)
                .like(StringUtils.isNotEmpty(name), Menu::getName, name)) );
    }

    @Override
    public void saveMenu(Menu menu) {
        this.save(menu);
    }

    @Override
    public Menu queryMenuById(Long id) {
        return this.getById(id);
    }

    @Override
    public void updateMenuById(Menu menu) {
        this.updateById(menu);
    }

    @Override
    public void removeMenuById(Long id) {
        this.removeById(id);
    }

    @Override
    public List<MenuEntity> queryMenuListByIds(List<Long> ids) {
        return BeanUtil.doToEntityBatch(this.listByIds(ids) );
    }


    @Override
    public List<MenuEntity> queryMenuListByParentId(Long baseParentMenuId) {
        return BeanUtil.doToEntityBatch(this.list(Wrappers.<Menu>lambdaQuery()
                .eq(Menu::getParentId, baseParentMenuId)) );
    }


}
