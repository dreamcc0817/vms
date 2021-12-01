package com.dreamcc.orm.mapstruct;


import com.dreamcc.domain.iot.domain.Profile;
import com.dreamcc.orm.po.ProfilePO;
import org.mapstruct.Mapper;

/**
 * @author cloud-cc
 * @ClassName ProfilePoMapper
 * @Description 转换类
 * @date 2021/12/1 10:00
 * @Version 1.0
 */
@Mapper(componentModel = "spring")
public interface ProfilePoMapper {

    /**
     * dto转entity
     *
     * @param profilePO po
     * @return entity
     */
//    @Mappings({
//            @Mapping(target = "pointIds",ignore = true)
//    })
    Profile poToProfile(ProfilePO profilePO);


    /**
     * entity转po
     *
     * @param profile entity
     * @return po
     */
//    @Mappings({
//            @Mapping(target = "pointIds",ignore = true)
//    })
    ProfilePO profileToPo(Profile profile);
}
