<?php

namespace App\Models;


use App\Models\BaseModel;

class MProducts extends BaseModel
{
    protected $table = 't_product';

    protected $primaryKey = 'p_id';

    public $timestamps = false;


    protected $fillable = [
        'c_type_id',
        'p_name',
        'p_price',
        'p_quantity', 
        'p_desc',
        'p_new_flag',
        'p_img',

    ];

    protected $guarded = [];


}
