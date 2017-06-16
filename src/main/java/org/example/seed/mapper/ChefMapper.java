package org.example.seed.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.RowBounds;
import org.example.seed.domain.Account;
import org.example.seed.domain.Chef;
import org.example.seed.event.chef.CreateChefEvent;
import org.example.seed.event.chef.DeleteChefEvent;
import org.example.seed.event.chef.RequestChefEvent;
import org.example.seed.event.chef.UpdateChefEvent;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Created by PINA on 15/06/2017.
 */
@Mapper
@Repository
public interface ChefMapper {

    @Insert("INSERT INTO chef" +
            "(id, status, rfc, curp, rating, create_date, change_date, deleted, fk_id_account)" +
            "VALUES(#{chef.id}, #{chef.status}, #{chef.rfc}, #{chef.curp}, #{chef.rating}," +
            "CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0, #{chef.account.id})")
    @Options(useGeneratedKeys = true)
    int createChef(final CreateChefEvent chefEvent);

    @Select("SELECT COUNT(*) FROM chef")
    long countChefs();

    @Select("SELECT * FROM chef")
    @Results(value = {
            @Result(property = "account", column = "fk_id_account", one = @One(select = "findAccount"))
    })
    Set<Chef> findChefs(final RowBounds rb);

    @Select("SELECT * FROM chef WHERE chef.id = #{id}")
    @Results(value = {
            @Result(property = "account", column = "fk_id_account", one = @One(select = "findAccount"))
    })
    Chef findChef(final RequestChefEvent chefEvent);

    @Select("SELECT * FROM account WHERE account.id = #{id}")
    Account findAccount(final String id);

    @Update("UPDATE chef SET" +
            "rfc = #{chef.rfc}," +
            "curp = #{chef.curp}," +
            "change_date = CURRENT_TIMESTAMP" +
            "WHERE chef.id = #{chef.id}")
    int updateChef(final UpdateChefEvent chefEvent);

    @Update("UPDATE chef SET deleted = 1, change_date = CURRENT_TIMESTAMP WHERE chef.id = #{id}")
    int deleteChef(final DeleteChefEvent chefEvent);
}
