package com.xiaoazhai.userinterface.controller;

import com.xiaoazhai.domain.service.ProductCategoryDomainService;
import com.xiaoazhai.repository.ProductCategoryRepository;
import com.xiaoazhai.result.ReturnMessage;
import com.xiaoazhai.userinterface.request.AddProductCategoryRequest;
import com.xiaoazhai.userinterface.request.QueryProductCategoryForm;
import com.xiaoazhai.userinterface.request.UpdateProductSortRequest;
import com.xiaoazhai.userinterface.response.ProductCategoryResponse;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jiangyun
 * @date 2021/9/5  12:12
 **/
@RestController
@RequestMapping("product/category")
public class ProductCategoryController {

    @Resource
    private ProductCategoryRepository productCategoryRepository;
    @Resource
    private ProductCategoryDomainService productCategoryDomainService;


    @PostMapping("save")
    public ReturnMessage saveProductCategory(@RequestBody AddProductCategoryRequest request) {
        productCategoryRepository.saveProductCategory(request.generateEntity());
        return ReturnMessage.success();
    }

    @PostMapping("update")
    public ReturnMessage updateProductCategory(@RequestBody AddProductCategoryRequest request) {
        productCategoryRepository.updateProductCategory(request.generateEntity());
        return ReturnMessage.success();
    }

    @GetMapping("list")
    public ReturnMessage getProductCategory(QueryProductCategoryForm queryProductCategoryForm) {
        return ReturnMessage.success(ProductCategoryResponse.generateFromEntityPage(productCategoryRepository.queryProductCategory(queryProductCategoryForm.getPage(), queryProductCategoryForm.getId())));
    }


    @PostMapping("remove")
    public ReturnMessage removeProductCategory(Long id) {
        productCategoryDomainService.removeProductCategory(id);
        return ReturnMessage.success();
    }

    @GetMapping("/parent-list")
    public ReturnMessage queryParentProductList() {
        return ReturnMessage.success(productCategoryRepository.queryParentProductCategoryList());
    }

    @GetMapping("query-by-id")
    public ReturnMessage queryById(Long id) {
        return ReturnMessage.success(productCategoryRepository.queryById(id));
    }

    @GetMapping("app-list")
    public ReturnMessage queryAppProductCategoryList() {
        return ReturnMessage.success(productCategoryRepository.queryAppProductCategoryList());
    }

    @PostMapping("update-product-category-sort")
    public ReturnMessage updateProductCategorySort(@RequestBody List<UpdateProductSortRequest> productSortRequestList) {
        productCategoryRepository.updateProductCategoryList(productSortRequestList.stream()
                .map(UpdateProductSortRequest::generateEntity)
                .collect(Collectors.toList()));
        return ReturnMessage.success();
    }

    @GetMapping("product-attribute-list-by-product-category-id")
    public ReturnMessage queryProductAttributeListByProductCategoryId(Long productCategoryId) {
        return ReturnMessage.success( productCategoryDomainService.queryProductAttributeListByProductCategoryId(productCategoryId));

    }

}
