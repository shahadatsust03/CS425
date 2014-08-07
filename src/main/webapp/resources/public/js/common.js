  $(document).ready(function(){

   linkClicked("#addproduct",function(url){
        console.debug(url);
       $.get( url, function( data ) {
         $( "#productsContainer" ).html( data );
        });
   });

    function linkClicked(id,callBack){
         $(id).click(function(){
                // do something
                var url = $(this).attr("href");
                //$(this).dropdown("toggle");
                callBack.call(this,url);
             
                return false;

          });
    }
  });