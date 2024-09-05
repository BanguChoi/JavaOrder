//package com.javaOrder.admin.dashBoard.repository;
//
//import java.util.List;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import com.javaOrder.admin.dashBoard.vo.DashboardDTO;
//import com.javaOrder.common.orders.domain.Orders;
//
//@Repository
//public interface DashboardRepository extends JpaRepository<Orders, Long> {
//
//    /* 주별 매출 */
//    @Query("SELECT new com.javaOrder.admin.dashBoard.vo.DashboardDTO(TO_CHAR(o.orderDate, 'YYYY-IW'), SUM(o.orderPrice)) " +
//           "FROM Orders o " +
//           "GROUP BY TO_CHAR(o.orderDate, 'YYYY-IW') " +
//           "ORDER BY TO_CHAR(o.orderDate, 'YYYY-IW')")
//    List<DashboardDTO> findWeeklySales();
//
//    /* 월별 매출
//    @Query("SELECT new com.javaOrder.admin.dashBoard.vo.DashboardDTO(TO_CHAR(o.orderDate, 'YYYY-MM'), SUM(o.orderPrice)) " +
//           "FROM Orders o " +
//           "GROUP BY TO_CHAR(o.orderDate, 'YYYY-MM') " +
//           "ORDER BY TO_CHAR(o.ordDate, 'YYYY-MM')")
//    List<DashboardDTO> findMonthlySales();*/
//
//    /* 분기별 매출
//    @Query("SELECT new com.javaOrder.admin.dashBoard.vo.DashboardDTO(CONCAT(TO_CHAR(o.orderDate, 'YYYY'), ' Q', TO_CHAR(o.orderDate, 'Q')), SUM(o.orderPrice)) " +
//           "FROM Orders o " +
//           "GROUP BY TO_CHAR(o.orderDate, 'YYYY'), TO_CHAR(o.orderDate, 'Q') " +
//           "ORDER BY TO_CHAR(o.orderDate, 'YYYY'), TO_CHAR(o.orderDate, 'Q')")
//    List<DashboardDTO> findQuarterlySales();*/
//
//    /* 연별 매출
//    @Query("SELECT new com.javaOrder.admin.dashBoard.vo.DashboardDTO(TO_CHAR(o.orderDate, 'YYYY'), SUM(o.orderPrice)) " +
//           "FROM Orders o " +
//           "GROUP BY TO_CHAR(o.orderDate, 'YYYY') " +
//           "ORDER BY TO_CHAR(o.orderDate, 'YYYY')")
//    List<DashboardDTO> findYearlySales();
//    */
//    /* 상품별 매출
//    @Query("SELECT new com.javaOrder.admin.dashBoard.vo.DashboardDTO(p.pName, SUM(oi.orderitemPrice * oi.orderitemNumber)) " +
//           "FROM OrderItem oi " +
//           "JOIN Orders o ON oi.orders.orderNumber = o.orderNumber " +
//           "JOIN Product p ON oi.product.productId = p.productId " +
//           "GROUP BY p.productName " +
//           "ORDER BY SUM(oi.orderitemPrice * oi.orderitemNumber) DESC")
//    List<DashboardDTO> findProductSales();
//    */
//    /* 상위 5개 상품
//    @Query("SELECT new com.javaOrder.admin.dashBoard.vo.DashboardDTO(p.pName, SUM(oi.orderitemNumber)) " +
//           "FROM OrderItem oi " +
//           "JOIN Product p ON oi.product.productId = p.productId " +
//           "GROUP BY p.productName " +
//           "ORDER BY SUM(oi.orderitemNumber) DESC")
//    List<DashboardDTO> findTop5Products();
//    */
//}
