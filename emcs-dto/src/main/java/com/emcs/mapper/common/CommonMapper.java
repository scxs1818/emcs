package com.emcs.mapper.common;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CommonMapper {
    int  getNextVal(String sqeName);
}
