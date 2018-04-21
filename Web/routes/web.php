<?php

/*Route điều hướng trong backend*/
//Backend
Route::get('/login','Backend\BackendController@loginForm');
Route::post('/login','Backend\BackendController@login');
Route::get('/logout','Backend\BackendController@logout');
Route::group(['prefix' => '/admin','middleware' => 'AdminAuthencation'], function(){
    //login 
    Route::get('/', 'Backend\BackendController@index');
    // code  
    Route::get('/code', 'Backend\CodeController@index');
    Route::get('/code/create', 'Backend\CodeController@create');
    Route::post('/code/deleteall','Backend\CodeController@deleteall');
    /*Category*/
    Route::get('/book-type','Backend\CategoryController@index');
    Route::get('/book-type/add','Backend\CategoryController@createCategoryForm');
    Route::post('/book-type/add','Backend\CategoryController@createCategory');
    Route::get('/book-type/edit/{id}', 'Backend\CategoryController@editCategoryForm');
    Route::post('/book-type/edit/{id}', 'Backend\CategoryController@updateCategory');
    /*End Category*/
    
    Route::get('/stories','Backend\StoryController@index');
    Route::get('/stories/create','Backend\StoryController@create');


    /*User*/
    Route::get('/users', 'Backend\UserController@users');
    Route::get('/users/create','Backend\UserController@createUser');
    Route::post('/users/create','Backend\UserController@create');
    Route::get('/users/{id}', 'Backend\UserController@detail');
    Route::get('/users/detail/{id}','Backend\UserController@editForm');
    Route::post('/users/detail/{id}','Backend\UserController@updateUser');
    Route::get('/users/delete/{id}','Backend\UserController@destroyUser');
    /*End User*/
    /*story item*/
    Route::get('/book', 'Backend\ProductController@index');
    Route::get('/book/des/{id}', 'Backend\ProductController@detail');
    Route::get('/book/create', 'Backend\ProductController@create');
    Route::post('/book/create','Backend\ProductController@postCreate');
    Route::get('/book/edit/{id}', 'Backend\ProductController@update');
    Route::post('/book/edit/{id}','Backend\ProductController@postUpdate');
    Route::get('/book/delete/{id}','Backend\ProductController@delete');
    /*End story item*/


});
