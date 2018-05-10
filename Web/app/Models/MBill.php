<?php

namespace App\Models;


use App\Models\BaseModel;

class MBill extends BaseModel
{
    protected $table = 't_bill';

    protected $primaryKey = 'id';

    public $timestamps = false;


    protected $fillable = [
        'bill_id',
        'cus_id',
        'pro_id',
        'quantity',
        'total_price',
        'created_time', 

    ];

    protected $guarded = [];


}
