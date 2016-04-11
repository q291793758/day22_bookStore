package cn.itcast.domain;

import java.util.List;
//分页:查询结果
public class QueryResult {
    private List list;
    private int  totalRecord;

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }
}
