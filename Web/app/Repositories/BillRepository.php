<?php

namespace App\Repositories;
use App\Models\MBill;
use App\Repositories\BaseRepository;
use Illuminate\Support\Facades\Schema;

class BillRepository extends BaseRepository
{
    public function __construct(MBill $item) 
    {
        $this->model = $item;
    }

    public function getLastID()
    {
        return $this->model->max('bill_id');
    }

        public function insertBill($data)
    {
        $this->model->insert([
            'cus_id'         => $data['cus_id'],
            'pro_id'      => $data['pro_id'],
            'total_price'     => $data['total_price'],
            'quantity'     => $data['quantity'],
            'bill_id'     => $data['bill_id'],
            'created_time'     => $data['created_time'],
        ]);
        return $data;
    }


}