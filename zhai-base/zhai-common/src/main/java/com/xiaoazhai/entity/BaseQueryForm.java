package com.xiaoazhai.entity;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

/**
 * @author jiangyun
 * @date 2021/9/10  22:25
 **/
@Data
public class BaseQueryForm<T> {

    private Integer limit;

    private Integer current;

    public Page<T> getPage() {
        return new Page<T>(current, limit);
    }
}
