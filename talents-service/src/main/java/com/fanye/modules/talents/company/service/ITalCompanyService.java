package com.fanye.modules.talents.company.service;

import com.fanye.modules.core.entity.Result;
import com.fanye.modules.talents.company.entity.TalCompany;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * <p>
 * 公司 服务接口类
 * </p>
 *
 * @author Administrator
 * @since 2021-04-16
 */
public interface ITalCompanyService {

    /**
     * 查询分页数据
     *
     * @param talCompany 查询条件
     * @param pageIndex  页码
     * @param pageSize   每页条数
     * @return Result
     */
    Result queryListByPage(TalCompany talCompany, Integer pageIndex, Integer pageSize, Map
            <String, String[]> paramsMap, String[] fastQueryFiledNames);


    /**
     * 导出查询的数据
     */
    void expData(Long tId, TalCompany talCompany, Map<String, String[]> paramsMap, String[] fastQueryFiledNames, HttpServletResponse response);

    /**
     * 导入数据
     */
    Map importData(Long tId,Long userId, MultipartFile file, HttpServletResponse response);

    /**
     * 下载导入模板
     */
    void downloadTemplate(Long tId, HttpServletResponse response) throws IOException;


    /**
     * 查询数量
     *
     * @param talCompany
     * @return
     */
    Integer queryCount(TalCompany talCompany);

    /**
     * 数据详情
     *
     * @param id
     * @return
     */
    Result getById(String id);

    TalCompany getOneByCreateUser(String id);
    TalCompany getOneByTaxId(String taxId);
    /**
     * 添加
     *
     * @param talCompany 信息
     * @return Result
     */
    Result add(TalCompany talCompany);


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
     * @param talCompany 信息
     * @return Result
     */
    Result updateData(TalCompany talCompany);


    Integer queryTODO();
}
