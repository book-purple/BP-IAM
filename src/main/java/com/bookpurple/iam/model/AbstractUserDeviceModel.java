package com.bookpurple.iam.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/*
 * Created by gauravsharma on 2019-03-10.
 */
@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractUserDeviceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @NotEmpty
    @Column(name = "device_id")
    private String deviceId;

    @NotNull
    @Column(name = "user_id")
    private Long userId;

    @NotNull
    @NotBlank
    @NotEmpty
    @Column(name = "user_uid")
    private String userUId;

    @Column(name = "mobile_version")
    private String mobileVersion;

    @Column(name = "app_version")
    private String appVersion;

    @Column(name = "app_version_code")
    private String appVersionCode;

    @Column(name = "device_type")
    private String deviceType;

    @Column(name = "os_version")
    private String osVersion;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "modified_at")
    private Date modifiedAt;

    @Column(name = "status")
    private int status;
}
