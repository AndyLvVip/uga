package uga.framework.db;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: andy.lv
 * @Date: created on 2017/11/24
 * @Description:
 */
public class DbArrayList<T> {
    private int pageNo;
    private int pageSize;
    private int pageCount;
    private int recordCount;
    private List<T> data = new ArrayList<>();

    /**
     * Added by andy.lv on 20171124
     * @param data
     * @param pageNo
     * @param pageSize
     * @param recordCount
     * @param <T>
     * @return
     */
    public static <T> DbArrayList<T> newInstance(List<T> data, int pageNo, int pageSize, int recordCount) {
        DbArrayList<T> list = new DbArrayList<>();
        list.setPageNo(pageNo);
        list.setPageSize(pageSize);
        list.setRecordCount(recordCount);
        list.setPageCount((int)Math.ceil(1.0 * recordCount / pageSize));
        list.setData(data);
        return list;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
