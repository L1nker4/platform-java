package wang.l1n.platform.common.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author     ：L1nker4
 * @date       ： 创建于  2020/1/18 12:24
 * @description： 分页查询实体类
 */
@Data
public class QueryRequest implements Serializable {

    private static final long serialVersionUID = -4869594085374385813L;

    private int pageSize = 10;
    private int pageNum = 1;

    private String sortField;
    private String sortOrder;
}
