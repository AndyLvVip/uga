package uga.framework.db.jooq.converter;

import org.jooq.Converter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @Author: andy.lv
 * @Date: created on 2017/11/24
 * @Description:
 */
public class JooqDateConverter implements Converter<Timestamp, LocalDateTime> {

    @Override
    public LocalDateTime from(Timestamp databaseObject) {
        return null == databaseObject ? null : databaseObject.toLocalDateTime();
    }

    @Override
    public Timestamp to(LocalDateTime userObject) {
        return null == userObject ? null : Timestamp.valueOf(userObject);
    }

    @Override
    public Class<Timestamp> fromType() {
        return Timestamp.class;
    }

    @Override
    public Class<LocalDateTime> toType() {
        return LocalDateTime.class;
    }
}
