<?php

namespace App\Repositories;
use App\Models\MProducts;
use App\Repositories\BaseRepository;
use Illuminate\Support\Facades\Schema;

class ProductRepository extends BaseRepository
{
    public function __construct(MProducts $item) 
    {
        $this->model = $item;
    }

    public function getAllItem()
    {
        return $this->model->get();
    }

    public function getListProduct()
    {
        return $this->model
            ->join('t_category', 't_product.c_type_id', '=', 't_category.c_type_id')
            ->select('t_product.*', 't_category.c_type_name')
            ->get();
    }

    public function getFirstItemInDB()
    {
        return $this->model->first();
    }

    public static function findItem($id)
    {
        return MProducts::where('p_id', $id)->first();
    }

    //
    //API
    public function getProductPrice($pro_id)
    {
        return $this->model->where('p_id',$pro_id)
            ->first(['p_price']);
    }
}