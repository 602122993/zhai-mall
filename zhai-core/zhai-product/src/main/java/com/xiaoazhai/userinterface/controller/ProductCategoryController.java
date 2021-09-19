package com.xiaoazhai.userinterface.controller;

import com.xiaoazhai.repository.ProductCategoryRepository;
import com.xiaoazhai.result.ReturnMessage;
import com.xiaoazhai.userinterface.request.AddProductCategoryRequest;
import com.xiaoazhai.userinterface.request.QueryProductCategoryForm;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author jiangyun
 * @date 2021/9/5  12:12
 **/
@RestController
@RequestMapping("product/category")
public class ProductCategoryController {

    @Resource
    private ProductCategoryRepository productCategoryRepository;


    @PostMapping("save")
    public ReturnMessage saveProductCategory(@RequestBody AddProductCategoryRequest request) {
        productCategoryRepository.saveProductCategory(request.generalEntity());
        return ReturnMessage.success();
    }


    @GetMapping("list")
    public ReturnMessage getProductCategory(QueryProductCategoryForm queryProductCategoryForm) {
        return ReturnMessage.success(productCategoryRepository.queryProductCategory(queryProductCategoryForm.getPage(), queryProductCategoryForm.getId()));
    }
}
