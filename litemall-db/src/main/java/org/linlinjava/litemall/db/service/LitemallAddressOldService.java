package org.linlinjava.litemall.db.service;

import com.github.pagehelper.PageHelper;
import org.linlinjava.litemall.db.dao.LitemallAddressOldMapper;
import org.linlinjava.litemall.db.domain.LitemallAddressOld;
import org.linlinjava.litemall.db.domain.LitemallAddressOldExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LitemallAddressOldService {
    @Resource
    private LitemallAddressOldMapper addressOldMapper;

    public List<LitemallAddressOld> queryByUid(Integer uid) {
        LitemallAddressOldExample example = new LitemallAddressOldExample();
        example.or().andUserIdEqualTo(uid).andDeletedEqualTo(false);
        return addressOldMapper.selectByExample(example);
    }

    public LitemallAddressOld findById(Integer id) {
        return addressOldMapper.selectByPrimaryKey(id);
    }

    public int add(LitemallAddressOld address) {
        address.setAddTime(LocalDateTime.now());
        address.setUpdateTime(LocalDateTime.now());
        return addressOldMapper.insertSelective(address);
    }

    public int update(LitemallAddressOld address) {
        address.setUpdateTime(LocalDateTime.now());
        return addressOldMapper.updateByPrimaryKeySelective(address);
    }

    public void delete(Integer id) {
        addressOldMapper.logicalDeleteByPrimaryKey(id);
    }

    public LitemallAddressOld findDefault(Integer userId) {
        LitemallAddressOldExample example = new LitemallAddressOldExample();
        example.or().andUserIdEqualTo(userId).andIsDefaultEqualTo(true).andDeletedEqualTo(false);
        return addressOldMapper.selectOneByExample(example);
    }

    public void resetDefault(Integer userId) {
        LitemallAddressOld address = new LitemallAddressOld();
        address.setIsDefault(false);
        address.setUpdateTime(LocalDateTime.now());
        LitemallAddressOldExample example = new LitemallAddressOldExample();
        example.or().andUserIdEqualTo(userId).andDeletedEqualTo(false);
        addressOldMapper.updateByExampleSelective(address, example);
    }

    public List<LitemallAddressOld> querySelective(Integer userId, String name, Integer page, Integer limit, String sort, String order) {
        LitemallAddressOldExample example = new LitemallAddressOldExample();
        LitemallAddressOldExample.Criteria criteria = example.createCriteria();

        if (userId != null) {
            criteria.andUserIdEqualTo(userId);
        }
        if (!StringUtils.isEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return addressOldMapper.selectByExample(example);
    }
}
