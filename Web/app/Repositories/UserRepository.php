<?php

namespace App\Repositories;
use App\Models\MUser;
use App\Repositories\BaseRepository;
use Illuminate\Support\Facades\Schema;

class UserRepository extends BaseRepository
{
    public function __construct(MUser $user) 
    {
        $this->model = $user;
    }

    public static function findUser($name)
    {
        return MUser::where('c_email', $name)->first();
    }
    public function getAllUsers() 
    {
        $users = $this->model->get();
        return $users;
    }
    

}