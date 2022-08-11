package com.fanye.modules.talents.talentsFormal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fanye.modules.talents.talentsFormal.entity.TalTalentsCreditFormal;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 信用记录_正式 Mapper 接口
 * </p>
 *
 * @author yichenlei
 * @since 2021-01-20
 */
@Component
public interface TalTalentsCreditFormalMapper extends BaseMapper<TalTalentsCreditFormal> {
    void copyToFormal(Long talId);
}
