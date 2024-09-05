// Sample data: Top 5 selling products
const top5 = $("#topSellingProdPie");
const top5Data = {
	labels: ['A0101', 'B0101', 'C0101', 'D0101', 'E0101'], // Example product IDs
	datasets: [{
		label: 'Sales',
		data: [5000, 4500, 4000, 3500, 3000], // Example sales quantities for the top 5 products
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

const topdata = new Chart(top5, {
	type:'pie',
	data:top5Data,
	options: {
		responsive : false,
	},
});
	
	
// Sample data
const weeklySalesData = {
	labels: ['Week 1', 'Week 2', 'Week 3', 'Week 4'], // Example week labels
    datasets: [{
        label: 'Sales',
        data: [12000, 5000, 15000, 14000], // Example sales data
        backgroundColor: 'rgba(75, 192, 192, 0.2)',
        borderColor: 'rgba(75, 192, 192, 1)',
        borderWidth: 2
    }]
};

const monthlySalesData = {
    labels: ['January', 'February', 'March', 'April', 'May', 'June'], // Example month labels
    datasets: [{
        label: 'Sales',
        data: [60000, 50000, 55000, 45000, 70000, 65000], // Example monthly sales data
        backgroundColor: 'rgba(153, 102, 255, 0.2)',
        borderColor: 'rgba(153, 102, 255, 1)',
        borderWidth: 2
    }]
};

// Weekly Sales Chart
const ctxWeekly = $('#weeklySalesChart');
new Chart(ctxWeekly, {
    type: 'bar',
    data: weeklySalesData,
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

// Monthly Sales Chart
const ctxMonthly = $('#monthlySalesChart');
new Chart(ctxMonthly, {
    type: 'line',
    data: monthlySalesData,
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