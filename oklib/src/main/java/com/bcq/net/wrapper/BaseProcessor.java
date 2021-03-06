package com.bcq.net.wrapper;

import com.bcq.net.wrapper.interfaces.IPage;
import com.bcq.net.wrapper.interfaces.IProcess;
import com.bcq.net.wrapper.interfaces.IResult;
import com.bcq.net.wrapper.interfaces.IWrap;
import com.google.gson.JsonElement;
import com.bcq.net.api.ORequest;

/**
 * @Author: BaiCQ
 * @ClassName: BaseProcessor
 * @CreateDate: 2019/3/27 14:08
 * @Description: 数据处理封装
 * 注意：仅支持 StatueResult的类型 和 ObjResult<List<T>>的结果
 */
public class BaseProcessor<IR extends IResult<R, E>, R, E, T> implements IProcess<IR, R, E, T> {
    private final static String TAG = "BaseProcessor";
    //最大重复请求次数
    private final static int MAX_REPEAT = 1;
    //重复次数
    private int repeat = 1;
    //缓存上次code
    private int lastCode = 0;
    private String lastUrl = "";

    @Override
    public final void process(int code, ORequest ORequest) {
        if (code == lastCode && lastUrl.equals(ORequest.url)) {
            //同一次请求同样的错误
            repeat++;
        } else {
            lastCode = code;
            lastUrl = ORequest.url;
            repeat = 1;
        }
        if (repeat > MAX_REPEAT) {
            OkUtil.e(TAG, "The maximum limit of repeat is " + MAX_REPEAT + " . current repeat = " + repeat);
            return;
        }
        OkUtil.e(TAG, "**************************** start process code error = " + code + " and request ****************************");
        if (processCode(code)) {
            ORequest.request();
        }
        OkUtil.e(TAG, "**************************** end   process code error = " + code + " and request ****************************");
    }

    /**
     * 处理error code
     *
     * @param code
     * @return true： 需要再次尝试请求 false：不需要再次请求
     */
    protected boolean processCode(int code) {
        return false;
    }

    @Override
    public IR processResult(IWrap wrap, Class<T> clazz) {
        if (null == clazz) {
            return (IR) new IResult.StatusResult(wrap.getCode(), wrap.getMessage());
        } else {
            OkUtil.e("processResult", "clazz:" + clazz.getSimpleName());
            IPage page = wrap.getPage();
            boolean extra = null == page ? false : (page.getPage() >= page.getTotal());
            R result = null;
            JsonElement element = wrap.getBody();
            if (null != element) {
                result = (R) OkUtil.json2List(wrap.getBody(), clazz);
            }
            return (IR) new IResult.ObjResult(result, extra);
        }
    }
}