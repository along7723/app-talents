package com.fanye.modules.talents.talentsFormal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fanye.modules.talents.talentsFormal.entity.TalTalentsCertificateFormal;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 资格证书_正式 Mapper 接口
 * </p>
 *
 * @author yichenlei
 * @since 2021-01-20
 */
@Component
public interface TalTalentsCertificateFormalMapper extends BaseMapper<TalTalentsCertificateFormal> {
    void copyToFormal(Long talId);
}
