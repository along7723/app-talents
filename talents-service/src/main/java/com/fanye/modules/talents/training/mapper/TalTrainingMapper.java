package com.fanye.modules.talents.training.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fanye.modules.talents.training.entity.TalTraining;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author yichenlei
 * @since 2020-11-20
 */
@Component
public interface TalTrainingMapper extends BaseMapper<TalTraining> {
    List<Map> countTrend(String year);
}
