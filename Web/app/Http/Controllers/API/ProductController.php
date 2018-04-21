<?php

namespace App\Http\Controllers\Backend;

use Illuminate\Http\Request;
use App\Http\Controllers\Controller;
use App\Repositories\CategoryRepository;
use App\Repositories\ProductRepository;
use Illuminate\Support\Facades\Validator;
use App\Models\MCategory;
use Illuminate\Support\Facades\Input;
use Illuminate\Support\Facades\File;

class ProductController extends Controller
{
    /*Trả về view danh sách các item*/
    public function index(ProductRepository $productRepository)
    {
        $lists = $productRepository->getListProduct();
        // var_dump($lists);die();
        return view('Backend.items.index',['lists' => $lists]);
    }

    public function detail($item_id,ProductRepository $productRepository)
    {
        $lists = $productRepository->getAllItem('en');
        $obj = $productRepository->findItem($item_id,'en');
        $validator = Validator::make(['item_story_id' => $item_id], [
            'item_id'   => 'exists:item_id,item_id'
            ], []);

        if ($validator->fails())
        {
            return redirect()->back();
        }
        else
        {
            return view('Backend.items.index', ['lists' => $lists,'firstObj' => $obj]);

        }
    }

    /*Trả về view tạo mới một item với danh sách parent_id */
    public function create(CategoryRepository $categoryRepository)
    {
        /*$category_list = $categoryRepository->getListCategoryWithLangParentID('en', 0);
        foreach ($category_list as &$row) {
            $sub = $categoryRepository->getListCategoryWithLangParentID('en', $row['category_id']);
            $row['sub'] = $sub;
            /*foreach ($row['sub'] as &$sub) {
                $sub_con = $categoryRepository->getListCategoryWithLangParentID('en', $sub['category_id']);
                $sub['sub_con'] = $sub_con;
            }
        }*/
        $category_list = MCategory::get();

        return view('Backend.items.create',['category_list' => $category_list]);
    }

    /*
        *Thực hiện chức năng tạo mới.
        *Có upload file.
    */
    public function postCreate(Request $request, ProductRepository $productRepository)
    {
        //Kiểm tra validate
        $validator = Validator::make(
            $request->all(),
            [
                
                'bookname'      =>  'required|unique:t_product,p_name',
                'description'    =>  'required',
                'price'    =>  'required',
                'quantity'    =>  'required',
                'image'     =>  'required|image|max:4096',


            ],
            [
                
                'bookname.required'             => 'Vui lòng nhập mã cho bối cảnh',
                'bookname.unique'               => 'Tên sách này đã tồn tại',          
                'price.required'           => 'Vui lòng nhập giá sách',
                'image.image'               => 'Hình ảnh bạn chọn không hợp lệ',
                'image.max'                 => 'Hình ảnh phải nhỏ hơn 4MB',
                'quantity.required'            => 'Vui lòng nhập số lượng',


            ]);
        if($validator->fails())
        {
            return redirect()->back()->with('notify', $validator->errors()->first())->withInput();
        }
        else
        {          
            $bookname = $request->input('bookname');
            $booktype = $request->input('booktype');
            $description = $request->input('description');
            $price = $request->input('price');
            $quantity = $request->input('quantity');
            $image = $request->input('image');
            //Thực hiện chức năng tạo
            $book_item = $productRepository->create(
                [                    
                    "p_name"              => $bookname,
                    "c_type_id"           => $booktype,
                    "p_desc"              => $description,
                    "p_price"             => $price,
                    "p_quantity"          => $quantity,
                    "p_new_flag"          => 1,
                    
                ]);
            /*
                *uplaod media.
                *Thực hiện lưu file theo id + thời gian tạo để dễ dàng phân biệt, và không bị trùng.
                *Đối với file âm thanh. kiểm tra đuôi trước khi lưu, Validate không làm được điều này nên phải viết riêng.
            */
            if ($book_item->p_id > 0) {
                // Edit image/sound here.
                if (Input::hasfile('image')) {
                    //image
                    $nameImage = Input::file('image')->getClientOriginalExtension();
                    $imageURL = $book_item->p_id . "." . date("H_i_s", time()) . "." . $nameImage;
                    Input::file('image')->move(public_path('upload/image/book-items/'), $imageURL);
                } else {
                    $imageURL = "";
                }

                // Thực hiện update file
                $productRepository->update([
                    'p_img' => $imageURL,
                ], $book_item->p_id, "p_id");

            } else {
                return redirect('book')->with('notify', "Xảy ra lỗi ở quá trình upload file!");
            }
            
            /* Thêm bối cảnh (vn) cho table m_item_story_translation */
           
            /* Kiểm tra trạng thái và redirect về trang danh sách danh mục*/
            if($book_item!=null) {
                return redirect('/admin/book')->with('notify-success', 'Thêm danh mục thành công');
            } else {
                return redirect('/admin/book')->with('notify-error', 'Thêm danh mục thất bại');
            }    
        }
    }
    /*
        function trả về view chỉnh sửa với dữ liệu là danh sách parent_id
    */
    public function update($id,Request $request, CategoryRepository $categoryRepository, ProductRepository $poductRepository)
    {
        $validator = Validator::make(['item_story_id' => $id], [
            'item_story_id'   => 'exists:m_item_story,item_story_id'
        ], [
            'item_story_id.exists'      => 'Không tồn bối cảnh',
        ]);

        if ($validator->fails())
        {
            return redirect()->back();
        }
        else
        {
            $category_list = MCategory::where('parent_id', 0)->get();
            $item_story = $ProductRepository->find((int)$id);
            return view('Backend.items.update', ['item_story' => $item_story,'category_list' => $category_list]);
        }
    }

