package com.xiaoazhai.userinterface.request;

import com.xiaoazhai.entity.BaseQueryForm;
import lombok.Data;

/**
 * @author jiangyun
 * @date 2021/9/20  12:19
 **/
@Data
public class QueryPermissionRequest extends BaseQueryForm {
    private String name;
}
