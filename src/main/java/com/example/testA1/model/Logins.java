package com.example.testA1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "logins")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Logins {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "application")
    private String application;
    @Column(name = "app_account_name")
    private String appAccountName;
    @Column(name = "is_active")
    private String isActive;
    @Column(name = "job_title")
    private String jobTitle;
    @Column(name = "department")
    private String Department;
}
