package org.example.seed.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.seed.domain.Telephone;
import org.example.seed.event.chef.UpdateChefEvent;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

/**
 * Created by PINA on 18/06/2017.
 */
@Mapper
@Repository
public interface TelephoneMapper {

    @Insert({"<script>",
            "INSERT INTO telephone (id, name, number, lada, type, fk_id_chef)",
            "<foreach collection='telephones' item='telephone' index='index' open='(' separator = '),(' close=')' >" +
                "id = #{id},",
                "name = #{name},",
                "number = #{number},",
                "lada = #{lada},",
                "type = #{type},",
                "fk_id_chef = #{fk}",
            "</foreach>",
            "</script>"})
    int createTelephones(@Param("telephones") final Set<Telephone> telephones, final UUID fk);

    @Select("SELECT * FROM telephone WHERE telephone.fk_id_chef = #{id}")
    Set<Telephone> findTelephonesByChef(final UpdateChefEvent chefEvent);
}
