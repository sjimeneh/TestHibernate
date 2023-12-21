package models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Basic;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Setter
@Getter
@AllArgsConstructor @NoArgsConstructor
@ToString
public class user {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Basic
    private String name;
    @Basic
    private String lastName;
    @Basic
    private int age;

}
