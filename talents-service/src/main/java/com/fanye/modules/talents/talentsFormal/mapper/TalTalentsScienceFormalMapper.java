package com.fanye.modules.talents.talentsFormal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fanye.modules.talents.talentsFormal.entity.TalTalentsScienceFormal;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 科研学术_正式 Mapper 接口
 * </p>
 *
 * @author yichenlei
 * @since 2021-01-20
 */
@Component
public interface TalTalentsScienceFormalMapper extends BaseMapper<TalTalentsScienceFormal> {
    void copyToFormal(Long talId);
}
