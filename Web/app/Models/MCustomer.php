<?php

namespace App\Models;

use App\Models\BaseModel;

/**
 * Class MUser
 */
class MCustomer extends BaseModel
{
    protected $table = 't_customer';

    protected $primaryKey = 'id';

	public $timestamps = false;

    protected $fillable = [
        'c_email',
        'c_phone',
        'c_name',
        'c_password',
        'c_coin',
        'c_address',
        'c_api_token',
    ];

    protected $guarded = [];
    
}
