package com.sadcrow.auth_service.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.*;

@Entity
@Table(name = "user_data", indexes = @Index(name = "idx_user_data_01", columnList = "userName"))
@Data
@NoArgsConstructor
//cache user table

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    private String firstName;
    @Column(length = 50)
    private String lastName;
    @Email
    @NotBlank(message ="email can not be empty")
    private String email;

    @Column(length = 50)
    @NotBlank(message = "user name can not be empty")
    private String userName;

    @JsonIgnore
    @NotBlank(message = "password can not be empty")
    @Min(value = 4, message = "password can not be less than 4")
    private String password;

    @ManyToMany
    @JoinTable(name = "user_dealer", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "dealer_id"))
    private List<Dealer> myDealers = new ArrayList<>();

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "role_name", length = 20)
    private List<Role> roles = new ArrayList<>();

    private Boolean active = false;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    public void addRole(Role role){
        this.roles.add(role);
    }

    public void addRoleList(List<Role> roles){
        this.roles.addAll(roles);
    }
}
