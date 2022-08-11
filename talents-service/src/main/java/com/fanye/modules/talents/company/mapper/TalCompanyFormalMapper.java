package com.fanye.modules.talents.company.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fanye.modules.talents.company.entity.TalCompanyFormal;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 公司-正式 Mapper 接口
 * </p>
 *
 * @author yichenlei
 * @since 2021-01-27
 */
@Component
public interface TalCompanyFormalMapper extends BaseMapper<TalCompanyFormal> {
    void copyToFormal(@Param("compId") Long compId);
    List<Map> countCompanyIndustry();
}
