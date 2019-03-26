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
public abstract class AbstractUserAccessCodeModel {

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

    @NotNull
    @NotBlank
    @NotEmpty
    @Column(name = "auth_token")
    private String authToken;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "modified_at")
    private Date modifiedAt;

    @Column(name = "expired_at")
    private Date expiredAt;

    @Column(name = "status")
    private int status;
}
