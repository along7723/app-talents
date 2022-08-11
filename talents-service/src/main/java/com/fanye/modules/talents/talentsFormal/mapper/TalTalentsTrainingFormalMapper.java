package com.fanye.modules.talents.talentsFormal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fanye.modules.talents.talentsFormal.entity.TalTalentsTrainingFormal;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 培训经历_正式 Mapper 接口
 * </p>
 *
 * @author yichenlei
 * @since 2021-01-20
 */
@Component
public interface TalTalentsTrainingFormalMapper extends BaseMapper<TalTalentsTrainingFormal> {
    void copyToFormal(Long talId);
}
