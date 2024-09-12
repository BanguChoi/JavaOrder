package com.javaOrder.admin.dashBoard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.javaOrder.common.orders.domain.Orders;

@Repository
public interface DashboardRepository extends JpaRepository<Orders, Long> {
    /****************** 상위 5개 잘 팔리는 상품 ******************/
    /* 상품별 */
    /* 주별 */
    @Query(value = "SELECT TO_CHAR(o.ord_date, 'YYYY-IW') AS period, p.p_name AS productName, " +
                   "SUM(oi.oi_num) AS totalCount, SUM(oi.oi_num * oi.oi_price) AS totalSales " +
                   "FROM orders o JOIN order_item oi ON o.ord_num = oi.ord_num " +
                   "JOIN product p ON oi.p_id = p.p_id " +
                   "GROUP BY TO_CHAR(o.ord_date, 'YYYY-IW'), p.p_name " +
                   "ORDER BY period, totalSales DESC FETCH FIRST 5 ROWS WITH TIES", nativeQuery = true)
    List<Object[]> findTop5WeeklyProductSales();
    /* 월별 */
    @Query(value = "SELECT TO_CHAR(o.ord_date, 'YYYY-MM') AS period, p.p_name AS productName, " +
            "SUM(oi.oi_num) AS totalCount, SUM(oi.oi_num * oi.oi_price) AS totalSales " +
            "FROM orders o JOIN order_item oi ON o.ord_num = oi.ord_num " +
            "JOIN product p ON oi.p_id = p.p_id " +
            "GROUP BY TO_CHAR(o.ord_date, 'YYYY-MM'), p.p_name " +
            "ORDER BY period, totalSales DESC FETCH FIRST 5 ROWS WITH TIES", nativeQuery = true)
    List<Object[]> findTop5MonthlyProductSales();
    /* 분기별 */
    @Query(value = "SELECT TO_CHAR(o.ord_date, 'YYYY-Q') AS period, p.p_name AS productName, " +
            "SUM(oi.oi_num) AS totalCount, SUM(oi.oi_num * oi.oi_price) AS totalSales " +
            "FROM orders o JOIN order_item oi ON o.ord_num = oi.ord_num " +
            "JOIN product p ON oi.p_id = p.p_id " +
            "GROUP BY TO_CHAR(o.ord_date, 'YYYY-Q'), p.p_name " +
            "ORDER BY period, totalSales DESC FETCH FIRST 5 ROWS WITH TIES", nativeQuery = true)
    List<Object[]> findTop5QuarterlyProductSales();
    /* 연별 */
    @Query(value = "SELECT TO_CHAR(o.ord_date, 'YYYY') AS period, p.p_name AS productName, " +
            "SUM(oi.oi_num) AS totalCount, SUM(oi.oi_num * oi.oi_price) AS totalSales " +
            "FROM orders o JOIN order_item oi ON o.ord_num = oi.ord_num " +
            "JOIN product p ON oi.p_id = p.p_id " +
            "GROUP BY TO_CHAR(o.ord_date, 'YYYY'), p.p_name " +
            "ORDER BY period, totalSales DESC FETCH FIRST 5 ROWS WITH TIES", nativeQuery = true)
    List<Object[]> findTop5YearlyProductSales();
    
