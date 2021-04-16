package com.example.gitrepositorystars;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import java.util.Date;
import java.util.List;

@Dao
public abstract class AbstractBaseEntityDao<T extends Note> {

    @Insert
    public abstract long actualInsert(T t);

    public long insert(T t) {
        t.setCreatedAt(new Date());
        t.setUpdatedAt(new Date());
        return actualInsert(t);
    }

    @Insert
    public abstract List<Long> actualInsertAll(List<T> ts);

    public List<Long> insertAll(List<T> ts) {
        if (ts != null) {
            for (T t : ts) {
                t.setCreatedAt(new Date());
                t.setUpdatedAt(new Date());
            }
        }
        return actualInsertAll(ts);
    }

    @Update
    public abstract void actualUpdate(T t);

    public void update(T t) {
        t.setCreatedAt(new Date());
        actualUpdate(t);
    }

    @Update
    public abstract void actualUpdateAll(List<T> ts);

    public void updateAll(List<T> ts) {
        if (ts != null) {
            for (T t : ts) {
                t.setCreatedAt(new Date());
            }
        }
        actualUpdateAll(ts);
    }

    @Delete
    public abstract void delete(T t);

    @Delete
    public abstract void deleteAll(List<T> ts);
}