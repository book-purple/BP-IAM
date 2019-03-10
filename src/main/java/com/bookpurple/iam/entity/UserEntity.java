package com.bookpurple.iam.entity;

import com.bookpurple.iam.model.AbstractUserModel;

import javax.persistence.Entity;
import javax.persistence.Table;

/*
 * Created by gauravsharma on 2019-03-10.
 */
@Entity
@Table(name = "user")
public class UserEntity extends AbstractUserModel {
}
