package wang.l1n.platform.platform.pms.entity.request;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import lombok.Data;
import wang.l1n.platform.platform.pms.entity.enums.ShowStatusEnums;

/**
 * @author ：L1nker4
 * @date ： 创建于  2020/7/22 21:46
 * @description： 导出商品分类 请求类
 */

@Data
@Excel("商品分类表")
public class ExportProductCategoryRequest {

    @ExcelField(value = "分类ID")
    private Long id;

    @ExcelField(value = "上级ID")
    private Long parentId;

    @ExcelField(value = "分类名称")
    private String name;

    @ExcelField(value = "分类等级")
    private Integer level;

    @ExcelField(value = "商品数量")
    private Integer productCount;

    @ExcelField(value = "商品单位")
    private String productUnit;

    @ExcelField(value = "导航栏显示状态")
    private ShowStatusEnums navStatus;

    @ExcelField(value = "显示状态")
    private ShowStatusEnums showStatus;

    @ExcelField(value = "排序")
    private Integer sort;

    @ExcelField(value = "logo")
    private String logo;

    @ExcelField(value = "关键词")
    private String keywords;

    @ExcelField(value = "描述")
    private String description;
}
