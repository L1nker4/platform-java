package wang.l1n.platform.platform.pms.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wuwenze.poi.ExcelKit;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import wang.l1n.platform.common.annotation.Log;
import wang.l1n.platform.common.controller.BaseController;
import wang.l1n.platform.common.entity.CommonResult;
import wang.l1n.platform.common.entity.QueryRequest;
import wang.l1n.platform.common.entity.constant.MessageConstant;
import wang.l1n.platform.platform.pms.entity.ProductService;

import wang.l1n.platform.platform.pms.entity.request.AddProductServiceRequest;
import wang.l1n.platform.platform.pms.entity.request.UpdateProductServiceRequest;
import wang.l1n.platform.platform.pms.service.ProductServiceService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品管理-商品服务表 前端控制器
 * </p>
 *
 * @author L1nker4
 * @since 2020-07-29
 */
@RestController
@RequestMapping("/service")
@Api(value = "/service", tags = {"商品-服务管理"})
@Slf4j
public class ProductServiceController extends BaseController {

    @Autowired
    private ProductServiceService productServiceService;

    @GetMapping
    @RequiresPermissions("pms:service:view")
    public Map<String, Object> serviceList(@Valid QueryRequest request){
        IPage<ProductService> iPage = productServiceService.getList(request);
        return getDataTable(iPage);
    }

    @PostMapping
    @RequiresPermissions("pms:service:add")
    @Log("新增商品服务")
    public CommonResult addService(@Valid @RequestBody AddProductServiceRequest request){
        ProductService service = new ProductService();
        BeanUtils.copyProperties(request, service);
        productServiceService.save(service);
        return new CommonResult().success(MessageConstant.ADD_SUCCESS_MESSAGE);
    }

    @PutMapping
    @Log("修改商品服务")
    @RequiresPermissions("pms:service:update")
    public CommonResult updateService(@Valid @RequestBody UpdateProductServiceRequest request){
        ProductService service = new ProductService();
        BeanUtils.copyProperties(request, service);
        productServiceService.updateById(service);
        return new CommonResult().success(MessageConstant.UPDATE_SUCCESS_MESSAGE);
    }

    @DeleteMapping("/{id}")
    @Log("删除商品服务")
    @RequiresPermissions("pms:service:delete")
    public CommonResult deleteService(@NotBlank(message = "{required}") @PathVariable String id){
        productServiceService.removeById(id);
        return new CommonResult().success(MessageConstant.DELETE_SUCCESS_MESSAGE);
    }


}
