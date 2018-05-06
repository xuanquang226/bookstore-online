<?php

namespace App\Http\Controllers\API;

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
    public function getListBook(Request $request,ProductRepository $productRepository)
    {
        $lists = $productRepository->getListProduct();
        if (!empty($lists)) {
        $data = ['resultCode' => 0, //Trả về dữ liệu kết quả
            'Message' => 'List Book',
            'data' => $lists
        ];
                return response()->json($data);
        }else{
            return response()->json(
                ['resultCode' => -1, //Trả về dữ liệu kết quả
                    'Message' => 'Data not found',
                    'data' => null
                ]);
        }
    }
}
