package com.fanye.modules.talents.talentsFormal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fanye.modules.talents.talentsFormal.entity.TalTalentsAttachmentFormal;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 人才附件 Mapper 接口
 * </p>
 *
 * @author dell
 * @since 2021-04-10
 */
@Component
public interface TalTalentsAttachmentFormalMapper extends BaseMapper<TalTalentsAttachmentFormal> {
    void copyToFormal(Long talId);
}
