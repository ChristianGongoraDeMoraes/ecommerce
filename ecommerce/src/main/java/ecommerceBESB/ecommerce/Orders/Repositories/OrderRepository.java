package ecommerceBESB.ecommerce.Orders.Repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ecommerceBESB.ecommerce.Orders.Order;


public interface OrderRepository extends JpaRepository<Order, UUID>{
    @Query(value = "SELECT * FROM Orders", nativeQuery = true)
    List<Order> findAllOrders();

    @Query(value = "SELECT * FROM Orders WHERE user_id = :id", nativeQuery = true)
    List<Order> findOrderByUserId(UUID id);
}