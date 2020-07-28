package wang.l1n.platform.platform.pms.controller;


import com.wuwenze.poi.ExcelKit;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import wang.l1n.platform.common.annotation.Log;
import wang.l1n.platform.common.authentication.JWTUtil;
import wang.l1n.platform.common.entity.CommonResult;
import wang.l1n.platform.common.entity.QueryRequest;
import wang.l1n.platform.common.entity.constant.MessageConstant;
import wang.l1n.platform.platform.pms.entity.ProductCategory;
import wang.l1n.platform.platform.pms.entity.request.AddProductCategoryRequest;
import wang.l1n.platform.platform.pms.entity.request.ExportProductCategoryRequest;
import wang.l1n.platform.platform.pms.entity.request.UpdateProductCategoryRequest;
import wang.l1n.platform.platform.pms.service.ProductCategoryService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 商品管理-分类表 前端控制器
 * </p>
 *
 * @author L1nker4
 * @since 2020-07-20
 */
@RestController
@RequestMapping("category")
@Api(value = "/category", tags = {"商品-分类"})
@Slf4j
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping
    @RequiresPermissions("pms:category:view")
    public CommonResult categoryList(@Valid QueryRequest request){
        return productCategoryService.getList(request);
    }

    @PostMapping
    @RequiresPermissions("pms:category:add")
    @Log("新增商品分类")
    public CommonResult addCategory(@Valid @RequestBody AddProductCategoryRequest request){
        ProductCategory category = new ProductCategory();
        BeanUtils.copyProperties(request, category);
        productCategoryService.save(category);
        return new CommonResult().success(MessageConstant.ADD_SUCCESS_MESSAGE);
    }

    @PutMapping
    @Log("修改商品分类")
    @RequiresPermissions("pms:category:update")
    public CommonResult updateCategory(@Valid @RequestBody UpdateProductCategoryRequest request){
        ProductCategory category = new ProductCategory();
        BeanUtils.copyProperties(request, category);
        productCategoryService.updateById(category);
        return new CommonResult().success(MessageConstant.UPDATE_SUCCESS_MESSAGE);
    }

    @DeleteMapping("/{ids}")
    @Log("删除商品分类")
    @RequiresPermissions("pms:category:delete")
    public CommonResult deleteCategory(@NotBlank(message = "{required}") @PathVariable String ids){
        return productCategoryService.deleteCategory(ids);

    }

    @PostMapping("excel")
    @Log("导出商品分类表")
    @RequiresPermissions("pms:category:export")
    public void export(HttpServletResponse response){
        try {
            List<ProductCategory> list = productCategoryService.list();
            List<ExportProductCategoryRequest> exportList = new ArrayList<>();
            list.forEach((category -> {
                ExportProductCategoryRequest request = new ExportProductCategoryRequest();
                BeanUtils.copyProperties(category, request );
                exportList.add(request);
            }));
            ExcelKit.$Export(ExportProductCategoryRequest.class, response)
                    .downXlsx(exportList, false);
        }catch (Exception e){
            log.error("导出失败",e);
        }
    }

}
