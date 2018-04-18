package uga.framework.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: andy.lv
 * @Date: created on 2018/3/6
 * @Description:
 */
@Component
public class Repositories {

    private static BaseRepository baseRepository;
    @Autowired
    public void setBaseRepository(BaseRepository repository) {
        baseRepository = repository;
    }

    public static BaseRepository baseRepository() {
        return baseRepository;
    }
}
