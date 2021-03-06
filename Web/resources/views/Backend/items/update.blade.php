
@extends('Backend.masterpage.masterpage')
@section('content')
    <!-- page content -->
    <div class="right_col" role="main">
        <div class="">
            <div class="clearfix"></div>

            <div class="row">

                <div class="col-md-12 col-sm-12 col-xs-12">
                    <div class="x_panel">
                        <div class="x_title">
                            <h3>Chỉnh sửa danh mục</h3>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content">
                            @if (session('notify'))
                                <div class="alert alert-error" style="color:red;">
                                    {{ session('notify') }}
                                </div>
                            @endif
                            <form class="form-horizontal form-label-left" method="post" enctype="multipart/form-data">
                                {{ csrf_field() }}
                                <div class="" role="tabpanel" data-example-id="togglable-tabs">
                                    <ul id="myTab" class="nav nav-tabs bar_tabs" role="tablist">
                                        <li role="presentation" class="active"><a href="#tab_content1" id="home-tab"
                                                                                  role="tab" data-toggle="tab"
                                                                                  aria-expanded="true">Chung</a>
                                        </li>
                                        <li role="presentation" class=""><a href="#tab_content2" role="tab"
                                                                            id="profile-tab" data-toggle="tab"
                                                                            aria-expanded="false">Đa ngôn ngữ</a>
                                        </li>
                                    </ul>
                                    <div id="myTabContent" class="tab-content">
                                        <div role="tabpanel" class="tab-pane fade active in" id="tab_content1"
                                             aria-labelledby="home-tab">
                                            <br/>


                                            <div class="form-group">
                                                <label class="control-label col-md-3 col-sm-3 col-xs-12">Tên sách</label>
                                                <div class="col-md-9 col-sm-9 col-xs-12">
                                                    <input type="text" name="bookname" class="form-control" placeholder="Tên sách">
                                                </div>
                                            </div>
                                            
                                            <div class="form-group">
                                                <label class="control-label col-md-3 col-sm-3 col-xs-12">Thể loại</label>
                                                <div class="col-md-9 col-sm-9 col-xs-12">
                                                    <select id="cate" name="booktype" class="form-control">
                                                        @foreach($category_list as $row)
                                                        <option value="{{ $row->c_type_id }}">{{ $row->c_type_name }}</option>
                                                        @endforeach
                                                    </select>
                                                </div>
                                            </div>
                                            
<div class="form-group">
                                                <label class="control-label col-md-3 col-sm-3 col-xs-12">Mô tả</label>
                                                <div class="col-md-9 col-sm-9 col-xs-12">
                                                    <input type="text" name="description" class="form-control" placeholder="Mô tả">
                                                </div>
                                            </div>
                                             <div class="form-group">
                                                <label class="control-label col-md-3 col-sm-3 col-xs-12">Giá</label>
                                                <div class="col-md-9 col-sm-9 col-xs-12">
                                                    <input type="text" name="price" class="form-control" placeholder="Giá">
                                                </div>
                                            </div>
                                             <div class="form-group">
                                                <label class="control-label col-md-3 col-sm-3 col-xs-12">Số lượng</label>
                                                <div class="col-md-9 col-sm-9 col-xs-12">
                                                    <input type="text" name="quantity" class="form-control" placeholder="Số lượng">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-md-3 col-sm-3 col-xs-12" id="change_img">Hình ảnh hiện tại:</label>
                                                <!-- Hiển thị hình ảnh cũ/khi thay đổi hình ảnh -->
                                                <div class="col-md-9 col-sm-9 col-xs-12">
                                                    <img id="output" src="{{ URL::asset('upload/image/book-items/'. $item_book->p_img) }}" alt="image" width="10%" class="img-responsive">
                                                </div>
                                                <script>
                                                    var loadFile = function(event) {
                                                        var output = document.getElementById('output');
                                                        output.src = URL.createObjectURL(event.target.files[0]);
                                                        document.getElementById('change_img').innerHTML = "Hình ảnh sẽ thay thế:";
                                                        
                                                    };
                                                </script>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="testName">Hình ảnh</label>
                                                <div class="col-md-9 col-sm-9 col-xs-12">
                                                    <div class="input-group image-preview">
                                                        <!-- file-input -->
                                                        <div class="btn btn-default file-input">
                                                            <span class="pe-7s-upload"></span>
                                                            <span class="file-input-title">Chọn hình</span>
                                                            <input type="file" onchange="loadFile(event)" name="image" multiple="">
                                                           
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        <div role="tabpanel" class="tab-pane fade" id="tab_content2"
                                             aria-labelledby="profile-tab">
                                            <div class="clearfix"></div>
                                        </div>
                                    </div>
                                </div>
                                <div class="ln_solid"></div>
                                <div class="form-group">
                                    <div class="col-md-9 col-sm-9 col-xs-12 col-md-offset-3">
                                        <button type="button" class="btn btn-primary">Cancel</button>
                                        <button type="reset" class="btn btn-primary">Reset</button>
                                        <button type="submit" class="btn btn-success">Submit</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- /page content -->



@endsection