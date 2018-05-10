<?php

namespace App\Http\Controllers\API;

use App\Models\MBill;
use Illuminate\Http\Request;
use App\Http\Controllers\Controller;
use App\Repositories\BillRepository;
use App\Repositories\ProductRepository;

class BillController extends Controller
{
    public function insertBill(Request $request ,BillRepository $billRepository,ProductRepository $productRepository)
    {
        // $json = array(  'cus_id' => 1, 
        //                 'product' => [
        //                             '0'=> ['pro_id' => 15,
        //                                     'quantity'=> 10,
        //                                 ],
        //                             '1'=> ['pro_id' => 6,
        //                                     'quantity'=> 12,
        //                                 ],
        //                             ],
        //         );
        //     var_dump(json_encode($json));
        

        $cus_id = $request->cus_id;
        $product = $request->product;
        $bill_id = $billRepository->getLastID()+1;
        $data_bill = ['cus_id' => $cus_id,];
    
        foreach ($product as $item) {
            $data_bill['bill_id'] = $bill_id;
            $data_bill['pro_id'] = $item['pro_id'];
            $data_bill['quantity']  = $item['quantity'];
            $price = $productRepository->getProductPrice($data_bill['pro_id'])->p_price;
            $data_bill['total_price'] = $price * $data_bill['quantity'];
            $data_bill['created_time'] = date("Y-m-d H:i:s");
            $result = $billRepository->insertBill($data_bill);
        }   
        if ($result) {
                    return response()->json([
                        'resultCode'    => 0,
                        'message'       => 'Đã lưu hóa đơn',
                        'data'          => $result,
                    ]);
            } else {
                    return response()->json([
                        'resultCode'    => -1,
                        'message'       => 'Lưu thất bại',
                        'data'          => NULL,
                    ]);
            }

    }
    
}