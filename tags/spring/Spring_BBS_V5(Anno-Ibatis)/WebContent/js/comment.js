$(document).ready(function(){	
	$('#comment_read').click(function(){
		alert("읽기");
		$.ajax({
			type : "POST",
			url:"/Spring_BBS_V5(Anno-Ibatis)/comment/commentRead",
			async : true,
			dataType : "json",
			data:{				
				article_num:$('#article_num').val()
			},
			success : function(data) {	
				alert(data);							
				var html = '<table border="1">';
//				@ResponseBody 경우
	    		$.each(data,function(entryIndex, entry){
//				MappingJackonJosnView 사용할 경우
//	    		$.each(data.comment,function(entryIndex, entry){
	    			var fomatted_date= new Date(entry.comment_date);
		    	  html += '<tr>';
		            html += '<td>' + entry.comment_num + '</td>';
		            html += '<td>' + entry.comment_content + '</td>';
		            html += '<td>' + fomatted_date.toLocaleDateString() + '</td>';
		            html += '<td>' + entry.article_num + '</td>';		               	        
		            html += '</tr>';    
	    		});	    		
	    		html += '</table>';	    		
				$("#show_comment").html(html);
			},
			error : function(xhr) {
				alert("error html = " + xhr.statusText);
			}			
		});
	});
	
	//id comment_wirte를 클릭했을 때
	$('#comment_write').click(function(){
		alert("쓰기");
		$.ajax({
			type : "POST",
			url:"/Spring_BBS_V5(Anno-Ibatis)/comment/commentWrite",
			async : true,
			dataType : "json",
			data:{				
				article_num:$('#article_num').val(),
				comment_content:$('#comment_content').val()
			},
			success : function(data) {				
				var html = '<table border="1">'; 
				//	@ResponseBody 경우
	    		$.each(data,function(entryIndex, entry){
	    			//	MappingJackonJosnView 사용할 경우
	    			//	$.each(data.comment,function(entryIndex, entry){
	    			var fomatted_date= new Date(entry.comment_date);
		    	    html += '<tr>';
		            html += '<td>' + entry.comment_num + '</td>';
		            html += '<td>' + entry.comment_content + '</td>';
		            //	 html += '<td>' + fomatted_date + '</td>';
		            html += '<td>' + fomatted_date.toLocaleDateString() + '</td>';
		            html += '<td>' + entry.article_num + '</td>';		               	        
		            html += '</tr>';    
	    		});	    	
	    		html += '</table>';	    		
				$("#show_comment").html(html);
			},
			error : function(xhr) {
				alert("error html = " + xhr.statusText);
			}			
		}); 
	});
});
