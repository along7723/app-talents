package com.fanye.modules.talents.talents.service;

import com.fanye.modules.core.entity.Result;
import com.fanye.modules.talents.talents.entity.TalTalents;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 人才 服务接口类
 * </p>
 *
 * @author yichenlei
 * @since 2020-11-20
 */
public interface ITalTalentsService {

    /**
     * 查询数据
     *
     * @param talTalents 查询条件
     * @param pageIndex  页码
     * @param pageSize   每页条数
     * @return Result
     */
    Result queryListByPage(TalTalents talTalents, Integer pageIndex, Integer pageSize, Map<String, String[]> paramsMap, String[] fastQueryFiledNames);

    /**
     * 查询数据
     *
     * @return Result
     */
    Result queryOneTalTalents(Long userId);
    TalTalents queryOneTalTalents(String idNo);

    Integer getCountOfIdNo(String idNo);

    /**
     * 数据详情
     *
     * @param id
     * @return
     */
    TalTalents getById(String id);
    /**
     * 导入数据
     */
    Map importData(Long tId, Long userId, Long compId, MultipartFile file) ;

    /**
     * 下载导入模板
     */
    void downloadTemplate( Long tId,HttpServletResponse response)throws IOException;

    /**
     * 添加
     *
     * @param talTalents 信息
     * @return Result
     */
    Result add(TalTalents talTalents);
    TalTalents insert(TalTalents talTalents);

    /**
     * 删除
     *
     * @param id 主键
     * @return Result
     */
    Result delete(String id);

    /**
     * 批量删除
     *
     * @param ids 主键
     * @return Result
     */
    Result delBatch(String[] ids);

    /**
     * 修改信息
     *
     * @param talTalents 信息
     * @return Result
     */
    Result updateData(TalTalents talTalents);

    /**
     * 根据身份证，判断人才是否存在
     * @param idno
     * @return
     */
    boolean checkIdno(String idno);

    Integer queryTODO();
}
