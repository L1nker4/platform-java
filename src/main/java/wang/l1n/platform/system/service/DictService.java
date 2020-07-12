package wang.l1n.platform.system.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import wang.l1n.platform.common.entity.QueryRequest;
import wang.l1n.platform.system.entity.Dict;


public interface DictService extends IService<Dict> {

    IPage<Dict> findDicts(QueryRequest request, Dict dict);

    void createDict(Dict dict);

    void updateDict(Dict dicdt);

    void deleteDicts(String[] dictIds);

}
