package com.bookpurple.iam.entity;

import com.bookpurple.iam.model.AbstractTempAuthModel;

import javax.persistence.Entity;
import javax.persistence.Table;

/*
 * Created by gauravsharma on 2019-03-10.
 */
@Entity
@Table(name = "temp_auth")
public class TempAuthEntity extends AbstractTempAuthModel {

}
