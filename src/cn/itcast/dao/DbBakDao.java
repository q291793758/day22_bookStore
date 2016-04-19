package cn.itcast.dao;

import cn.itcast.domain.Databackup;

import java.util.List;

public interface DbBakDao {

    boolean add(Databackup d);

    List getAll();
    Databackup findDatabackupById(String id);
}
