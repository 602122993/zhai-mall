package com.xiaoazhai.util;

import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoazhai.entity.BaseDO;
import com.xiaoazhai.entity.BaseEntity;
import com.xiaoazhai.exception.GlobalException;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 扩展工具
 *
 * @author wenrui
 */
public class BeanUtil extends cn.hutool.core.bean.BeanUtil {


    /**
     * 忽略空值 泛型
     *
     * @param source
     * @param target
     */
    @SneakyThrows
    public static <T> T copyPropertiesIgnoreNullValue(Object source, Class<T> calzz) {
        CopyOptions copyOptions = new CopyOptions();
        copyOptions.setIgnoreNullValue(true);
        T obj = calzz.newInstance();
        copyProperties(source, obj);
        return obj;
    }


    /**
     * 忽略空值
     *
     * @param source
     * @param target
     */
    public static void copyIgnoreNullProperties(Object source, Object target) {
        CopyOptions copyOptions = new CopyOptions();
        copyOptions.setIgnoreNullValue(true);
        copyProperties(source, target, copyOptions);
    }

    /**
     * 忽略空值
     *
     * @param
     */
    public static <T, V> List<V> copyIgnoreNullProperties(List<T> source, Class<V> cla) {
        if (source == null || source.isEmpty()) {
            return new ArrayList<>();
        }
        List<V> data = new ArrayList<>();
        CopyOptions copyOptions = new CopyOptions();
        copyOptions.setIgnoreNullValue(true);
        for (T t : source) {
            V target = null;
            try {
                target = cla.newInstance();
            } catch (Exception e) {
                throw new GlobalException(e.getMessage());
            }
            copyProperties(t, target, copyOptions);
            data.add(target);
        }
        return data;
    }


    public static <T> IPage copyPage(IPage<? extends BaseDO> page, Class<T> clazz) {
        return page.convert(obj -> obj.generateEntity(clazz));
    }

    public static <T> T doToEntity(BaseDO<T> baseDO, Class<T> clazz) {
        if (baseDO == null) {
            return null;
        }
        return baseDO.generateEntity(clazz);
    }


    public static <T> List<T> entityToDOBatch(List<? extends BaseEntity<T>> entityList, Class<T> clazz) {
        return entityList.stream()
                .map(entity -> entity.generateDO(clazz))
                .collect(Collectors.toList());
    }

    public static <T> List<T> doToEntityBatch(List<? extends BaseDO<T>> doList, Class<T> clazz) {
        return doList.stream()
                .map(obj -> obj.generateEntity(clazz))
                .collect(Collectors.toList());
    }
}
