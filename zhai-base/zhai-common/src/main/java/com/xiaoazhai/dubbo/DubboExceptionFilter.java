package com.xiaoazhai.dubbo;

import com.xiaoazhai.exception.GlobalException;
import com.xiaoazhai.exception.GlobalExceptionWrapper;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.common.logger.Logger;
import org.apache.dubbo.common.logger.LoggerFactory;
import org.apache.dubbo.common.utils.ReflectUtils;
import org.apache.dubbo.common.utils.StringUtils;
import org.apache.dubbo.rpc.*;
import org.apache.dubbo.rpc.filter.ExceptionFilter;
import org.apache.dubbo.rpc.service.GenericService;

import java.lang.reflect.Method;

@Activate(group = CommonConstants.PROVIDER)
public class DubboExceptionFilter implements Filter {
    private Logger logger = LoggerFactory.getLogger(DubboExceptionFilter.class);

    public DubboExceptionFilter() {
    }

    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        Result result = invoker.invoke(invocation);
        if (result.hasException() && GenericService.class != invoker.getInterface()) {
            try {
                Throwable exception = result.getException();
                if (exception instanceof GlobalException) {
                    result.setException(new GlobalExceptionWrapper((GlobalException) exception));
                    return result;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }


}
