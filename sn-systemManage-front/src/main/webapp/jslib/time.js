function myformatter(date){
            var y = date.getFullYear();
            var m = date.getMonth()+1;
            var d = date.getDate();
            return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
        }
       
 function myparser(s){
            if (!s) return new Date();
            var ss = (s.split('-'));
            var y = parseInt(ss[0],10);
            var m = parseInt(ss[1],10);
            var d = parseInt(ss[2],10);
            if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
                return new Date(y,m-1,d);
            } else {
                return new Date();
            }
        }

 // @Troy 2014-08-26 19:34:41
 function lastMonth(obj){
	 var now = new Date() ;
     var y = now.getFullYear();
     var m = now.getMonth() ;
     var d = now.getDate();
     
     if(m == 0 || m == "0" || m == "00"){
    	 m = 12 ;
    	 y = y - 1 ;
     }
     
     if(m < 10){
    	 m = "0" + m ;
     }
     
     if(("04,06,09,11,4,6,9".indexOf(m) > -1) && m != 1 && d > 30){
    	 d = 30 ;
     }else if((m == 2 || m == '02' || m == '2') && d > 28){
    	 if(isLeapYear(y)){
    		 // 闰年
    		 d = 29 ;
    	 }else{
    		 // 平年
    		 d = 28 ;
    	 }
     }
     
     if(d < 10){
    	 d = "0" + d ;
     }
     
     if(typeof(obj) != "undefined"){
    	 obj.datebox('setValue', y + "-" + m + "-" + d) ;
     }
     
	 return y + "-" + m + "-" + d ;
	 
 }
 
 // @Troy 2014-08-26 19:34:45
 function now(obj){
	 var now = new Date() ;
     var y = now.getFullYear();
     var m = now.getMonth() + 1 ;
     var d = now.getDate();
     
     if(m < 10){
    	 m = "0" + m ;
     }
     
     if(("04,06,09,11,4,6,9".indexOf(m) > -1) && m != 1 && d > 30){
    	 d = 30 ;
     }else if((m == 2 || m == '02' || m == '2') && d > 28){
    	 if(isLeapYear(y)){
    		 // 闰年
    		 d = 29 ;
    	 }else{
    		 // 平年
    		 d = 28 ;
    	 }
     }
     
     if(d < 10){
    	 d = "0" + d ;
     }
     
     if(typeof(obj) != "undefined"){
    	 obj.datebox('setValue', y + "-" + m + "-" + d) ;
     }
     
	 return y + "-" + m + "-" + d ;
 }
 
 // 判断是否是闰年
 function isLeapYear(year) {  
	 return (year % 4 == 0) && (year % 100 != 0 || year % 400 == 0);  
 }

function getBrowserHeight() 
{ 
	return document.body.clientHeight ;
} 

function getTitleHeight(){
	return 80 ;
}

//add by ming 2016-03-02
function formatter_datettime(value) {
	if(value==null){
		return '';
	}
    var date = new Date(value);
    var year = date.getFullYear().toString();
    var month = (date.getMonth() + 1);
    var day = date.getDate().toString();
    var hour = date.getHours().toString();
    var minutes = date.getMinutes().toString();
    var seconds = date.getSeconds().toString();
    if (month < 10) {
        month = "0" + month;
    }
    if (day < 10) {
        day = "0" + day;
    }
    if (hour < 10) {
        hour = "0" + hour;
    }
    if (minutes < 10) {
        minutes = "0" + minutes;
    }
    if (seconds < 10) {
        seconds = "0" + seconds;
    }
    return year + "-" + month + "-" + day + " " + hour + ":" + minutes + ":" + seconds;
}