    /*
        *Thực hiện chức năng chỉnh sửa.
    */
    public function postUpdate($id, Request $request, ProductRepository $ProductRepository,ItemTranslateRepository $itemTranslateRepository )
    {
        //Tìm kiếm item theo id và kiểm tra validate khi chỉnh sửa
        $item = $ProductRepository->find((int) $id);
        $validator = Validator::make(
            $request->all(),
            [
                
                'code'              =>  'required|min:5|max:5|unique:m_item_story,code',
                'status'            =>  'required',
                'name_vn'           =>  'required',
                'name_en'           =>  'required',
                'name_jp'           =>  'required',
                'description_vn'    =>  'required',
                'description_en'    =>  'required',
                'description_jp'    =>  'required',
                'image'             => 'sometimes|image',
                
            ],
            [
                
                'code.required'             => 'Vui lòng nhập mã cho bối cảnh',
                'code.min'                  => 'Mã tìm kiếm có tối thiểu 5 ký tự',
                'code.max'                  => 'Mã tìm kiếm có tối đa 5 ký tự',
                'code.unique'               => 'Mã tìm kiếm này đã tồn tại', 
                'status.required'           => 'Vui lòng chọn trạng thái cho bối cảnh',
                'name_vn.required'          => 'Vui lòng nhập tên Tiếng Việt cho bối cảnh',
                'name_en.required'          => 'Vui lòng nhập tên Tiếng Anh cho bối cảnh',
                'name_jp.required'          => 'Vui lòng nhập tên Tiếng Nhật cho bối cảnh',
                'description_vn.required'   => 'Vui lòng nhập mô tả Tiếng Việt cho bối cảnh',
                'parent_id.description_en'  => 'Vui lòng nhập mô tả Tiếng Anh cho bối cảnh  ',
                'description_jp.required'   => 'Vui lòng nhập mô tả Tiếng Nhật cho bối cảnh ',

            ]);

        if ($validator->fails())
        {
            return redirect()->back()->with('notify', $validator->errors()->first())->withInput();
        }
        else
        {
            
            $code = $request->code;
            $status = $request->status;
            $name_vn = $request->name_vn;
            $name_en = $request->name_en;
            $name_jp = $request->name_jp;
            $description_vn = $request->description_vn;
            $description_en = $request->description_en;
            $description_jp = $request->description_jp;
            
            //upload file
            if (Input::hasfile('image'))
            {
                $nameImage = $request->file('image');
                $extension = $nameImage->getClientOriginalExtension();

               // return $nameImage;
                $imageURL = $id . "." . date("H_i_s"). ".". $extension;
                $oldImage = $item->url_image;

                if($oldImage != '')
                {
                    if(File::exists(public_path('upload/image/item_Story/') . $oldImage))
                    {
                        unlink(public_path('upload/image/item_Story/') . $oldImage);   
                    } 
                }

                $nameImage->move(public_path('upload/image/item_Story/'), $imageURL);
                
                $ProductRepository->update(
                    [
                        "url_image"          => $imageURL,
                    ],
                    $id,
                    "item_story_id"
                );   
            }
            //upload audio
            if(Input::hasfile('sound'))
            {
                //sound
                $nameSound = Input::file('sound')->getClientOriginalExtension();
                $oldSound = $item->sound;
                if($nameSound == 'mp3')
                {
                    $soundURL = $id . "." . date("H_i_s",time()). ".". $nameSound;
                    if($oldSound != '')
                    {
                        //Xóa file cũ khi có chỉnh sửa để tránh lãng phí bộ nhớ
                        if(File::exists(public_path('upload/audio/item_Story/') . $oldSound))
                        {
                            unlink(public_path('upload/audio/item_Story/').$oldSound);   
                        }
                    }
                    Input::file('sound')->move(public_path('upload/audio/item_Story/'), $soundURL);
                    //update file
                    $ProductRepository->update(
                        [
                            "sound"          =>$soundURL,
                        ],
                        $id,
                        "item_story_id"
                    );
                }else
                {
                    return redirect()->back()->withErrors("Vui lòng chọn đúng kiểu âm thanh")->withInput();                    
                }
                
                
            }

            //update item
            $ProductRepository->update([
                "code"              => $code,                
                "status"            => $status,
            ],
            $id,
            "item_story_id");
            
            /* Chỉnh sửa bối cảnh (vn) cho table m_item_story_translation */
            $item_story_translation_vn = $itemTranslateRepository->updateItemTranslate(
                [
                    "title"         => $name_vn,
                    "description"   => $description_vn,
                    "locale"        => 'vi',

                ],$item->item_story_id,
                "item_story_id", 
                'vi',
                "locale"
            );

            /* Chỉnh sửa bối cảnh (en) cho table m_item_story_translation */
            $item_story_translation_en = $itemTranslateRepository->updateItemTranslate(
                [
                    "title"         => $name_en,
                    "description"   => $description_en,
                    "locale"        => 'en',

                ],$item->item_story_id,
                "item_story_id", 
                'en',
                "locale"
            );

            /* Chỉnh sửa bối cảnh (jp) cho table m_item_story_translation */
            $item_story_translation_jp = $itemTranslateRepository->updateItemTranslate(
                [
                    "title"         => $name_jp,
                    "description"   => $description_jp,
                    "locale"        => 'jp',

                ],$item->item_story_id,
                "item_story_id", 
                'jp',
                "locale"
            );
            /* Kiểm tra trạng thái và redirect về trang danh sách danh mục*/
            if($item!=null && $item_story_translation_vn!=null && $item_story_translation_en!=null && $item_story_translation_jp!=null) {
                return redirect('/admin/story_item')->with('notify-success', 'Thêm danh mục thành công');
            } else {
                return redirect('/admin/story_item')->with('notify-error', 'Thêm danh mục thất bại');
            }   

        }
    }

    /*Thực hiện update cờ delete_flag*/
    public function delete($id,ProductRepository $productRepository )
    {
        //get list quiz choosed 
        //$list_id = $rq->get('list_id');
        
            $productRepository->delete(
                $id,
                "p_id"
            );
        
        return redirect()->back();
    }
}
