package wang.l1n.platform.system.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wang.l1n.platform.common.entity.CommonResult;
import wang.l1n.platform.common.entity.RedisInfo;
import wang.l1n.platform.common.service.RedisService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("redis")
public class RedisController {

    @Autowired
    private RedisService redisService;

    @GetMapping("info")
    public CommonResult getRedisInfo() throws Exception {
        List<RedisInfo> infoList = this.redisService.getRedisInfo();
        return new CommonResult().data(infoList);
    }

    @GetMapping("keysSize")
    public Map<String, Object> getKeysSize() throws Exception {
        return redisService.getKeysSize();
    }

    @GetMapping("memoryInfo")
    public Map<String, Object> getMemoryInfo() throws Exception {
        return redisService.getMemoryInfo();
    }
}
