<?php

namespace App\Repositories;
use App\Models\MCustomer;
use App\Repositories\BaseRepository;
use Illuminate\Support\Facades\Schema;

class CustomerRepository extends BaseRepository
{
    public function __construct(MCustomer $customer) 
    {
        $this->model = $customer;
    }

    public static function findUser($name)
    {
        return MUser::where('email', $name)->first();
    }
    public function getAllUsers() 
    {
        $users = $this->model->get();
        return $users;
    }
    public function getByLoginId($userNameOrEmail) {
        $userInfo = $this->model
            ->where('c_mail', $userNameOrEmail)
            ->first([
                'id',
                'c_mail',
                'c_phone',
                'c_name',
                'c_password',
                'c_address',
                'c_coin',
                'c_api_token',
            ]);
        return $userInfo;
    }
    public function check_API_token_User($login_id) {
        $api_token = $this->model->where('c_mail',$login_id)->first(['c_api_token']);
        return $api_token;
    }
    public function create_api_token($api_token,$email)
    {          $this->model->where('c_mail',$email)->update(['c_api_token' => $api_token]);
        return $this->model->where('c_mail',$email)->get([
            'id',
            'c_mail',
            'c_phone',
            'c_name',
            'c_coin',
            'c_api_token',
            'c_address',
        ]);
    }
    // public function refreshLogin($user_id)
    // {
    //     return $this->model->where('user_id',$user_id)->get([
    //         'user_id',
    //         'login_id',
    //         'login_type',
    //         'c_email',
    //         'phone_num',
    //         'nick_name',
    //         'avatar',
    //         'remain_coin',
    //         'api_token',
    //     ]);
    // }

    //////////////////////////Regist
    public function getLogin() {
        $arrayEmail = $this->model->get(['c_mail']);
        return $arrayEmail->toArray();
    }
    public function registerUser($data)
    {
        $this->model->insert([
            'c_mail'         => $data['c_mail'],
            'c_password'      => $data['c_password'],
            'c_phone'     => $data['c_phone'],
            'c_name'     => $data['c_name'],
            'c_address'     => $data['c_address'],
            'c_coin'   => $data['c_coin'],// =0
            'c_api_token'     => $data['c_api_token'],
        ]);
        return $this->model->where('c_mail',$data['c_mail'])->first(['id']);
    }
}