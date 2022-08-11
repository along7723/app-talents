package com.fanye.modules.talents.countCompany.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fanye.modules.core.entity.Result;
import com.fanye.modules.talents.countCompany.entity.TalCountCompany;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户统计表 Mapper 接口
 * </p>
 *
 * @author yichenlei
 * @since 2021-02-03
 */
@Component
public interface TalCountCompanyMapper extends BaseMapper<TalCountCompany> {

    /**
     * 企业在职人数排名
     *
     * @return Result
     */
    List<Map> onTheJob();
    /**
     * 单位数量变化趋势
     */
    List<Map> quantityChange();
    /**
     * 单位类型分布
     */
    List<Map> unitType();
}
