package com.czg.service;

import java.util.List;

import com.czg.bean.ChanPin;
import com.czg.common.DaoCriteria;

public interface ChanPinService {
    int countByExample(DaoCriteria example);

    ChanPin selectByPrimaryKey(Integer id);

    List<ChanPin> selectByExample(DaoCriteria example);

    int deleteByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ChanPin record);

    int updateByPrimaryKey(ChanPin record);

    int deleteByExample(DaoCriteria example);

    int updateByExampleSelective(ChanPin record, DaoCriteria example);

    int updateByExample(ChanPin record, DaoCriteria example);

    int insert(ChanPin record);

    int insertSelective(ChanPin record);
}