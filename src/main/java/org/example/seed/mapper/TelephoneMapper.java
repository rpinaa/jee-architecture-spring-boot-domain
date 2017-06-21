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
                "#{telephone.id},",
                "#{telephone.name},",
                "#{telephone.number},",
                "#{telephone.lada},",
                "#{telephone.type},",
                "#{fk}",
            ")"})
    @Options(useGeneratedKeys = true)
    int create(@Param("telephone") final Telephone telephone, @Param("fk") final UUID fk);

    @Select("SELECT id, name, number, lada, type FROM telephone WHERE fk_id_chef = #{id}")
    Set<Telephone> findManyByChef(final UUID id);

    @Delete("DELETE FROM telephone WHERE id = #{id}")
    int delete(final UUID id);
}
