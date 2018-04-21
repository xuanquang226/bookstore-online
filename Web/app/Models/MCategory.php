<?php

namespace App\Models;

use App\Models\BaseModel;

class MCategory extends BaseModel
{
    protected $table = 't_category';

    protected $primaryKey = 'c_type_id';

    public $timestamps = false;

    protected $fillable = [
        'c_type_name',
    ];

    protected $guarded = [];


}
