package com.example.kiosk.service

import com.example.kiosk.entity.Order
import com.example.kiosk.entity.OrderMenu
import com.example.kiosk.entity.OrderState
import com.example.kiosk.entity.dto.CreateOrderMenuDTO
import com.example.kiosk.entity.dto.OrderDTO
import com.example.kiosk.entity.dto.PaymentDTO
import com.example.kiosk.entity.toDto
import com.example.kiosk.exception.CustomException
import com.example.kiosk.exception.ErrorCode
import com.example.kiosk.repository.KioskRepository
import com.example.kiosk.repository.MenuRepository
import com.example.kiosk.repository.OrderRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class OrderService(
    @Autowired val orderRepository: OrderRepository,
    @Autowired val kioskRepository: KioskRepository,
    @Autowired val menuRepository: MenuRepository,
) {
    private fun addAllOrderMenus(order: Order, orderMenusDTO: List<CreateOrderMenuDTO>): Order {
        val orderMenus = orderMenusDTO.map {
            val menu = menuRepository.findByIdOrNull(it.menuId)
                ?: throw CustomException(ErrorCode.BAD_REQUEST)

            OrderMenu(
                quantity = it.quantity,
                order = order,
                menu = menu
            )
        }

        order.orderMenus.addAll(orderMenus)

        return order
    }

    fun order(tableNumber: Int, orderMenusDTO: List<CreateOrderMenuDTO>): OrderDTO {
        orderRepository.pendingOrder(tableNumber)?.let{
            throw CustomException(ErrorCode.BAD_REQUEST, "$tableNumber already has order")
        }

        val kiosk = kioskRepository.findByNumber(tableNumber)
            ?: throw CustomException(ErrorCode.BAD_REQUEST)

        val order = Order(table = kiosk)

        val addAllMenu = addAllOrderMenus(order, orderMenusDTO)

        val saveOrder = orderRepository.save(addAllMenu)

        return saveOrder.toDto()
    }

    fun findAllOrder() : List<OrderDTO> = orderRepository.findAll().map { it.toDto() }

    fun findByOrderId(id: Long): OrderDTO = orderRepository.findByIdOrNull(id)?.toDto()
        ?: throw CustomException(ErrorCode.BAD_REQUEST, "order ID: $id is not exists")

    fun findByTableNumberPendingOrder(tableNumber: Int): OrderDTO = orderRepository.pendingOrder(tableNumber)?.toDto()
        ?: throw CustomException(ErrorCode.BAD_REQUEST, "table number: $tableNumber is not exists")

    fun additionalOrder(tableNumber: Int, orderMenusDTO: List<CreateOrderMenuDTO>): OrderDTO {
        val order = orderRepository.pendingOrder(tableNumber)
            ?: throw CustomException(ErrorCode.BAD_REQUEST, "table number: $tableNumber is not exists")

        val addAll = addAllOrderMenus(order, orderMenusDTO)

        val additional = orderRepository.save(addAll)

        return additional.toDto()
    }

    fun payment(tableNumber: Int): PaymentDTO {
        val order = orderRepository.pendingOrder(tableNumber)
            ?: throw CustomException(ErrorCode.BAD_REQUEST, "table number: $tableNumber is not exists")

        var total: Int = 0

        order.orderMenus.forEach {
            total += (it.menu.price * it.quantity).toInt()
        }

        order.state = OrderState.COMPLETED

        orderRepository.save(order)

        return PaymentDTO(total, order.orderMenus)
    }
}