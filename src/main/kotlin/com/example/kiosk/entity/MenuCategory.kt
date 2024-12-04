package com.example.kiosk.entity

import com.example.kiosk.entity.dto.CategoryDTO
import jakarta.persistence.*
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.DynamicInsert

@Entity
@Table(name = "Menu_Category")
@DynamicInsert
class MenuCategory(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mc_id", nullable = false)
    var id: Long? = 0,

    @Column(name = "mc_name", nullable = false, unique = true)
    var name: String,

    @Column(name = "mc_is_special")
    @ColumnDefault("0")
    var isSpecial: Boolean,

    @OneToMany(mappedBy = "category")
    var menu: MutableSet<Menu> = mutableSetOf(),
)

fun MenuCategory.toDTO(): CategoryDTO = CategoryDTO(id, name, isSpecial)