package com.example.kiosk.repository

import com.example.kiosk.entity.Order
import com.example.kiosk.entity.dto.OrderDTO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface OrderRepository: JpaRepository<Order, Long> {
    @Query("""
        SELECT * FROM Orders WHERE k_number = :tableNumber AND o_state = 'PENDING'
    """, nativeQuery = true)
    fun pendingOrder(tableNumber: Int): Order?
}