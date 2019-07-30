package org.linlinjava.litemall.db.service;

import org.linlinjava.litemall.db.dao.TicSystemMapper;
import org.linlinjava.litemall.db.domain.TicSystem;
import org.linlinjava.litemall.db.domain.TicSystemExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LitemallSystemConfigService {
    @Resource
    private TicSystemMapper systemMapper;

    public List<TicSystem> queryAll() {
    	TicSystemExample example = new TicSystemExample();
        example.or();
        return systemMapper.selectByExample(example);
    }
}
