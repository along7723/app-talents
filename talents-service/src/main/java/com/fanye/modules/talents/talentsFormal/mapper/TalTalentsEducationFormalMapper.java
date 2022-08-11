package com.fanye.modules.talents.talentsFormal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fanye.modules.talents.talentsFormal.entity.TalTalentsEducationFormal;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 教育经历_正式 Mapper 接口
 * </p>
 *
 * @author yichenlei
 * @since 2021-01-20
 */
@Component
public interface TalTalentsEducationFormalMapper extends BaseMapper<TalTalentsEducationFormal> {
    void copyToFormal(Long talId);
}
