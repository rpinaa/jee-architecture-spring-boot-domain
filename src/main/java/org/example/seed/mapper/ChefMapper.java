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

    @Insert({"INSERT INTO chef (id, status, rfc, curp, rating, create_date, change_date, deleted, fk_id_account)",
            "VALUES(",
                "#{chef.id},",
                "#{chef.status},",
                "#{chef.rfc},",
                "#{chef.curp},",
                "#{chef.rating},",
                "CURRENT_TIMESTAMP,",
                "CURRENT_TIMESTAMP, 0,",
                "#{chef.account.id}",
            ")"
    })
    @Options(useGeneratedKeys = true)
    int create(final CreateChefEvent chefEvent);

    @Select("SELECT COUNT(*) FROM chef WHERE deleted = 0")
    long count();

    @Select("SELECT * FROM chef WHERE deleted = 0")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "account", column = "fk_id_account",
                    one = @One(select = "org.example.seed.mapper.AccountMapper.findOne")),
            @Result(property = "telephones", column = "id",
                    many = @Many(select = "org.example.seed.mapper.TelephoneMapper.findManyByChef"))
    })
    Set<Chef> findMany(final RowBounds rb);

    @Select("SELECT * FROM chef WHERE id = #{id} AND deleted = 0")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "account", column = "fk_id_account",
                one = @One(select = "org.example.seed.mapper.AccountMapper.findOne")),
            @Result(property = "telephones", column = "id",
                many = @Many(select = "org.example.seed.mapper.TelephoneMapper.findManyByChef"))
    })
    Chef findOne(final RequestChefEvent chefEvent);

    @Select("SELECT chef.fk_id_account FROM chef WHERE id = #{id} AND deleted = 0")
    UUID findAccountUUID(final UUID id);

    @Update({"UPDATE chef SET",
            "rfc = #{chef.rfc},",
            "curp = #{chef.curp},",
            "change_date = CURRENT_TIMESTAMP",
            "WHERE id = #{chef.id} ",
            "AND deleted = 0"})
    int update(final UpdateChefEvent chefEvent);

    @Update({"UPDATE chef SET",
            "deleted = 1,",
            "change_date = CURRENT_TIMESTAMP",
            "WHERE id = #{id}"})
    int delete(final DeleteChefEvent chefEvent);
}