    /* 카테고리별 */
    /* 주별 
    @Query(value = "SELECT period, categoryName, totalSales, totalCount " +
            "FROM ( " +
            "    SELECT " +
            "        TO_CHAR(o.ord_date, 'YYYY-IW') AS period, " +
            "        c.cate_name AS categoryName, " +
            "        SUM(oi.oi_num * oi.oi_price) AS totalSales, " +
            "        SUM(oi.oi_num) AS totalCount, " +
            "        ROW_NUMBER() OVER (PARTITION BY TO_CHAR(o.ord_date, 'YYYY-IW') " +
            "                           ORDER BY SUM(oi.oi_num * oi.oi_price) DESC) AS rn " +
            "    FROM orders o " +
            "    JOIN order_item oi ON o.ord_num = oi.ord_num " +
            "    JOIN product p ON oi.p_id = p.p_id " +
            "    JOIN category c ON p.cate_code = c.cate_code " +
            "    GROUP BY TO_CHAR(o.ord_date, 'YYYY-IW'), c.cate_name " +
            ") " +
            "WHERE rn <= 5 " +
            "ORDER BY period, totalSales DESC", nativeQuery = true)
    List<Object[]> findTop5WeeklyCategorySales();*/
    @Query(value = "SELECT period, categoryName, totalSales, quantitySold " +
            "FROM (" +
            "    SELECT TO_CHAR(o.ord_date, 'YYYY-IW') AS period, " +
            "           c.cate_name AS categoryName, " +
            "           SUM(oi.oi_num * oi.oi_price) AS totalSales, " +
            "           SUM(oi.oi_num) AS quantitySold " +
            "    FROM orders o " +
            "    JOIN order_item oi ON o.ord_num = oi.ord_num " +
            "    JOIN product p ON oi.p_id = p.p_id " +
            "    JOIN category c ON p.cate_code = c.cate_code " +
            "    WHERE TO_CHAR(o.ord_date, 'YYYY-IW') = TO_CHAR(SYSDATE, 'YYYY-IW') " +
            "    GROUP BY TO_CHAR(o.ord_date, 'YYYY-IW'), c.cate_name " +
            ") sub " +
            "ORDER BY totalSales DESC " +
            "FETCH FIRST 5 ROWS ONLY",
    nativeQuery = true)
    List<Object[]> findTop5WeeklyCategorySales();
    /* 월별 */
    @Query(value = "SELECT period, categoryName, totalSales, quantitySold " +
            "FROM (" +
            "    SELECT TO_CHAR(o.ord_date, 'YYYY-MM') AS period, " +
            "           c.cate_name AS categoryName, " +
            "           SUM(oi.oi_num * oi.oi_price) AS totalSales, " +
            "           SUM(oi.oi_num) AS quantitySold " +
            "    FROM orders o " +
            "    JOIN order_item oi ON o.ord_num = oi.ord_num " +
            "    JOIN product p ON oi.p_id = p.p_id " +
            "    JOIN category c ON p.cate_code = c.cate_code " +
            "    WHERE TO_CHAR(o.ord_date, 'YYYY-MM') = TO_CHAR(SYSDATE, 'YYYY-MM') " +
            "    GROUP BY TO_CHAR(o.ord_date, 'YYYY-MM'), c.cate_name " +
            ") sub " +
            "ORDER BY totalSales DESC " +
            "FETCH FIRST 5 ROWS ONLY",
    nativeQuery = true)
    List<Object[]> findTop5MonthlyCategorySales();
    /* 분기별 */
    @Query(value = "SELECT period, categoryName, totalSales, quantitySold " +
            "FROM (" +
            "    SELECT TO_CHAR(o.ord_date, 'YYYY-Q') AS period, " +
            "           c.cate_name AS categoryName, " +
            "           SUM(oi.oi_num * oi.oi_price) AS totalSales, " +
            "           SUM(oi.oi_num) AS quantitySold " +
            "    FROM orders o " +
            "    JOIN order_item oi ON o.ord_num = oi.ord_num " +
            "    JOIN product p ON oi.p_id = p.p_id " +
            "    JOIN category c ON p.cate_code = c.cate_code " +
            "    WHERE TO_CHAR(o.ord_date, 'YYYY-Q') = TO_CHAR(SYSDATE, 'YYYY-Q') " +
            "    GROUP BY TO_CHAR(o.ord_date, 'YYYY-Q'), c.cate_name " +
            ") sub " +
            "ORDER BY totalSales DESC " +
            "FETCH FIRST 5 ROWS ONLY",
    nativeQuery = true)
    List<Object[]> findTop5QuarterlyCategorySales();
    /* 연별 */
    @Query(value = "SELECT period, categoryName, totalSales, quantitySold " +
            "FROM (" +
            "    SELECT TO_CHAR(o.ord_date, 'YYYY') AS period, " +
            "           c.cate_name AS categoryName, " +
            "           SUM(oi.oi_num * oi.oi_price) AS totalSales, " +
            "           SUM(oi.oi_num) AS quantitySold " +
            "    FROM orders o " +
            "    JOIN order_item oi ON o.ord_num = oi.ord_num " +
            "    JOIN product p ON oi.p_id = p.p_id " +
            "    JOIN category c ON p.cate_code = c.cate_code " +
            "    WHERE TO_CHAR(o.ord_date, 'YYYY') = TO_CHAR(SYSDATE, 'YYYY') " +
            "    GROUP BY TO_CHAR(o.ord_date, 'YYYY'), c.cate_name " +
            ") sub " +
            "ORDER BY totalSales DESC " +
            "FETCH FIRST 5 ROWS ONLY",
    nativeQuery = true)
    List<Object[]> findTop5YearlyCategorySales();
	
	/****************** 기간별 총 매출 ******************/
    /* 일별 총 매출 */
    @Query(value = "SELECT TO_CHAR(o.ord_date, 'YYYY-MM-DD') AS period, SUM(oi.oi_num * oi.oi_price) AS totalSales " +
                   "FROM orders o JOIN order_item oi ON o.ord_num = oi.ord_num " +
                   "GROUP BY TO_CHAR(o.ord_date, 'YYYY-MM-DD') " +
                   "ORDER BY period", nativeQuery = true)
    List<Object[]> findDailyTotalSales();

