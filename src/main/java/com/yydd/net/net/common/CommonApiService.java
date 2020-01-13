package com.yydd.net.net.common;


import com.yydd.net.net.ApiResponse;
import com.yydd.net.net.BaseDto;
import com.yydd.net.net.DataResponse;
import com.yydd.net.net.HttpUtils;
import com.yydd.net.net.PagedList;
import com.yydd.net.net.common.dto.ApplicationDto;
import com.yydd.net.net.common.dto.ChangePasswordDto;
import com.yydd.net.net.common.dto.ConfirmOrderDto;
import com.yydd.net.net.common.dto.DashangListDto;
import com.yydd.net.net.common.dto.DownloadFileDto;
import com.yydd.net.net.common.dto.OrderStatusDto;
import com.yydd.net.net.common.dto.ProductListDto;
import com.yydd.net.net.common.dto.RegisterUserDto;
import com.yydd.net.net.common.vo.ConfirmOrderVO;
import com.yydd.net.net.common.vo.DashangVO;
import com.yydd.net.net.common.vo.LoginVO;
import com.yydd.net.net.common.vo.OrderVO;
import com.yydd.net.net.common.vo.ProductVO;
import com.yydd.net.net.common.vo.UserFeatureVO;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;


/**
 * Author: liaohaiping
 * Time: 2019-06-15
 * Description:
 */
public interface CommonApiService {

    //没有使用自定义CallAdapter的时候
//    @POST(HttpUtils.API_PREFIX+"deviceuser/newuser")
//    Call<ApiResponse> newDeviceUser(@Body BaseDto dto);

    //使用RxJava2CallAdapterFactory的时候
//    @POST(HttpUtils.API_PREFIX+"deviceuser/newuser")
//    Observable<ApiResponse> newDeviceUser(@Body BaseDto dto);

    /**
     * 新用户
     * @param dto
     * @return
     */
    @POST(HttpUtils.API_PREFIX+"deviceuser/newuser")
    ApiResponse newDeviceUser(@Body ApplicationDto dto);

    /**
     * 获取广告配置
     * @param dto
     * @return
     */
    @POST(HttpUtils.API_PREFIX+"config/configs")
    DataResponse<Map<String,String>> configs(@Body BaseDto dto);

    /**
     * 注册
     * @param dto
     * @return
     */
    @POST(HttpUtils.API_PREFIX+"user/register")
    ApiResponse register(@Body RegisterUserDto dto);

    /**
     * 用户名密码登录
     * @param dto
     * @return
     */
    @POST(HttpUtils.API_PREFIX+"user/login")
    DataResponse<LoginVO> login(@Body RegisterUserDto dto);


    /**
     * 注册用户并同时登录，如果用户已存在则直接登录
     * @param dto
     * @return
     */
    @POST(HttpUtils.API_PREFIX+"user/register_login")
    DataResponse<LoginVO> registerLogin(@Body RegisterUserDto dto);

    /**
     * 修改用户密码
     * @param dto
     * @return
     */
    @POST(HttpUtils.API_PREFIX+"user/change_password")
    ApiResponse changePassword(@Body ChangePasswordDto dto);

    /**
     * 获取用户购买的功能
     * @param dto
     * @return
     */
    @POST(HttpUtils.API_PREFIX+"user/user_features")
    DataResponse<List<UserFeatureVO>> userFeatures(@Body BaseDto dto);

    /**
     * 商品列表
     * @param dto
     * @return
     */
    @POST(HttpUtils.API_PREFIX+"product/list")
    DataResponse<List<ProductVO>> productList(@Body ProductListDto dto);

    /**
     * 打赏商品列表
     * @param dto
     * @return
     */
    @POST(HttpUtils.API_PREFIX+"product/list_rewards")
    DataResponse<List<ProductVO>> list_rewards(@Body BaseDto dto);

    /**
     * 获取打赏榜数据
     * @param dto
     * @return
     */
    @POST(HttpUtils.API_PREFIX+"dashang/list")
    DataResponse<PagedList<DashangVO>> dashang_list(@Body DashangListDto dto);

    /**
     *
     * @param dto
     * @return
     */
    @POST(HttpUtils.API_PREFIX+"user/add_old_vip")
    ApiResponse addOldVip(@Body BaseDto dto);

    /**
     * 下单
     * @param dto
     * @return
     */
    @POST(HttpUtils.API_PREFIX+"order/confirm_order")
    DataResponse<ConfirmOrderVO> confirmOrder(@Body ConfirmOrderDto dto);

    /**
     * 订单状态
     * @param dto
     * @return
     */
    @POST(HttpUtils.API_PREFIX+"order/order_status")
    DataResponse<OrderVO> orderStatus(@Body OrderStatusDto dto);


    /**
     * 上传文件
     * @param file
     * @return
     */
    @Multipart
    @POST(HttpUtils.API_PREFIX+"file/upload")
    DataResponse<Long> uploadFile(@Part("file") MultipartBody.Part file);


    /**
     * 上传文件
     * @param file
     * @return
     */
    @Multipart
    @POST(HttpUtils.API_PREFIX+"file/upload_forever")
    DataResponse<Long> uploadFileForever(@Part("file") MultipartBody.Part file);

    /**
     * 下传文件
     * @param dto
     * @return
     */
    @Multipart
    @POST(HttpUtils.API_PREFIX+"file/download")
    Call<ResponseBody> downloadFile(@Body DownloadFileDto dto);


}
