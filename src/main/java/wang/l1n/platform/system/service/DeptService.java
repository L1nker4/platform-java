package wang.l1n.platform.system.service;




import com.baomidou.mybatisplus.extension.service.IService;
import wang.l1n.platform.common.entity.QueryRequest;
import wang.l1n.platform.system.entity.Dept;

import java.util.List;
import java.util.Map;
/**
 * @author     ：L1nker4
 * @date       ： 创建于  2020/1/18 12:25
 * @description： 部门服务
 */
public interface DeptService extends IService<Dept> {

    Map<String, Object> findDepts(QueryRequest request, Dept dept);

    List<Dept> findDepts(Dept dept, QueryRequest request);

    void createDept(Dept dept);

    void updateDept(Dept dept);

    void deleteDepts(String[] deptIds);
}
