let topSellingProductsChart = null;
let topSellingCategoriesChart = null;
let totalSalesChart = null;
let productSalesChart = null;

$(document).ready(function() {
    // 페이지 로드 시 기본적으로 주별 데이터를 요청
    fetchTop5SellingProducts('week');
	fetchTop5SellingCategories('week');
	fetchTotalSales('daily');				// 기본 주별 판매 데이터 요청
	fetchProductSales('daily', 'product');	// 기본 주별 상품/카테고리 판매 데이터 요청
	   
    // Top 5 제품 기간 선택 시 이벤트 리스너
    $('select[aria-label="Top5 Product"]').change(function() {
        const period = $(this).val();
        fetchTop5SellingProducts(period);
    });
	
	$('select[aria-label="Top5 Category"]').change(function() {
	        const period = $(this).val();
	        fetchTop5SellingCategories(period);
	    });
	
	// Total Sales 차트 기간 선택 시 이벤트 리스너
	$('select[aria-label="Total Sales"]').change(function () {
	    const period = $(this).val();
	    fetchTotalSales(period);
	});

	// Product/Category Sales 차트 선택 시 이벤트 리스너
	$('select[aria-label="Period for Product Sales"], select[aria-label="Select Product/Category"]').change(function () {
	    const period = $('select[aria-label="Period for Product Sales"]').val();
	    const type = $('select[aria-label="Select Product/Category"]').val();
	    fetchProductSales(period, type);
	});
});

/*************** 데이터 가져오기 ***************/
// Top 5 판매 상품 데이터를 가져오기
function fetchTop5SellingProducts(period) {
    $.ajax({
        url: `/admin/topVProductSales`,
        type: 'GET',
        data: { period: period },
        success: function(response) {
            // 성공 시 response 데이터를 기반으로 차트 업데이트
            updateTopSellingProductsChart(response);
        },
        error: function(error) {
            console.error('Error fetching top 5 products:', error);
        }
    });
}

// Top 5 판매 카테고리 데이터를 가져오기
function fetchTop5SellingCategories(period) {
    $.ajax({
        url: `/admin/topVCategorySales`,
        type: 'GET',
        data: { period: period },
        success: function(response) {
            // 성공 시 response 데이터를 기반으로 차트 업데이트
            updateTopSellingCategoriesChart(response);
        },
        error: function(error) {
            console.error('Error fetching top 5 categories:', error);
        }
    });
}

// Total Sales 데이터를 가져오기
function fetchTotalSales(period) {
    $.ajax({
        url: `/admin/totalSales`,
        type: 'GET',
        data: { period: period },
        success: function (response) {
            updateTotalSalesChart(response);
        },
        error: function (error) {
            console.error('Error fetching total sales:', error);
        }
    });
}

// Product/Category Sales 데이터를 가져오기
function fetchProductSales(period, type) {
    $.ajax({
        url: `/admin/productSales`,
        type: 'GET',
        data: { period: period, type: type },
        success: function (response) {
            updateProductSalesChart(response, type);
        },
        error: function (error) {
            console.error('Error fetching product/category sales:', error);
        }
    });
}

/*************** 차트 업데이트 ***************/
// Top 5 판매 상품 차트 업데이트 함수
function updateTopSellingProductsChart(data) {
    const labels = data.map(item => item.productName); // 상품명
    const quantity = data.map(item => item.quantitySold); // 판매량
	const totalSales = data.map(item => item.totalSales); // 판매량

    const chartData = {
        labels: labels,
        datasets: [{
            label: 'Sales',
            data: totalSales,
            backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)'
            ],
            borderColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)'
            ],
            borderWidth: 2
        }]
    };
	
	const ctx = $('#topSellingProdPie');
	
	// 차트가 이미 존재하면 파괴
	if (topSellingProductsChart) {
		topSellingProductsChart.destroy();
	}
    
    topSellingProductsChart = new Chart(ctx, {
        type: 'pie',
        data: chartData,
        options: {
            responsive: false,
			plugins:{legend:{position:'right'}}
        }
    });
}

// Top 5 판매 카테고리 차트 업데이트 함수
function updateTopSellingCategoriesChart(data) {
    const labels = data.map(item => item.categoryName); // 상품명
    const quantity = data.map(item => item.quantitySold); // 판매량
	const totalSales = data.map(item => item.totalSales); // 총 매출

    const chartData = {
        labels: labels,
        datasets: [{
            label: 'Sales',
            data: quantity,
            backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)'
            ],
            borderColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)'
            ],
            borderWidth: 2
        }]
    };
	
	const ctx = $('#topSellingCatePie');
	
	// 차트가 이미 존재하면 파괴
	if (topSellingCategoriesChart) {
		topSellingCategoriesChart.destroy();
	}
    
    topSellingCategoriesChart = new Chart(ctx, {
        type: 'pie',
        data: chartData,
        options: {
            responsive: false,
			plugins:{legend:{
				position:'right',
			}},
			
        }
    });
}


// Total Sales 차트 업데이트 함수
function updateTotalSalesChart(data) {
    const labels = data.map(item => item.period); // 기간별 라벨
    const salesData = data.map(item => item.totalSales); // 총 판매량

    const chartData = {
        labels: labels,
        datasets: [{
            label: 'Total Sales',
            data: salesData,
            backgroundColor: 'rgba(75, 192, 192, 0.2)',
            borderColor: 'rgba(75, 192, 192, 1)',
            borderWidth: 2
        }]
    };

    const ctx = $('#totalSalesChart');

    if (totalSalesChart) {
        totalSalesChart.destroy();
    }

    totalSalesChart = new Chart(ctx, {
        type: 'bar',
        data: chartData,
        options: {
            responsive: false,
            scales: {
                x: {
                    beginAtZero: true
                },
                y: {
                    beginAtZero: true
                }
            }
        }
    });
}

// Product/Category Sales 차트 업데이트 함수
function updateProductSalesChart(data, type) {
    const labels = data.map(item => item.period);
    const salesData = data.map(item => item.totalSales);
    const productOrCategoryData = data.map(item => item.productOrCategorySales);

    const chartData = {
        labels: labels,
        datasets: [
            {
                label: 'Period Sales',
                data: salesData,
                type: 'line',
                backgroundColor: 'rgba(153, 102, 255, 0.2)',
                borderColor: 'rgba(153, 102, 255, 1)',
                borderWidth: 2
            },
            {
                label: type === 'product' ? 'Product Sales' : 'Category Sales',
                data: productOrCategoryData,
                type: 'bar',
                backgroundColor: 'rgba(255, 159, 64, 0.2)',
                borderColor: 'rgba(255, 159, 64, 1)',
                borderWidth: 2
            }
        ]
    };

    const ctx = $('#productSalesChart');

    if (productSalesChart) {
        productSalesChart.destroy();
    }

    productSalesChart = new Chart(ctx, {
        data: chartData,
        options: {
            responsive: false,
            scales: {
                x: {
                    beginAtZero: true
                },
                y: {
                    beginAtZero: true
                }
            }
        }
    });
}