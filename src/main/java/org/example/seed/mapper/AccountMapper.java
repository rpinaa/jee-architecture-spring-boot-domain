package org.example.seed.mapper;

import org.apache.ibatis.annotations.*;
import org.example.seed.domain.Account;
import org.example.seed.event.account.UpdateAccountEvent;
import org.example.seed.event.chef.CreateChefEvent;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Created by PINA on 15/06/2017.
 */
@Mapper
@Repository
public interface AccountMapper {

    @Insert("INSERT INTO account (id, first_name, last_name, email, secret, deleted, create_date, change_date)" +
            "VALUES(" +
                "#{chef.account.id}," +
                "#{chef.account.firstName}," +
                "#{chef.account.lastName}," +
                "#{chef.account.email}," +
                "NULL, 0," +
                "CURRENT_TIMESTAMP," +
                "CURRENT_TIMESTAMP" +
            ")")
    @Options(useGeneratedKeys = true)
    int createAccount(final CreateChefEvent chefEvent);

    @Select("SELECT * FROM account WHERE account.id = #{id} AND deleted = 0")
    Account findAccount(final String id);

    @Update("UPDATE account SET" +
                "first_name = #{chef.account.firstName}," +
                "last_name = #{chef.account.lastName}," +
                "email = #{chef.account.email}," +
                "change_date = CURRENT_TIMESTAMP" +
            "WHERE account.id = #{chef.account.id}" +
            "AND deleted = 0")
    int updateAccount(final UpdateAccountEvent accountEvent);

    @Update("UPDATE account SET" +
                "deleted = 1," +
                "change_date = CURRENT_TIMESTAMP" +
            "WHERE account.id = #{id}")
    int deleteAccount(final UUID id);
}
