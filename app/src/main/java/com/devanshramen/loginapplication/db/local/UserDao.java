package com.devanshramen.loginapplication.db.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.devanshramen.loginapplication.model.LoginResponse.User;

/**
 * Created by devanshramen on 11/19/17.
 */

@Dao
public interface UserDao {
    
    @Query("SELECT * FROM user LIMIT 1")
    LiveData<User> getUser();

    @Insert
    (onConflict = OnConflictStrategy.REPLACE)
    void insert(User user);

    @Delete
    void delete(User user);

    @Query("DELETE FROM user")
    void deleteAll();
}