    /* 주별 총 매출 */
    @Query(value = "SELECT TO_CHAR(o.ord_date, 'YYYY-IW') AS period, SUM(oi.oi_num * oi.oi_price) AS totalSales " +
                   "FROM orders o JOIN order_item oi ON o.ord_num = oi.ord_num " +
                   "GROUP BY TO_CHAR(o.ord_date, 'YYYY-IW') " +
                   "ORDER BY period", nativeQuery = true)
    List<Object[]> findWeeklyTotalSales();

    /* 월별 총 매출 */
    @Query(value = "SELECT TO_CHAR(o.ord_date, 'YYYY-MM') AS period, SUM(oi.oi_num * oi.oi_price) AS totalSales " +
                   "FROM orders o JOIN order_item oi ON o.ord_num = oi.ord_num " +
                   "GROUP BY TO_CHAR(o.ord_date, 'YYYY-MM') " +
                   "ORDER BY period", nativeQuery = true)
    List<Object[]> findMonthlyTotalSales();

    /* 분기별 총 매출 */
    @Query(value = "SELECT TO_CHAR(o.ord_date, 'YYYY-Q') AS period, SUM(oi.oi_num * oi.oi_price) AS totalSales " +
                   "FROM orders o JOIN order_item oi ON o.ord_num = oi.ord_num " +
                   "GROUP BY TO_CHAR(o.ord_date, 'YYYY-Q') " +
                   "ORDER BY period", nativeQuery = true)
    List<Object[]> findQuarterlyTotalSales();

    /* 연별 총 매출 */
    @Query(value = "SELECT TO_CHAR(o.ord_date, 'YYYY') AS period, SUM(oi.oi_num * oi.oi_price) AS totalSales " +
                   "FROM orders o JOIN order_item oi ON o.ord_num = oi.ord_num " +
                   "GROUP BY TO_CHAR(o.ord_date, 'YYYY') " +
                   "ORDER BY period", nativeQuery = true)
    List<Object[]> findYearlyTotalSales();
    
    /* ------------------------------------------------------------ */
    
    /****************** 상품별 매출 ******************/
    /* 일별 상품별 매출 */
    @Query(value = "SELECT TO_CHAR(o.ord_date, 'YYYY-MM-DD') AS period, p.p_name AS productName, " +
                   "SUM(oi.oi_num * oi.oi_price) AS totalSales, SUM(oi.oi_num) AS totalCount " +
                   "FROM orders o JOIN order_item oi ON o.ord_num = oi.ord_num " +
                   "JOIN product p ON oi.p_id = p.p_id " +
                   "GROUP BY TO_CHAR(o.ord_date, 'YYYY-MM-DD'), p.p_name " +
                   "ORDER BY period, totalSales DESC", nativeQuery = true)
    List<Object[]> findDailyProductSales();

    /* 주별 상품별 매출 */
    @Query(value = "SELECT TO_CHAR(o.ord_date, 'YYYY-IW') AS period, p.p_name AS productName, " +
                   "SUM(oi.oi_num * oi.oi_price) AS totalSales, SUM(oi.oi_num) AS totalCount " +
                   "FROM orders o JOIN order_item oi ON o.ord_num = oi.ord_num " +
                   "JOIN product p ON oi.p_id = p.p_id " +
                   "GROUP BY TO_CHAR(o.ord_date, 'YYYY-IW'), p.p_name " +
                   "ORDER BY period, totalSales DESC", nativeQuery = true)
    List<Object[]> findWeeklyProductSales();

    /* 월별 상품별 매출 */
    @Query(value = "SELECT TO_CHAR(o.ord_date, 'YYYY-MM') AS period, p.p_name AS productName, " +
                   "SUM(oi.oi_num * oi.oi_price) AS totalSales, SUM(oi.oi_num) AS totalCount " +
                   "FROM orders o JOIN order_item oi ON o.ord_num = oi.ord_num " +
                   "JOIN product p ON oi.p_id = p.p_id " +
                   "GROUP BY TO_CHAR(o.ord_date, 'YYYY-MM'), p.p_name " +
                   "ORDER BY period, totalSales DESC", nativeQuery = true)
    List<Object[]> findMonthlyProductSales();
    
    /* 분기별 상품별 매출 */
    @Query(value = "SELECT TO_CHAR(o.ord_date, 'YYYY-Q') AS period, p.p_name AS productName, " +
                   "SUM(oi.oi_num * oi.oi_price) AS totalSales, SUM(oi.oi_num) AS totalCount " +
                   "FROM orders o JOIN order_item oi ON o.ord_num = oi.ord_num " +
                   "JOIN product p ON oi.p_id = p.p_id " +
                   "GROUP BY TO_CHAR(o.ord_date, 'YYYY-Q'), p.p_name " +
                   "ORDER BY period, totalSales DESC", nativeQuery = true)
    List<Object[]> findQuarterlyProductSales();
    
