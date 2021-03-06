package com.bcq.net.wrapper;

import com.bcq.net.wrapper.interfaces.IHeader;
import com.bcq.net.wrapper.interfaces.IParse;
import com.bcq.net.wrapper.interfaces.IProcess;
import com.bcq.net.wrapper.interfaces.IResult;

public class OkHelper {
    private final static OkHelper instance = new OkHelper();
    //通用json解析器
    private IParse defaultParser;
    private IProcess defaultProcessor;
    private IHeader headCacher;

    public static OkHelper get() {
        return instance;
    }

    public static void setDebug(boolean debug) {
        OkUtil.debug = debug;
    }

    public void setHeadCacher(IHeader headCacher) {
        this.headCacher = headCacher;
    }

    public IHeader getHeadCacher() {
        return headCacher;
    }

    public void setDefaultParser(IParse parser) {
        defaultParser = parser;
    }

    public IParse getParser() {
        if (null == defaultParser) {
            defaultParser = new BaseParser();
        }
        return defaultParser;
    }

    public void setDefaultProcessor(IProcess defaultProcessor) {
        this.defaultProcessor = defaultProcessor;
    }

    protected <IR extends IResult<R, E>, R, E, T> IProcess<IR, R, E, T> getProcessor() {
        if (null == defaultProcessor) {
            defaultProcessor = new BaseProcessor();
        }
        return defaultProcessor;
    }
}
