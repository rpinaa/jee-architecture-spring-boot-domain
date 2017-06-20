package org.example.seed.mapper;

import org.apache.ibatis.annotations.*;
import org.example.seed.domain.Telephone;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

/**
 * Created by PINA on 18/06/2017.
 */
@Mapper
@Repository
public interface TelephoneMapper {

    @Insert({"INSERT INTO telephone (id, name, number, lada, type, fk_id_chef)",
            "VALUES(",
                "id = #{id},",
                "name = #{name},",
                "number = #{number},",
                "lada = #{lada},",
                "type = #{type},",
                "fk_id_chef = #{fk}",
            ")"})
    @Options(useGeneratedKeys = true)
    int create(final Telephone telephone, final UUID fk);

    @Select("SELECT id, name, number, lada, type FROM telephone WHERE fk_id_chef = #{id}")
    Set<Telephone> findManyByChef(final UUID id);

    @Delete("DELETE FROM telephone WHERE id = #{id}")
    int delete(final UUID id);
}
