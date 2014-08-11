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

            <form class="navbar-form navbar-left" role="search">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Search">
                </div>
                <button type="submit" class="btn btn-default">Submit</button>
            </form>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Manage <span class="caret"></span></a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="${pageContext.request.contextPath}/semesters/add">Add</a></li>
                            <li class="divider"></li>                           
                        </ul>
                    </li>
                </ul>
            </sec:authorize>
            
            
             <div class="nav navbar-nav navbar-right">

                <button data-toggle="dropdown" class="btn dropdown-toggle">Manage<span class="caret"></span></button>

                <ul class="dropdown-menu">           
                    <li><a href="${pageContext.request.contextPath}/semesters/add">Add Semester</a></li>
                     
                </ul>

            </div>
            
            
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>