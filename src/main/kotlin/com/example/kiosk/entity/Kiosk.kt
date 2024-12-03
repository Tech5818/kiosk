package com.example.kiosk.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "Kiosk")
class Kiosk(
    @Id
    @Column(name = "k_id", nullable = false, length = 36)
    var id: UUID,

    @Column(name = "k_number", nullable = false, length = 2, unique = true)
    var number: Int
)