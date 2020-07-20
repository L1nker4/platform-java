package wang.l1n.platform.platform.pms.controller;


import com.p6spy.engine.logging.Category;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import wang.l1n.platform.common.annotation.Log;
import wang.l1n.platform.common.entity.CommonResult;
import wang.l1n.platform.common.entity.constant.MessageConstant;
import wang.l1n.platform.platform.pms.entity.ProductCategory;
import wang.l1n.platform.platform.pms.entity.request.AddProductCategoryRequest;
import wang.l1n.platform.platform.pms.service.ProductCategoryService;

import javax.validation.Valid;

/**
 * <p>
 * 商品管理-分类表 前端控制器
 * </p>
 *
 * @author L1nker4
 * @since 2020-07-20
 */
@RestController
@RequestMapping("/product/category")
@Api(value = "/product/category", tags = {"商品-分类"})
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @PostMapping
//    @RequiresPermissions("pms:category:add")
    @Log("新增商品分类")
    public CommonResult addCategory(@Valid @RequestBody AddProductCategoryRequest request,
                                    BindingResult result){
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return new CommonResult().failed(error.getDefaultMessage());
            }
        }
        ProductCategory category = new ProductCategory();
        BeanUtils.copyProperties(request, category);
        productCategoryService.save(category);
        return new CommonResult().success(MessageConstant.ADD_SUCCESS_MESSAGE);
    }

}
