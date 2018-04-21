@extends('Backend.masterpage.masterpage')
@section('content')

    <!-- page content -->
    <div class="right_col" role="main">
        <div class="">
            <div class="page-title">
                <div class="title_left">
                    <h3>Quản lý danh mục
                        <small></small>
                    </h3>
                </div>

                <div class="title_right">
                    <div class="col-md-5 col-sm-5 col-xs-12 form-group pull-right top_search">
                        <div class="input-group">
                            <input type="text" class="form-control" placeholder="Search for...">
                            <span class="input-group-btn">
                                <button class="btn btn-default" type="button">Tìm kiếm!</button>
                            </span>
                        </div>
                        <div style="float: right; margin-top: 50px">
                            <button type="submit" class="btn btn-success"
                                    onclick="location.href='{{ URL::asset('/admin/book-type/add')}}';">Thêm danh mục
                            </button>
                        </div>
                    </div>


                </div>
            </div>

            <div class="clearfix"></div>

            <div class="row">

                <div class="col-md-12 col-sm-12 col-xs-12">
                    <div class="x_panel">
                        <div class="x_title">
                            <h2>Tất cả thể loại sách
                            </h2>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content">
                            @if (session('notify-success'))
                                <div class="alert alert-success">
                                    {{ session('notify-success') }}
                                </div>
                            @elseif(session('notify-error'))
                                <div class="alert alert-error">
                                    {{ session('notify-error') }}
                                </div>
                            @endif

                            {{--<p class="text-muted font-13 m-b-30">
                              Responsive is an extension for DataTables that resolves that problem by optimising the table's layout for different screen sizes through the dynamic insertion and removal of columns from the table.
                            </p>--}}

                            <table id="datatable-responsive"
                                   class="table table-striped table-bordered dt-responsive nowrap" cellspacing="0"
                                   width="100%">
                                <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Name</th>
                                    <th>Hành động</th>
                                </tr>
                                </thead>
                                <tbody>
                                @foreach ($category_list as $row)
                                    <tr>
                                        <?php $id = $row['c_type_id'];
                                        echo($id); ?>
                                        <td>{{$row['c_type_id']}}</td>
                                        <td>{{$row['c_type_name']}}</td>
                                        <td>
                                            {{-- Nút Xem chi tiết--}}
                                            <button style="background: none; border: none" title="Xem chi tiết"
                                                    onclick="location.href='{{ URL::asset('/admin/book-type/detail/')}}';'">
                                                <span class="fa fa-eye"></span>
                                            </button>

                                            {{-- Nút Chỉnh sửa--}}
                                            <button style="background: none; border: none" title="Chỉnh sửa"
                                                    onclick="location.href='{{ URL::asset('/admin/book-type/edit/')}}/<?php echo $row['c_type_id'] ?>';">
                                                <span class="fa fa-pencil-square-o"></span>
                                            </button>

                                            {{--Nút Xóa--}}
                                            <button style="background: none; border: none" title="Xóa"
                                                    onclick="location.href='{{ URL::asset('/admin/book-type/delete/')}}';">
                                                <span class="fa fa-remove"></span>
                                            </button>
                                        </td>
                                    </tr>
                                @endforeach
                                </tbody>
                            </table>


                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- /page content -->
@endsection