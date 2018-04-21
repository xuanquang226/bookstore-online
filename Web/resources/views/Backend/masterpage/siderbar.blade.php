<?php
    $paramater = Request::segment(1);
    $view = Request::segment(2);
?>

<div class="sidebar-wrapper">
    <div class="logo">
        <a href="http://www.creative-tim.com" class="simple-text">
            Kaffeines
        </a>
    </div>

    <ul class="nav">
        @if($paramater == "admin" && $view == "")
        <li class="active">
            <a href="{{ URL::asset('/admin')}}">
                <i class="pe-7s-home"></i>
                <p>Trang chủ</p>
            </a>
        </li>
        @else
        <li class="">
            <a href="{{ URL::asset('/admin')}}">
                <i class="pe-7s-home"></i>
                <p>Trang chủ</p>
            </a>
        </li>
        @endif

        @if($view == "users")
        <li class="active">
            <a href="{{ URL::asset('/admin/users')}}">
                <i class="pe-7s-user"></i>
                <p>Quản trị viên</p>
            </a>
        </li>
        @else 
        <li class="">
                <a href="{{ URL::asset('/admin/users')}}">
                    <i class="pe-7s-user"></i>
                    <p>Quản trị viên</p>
                </a>
            </li>
        @endif

        @if($view == "book-type")
        <li class="active">
            <a href="{{ URL::asset('admin/book-type')}}">
                <i class="pe-7s-photo"></i>
                <p>Thể loại sách</p>
            </a>
        </li>
        @else
        <li>
            <a href="{{ URL::asset('admin/book-type')}}">
                <i class="pe-7s-photo"></i>
                <p>Thể loại sách</p>
            </a>
        </li>
        @endif
        @if($view == "book")
        <li class="active">
            <a href="{{ URL::asset('admin/book')}}">
                <i class="pe-7s-news-paper"></i>
                <p>Sách</p>
            </a>
        </li>
        @else
        <li>
            <a href="{{ URL::asset('admin/book')}}">
                <i class="pe-7s-news-paper"></i>
                <p>Sách</p>
            </a>
        </li>
        @endif


    </ul>
</div>