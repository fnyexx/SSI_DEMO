package com.czg.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.czg.bean.ChanPin;
import com.czg.common.DaoCriteria;
import com.czg.dao.ChanPinMapper;
import com.czg.service.ChanPinService;

@Service
public class ChanPinServiceImpl implements ChanPinService {
    @Autowired
    private ChanPinMapper chanPinMapper;

    private static final Logger logger = Logger.getLogger(ChanPinServiceImpl.class);

    public int countByExample(DaoCriteria example) {
        int count = this.chanPinMapper.countByExample(example);
        logger.debug("count: {}");
        return count;
    }

    public ChanPin selectByPrimaryKey(Integer id) {
        return this.chanPinMapper.selectByPrimaryKey(id);
    }

    public List<ChanPin> selectByExample(DaoCriteria example) {
        return this.chanPinMapper.selectByExample(example);
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.chanPinMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(ChanPin record) {
        return this.chanPinMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(ChanPin record) {
        return this.chanPinMapper.updateByPrimaryKey(record);
    }

    public int deleteByExample(DaoCriteria example) {
        return this.chanPinMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(ChanPin record, DaoCriteria example) {
        return this.chanPinMapper.updateByExampleSelective(record, example.getCondition());
    }

    public int updateByExample(ChanPin record, DaoCriteria example) {
        return this.chanPinMapper.updateByExample(record, example.getCondition());
    }

    public int insert(ChanPin record) {
        return this.chanPinMapper.insert(record);
    }

    public int insertSelective(ChanPin record) {
        return this.chanPinMapper.insertSelective(record);
    }
}