package com.fanye.modules.talents.talentsFormal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fanye.modules.talents.talentsFormal.entity.TalTalentsTechTitleFormal;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 技术职称_正式 Mapper 接口
 * </p>
 *
 * @author yichenlei
 * @since 2021-01-20
 */
@Component
public interface TalTalentsTechTitleFormalMapper extends BaseMapper<TalTalentsTechTitleFormal> {
    void copyToFormal(Long talId);
}
