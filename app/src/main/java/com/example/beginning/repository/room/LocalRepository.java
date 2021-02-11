package com.example.beginning.repository.room;

import android.content.Context;

import androidx.paging.DataSource;

import com.example.beginning.Model.User;
import com.example.beginning.repository.room.room.UserDao;
import com.example.beginning.repository.room.room.UserDatabase;

import java.util.List;

import io.reactivex.Completable;

public class LocalRepository {

    private UserDao userDao;

    public LocalRepository(Context ctx) {
        userDao = UserDatabase.getInstance(ctx).userDao();
    }


    public Completable addUser(User user) {
        return userDao.addUser(user);
    }

    public Completable deleteUser(User user) {
        return userDao.deleteUser(user);
    }

    public Completable updateUser(User user) {
        return userDao.updateUser(user);
    }


    public DataSource.Factory<Integer, User> getAllUser() {
        return userDao.getAllUser();
    }

    public DataSource.Factory<Integer, User> queryAllUser(String query) {
        return userDao.queryAllUser(query);
    }

    public Completable deleteListOfUsers(List<User> userArrayList) {
        return userDao.deleteListOfUsers(userArrayList);
    }


}

