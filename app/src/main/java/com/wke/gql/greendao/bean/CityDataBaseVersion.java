package com.wke.gql.greendao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created by Administrator on 2018/3/11.
 */
@Entity(nameInDb = "CityVersion")
public class CityDataBaseVersion extends BaseEntity {
    //"version": "201802061515"
    @Id(autoincrement = true)
    @Property(nameInDb = "ID")
    public long id;
    @Property(nameInDb = "VERSION")
    public String version;

    @Generated(hash = 1008346573)
    public CityDataBaseVersion(long id, String version) {
        this.id = id;
        this.version = version;
    }

    @Generated(hash = 1564706535)
    public CityDataBaseVersion() {
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
