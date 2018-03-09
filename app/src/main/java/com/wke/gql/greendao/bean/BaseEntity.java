package com.wke.gql.greendao.bean;

import com.wke.gql.greendao.DaoManager;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * @author gql
 * @since 18-3-9.
 */

public class BaseEntity {


    public static <T extends BaseEntity> DaoManager getInstance() {
        return DaoManager.getInstance();

    }

    /**
     * 返回常用的QueryBuilder
     *
     * @param entity
     * @param <T>
     * @return
     */
    public static <T extends BaseEntity> QueryBuilder<T> queryBuilder(Class<T> entity) {
        return getInstance().getDaoSession().queryBuilder(entity);
    }

    /**
     * 插入
     *
     * @param entity
     * @param <T>
     * @return
     */
    public static <T extends BaseEntity> long insert(T entity) {
        return getInstance().getDaoSession().insert(entity);
    }

    /**
     * 更新
     *
     * @param entity
     * @param <T>
     */
    public static <T extends BaseEntity> void update(T entity) {
        getInstance().getDaoSession().update(entity);
    }

    /**
     * 插入单个，当_id相同时会去更新
     *
     * @param entity
     * @param <T>
     * @return
     */
    public static <T extends BaseEntity> long insertOrReplace(T entity) {
        return getInstance().getDaoSession().insertOrReplace(entity);
    }

    /**
     * 删除单条记录
     *
     * @param entity
     * @param <T>
     */
    public static <T extends BaseEntity> void delete(T entity) {
        getInstance().getDaoSession().delete(entity);
    }


    /**
     * 删除全部记录
     *
     * @param entity
     * @param <T>
     */
    public static <T extends BaseEntity> void deleteAll(Class<T> entity) {
        getInstance().getDaoSession().deleteAll(entity);
    }

    /**
     * 查询全部
     *
     * @param entity
     * @param <T>
     */
    public static <T extends BaseEntity> List<T> findAll(Class<T> entity) {
        return getInstance().getDaoSession().loadAll(entity);
    }

    /**
     * 按id查询
     *
     * @param entity
     * @param <T>
     */
    public static <T extends BaseEntity, K> T findById(Class<T> entity, K key) {
        return getInstance().getDaoSession().load(entity, key);
    }

    /**
     * 统计
     *
     * @param entity
     * @param <T>
     * @return
     */
    public static <T extends BaseEntity> long count(Class<T> entity) {
        return getInstance().getDaoSession().getDao(entity).count();
    }

    /**
     * 获取第一条记录
     *
     * @param entity
     * @param <T>
     * @return
     */
    public static <T extends BaseEntity> T getFirst(Class<T> entity) {
        List<T> datas = getInstance().getDaoSession().loadAll(entity);
        if (datas != null && !datas.isEmpty()) return datas.get(0);
        return null;
    }
}

