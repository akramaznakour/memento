package com.aghmat.memento.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.aghmat.memento.domain.model.Account;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface AccountDao {

    @Query("SELECT * from account LIMIT 1")
    Single<Account> getAccount();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable create(Account account);

}
