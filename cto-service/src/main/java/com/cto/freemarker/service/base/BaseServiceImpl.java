package com.cto.freemarker.service.base;

import com.cto.freemarker.dao.base.BaseDao;
import com.cto.freemarker.utils.ReflectUtils;
import com.cto.freemarker.utils.UUIDUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zhangyongwei
 */
public abstract class BaseServiceImpl<T> implements BaseService<T>{

    /**
     * 获取mapper
     * @return
     */
    public abstract BaseDao<T> getNameSpace();

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(T obj) {
        try {
            ReflectUtils.setFieldValue(obj, "uuid", UUIDUtils.getRandom(32));
        } catch (Exception var4) {
            throw new RuntimeException();
        }
        return getNameSpace().insert(obj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateBySelective(T obj) {
        return getNameSpace().updateBySelective(obj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(T obj) {
        return getNameSpace().update(obj);
    }

    @Override
    public int deleteById(Long id) {
        return getNameSpace().deleteById(id);
    }

    @Override
    public int deleteByUUId(String id) {
        return getNameSpace().deleteByUUId(id);
    }

    @Override
    public T selectEntityById(Long id) {
        return getNameSpace().selectEntityById(id);
    }

    @Override
    public T selectEntityByUUId(String id) {
        return getNameSpace().selectEntityByUUId(id);
    }

    @Override
    public List<T> selectListBySearch(T obj) {
        return getNameSpace().selectListBySearch(obj);
    }
}
