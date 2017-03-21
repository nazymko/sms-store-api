<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
        google.charts.load("current", {packages: ["corechart"]});
        google.charts.setOnLoadCallback(drawChart);
        function drawChart() {
            var data = google.visualization.arrayToDataTable([
                ['Day', 'Money'],
                <c:forEach items="${pipe.legend}" var="item">
                ['${item.title}', ${item.money.value.toPlainString()}],
                </c:forEach>
            ]);

            var options = {
                title: 'My Daily Activities',
                pieHole: 0.4,
            };

            var chart = new google.visualization.PieChart(document.getElementById('donutchart'));
            chart.draw(data, options);
        }
    </script>
</head>
<body>
<div id="donutchart" style="width: 900px; height: 500px;"></div>
</body>
</html>