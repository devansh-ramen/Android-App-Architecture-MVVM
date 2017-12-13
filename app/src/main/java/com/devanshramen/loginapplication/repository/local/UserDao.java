package com.devanshramen.loginapplication.repository.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.devanshramen.loginapplication.repository.model.LoginResponse.User;

import java.util.List;

/**
 * Created by devanshramen on 11/19/17.
 */

public interface UserDao {
    //todo
    
    @Query("SELECT * FROM user LIMIT 1")
    LiveData<User> getUser();

    @Insert
    void insert(User user);

    @Delete
    void delete(User user);
}
