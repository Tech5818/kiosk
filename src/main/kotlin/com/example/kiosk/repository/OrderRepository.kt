package com.example.kiosk.repository

import com.example.kiosk.entity.Order
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository: JpaRepository<Order, Long> {
}