    /* 연별 상품별 매출 */
    @Query(value = "SELECT TO_CHAR(o.ord_date, 'YYYY') AS period, p.p_name AS productName, " +
                   "SUM(oi.oi_num * oi.oi_price) AS totalSales, SUM(oi.oi_num) AS totalCount " +
                   "FROM orders o JOIN order_item oi ON o.ord_num = oi.ord_num " +
                   "JOIN product p ON oi.p_id = p.p_id " +
                   "GROUP BY TO_CHAR(o.ord_date, 'YYYY'), p.p_name " +
                   "ORDER BY period, totalSales DESC", nativeQuery = true)
    List<Object[]> findYearlyProductSales();
    
    /****************** 카테고리별 매출 ******************/
    /* 일별 카테고리별 매출 */
    @Query(value = "SELECT TO_CHAR(o.ord_date, 'YYYY-MM-DD') AS period, c.cate_name AS categoryName, " +
                   "SUM(oi.oi_num * oi.oi_price) AS totalSales, SUM(oi.oi_num) AS totalCount " +
                   "FROM orders o JOIN order_item oi ON o.ord_num = oi.ord_num " +
                   "JOIN product p ON oi.p_id = p.p_id " +
                   "JOIN category c ON p.cate_code = c.cate_code " +
                   "GROUP BY TO_CHAR(o.ord_date, 'YYYY-MM-DD'), c.cate_name " +
                   "ORDER BY period, totalSales DESC", nativeQuery = true)
    List<Object[]> findDailyCategorySales();
    /* 주별 카테고리별 매출 */
    @Query(value = "SELECT TO_CHAR(o.ord_date, 'YYYY-IW') AS period, c.cate_name AS categoryName, " +
                   "SUM(oi.oi_num * oi.oi_price) AS totalSales, SUM(oi.oi_num) AS totalCount " +
                   "FROM orders o JOIN order_item oi ON o.ord_num = oi.ord_num " +
                   "JOIN product p ON oi.p_id = p.p_id " +
                   "JOIN category c ON p.cate_code = c.cate_code " +
                   "GROUP BY TO_CHAR(o.ord_date, 'YYYY-IW'), c.cate_name " +
                   "ORDER BY period, totalSales DESC", nativeQuery = true)
    List<Object[]> findWeeklyCategorySales();

    /* 월별 카테고리별 매출 */
    @Query(value = "SELECT TO_CHAR(o.ord_date, 'YYYY-MM') AS period, c.cate_name AS categoryName, " +
                   "SUM(oi.oi_num * oi.oi_price) AS totalSales, SUM(oi.oi_num) AS totalCount " +
                   "FROM orders o JOIN order_item oi ON o.ord_num = oi.ord_num " +
                   "JOIN product p ON oi.p_id = p.p_id " +
                   "JOIN category c ON p.cate_code = c.cate_code " +
                   "GROUP BY TO_CHAR(o.ord_date, 'YYYY-MM'), c.cate_name " +
                   "ORDER BY period, totalSales DESC", nativeQuery = true)
    List<Object[]> findMonthlyCategorySales();

    
    /* 분기별 카테고리별 매출 */
    @Query(value = "SELECT TO_CHAR(o.ord_date, 'YYYY-Q') AS period, c.cate_name AS categoryName, " +
                   "SUM(oi.oi_num * oi.oi_price) AS totalSales, SUM(oi.oi_num) AS totalCount " +
                   "FROM orders o JOIN order_item oi ON o.ord_num = oi.ord_num " +
                   "JOIN product p ON oi.p_id = p.p_id " +
                   "JOIN category c ON p.cate_code = c.cate_code " +
                   "GROUP BY TO_CHAR(o.ord_date, 'YYYY-Q'), c.cate_name " +
                   "ORDER BY period, totalSales DESC", nativeQuery = true)
    List<Object[]> findQuarterlyCategorySales();

    
    /* 연별 카테고리별 매출 */
    @Query(value = "SELECT TO_CHAR(o.ord_date, 'YYYY') AS period, c.cate_name AS categoryName, " +
                   "SUM(oi.oi_num * oi.oi_price) AS totalSales, SUM(oi.oi_num) AS totalCount " +
                   "FROM orders o JOIN order_item oi ON o.ord_num = oi.ord_num " +
                   "JOIN product p ON oi.p_id = p.p_id " +
                   "JOIN category c ON p.cate_code = c.cate_code " +
                   "GROUP BY TO_CHAR(o.ord_date, 'YYYY'), c.cate_name " +
                   "ORDER BY period, totalSales DESC", nativeQuery = true)
    List<Object[]> findYearlyCategorySales();

}
