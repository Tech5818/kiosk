package com.example.kiosk.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name = "Order_Menu")
class OrderMenu (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "om_id")
    var id: Long? = null,

    @Column(name = "om_quantity", nullable = false)
    var quantity: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "o_id")
    var order: Order,

    @ManyToOne
    @JoinColumn(name = "m_id")
    @JsonIgnore
    var menu: Menu
)