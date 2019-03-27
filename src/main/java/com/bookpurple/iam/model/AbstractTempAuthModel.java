package com.bookpurple.iam.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/*
 * Created by gauravsharma on 2019-03-09.
 */
@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractTempAuthModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @NotEmpty
    @Column(name = "mobile")
    private String mobile;

    @NotNull
    @NotBlank
    @NotEmpty
    @Column(name = "device_id")
    private String deviceId;

    @NotNull
    @NotBlank
    @NotEmpty
    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "otp")
    private String otp;

    @Column(name = "counter")
    private Integer counter;

    @Column(name = "expired_at")
    private Date expiredAt;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "modified_at")
    private Date modifiedAt;

    @Column(name = "status")
    private int status;
}
