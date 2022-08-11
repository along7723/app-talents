package com.fanye.modules.talents.talentsFormal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fanye.modules.talents.talents.entity.TalTalentsPaper;
import com.fanye.modules.talents.talentsFormal.entity.TalTalentsPaperFormal;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 发表论文 Mapper 接口
 * </p>
 *
 * @author dell
 * @since 2021-04-06
 */
@Component
public interface TalTalentsPaperFormalMapper extends BaseMapper<TalTalentsPaperFormal> {
    void copyToFormal(Long talId);
}
