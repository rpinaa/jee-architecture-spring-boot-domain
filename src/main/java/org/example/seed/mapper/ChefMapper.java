package org.example.seed.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.RowBounds;
import org.example.seed.domain.Chef;
import org.example.seed.event.chef.CreateChefEvent;
import org.example.seed.event.chef.DeleteChefEvent;
import org.example.seed.event.chef.RequestChefEvent;
import org.example.seed.event.chef.UpdateChefEvent;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

/**
 * Created by PINA on 15/06/2017.
 */
@Mapper
@Repository
public interface ChefMapper {

    @Insert("INSERT INTO chef (id, status, rfc, curp, rating, create_date, change_date, deleted, fk_id_account)" +
            "VALUES(#{chef.id}, #{chef.status}, #{chef.rfc}, #{chef.curp}, #{chef.rating}, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0, #{chef.account.id})")
    @Options(useGeneratedKeys = true)
    int createChef(final CreateChefEvent chefEvent);

    @Select("SELECT COUNT(*) FROM chef WHERE deleted = 0")
    long countChefs();

    @Select("SELECT * FROM chef WHERE deleted = 0")
    @Results(value = {
            @Result(property = "account", column = "fk_id_account", one = @One(select = "org.example.seed.mapper.AccountMapper.findAccount"))
    })
    Set<Chef> findChefs(final RowBounds rb);

    @Select("SELECT * FROM chef WHERE chef.id = #{id} AND deleted = 0")
    @Results(value = {
            @Result(property = "account", column = "fk_id_account", one = @One(select = "org.example.seed.mapper.AccountMapper.findAccount"))
    })
    Chef findChef(final RequestChefEvent chefEvent);

    @Select("SELECT chef.fk_id_account FROM chef WHERE chef.id = #{id} AND deleted = 0")
    UUID findAccountUUID(final UUID id);

    @Update("UPDATE chef SET rfc = #{chef.rfc}, curp = #{chef.curp}, change_date = CURRENT_TIMESTAMP WHERE chef.id = #{chef.id} AND deleted = 0")
    int updateChef(final UpdateChefEvent chefEvent);

    @Update("UPDATE chef SET deleted = 1, change_date = CURRENT_TIMESTAMP WHERE chef.id = #{id}")
    int deleteChef(final DeleteChefEvent chefEvent);
}
