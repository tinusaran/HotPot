package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.OrderNew;

@Repository
public interface OrderNewRepository extends JpaRepository<OrderNew, Integer> {
    List<OrderNew> findByMenuId(int menuId);
    List<OrderNew> findByCustId(int custId);
    List<OrderNew> findByVenId(int venId);
    List<OrderNew> findByOrderStatus(String status);
List<OrderNew> findByCustIdAndOrderStatus(int custId, String status);
List<OrderNew> findByVenIdAndOrderStatus(int venId, String status);
    @Query("SELECT o FROM OrderNew o WHERE o.menuId IN (SELECT m.menuId FROM Menu m WHERE m.restaurant.resId = :resId)")
    List<OrderNew> findByRestaurantId(@Param("resId") int resId);


}

