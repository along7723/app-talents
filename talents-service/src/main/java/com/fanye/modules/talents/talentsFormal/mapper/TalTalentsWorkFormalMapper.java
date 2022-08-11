package com.fanye.modules.talents.talentsFormal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fanye.modules.talents.talentsFormal.entity.TalTalentsWorkFormal;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 从业经历_正式 Mapper 接口
 * </p>
 *
 * @author yichenlei
 * @since 2021-01-20
 */
@Component
public interface TalTalentsWorkFormalMapper extends BaseMapper<TalTalentsWorkFormal> {
    void copyToFormal(Long talId);
}
