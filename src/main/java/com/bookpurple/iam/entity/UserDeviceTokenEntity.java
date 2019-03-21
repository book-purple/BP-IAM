package com.bookpurple.iam.entity;

import com.bookpurple.iam.model.AbstractUserDeviceTokenModel;

import javax.persistence.Entity;
import javax.persistence.Table;

/*
 * Created by Gaurav Sharma on 21 Mar 2019
 */
@Entity
@Table(name = "user_device_token")
public class UserDeviceTokenEntity extends AbstractUserDeviceTokenModel {
}
