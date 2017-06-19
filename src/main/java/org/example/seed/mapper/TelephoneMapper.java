package org.example.seed.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
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
    int createTelephone(Telephone telephone, final UUID fk);

    @Select("SELECT * FROM telephone WHERE telephone.fk_id_chef = #{id}")
    Set<Telephone> findTelephonesByChef(final UUID id);

    default void mergeTelephones(final Set<Telephone> telephones, final UUID id) {
        this.findTelephonesByChef(id)
                .parallelStream()
                .filter(t -> !telephones.contains(t))
                .forEach(t -> this.createTelephone(t, id));
    }
}