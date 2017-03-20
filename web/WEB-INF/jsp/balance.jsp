<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
        google.charts.load('current', {'packages': ['corechart']});
        google.charts.setOnLoadCallback(drawChart);

        function drawChart() {
            var data = new google.visualization.DataTable();
            data.addColumn('date', 'Date');
            data.addColumn('number', 'Money');
            var arrayData = [
                <c:forEach items="${money1.legend}" var="item">
                [new Date(${item.time.year}, ${item.time.monthValue}, ${item.time.dayOfMonth}), ${item.money.value.toPlainString()}],
                </c:forEach>
            ];

            for (var obj of arrayData) {

            }
            data.addRows(arrayData);

            var options = {
                title: 'Numbers',
                hAxis: {title: 'Date', titleTextStyle: {color: '#333'}},
                vAxis: {minValue: 0}
            };

            var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));

            chart.draw(data, options);
        }

    </script>
</head>
<body>
<div id="curve_chart" style="width: auto; height: 500px"></div>
</body>
</html>