  $(document).ready(function(){
    $('#password-request')
        .on('hidden.bs.modal', function(){
            $("#request-password-model-msg").html("");
        });
   
    $('#creditcard-modal')
        .on('hidden.bs.modal', function(){
            $("#credit-card-model-msg").html("");
        });
        
   linkClicked("#addproduct",function(url){
        console.debug(url);
       $.get( url, function( data ) {
         $( "#productsContainer" ).html( data );
        });
   });

   //load customer pages
   linkClicked("#userEnrollments",function(url){
        console.debug(url);
         $.get( url, function( data ) {
         $("#userAccountContainer").html(data);
        });
   });
   
    linkClicked("#userProfile",function(url){
        console.debug(url);
         $.get( url, function( data ) {
         $("#userAccountContainer").html(data);
        });
   });
   
   
    linkClicked("#userOrders",function(url){
        console.debug(url);
         $.get( url, function( data ) {
         $("#userAccountContainer").html(data);
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
  
 jQuery(function($) {
       $.fn.checkOut = function(data) {
           console.log("add to cart");
           console.log(data);
           $.ajax({
              url: "checkout",
              data: JSON.stringify(data), 
              dataType: 'json',
              type: 'POST',
              contentType: 'application/json',
              mimeType: 'application/json',
              success: function(data) { 
                      if(data.success){
                            var message =  '<div class="alert alert-success">'+
                                        '<a href="#" class="close" data-dismiss="alert">&times;</a>'+
                                        '<strong>Success!</strong> '+data.message+
                                        '</div>';
                                
                         $("#cartMsg").html(message);
                          simpleCart.empty();
                      }
                      else{
                           var alert ='<div class="alert alert-warning">'+
                                        '<a href="#" class="close" data-dismiss="alert">&times;</a>'+
                                        '<strong>Failed!</strong> '+data.message+
                             '</div>';
                                
                          $("#cartMsg").html(alert);
                      }
   
                },
                error:function(data,status,er) { 
                    alert("error: "+data+" status: "+status+" er:"+er);
                }
            });

         return false;
         };
         
         //save order
         $.fn.saveOrder = function(data) {
           console.log("save to cart");
           console.log(data);
           $.ajax({
              url: "saveorder",
              data: JSON.stringify(data), 
              dataType: 'json',
              type: 'POST',
              contentType: 'application/json',
              mimeType: 'application/json',
              success: function(data) { 
                      if(data.success){
                           var message =  '<div class="alert alert-success">'+
                                        '<a href="#" class="close" data-dismiss="alert">&times;</a>'+
                                        '<strong>Success!</strong> '+data.message+
                                        '</div>';
                                
                         $("#cartMsg").html(message);
                          simpleCart.empty();
                      }
                      else{
                           var alert ='<div class="alert alert-warning">'+
                                        '<a href="#" class="close" data-dismiss="alert">&times;</a>'+
                                        '<strong>Failed!</strong> '+data.message+
                             '</div>';
                                
                          $("#cartMsg").html(alert);
                      }
   
                },
                error:function(data,status,er) { 
                    alert("error: "+data+" status: "+status+" er:"+er);
                }
            });

         return false;
         };
         
         //forget password
         $.fn.requestPassword = function(formId){
             console.log("request password");
              var form = $(formId);
              $.ajax({
              url: $(form).attr('action'),
              data: $(form).serialize(), 
              type: 'POST',
              success: function(resp) {
                   var data = $.parseJSON( resp );
                     console.log(data);
                      if(data.success){
                         console.log(data.message);
                         $("#password-request").modal('hide');
                         var message =  '<div class="alert alert-success">'+
                                        '<a href="#" class="close" data-dismiss="alert">&times;</a>'+
                                        '<strong>Success!</strong> '+data.message+
                                        '</div>';
                                
                         $("#serverResponseMsg").html(message);
                      }
                      else{
                          var alert ='<div class="alert alert-warning">'+
                                        '<a href="#" class="close" data-dismiss="alert">&times;</a>'+
                                        '<strong>Warning!</strong> '+data.message+
                                        '</div>';
                                
                         $("#request-password-model-msg").html(alert);
                      }
   
                },
                error:function(data,status,er) { 
                    alert("error: "+data+" status: "+status+" er:"+er);
                }
            });
             return false;
         }
         
            //forget password
         $.fn.registerUser = function(formId){
             console.log("request password");
              var form = $(formId);
              $.ajax({
              url: $(form).attr('action'),
              data: $(form).serialize(), 
              type: 'POST',
              success: function(resp) {
                   var data = $.parseJSON( resp );
                     console.log(data);
                      if(data.success){
                         console.log(data.message);
                         $("#regisration-modal").modal('hide');
                         var message =  '<div class="alert alert-success">'+
                                        '<a href="#" class="close" data-dismiss="alert">&times;</a>'+
                                        '<strong>Success!</strong> '+data.message+
                                        '</div>';
                                
                         $("#serverResponseMsg").html(message);
                      }
                      else{
                          var alert ='<div class="alert alert-warning">'+
                                        '<a href="#" class="close" data-dismiss="alert">&times;</a>'+
                                        '<strong>Warning!</strong> '+data.message+
                                        '</div>';
                                
                         $("#registration-model-msg").html(alert);
                      }
   
                },
                error:function(data,status,er) { 
                    alert("error: "+data+" status: "+status+" er:"+er);
                }
            });
             return false;
         }
         //save credit card
         $.fn.saveCreditCard = function(formId){
             console.log("save credit ");
              var form = $(formId);
              $.ajax({
              url: $(form).attr('action'),
              data: $(form).serialize(), 
              type: 'POST',
              success: function(resp) {
                   var data = $.parseJSON( resp );
                     console.log(data);
                      if(data.success){
                         console.log(data.message);
                         
                         var message =  '<div class="alert alert-success">'+
                                        '<a href="#" class="close" data-dismiss="alert">&times;</a>'+
                                        '<strong>Success!</strong> '+data.message+
                                        '</div>';
                                
                         $("#serverResponseMsg").html(message);
                         //refresh the
                          var url = $("#userProfile").attr("href");
                          $.get( url, function( data ) {
                            $("#userAccountContainer").html(data);
                           });
                         $("#creditcard-modal").modal('hide');
                      }
                      else{
                          var alert ='<div class="alert alert-warning">'+
                                        '<a href="#" class="close" data-dismiss="alert">&times;</a>'+
                                        '<strong>Warning!</strong> '+data.message+
                                        '</div>';
                                
                         $("#credit-card-model-msg").html(alert);
                      }
   
                },
                error:function(data,status,er) { 
                    alert("error: "+data+" status: "+status+" er:"+er);
                }
            });
             return false;
         }
         //search for product
           $.fn.searchProducts = function(data) {
               $(this).search("pro");
           }
           
         $.fn.search = function(formId,elementId) {
           var form = $(formId);
              $.ajax({
              url: $(form).attr('action'),
              data: $(form).serialize(), 
              type: 'GET',
              success: function(data) {                   
                 $(elementId).html(data);
                },
                error:function(data,status,er) { 
                    alert("error: "+data+" status: "+status+" er:"+er);
                }
            });
           /*
            * "[{\"price\":20,\"quantity\": 1,\"totalAmount\":3,\"product\":{\"id\":10}},\n\
                      {\"price\":40,\"quantity\": 1,\"totalAmount\":5,\"product\":{\"id\":11}}]"
            */
         return false;
         };
         
         $.fn.loadCustomerPage = function(id){
             
             console.log("loading customer page" ,id);
             console.log("page");
             
         }
         
         //remove card
        $.fn.removeCard = function(){
            $.ajax({
              url: "removecard",
              data: {render: "ajax"}, 
              type: 'POST',
              success: function(resp) {
                    $("#userAccountContainer").html(resp);
                     console.log(resp);  
                },
                error:function(data,status,er) { 
                    alert("error: "+data+" status: "+status+" er:"+er);
                }
              });
              
           return false;
        }
 });
            
