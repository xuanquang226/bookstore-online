<?php

namespace App\Http\Controllers\API;

use Illuminate\Http\Request;
use App\Models\MCustomer;
use App\Http\Controllers\Controller;
use App\Repositories\CustomerRepository;
use Illuminate\Support\Facades\Validator;
use Illuminate\Support\Facades\Hash;
use App\Utils\SessionManager;
use Illuminate\Support\Facades\Input;
use Illuminate\Support\Facades\File;
class CustomerController extends Controller
{
    //view user
    public function login(Request $request, CustomerRepository $customerRepository)
    {
        $validator = Validator::make($request->all(),
            [
                'c_email'          => 'required|email|regex:/^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$/',
                'c_password'          => 'required',
            ],
            [
                'c_email.required'    => 'Vui lòng nhập email!',
                'c_password.required'    => 'Vui lòng nhập mật khẩu!',
                'c_email.email'       => 'Vui lòng nhập email!',
                'c_email.regex'       => 'Vui lòng nhập email không chứa những kí hiệu đặc biệt',
            ]);
        if ($validator->fails())
        {
            return response()->json([
                'resultCode'            => -1,
                'message'               => $validator->errors()->first(),
                'data'                  => null
            ]);
        }else{
            $login_id           = $request->c_email;
            $password           = $request->c_password;
            $user               = $customerRepository->getByLoginId($login_id);
            $check_api_token    = $customerRepository->check_API_token_User($login_id);
            if (!empty($user)){

                    if ($password===$user['c_password']){
                        $user['c_api_token']  = str_random(60);
                        $create_api         = $customerRepository->create_api_token($user['c_api_token'],$login_id);
                        $json               = $create_api->toArray();
                        foreach ($create_api as $key) {
                            $user_data[] = $key;
                        }
                        return response()->json([
                            'resultCode'        => 0,
                            'message'           => 'Đăng nhập thành công!',
                            'data'              => $user_data,
                        ]);
                    }
                }
            }
            return response()->json([
                'resultCode'            => -1,
                'message'               => 'Đăng nhập thất bại!',
                'data'                  => null
            ]);
            // }
        }

    public function register(Request $request, CustomerRepository $customerRepository)
    {
        $validator = Validator::make($request->all(),
            [
                'c_mail'          => 'required|email|regex:/^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$/',
                'c_password'          => 'required',
                'c_phone'              => 'required|digits_between:10,15|numeric',
                'c_password_confirm'  => 'required|min:6|max:30|same:c_password',
                'c_address'          => 'required',
            ],
            [
                'c_phone.digits_between'        => 'Vui lòng nhập số tối thiểu 10, tối đa 15',
                'c_phone.required'          => 'Vui lòng nhập số điện thoại',
                'c_password_confirm.required'   => 'Vui lòng xác nhận mật khẩu',
                'c_mail.required'    => 'Vui lòng nhập email!',
                'c_password.required'    => 'Vui lòng nhập mật khẩu!',
                'c_mail.email'       => 'Vui lòng nhập email!',
                'c_mail.regex'       => 'Vui lòng nhập email không chứa những kí hiệu đặc biệt',
                'c_password_confirm.same'       => 'Mật khẩu không đúng. Vui lòng nhập lại',
            ]);
        if ($validator->fails())
        {
            return response()->json([
                'resultCode'            => -1,
                'message'               => $validator->errors()->first(),
                'data'                  => null
            ]);
        }else{
            $data           = array_merge($request->all(),[
                'c_api_token'     => null,
                'c_coin'     => null,
            ]);
            $arrayEmail = array();
            $check = $customerRepository->getLogin();
                foreach ($check as $row) {
                    foreach ($row as $key) {
                        $arrayEmail[]= $key;
                    }
                }

                $login_id = $request->get('c_mail');
                if (in_array($login_id, $arrayEmail,true))
                {
                    return response()->json([
                        'resultCode'    => -1,
                        'message'       => 'Tên đăng nhập này đã tồn tại',
                        'data'          => null,
                    ]);
            } else{
                    $data['c_password']      = bcrypt($data['c_password']);
                    $user = $customerRepository->registerUser($data);
                    return response()->json([
                        'resultCode'=> 0,
                        'message'   => 'Đăng ký thành công!',
                        'data'      => $user
                    ]);
            }

        }
    }
}
