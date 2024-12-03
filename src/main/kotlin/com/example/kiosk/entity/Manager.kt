package com.example.kiosk.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "Manager")
class Manager (
    @Id
    @Column(name = "mng_id", nullable = false, length = 10)
    var id: String,

    @Column(name = "mng_password", nullable = false, length = 18)
    var password: String,
)