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
 * Created by Gaurav Sharma on 21 Mar 2019
 */
@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractUserDeviceTokenModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @NotEmpty
    @Column(name = "device_id")
    private String deviceId;

    @NotNull
    @NotBlank
    @NotEmpty
    @Column(name = "user_id")
    private Long userId;

    @NotNull
    @NotBlank
    @NotEmpty
    @Column(name = "user_uid")
    private String userUId;

    @NotNull
    @NotBlank
    @NotEmpty
    @Column(name = "device_token")
    private String deviceToken;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "modified_at")
    private Date modifiedAt;

    @Column(name = "install_status")
    private int installStatus;
}
