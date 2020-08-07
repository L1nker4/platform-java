package wang.l1n.platform.platform.pms.controller;


import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import wang.l1n.platform.common.annotation.Log;
import wang.l1n.platform.common.entity.CommonResult;
import wang.l1n.platform.common.entity.QueryRequest;
import wang.l1n.platform.common.entity.constant.MessageConstant;
import wang.l1n.platform.platform.pms.entity.request.AddProductRequest;
import wang.l1n.platform.platform.pms.entity.Product;
import wang.l1n.platform.platform.pms.entity.request.UpdateProductRequest;
import wang.l1n.platform.platform.pms.service.IProductService;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 商品管理-商品表 前端控制器
 * </p>
 *
 * @author L1nker4
 * @since 2020-07-28
 */
@RestController
@RequestMapping("/product")
@Api(value = "/product", tags = {"商品-管理"})
public class ProductController {


    @Autowired
    private IProductService productService;

    @GetMapping
    @RequiresPermissions("pms:product:view")
    public CommonResult productList(@Valid QueryRequest request){
        return productService.getList(request);
    }

    @PostMapping
    @RequiresPermissions("pms:product:add")
    @Log("新增商品")
    public CommonResult addProduct(@Valid @RequestBody AddProductRequest request){
        Product product = new Product();
        BeanUtils.copyProperties(request, product);
        productService.save(product);
        return new CommonResult().success(MessageConstant.ADD_SUCCESS_MESSAGE);
    }

    @PutMapping
    @Log("修改商品")
    @RequiresPermissions("pms:product:update")
    public CommonResult updateProduct(@Valid @RequestBody UpdateProductRequest request){
        Product product = new Product();
        BeanUtils.copyProperties(request, product);
        productService.updateById(product);
        return new CommonResult().success(MessageConstant.UPDATE_SUCCESS_MESSAGE);
    }

    @DeleteMapping("/{id}")
    @Log("删除商品")
    @RequiresPermissions("pms:product:delete")
    public CommonResult deleteProduct(@NotBlank(message = "{required}") @PathVariable String id){
        productService.removeById(id);
        return new CommonResult().success(MessageConstant.DELETE_SUCCESS_MESSAGE);
    }

}
