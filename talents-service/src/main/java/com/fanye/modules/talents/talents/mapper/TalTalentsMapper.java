package com.fanye.modules.talents.talents.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fanye.modules.talents.talents.entity.TalTalents;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 人才 Mapper 接口
 * </p>
 *
 * @author yichenlei
 * @since 2020-11-20
 */
@Component
public interface TalTalentsMapper extends BaseMapper<TalTalents> {
    TalTalents queryOneTalTalents(Long userId);
}
