package com.xiaoazhai.handler;

import cn.hutool.core.collection.CollectionUtil;
import com.xiaoazhai.repository.entity.Permission;
import com.xiaoazhai.repository.entity.PermissionCategory;
import com.xiaoazhai.repository.service.PermissionCategoryService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author jiangyun
 * @date 2021/5/25  15:05
 **/
@Component
public class FillFieldHandler {

    @Resource
    private PermissionCategoryService permissionCategoryService;


    private <T, P> void fillField(Function<? super T, Long> getFunction, BiConsumer<? super T, String> setFunction, List<T> targetList,
                                  Function<List<Long>, List<? extends P>> queryFunction, Function<? super P, Long> keyFunction,
                                  Function<? super P, String> valueFunction) {
        List<Long> idList = targetList.stream()
                .map(getFunction)
                .collect(Collectors.toList());
        if (CollectionUtil.isNotEmpty(idList)) {
            List<? extends P> resultList = queryFunction.apply(idList);
            Map<Long, String> map = resultList.stream()
                    .collect(Collectors.toMap(keyFunction, valueFunction));
            targetList.forEach(target -> setFunction.accept(target, map.get(getFunction.apply(target))));
        }
    }

    private <T, P> void fillStringField(Function<? super T, String> getFunction, BiConsumer<? super T, String> setFunction, List<T> targetList,
                                        Function<List<String>, List<? extends P>> queryFunction, Function<? super P, String> keyFunction,
                                        Function<? super P, String> valueFunction) {
        List<String> idList = targetList.stream()
                .map(getFunction)
                .collect(Collectors.toList());
        if (CollectionUtil.isNotEmpty(idList)) {
            List<? extends P> resultList = queryFunction.apply(idList);
            Map<String, String> map = resultList.stream()
                    .collect(Collectors.toMap(keyFunction, valueFunction));
            targetList.forEach(target -> setFunction.accept(target, map.get(getFunction.apply(target))));
        }
    }


    public <T> void fillPermissionCategoryName(Function<? super T, Long> getFunction,
                                               BiConsumer<? super T, String> setFunction,
                                               List<T> targetList) {
        fillField(getFunction, setFunction, targetList, permissionCategoryService::listByIds, PermissionCategory::getId, PermissionCategory::getName);
    }

}
