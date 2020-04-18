package com.cto.freemarker.dao.base;

import java.util.List;

/**
 * @author zhangyongwei
 * @date 2019-03-02 20:19:53
 */
public interface BaseMapper<T>{
    /**
     * 插入
     * @param obj
     * @return
     */
    int insert(T obj);

    /**
     * 根据条件修改
     * @param obj
     * @return
     */
    int updateBySelective(T obj);

    /**
     * 修改
     * @param obj
     * @return
     */
    int update(T obj);

    /**
     * 删除
     * @param id
     * @return
     */
    int deleteById(Long id);

    /**
     * 删除
     * @param uuid
     * @return
     */
    int deleteByUUId(String uuid);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    T selectEntityById(Long id);


    /**
     * 根据UUID查询
     * @param uuid
     * @return
     */
    T selectEntityByUUId(String uuid);

    /**
     * 根据条件查询列表
     * @param obj
     * @return
     */
    List<T> selectListBySearch(T obj);

    /**
     * 查询分页列表
     * @param obj
     * @return
     */
    List<T> selectPageList(T obj);

    /**
     * 查询分页总条数
     * @param obj
     * @return
     */
    int selectPageCount(T obj);

}
