package com.example.kiosk.entity

import com.example.kiosk.entity.dto.MenuDTO
import jakarta.persistence.*
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.DynamicInsert

@Entity
@Table(name = "Menu")
@DynamicInsert
class Menu (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "m_id", nullable = false)
    var id: Long?,

    @Column(name = "m_name", nullable = false, length = 30)
    var name: String,

    @Column(name = "m_img_path", nullable = false, length = 100)
    var imgPath: String,

    @Column(name = "m_price", nullable = false, length = 30)
    var price: Long,

    @Enumerated(EnumType.STRING)
    @Column(name = "m_type", nullable = false)
    var type: MenuType,

    @Column(name = "m_discount", length = 3)
    @ColumnDefault("0")
    var discount: Int = 0,

    @Column(name = "m_calories")
    @ColumnDefault("0")
    var calories: Double = 0.0,

    @ManyToOne
    @JoinColumn(name = "mc_id")
    var category: MenuCategory,
    )

fun Menu.toDto(): MenuDTO = MenuDTO(
        this.id,
        this.name,
        this.imgPath,
        this.price,
        this.type,
        this.discount,
        this.calories,
        this.category
    )