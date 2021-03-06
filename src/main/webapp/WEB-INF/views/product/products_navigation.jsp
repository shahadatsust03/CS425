<nav class="navbar navbar-default" role="navigation">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
   
       <form class="navbar-form navbar-left" role="search" id="productSearch" action="products/query" onsubmit="$(this).search('#productSearch','#productsList');return false;">
         <div class="form-group">
          <input type="text" class="form-control" name="product" placeholder="Search" />
         </div>
           
           <div class="btn-group">
                <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">Price <span class="caret"></span></a>
                <ul class="dropdown-menu">
                  <li><a href="#">$20 - $50</a></li>
                  <li><a href="#">$50 - $100</a></li>
                  <li><a href="#">Item III</a></li>
                </ul>
           </div>
  

        <button type="submit" class="btn btn-primary btn-sm" >Search</button>
        </form>
         <sec:authorize access="hasRole('ROLE_ADMIN')">
            <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                      <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                          <span class="glyphicon glyphicon-cog"></span>
                          Manage <span class="caret"></span></a>
                      <ul class="dropdown-menu" role="menu">
                        <li><a href="product/add">Add</a></li>
                        <li class="divider"></li>
                        <li><a href="#">Edit</a></li>
                        <li class="divider"></li>
                        <li><a href="#">Remove</a></li>
                        <li class="divider"></li>
                      </ul>
                    </li>
              </ul>
         </sec:authorize>
     
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>