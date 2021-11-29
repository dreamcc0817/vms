package com.dreamcc.api.iot.mapstruct;


import com.dreamcc.api.iot.dto.ProfileDTO;
import com.dreamcc.domain.iot.domain.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author cloud-cc
 * @ClassName ProfileMap
 * @Description 转换类
 * @date 2021/11/27 14:55
 * @Version 1.0
 */
@Mapper(componentModel = "spring")
public interface ProfileMap {

    /**
     * dto转entity
     *
     * @param profileDTO dto
     * @return entity
     */
    @Mappings({
            @Mapping(source = "profileId", target = "id"),
            @Mapping(source = "profileName", target = "name"),
            @Mapping(source = "profileDesc", target = "description")
    })
    Profile dtoToProfile(ProfileDTO profileDTO);

    /**
     * entity转dto
     *
     * @param profile entity
     * @return dto
     */
    @Mapping(source = "id", target = "profileId")
    @Mapping(source = "name", target = "profileName")
    @Mapping(source = "description", target = "profileDesc")
    ProfileDTO profileToDto(Profile profile);
}
