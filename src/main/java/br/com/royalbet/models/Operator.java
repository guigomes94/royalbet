package br.com.royalbet.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "OPERATOR")
public class Operator extends User {

}
