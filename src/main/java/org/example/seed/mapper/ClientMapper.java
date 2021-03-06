package org.example.seed.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.RowBounds;
import org.example.seed.domain.Client;
import org.example.seed.event.client.CreateClientEvent;
import org.example.seed.event.client.DeleteClientEvent;
import org.example.seed.event.client.RequestClientEvent;
import org.example.seed.event.client.UpdateClientEvent;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.UUID;

/**
 * Created by PINA on 16/06/2017.
 */
@Mapper
@Component
public interface ClientMapper {

  @Insert({"INSERT INTO client (id, status, first_name, last_name, email, rating, create_date, change_date, deleted)",
    "VALUES(",
    "#{client.id},",
    "#{client.status},",
    "#{client.firstName},",
    "#{client.lastName},",
    "#{client.email},",
    "#{client.rating},",
    "CURRENT_TIMESTAMP,",
    "CURRENT_TIMESTAMP, 0",
    ")"
  })
  @Options(useGeneratedKeys = true)
  int create(final CreateClientEvent clientEvent);

  @Select("SELECT COUNT(*) FROM client WHERE deleted = 0")
  long count();

  @Select("SELECT * FROM client WHERE deleted = 0")
  @Results(value = {
    @Result(property = "telephone", column = "fk_id_telephone",
      one = @One(select = "org.example.seed.mapper.TelephoneMapper.findOne"))
  })
  Set<Client> findMany(final RowBounds rb);

  @Select("SELECT * FROM client WHERE id = #{id} AND deleted = 0")
  @Results(value = {
    @Result(property = "telephone", column = "fk_id_telephone",
      one = @One(select = "org.example.seed.mapper.TelephoneMapper.findOne"))
  })
  Client findOne(final RequestClientEvent clientEvent);

  @Select("SELECT client.fk_id_telephone FROM client WHERE id = #{id} AND deleted = 0")
  UUID findTelephoneUUID(final UUID id);

  @Update({"UPDATE client SET",
    "first_name = #{client.firstName},",
    "last_name = #{client.lastName},",
    "email = #{client.email},",
    "rating = #{client.rating},",
    "change_date = CURRENT_TIMESTAMP",
    "WHERE id = #{client.id}",
    "AND deleted = 0"
  })
  int update(final UpdateClientEvent clientEvent);

  @Update({"UPDATE client SET",
    "deleted = 1,",
    "change_date = CURRENT_TIMESTAMP",
    "WHERE id = #{id}"})
  int delete(final DeleteClientEvent clientEvent);
}
