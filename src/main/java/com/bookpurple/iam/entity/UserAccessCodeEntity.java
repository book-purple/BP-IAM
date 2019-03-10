package com.bookpurple.iam.entity;

import com.bookpurple.iam.model.AbstractUserAccessCodeModel;

import javax.persistence.Entity;
import javax.persistence.Table;

/*
 * Created by gauravsharma on 2019-03-10.
 */
@Entity
@Table(name = "user_access_code")
public class UserAccessCodeEntity extends AbstractUserAccessCodeModel {
}
