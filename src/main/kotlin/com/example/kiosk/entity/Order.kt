package com.example.kiosk.entity

import jakarta.persistence.*
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.DynamicInsert

@Entity
@Table(name = "Orders")
@DynamicInsert
class Order (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "o_id", nullable = false)
    var id: Long,

    @Enumerated(value = EnumType.STRING)
    @Column(name = "o_state")
    @ColumnDefault("'PENDING'")
    var state: OrderState,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "k_number", referencedColumnName = "k_number", nullable = false)
    var table: Kiosk,

    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL])
    var orderMenus: MutableList<OrderMenu> = mutableListOf(),
)
