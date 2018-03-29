<?php

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::get('/login','Backend\BackendController@loginForm');
Route::post('/login','Backend\BackendController@login');
Route::get('/logout','Backend\BackendController@logout');
Route::group(['prefix' => '/admin'], function(){
    //login 
    Route::get('/', 'Backend\BackendController@index');

    Route::get('/users', 'Backend\UserController@users');
    Route::get('/users/create','Backend\UserController@createUser');
    Route::post('/users/create','Backend\UserController@create');
    Route::get('/users/{id}', 'Backend\UserController@detail');
    Route::get('/users/detail/{id}','Backend\UserController@editForm');
    Route::post('/users/detail/{id}','Backend\UserController@updateUser');
    Route::get('/users/delete/{id}','Backend\UserController@destroyUser');
    });