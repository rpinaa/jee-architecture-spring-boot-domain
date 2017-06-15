package org.example.seed.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.example.seed.event.chef.CreateChefEvent;
import org.springframework.stereotype.Repository;

/**
 * Created by PINA on 15/06/2017.
 */
@Mapper
@Repository
public interface AccountMapper {

    @Insert("INSERT INTO account" +
            "(id, first_name, last_name, email, secret, deleted, create_date, change_date)" +
            "VALUES(#{chef.account.id}, #{chef.account.firstName}, #{chef.account.lastName}, " +
            "#{chef.account.email}, NULL, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)")
    @Options(useGeneratedKeys = true)
    int createAccount(final CreateChefEvent createChefEvent);
}
