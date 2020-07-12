package wang.l1n.platform.common.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.HashMap;
import java.util.Map;


/**
 * @author     ：L1nker4
 * @date       ： 创建于  2020/1/19 13:42
 * @description：
 */
public class BaseController {

    protected Map<String, Object> getDataTable(IPage<?> pageInfo) {
        Map<String, Object> rspData = new HashMap<>();
        rspData.put("rows", pageInfo.getRecords());
        rspData.put("total", pageInfo.getTotal());
        return rspData;
    }
}
