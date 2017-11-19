package com.devanshramen.loginapplication.repository.local;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.devanshramen.loginapplication.repository.model.LoginResponse;

import java.util.List;

/**
 * Created by devanshramen on 11/19/17.
 */

public interface UserDao {

    @Query("SELECT * FROM user")
    List<LoginResponse.User> getAll();

    @Query("SELECT * FROM user WHERE id IN (:userIds)")
    List<LoginResponse.User> loadAllByIds(int[] userIds);

    @Insert
    void insertAll(LoginResponse.User... users);

    @Delete
    void delete(LoginResponse.User user);
}
