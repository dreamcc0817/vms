package com.dreamcc.application.iot.mapstruct;


import com.dreamcc.application.iot.dto.ProfileDTO;
import com.dreamcc.application.iot.query.ProfileQuery;
import com.dreamcc.domain.iot.domain.aggregate.profile.Profile;
import org.mapstruct.Mapper;

/**
 * @author cloud-cc
 * @ClassName ProfileMap
 * @Description 转换类
 * @date 2021/11/27 14:55
 * @Version 1.0
 */
@Mapper(componentModel = "spring")
public interface ProfileMapper {

    /**
     * entity转dto
     *
     * @param profile entity
     * @return dto
     */
    ProfileDTO profileToDto(Profile profile);

    /**
     * 查询条件转entity
     *
     * @param profileQuery 查询条件
     * @return entity
     */
    Profile queryToProfile(ProfileQuery profileQuery);
}
