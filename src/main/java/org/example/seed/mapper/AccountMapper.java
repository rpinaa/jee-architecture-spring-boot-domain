package org.example.seed.mapper;

import org.apache.ibatis.annotations.*;
import org.example.seed.domain.Account;
import org.example.seed.event.account.CreateAccountEvent;
import org.example.seed.event.account.UpdateAccountEvent;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Created by PINA on 15/06/2017.
 */
@Mapper
@Component
public interface AccountMapper {

  @Insert({"INSERT INTO account (id, first_name, last_name, email, secret, deleted, create_date, change_date)",
    "VALUES(",
    "#{chef.account.id},",
    "#{chef.account.firstName},",
    "#{chef.account.lastName},",
    "#{chef.account.email},",
    "NULL, 0,",
    "CURRENT_TIMESTAMP,",
    "CURRENT_TIMESTAMP",
    ")"
  })
  @Options(useGeneratedKeys = true)
  int create(final CreateAccountEvent chefEvent);

  @Select("SELECT * FROM account WHERE id = #{id} AND deleted = 0")
  Account findOne(final String id);

  @Update({"UPDATE account SET",
    "first_name = #{chef.account.firstName},",
    "last_name = #{chef.account.lastName},",
    "email = #{chef.account.email},",
    "change_date = CURRENT_TIMESTAMP",
    "WHERE id = #{chef.account.id} ",
    "AND deleted = 0"
  })
  int update(final UpdateAccountEvent accountEvent);

  @Update({"UPDATE account SET",
    "deleted = 1,",
    "change_date = CURRENT_TIMESTAMP",
    "WHERE id = #{id}"
  })
  int delete(final UUID id);
}
