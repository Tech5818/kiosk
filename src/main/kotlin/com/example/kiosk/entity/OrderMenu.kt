package com.example.kiosk.entity

import jakarta.persistence.*

@Entity
@Table(name = "Order_Menu")
class OrderMenu (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "om_id")
    var id: Long,

    @Column(name = "om_quantity", nullable = false)
    var quantity: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "o_id")
    var order: Order,

    @ManyToOne
    @JoinColumn(name = "m_id")
    var menu: Menu
)