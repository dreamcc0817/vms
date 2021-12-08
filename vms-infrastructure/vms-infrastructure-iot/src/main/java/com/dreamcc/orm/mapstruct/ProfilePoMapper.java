package com.dreamcc.orm.mapstruct;


import com.dreamcc.application.iot.dto.ProfileDTO;
import com.dreamcc.domain.iot.domain.aggregate.profile.Profile;
import com.dreamcc.orm.dataobject.ProfileDO;
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
     * do转dto
     *
     * @param profileDO do
     * @return entity
     */
    ProfileDTO doToDto(ProfileDO profileDO);

    /**
     * entity转do
     *
     * @param profile entity
     * @return do
     */
    ProfileDO profileToPo(Profile profile);

    /**
     * do 转 entity
     *
     * @param profileDO do
     * @return entity
     */
    Profile poToProfile(ProfileDO profileDO);
}
