package com.javaOrder.admin.dashBoard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.javaOrder.admin.dashBoard.vo.DashboardDTO;
import com.javaOrder.common.orders.domain.Orders;

@Repository
public interface DashboardRepository extends JpaRepository<Orders, Long> {

    // 주별 매출
    @Query("SELECT new com.example.dashboard.DashboardDTO(TO_CHAR(o.ordDate, 'YYYY-IW'), SUM(o.ordPrice)) " +
           "FROM Orders o " +
           "GROUP BY TO_CHAR(o.ordDate, 'YYYY-IW') " +
           "ORDER BY TO_CHAR(o.ordDate, 'YYYY-IW')")
    List<DashboardDTO> findWeeklySales();

    // 월별 매출
    @Query("SELECT new com.example.dashboard.DashboardDTO(TO_CHAR(o.ordDate, 'YYYY-MM'), SUM(o.ordPrice)) " +
           "FROM Orders o " +
           "GROUP BY TO_CHAR(o.ordDate, 'YYYY-MM') " +
           "ORDER BY TO_CHAR(o.ordDate, 'YYYY-MM')")
    List<DashboardDTO> findMonthlySales();

    // 분기별 매출
    @Query("SELECT new com.example.dashboard.DashboardDTO(CONCAT(TO_CHAR(o.ordDate, 'YYYY'), ' Q', TO_CHAR(o.ordDate, 'Q')), SUM(o.ordPrice)) " +
           "FROM Orders o " +
           "GROUP BY TO_CHAR(o.ordDate, 'YYYY'), TO_CHAR(o.ordDate, 'Q') " +
           "ORDER BY TO_CHAR(o.ordDate, 'YYYY'), TO_CHAR(o.ordDate, 'Q')")
    List<DashboardDTO> findQuarterlySales();

    // 연별 매출
    @Query("SELECT new com.example.dashboard.DashboardDTO(TO_CHAR(o.ordDate, 'YYYY'), SUM(o.ordPrice)) " +
           "FROM Orders o " +
           "GROUP BY TO_CHAR(o.ordDate, 'YYYY') " +
           "ORDER BY TO_CHAR(o.ordDate, 'YYYY')")
    List<DashboardDTO> findYearlySales();

    // 상품별 매출
    @Query("SELECT new com.example.dashboard.DashboardDTO(p.pName, SUM(oi.oiPrice * oi.oiNum)) " +
           "FROM OrderItem oi " +
           "JOIN Orders o ON oi.orders.ordNum = o.ordNum " +
           "JOIN Product p ON oi.product.pId = p.pId " +
           "GROUP BY p.pName " +
           "ORDER BY SUM(oi.oiPrice * oi.oiNum) DESC")
    List<DashboardDTO> findProductSales();

    // 상위 5개 상품
    @Query("SELECT new com.example.dashboard.DashboardDTO(p.pName, SUM(oi.oiNum)) " +
           "FROM OrderItem oi " +
           "JOIN Product p ON oi.product.pId = p.pId " +
           "GROUP BY p.pName " +
           "ORDER BY SUM(oi.oiNum) DESC")
    List<DashboardDTO> findTop5Products();
}
