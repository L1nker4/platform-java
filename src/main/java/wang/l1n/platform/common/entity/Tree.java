package wang.l1n.platform.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import wang.l1n.platform.platform.pms.entity.enums.ShowStatusEnums;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Tree<T> {

    private String id;

    private String key;

    private String name;

    private Integer level;

    private Integer productCount;

    private String productUnit;

    private ShowStatusEnums showStatus;

    private ShowStatusEnums navStatus;

    private String logo;

    private String keywords;

    private String description;

    private String icon;

    private String title;

    private String value;

    private String text;

    private String permission;

    private String type;

    private Double order;

    private String path;

    private String component;

    private List<Tree<T>> children;

    private String parentId;

    private boolean hasParent = false;

    private boolean hasChildren = false;

    private Date createTime;

    private Date updateTime;

    public void initChildren(){
        this.children = new ArrayList<>();
    }

}
