package com.fanye.modules.talents.talentsFormal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fanye.modules.talents.talents.entity.TalTalentsPatent;
import com.fanye.modules.talents.talentsFormal.entity.TalTalentsPatentFormal;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dell
 * @since 2021-04-06
 */
@Component
public interface TalTalentsPatentFormalMapper extends BaseMapper<TalTalentsPatentFormal> {
    void copyToFormal(Long talId);
}
