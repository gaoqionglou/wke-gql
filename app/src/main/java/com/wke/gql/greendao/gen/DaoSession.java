package com.wke.gql.greendao.gen;

import com.wke.gql.greendao.bean.CityDataBaseVersion;
import com.wke.gql.greendao.bean.CityItem;
import com.wke.gql.greendao.bean.HistoryCityItem;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import java.util.Map;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig cityItemDaoConfig;
    private final DaoConfig historyCityItemDaoConfig;
    private final DaoConfig cityDataBaseVersionDaoConfig;

    private final CityItemDao cityItemDao;
    private final HistoryCityItemDao historyCityItemDao;
    private final CityDataBaseVersionDao cityDataBaseVersionDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        cityItemDaoConfig = daoConfigMap.get(CityItemDao.class).clone();
        cityItemDaoConfig.initIdentityScope(type);

        historyCityItemDaoConfig = daoConfigMap.get(HistoryCityItemDao.class).clone();
        historyCityItemDaoConfig.initIdentityScope(type);

        cityDataBaseVersionDaoConfig = daoConfigMap.get(CityDataBaseVersionDao.class).clone();
        cityDataBaseVersionDaoConfig.initIdentityScope(type);

        cityItemDao = new CityItemDao(cityItemDaoConfig, this);
        historyCityItemDao = new HistoryCityItemDao(historyCityItemDaoConfig, this);
        cityDataBaseVersionDao = new CityDataBaseVersionDao(cityDataBaseVersionDaoConfig, this);

        registerDao(CityItem.class, cityItemDao);
        registerDao(HistoryCityItem.class, historyCityItemDao);
        registerDao(CityDataBaseVersion.class, cityDataBaseVersionDao);
    }

    public void clear() {
        cityItemDaoConfig.getIdentityScope().clear();
        historyCityItemDaoConfig.getIdentityScope().clear();
        cityDataBaseVersionDaoConfig.getIdentityScope().clear();
    }

    public CityItemDao getCityItemDao() {
        return cityItemDao;
    }

    public HistoryCityItemDao getHistoryCityItemDao() {
        return historyCityItemDao;
    }

    public CityDataBaseVersionDao getCityDataBaseVersionDao() {
        return cityDataBaseVersionDao;
    }

}
