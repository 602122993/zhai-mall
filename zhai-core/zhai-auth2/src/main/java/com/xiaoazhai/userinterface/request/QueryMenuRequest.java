package com.xiaoazhai.userinterface.request;

import com.xiaoazhai.entity.BaseQueryForm;
import lombok.Data;

/**
 * @author jiangyun
 * @date 2021/9/20  13:33
 **/
@Data
public class QueryMenuRequest extends BaseQueryForm {

    private String name;

    //父id默认为0
    private Long parentId = 0L;


}
