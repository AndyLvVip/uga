package uga.framework.model;

import org.apache.commons.lang3.StringUtils;
import org.jooq.Table;
import org.jooq.UpdatableRecord;
import uga.framework.repository.Repositories;
import uga.framework.utils.UgaUtils;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: andy.lv
 * @Date: created on 2018/2/5
 * @Description:
 */
public abstract class BaseModel implements Serializable {

    private String id;

    private LocalDateTime createdOn;

    private String createdBy;

    private LocalDateTime updatedOn;

    private String updatedBy;

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    private Integer version;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public abstract <R extends UpdatableRecord> Table<R> table();

    public void batchInsert(List<? extends BaseModel> models) {
        Repositories.baseRepository().batchInsert(models);
    }

    public void insert(String createdBy) {
        setCreatedBy(createdBy);
        setUpdatedBy(createdBy);
        insert();
    }

    public void insert() {
        if(StringUtils.isEmpty(getId()))
            setId(UgaUtils.uuid());
        if(null == getCreatedOn())
            setCreatedOn(LocalDateTime.now());
        if(null == getUpdatedOn())
            setUpdatedOn(LocalDateTime.now());
        setVersion(0);
        Repositories.baseRepository().insert(this);
    }

    public void update(String updatedBy) {
        setUpdatedBy(updatedBy);
        update();
    }
    public void update() {
        setUpdatedOn(LocalDateTime.now());
        Repositories.baseRepository().update(this);
    }

    public void delete() {
        Repositories.baseRepository().delete(this);
    }

    public static <T extends BaseModel> T fetchEntry(Class<T> returnType, String id) {
        return Repositories.baseRepository().fetchEntry(returnType, id);
    }

}
