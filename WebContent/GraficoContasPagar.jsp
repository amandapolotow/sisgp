<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/css.css">
<title>SisGp</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
</head>
<body class="parallax">
<%@ include file="menu.jsp" %>
<div class=content>
	<h3>Grafico de Contas a Pagar</h3>
<br>
<div class="container">
<script type="text/javascript">
	$(document).ready(function(){
		
		$.ajax({
			url: "GsonContasPagar",
			dataType: "JSON",
			success: function(result){
				google.charts.load('current', {packages: ['corechart']});
				google.charts.setOnLoadCallback(function(){
					drawChart(result)
				});
				
				 function drawChart() {

				      var data = new google.visualization.DataTable();
				      data.addColumn('string', 'Mês');
				      data.addColumn('number', 'Total de Despesa');
				      
				      var dataArray = [];
				      
				      $.each(result, function(i, obj){
				    	  dataArray.push([ obj.mes, obj.vlTotal ]);
				    	  //alert(obj.mes);
				    	  
				      });
				      
				      data.addRows(dataArray);
				      
				      //opções do gráfico de pizza
				      /*var piechart_options = {
				      		title: "Distribuição Contas a Pagar",
				      	   // width: 800,
				      	    height: 500
				      }

				      // Instantiate and draw the chart.
				      var piechart = new google.visualization.PieChart(document.getElementById('piechart_div'));
				      piechart.draw(data, piechart_options);*/
				      
				      //opções do gráfico de colunas
				      var columnchart_options = {
					      		title: "Contas a Pagar",
					      	   // width: 800,
					      	    height: 500
					      }
				      
				   // Instantiate and draw the chart.
				      var columnchart = new google.visualization.ColumnChart(document.getElementById('columnchart_div'));
				      columnchart.draw(data, columnchart_options);
				      
				    }
				 
				 
				        
				      
				 
				 	
			}
		
			
		});
		
		/*google.charts.load('current', {'packages':['timeline']});
	      google.charts.setOnLoadCallback(drawChart);
		
		function drawChart() {
		var container = document.getElementById('timeline');
        var chart = new google.visualization.Timeline(container);
        var dataTable = new google.visualization.DataTable();

        dataTable.addColumn({ type: 'string', id: 'President' });
        dataTable.addColumn({ type: 'date', id: 'Start' });
        dataTable.addColumn({ type: 'date', id: 'End' });
        dataTable.addRows([
        	 [ 'Washington', new Date(0, 1, 29), new Date(0, 2, 3) ],
             [ 'Adams', new Date(0, 2, 3),  new Date(0, 2, 26) ],
             [ 'Jefferson', new Date(0, 1, 3),  new Date(0, 2, 3) ]]);

        chart.draw(dataTable);
		}
		
		drawChart();*/
		
	});
</script>
<!-- <table>
		<tr>
			<td> 
			<div id="piechart_div" style="border : 1px solid #ccc"></div>
			</td>
			<td> 
			<div id="columnchart_div" style="border : 1px solid #ccc" ></div>
			</td>
		</tr>	
	</table> -->
</div>
<!-- <div id="piechart_div" style="border : 1px solid #ccc"></div> -->
<div id="columnchart_div" style="border : 1px solid #ccc" ></div> 
<!-- <div id="timeline" style="height: 180px;"></div> -->
</div>

</body>
</html>