package org.example.seed.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.RowBounds;
import org.example.seed.domain.Client;
import org.example.seed.event.client.CreateClientEvent;
import org.example.seed.event.client.DeleteClientEvent;
import org.example.seed.event.client.RequestClientEvent;
import org.example.seed.event.client.UpdateClientEvent;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Created by PINA on 16/06/2017.
 */
@Mapper
@Repository
public interface ClientMapper {

    @Insert("INSERT INTO client (id, status, first_name, last_name, email, rating, create_date, change_date, deleted)" +
            "VALUES(" +
                "#{client.id}," +
                "#{client.status}," +
                "#{client.firstName}," +
                "#{client.lastName}," +
                "#{client.email}," +
                "#{client.rating}," +
                "CURRENT_TIMESTAMP," +
                "CURRENT_TIMESTAMP, 0" +
            ")")
    @Options(useGeneratedKeys = true)
    int create(final CreateClientEvent clientEvent);

    @Select("SELECT COUNT(*) FROM client WHERE deleted = 0")
    long count();

    @Select("SELECT * FROM client WHERE deleted = 0")
    Set<Client> findMany(final RowBounds rb);

    @Select("SELECT * FROM client WHERE id = #{id} AND deleted = 0")
    Client findOne(final RequestClientEvent clientEvent);

    @Update("UPDATE client SET" +
                "first_name = #{client.firstName}," +
                "last_name = #{client.lastName}," +
                "email = #{client.email}," +
                "rating = #{client.rating}," +
                "change_date = CURRENT_TIMESTAMP" +
            "WHERE id = #{client.id}" +
            "AND deleted = 0")
    int update(final UpdateClientEvent clientEvent);

    @Update("UPDATE client SET" +
                "deleted = 1," +
                "change_date = CURRENT_TIMESTAMP" +
            "WHERE id = #{id}")
    int delete(final DeleteClientEvent clientEvent);
}
