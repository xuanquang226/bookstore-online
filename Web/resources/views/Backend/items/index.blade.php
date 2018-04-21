
@extends('Backend.masterpage.masterpage')
@section('content')
    <div class="container-fluid">
        <div class="row">
                            <div class="title_right">
                    <div class="col-md-5 col-sm-5 col-xs-12 form-group pull-right top_search">
                        <div class="input-group">
                            <input type="text" class="form-control" placeholder="Search for...">
                            <span class="input-group-btn">
                                <button class="btn btn-default" type="button">Tìm kiếm!</button>
                            </span>
                        </div>

                    </div>


                </div>
            <div class="text-right" style="margin-bottom:30px">
                <button type="submit" class="btn btn-success btn-fill" onclick="location.href='{{ URL::asset('/admin/book/create')}}';">Tạo mới</button>
            </div>
            <div class="col-md-12">
                <div class="card">
                    <div class="header">
                        <h4 class="title">Tất cả sách</h4>
                    </div>
                    <div class="content table-responsive table-full-width">
                        <table class="table table-hover table-striped">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Thể loại</th>
                                    <th>Tên sách</th>
                                    <th>Mô tả</th>
                                    <th>Giá</th>
                                    <th>Số lượng</th>
                                    <th width=300px class="text-center">Ảnh</th>
                                    <th>Hành động</th>
                                </tr>
                                </thead>
                                <tbody>
                                @foreach($lists as $row)
                                    <tr story-id="{{$row->p_id}}">
                                        <th>{{$row->p_id}}</th>
                                        <th>{{$row->c_type_name}}</th>
                                        <th>{{$row->p_name}}</th>
                                        <th>{{$row->p_desc}}</th>
                                        <th>{{$row->p_price}}</th>
                                        <th>{{$row->p_quantity}}</th>
                                        <th><a href="{{ URL::asset('/admin/story_item/des/'.$row->p_id)}}">
                                            <img class="img-responsive" src="{{ URL::asset('upload/image/book-items/'.$row['p_img']) }}" style="width: 100px; height: 150px; display: block; margin-left: auto;margin-right: auto;"></a></th>

                                        <td>
                                            {{-- Xem chỉnh sửa--}}
                                            <button style="background: none; border: none" title="Chỉnh sửa"
                                                    onclick="location.href='{{ URL::asset('/admin/story_item/edit/'. $row['p_id'])}}';">
                                                <span class="pe-7s-tools" style="font-size: 20px;font-weight: bold;"></span>
                                            </button>
                                            {{--Nút Xóa--}}
                                            <div class="btn-delete-item pe-7s-trash" style="font-size: 20px;color: red;font-weight: bold;"></div>
                                        </td>
                                    </tr>
                                @endforeach
                            </tbody>
                        </table>
                        <hr>
                    </div>
                </div>
            </div> 
            </div>                
        </div>
    </div>
@endsection