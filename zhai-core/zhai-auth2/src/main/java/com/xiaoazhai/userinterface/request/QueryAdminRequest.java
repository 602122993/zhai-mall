package com.xiaoazhai.userinterface.request;

import com.xiaoazhai.entity.BaseQueryForm;
import lombok.Data;

/**
 * @author jiangyun
 * @date 2021/9/20  20:40
 **/
@Data
public class QueryAdminRequest extends BaseQueryForm {

    private String name;
}
