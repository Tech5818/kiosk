package com.example.kiosk.controller

import com.example.kiosk.entity.dto.CreateOrderMenuDTO
import com.example.kiosk.entity.dto.OrderDTO
import com.example.kiosk.entity.dto.PaymentDTO
import com.example.kiosk.service.OrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/order")
class OrderController(@Autowired val orderService: OrderService) {

    @PostMapping("/{tableId}")
    fun newOrder(
        @PathVariable tableId: Int,
        @RequestBody dto: List<CreateOrderMenuDTO>
    ): OrderDTO {
      val order = orderService.order(tableId, dto)

      return order
    }

    @GetMapping("")
    fun findAllOrder() : List<OrderDTO> = orderService.findAllOrder()

    @GetMapping("/{orderId}")
    fun findByOrderId(@PathVariable orderId: Long) : OrderDTO = orderService.findByOrderId(orderId)

    @GetMapping("/table/{tableNumber}")
    fun findByTableNumberPendingOrder(@PathVariable tableNumber: Int): OrderDTO = orderService.findByTableNumberPendingOrder(tableNumber)

    @PatchMapping("/{tableNumber}")
    fun additionalOrder(
        @PathVariable tableNumber: Int,
        @RequestBody dto: List<CreateOrderMenuDTO>
    ): OrderDTO = orderService.additionalOrder(tableNumber, dto)

    @GetMapping("/payment/{tableNumber}")
    fun payment(@PathVariable tableNumber: Int): PaymentDTO = orderService.payment(tableNumber)
}