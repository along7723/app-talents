package com.fanye.modules.talents.talentsFormal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fanye.modules.talents.talentsFormal.entity.TalTalentsTagsFormal;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 人才标签_正式 Mapper 接口
 * </p>
 *
 * @author yichenlei
 * @since 2021-01-20
 */
@Component
public interface TalTalentsTagsFormalMapper extends BaseMapper<TalTalentsTagsFormal> {
    void copyToFormal(Long talId);
}
