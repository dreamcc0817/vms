package com.dreamcc.application.iot.command;

import lombok.Data;

/**
 * @author cloud-cc
 * @ClassName CreateProfileCommand
 * @Description 创建模板命令
 * @date 2021/12/7 16:13
 * @Version 1.0
 */
@Data
public class CreateProfileCommand {

    private String name;

    private String description;
}
