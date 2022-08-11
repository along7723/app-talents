package com.fanye.modules.talents.countUserType.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fanye.modules.talents.countUserType.entity.TalCountUser;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户统计表 Mapper 接口
 * </p>
 *
 * @author yichenlei
 * @since 2021-02-03
 */
@Component
public interface TalCountUserMapper extends BaseMapper<TalCountUser> {

    List<Map> countSex();

    List<Map> countAge();

    List<Map> countEducation();

    List<Map> countNation();

    List<Map> countSource();

    List<Map> countUserTrend();
}
