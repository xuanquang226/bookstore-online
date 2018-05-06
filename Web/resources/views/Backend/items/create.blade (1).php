
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
                            <h2>Thêm sách mới</h2>
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
                                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="testName">Hình ảnh</label>
                                                <div class="col-md-9 col-sm-9 col-xs-12">
                                                    <div class="input-group image-preview">
                                                        <!-- file-input -->
                                                        <div class="btn btn-default file-input">
                                                            <span class="pe-7s-upload"></span>
                                                            <span class="file-input-title">Chọn hình</span>
                                                            <input type="file" accept="image/png, image/jpeg, image/gif" id="testImageIcon" name="image"/> 
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
<!--                                             <div class="form-group">
                                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="testName">Âm thanh</label>
                                                <div class="col-md-9 col-sm-9 col-xs-12">
                                                    <div class="input-group image-preview">

                                                        <div class="btn btn-default file-input">
                                                            <span class="pe-7s-upload"></span>
                                                            <span class="file-input-title">Chọn âm thanh</span>
                                                            <input type="file"accept="audio/*" id="testImageIcon" name="sound"/> 
                                                        </div>
                                                    </div>
                                                </div>
                                            </div> -->

                                        </div>
                                    </div>
                                </div>
                                <div class="ln_solid"></div>
                                <div class="form-group">
                                    <div class="col-md-7">
                                        <button type="button" class="btn btn-primary pull-right" onclick="location.href='{{ URL::asset('/admin/story_item')}}';">Cancel</button>
                                        <button type="submit" class="btn btn-success pull-right" style=" margin-right: 10px;">Lưu</button>
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