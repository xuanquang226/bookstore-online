<?php

namespace App\Http\Controllers\API;

use Illuminate\Http\Request;
use App\Http\Controllers\Controller;
use App\Repositories\CategoryRepository;
use Illuminate\Support\Facades\Validator;


class CategoryController extends Controller
{
    /* Danh sách danh mục */
    public function index(Request $request, CategoryRepository $categoryRepository)
    {
        /* Mảng danh sách danh mục*/
        /* End */

        /* Tạo mảng kiểm tra gán tên cho danh mục */
        $category_list = $categoryRepository->getListCategory();
        // var_dump($category_list);die();

        return view('Backend.category.index', ['category_list' => $category_list]);
    }

    /* Create Category Form*/
    public function createCategoryForm(CategoryRepository $categoryRepository)
    {
        /* Mảng danh mục cho dropdown*/


        return view('Backend.category.create');
    }

    /* Create Category */
    public function createCategory(Request $request, CategoryRepository $categoryRepository)
    {
        $validator = Validator::make(
            $request->all(),
            [
                
                'book_type' => 'required',

            ]
            ,
            [
                
                'book_type.required' => 'Vui lòng nhập loại sách',
            ]
        );
        if ($validator->fails()) {
//            print_r($validator->errors()->first());
//            die();
            return redirect('/admin/book-type/add')->with('notify', $validator->errors()->first())->withInput();
        } else {
            $book_type = $request->input('book_type');

//            $imageURL = "";
//            $soundURL = "";
            /* Thêm danh mục cho table m_category */
            $item_category = $categoryRepository->create(
                [
                    
                    "c_type_name" => $book_type,
                ]
            );

            /* Thêm danh mục (vn) cho table m_category_translation */

            /* Thêm danh mục (jp) cho table m_category_translation */

            /* Thêm danh mục (jp) cho table m_category_translation */

            /* Kiểm tra trạng thái và redirect về trang danh sách danh mục*/
            if($item_category!=null) {
                return redirect('/admin/book-type')->with('notify-success', 'Thêm danh mục thành công');
            } else {
                return redirect('/admin/book-type')->with('notify-error', 'Thêm danh mục thất bại');
            }            
        }

    }


    //view update a quiz
    public function editCategoryForm($id, CategoryRepository $categoryRepository)
    {
        $validator = Validator::make(['category_id' => $id], [
            'category_id'   => 'exists:t_category,c_type_id'
        ], [
            'category_id.required'      => 'Không tồn tại',
        ]);

        if ($validator->fails())
        {
            return redirect()->back();
        }
        else
        {
            $cate = $categoryRepository->find((int)$id);
            return view('Backend.category.create', ['category' => $cate]);
        }
    }

    //update a quiz
    public function updateCategory(Request $request, $id, CategoryRepository $categoryRepository)
    {
        $cate = $categoryRepository->find((int)$id);
        $validator = Validator::make(
            $request->all(),
            [
                'book_type' => 'required',

            ]
            ,
            [
                'book_type.required' => 'Vui lòng nhập loại sách',
            ]
        );

        if ($validator->fails())
        {
            return redirect()->back()->withErrors($validator)->withInput();
        }
        else
        {
         $book_type = $request->input('book_type');
         // var_dump($book_type);die();
            $item_category = $categoryRepository->update(
                [
                    
                    "c_type_name" => $book_type,
                ],
                $id,"c_type_id"
            );
            if($item_category!=null) {
                return redirect('/admin/book-type')->with('notify-success', 'Thêm danh mục thành công');
            } else {
                return redirect('/admin/book-type')->with('notify-error', 'Thêm danh mục thất bại');
            }            
        }

    }

    //detail a quiz
    public function detailQuiz($id, CategoryRepository $categoryRepository)
    {
        $validator = Validator::make(['quiz_id' => $id], [
            'quiz_id'   => 'exists:m_quiz,quiz_id'
        ], [
            'quiz_id.require'   =>'Không tồn tại câu hỏi',
        ]);

        if ($validator->fails())
        {
            return redirect()->back();
        }
        else
        {
            $quiz = $quizRepository->find((int)$id);
            return view('Backend.quizs.detail_quiz', ['quiz' => $quiz]);
        }
    }

    //destroy a quiz
    public function destroyQuiz($id, CategoryRepository $categoryRepository)
    {
        //update delete_flag
        $quizRepository->update(
            [
                "deleted_flag"          => 1,
            ],
            $id,
            "quiz_id"
        );
        return redirect()->back();
    }
}
