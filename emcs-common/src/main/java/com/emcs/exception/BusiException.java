package com.emcs.exception;



/**
 * 业务异常类
 * 所有的业务操作异常使用该异常类抛出，
 * 平台捕获该异常后会组织错误信息返回给消费者
 */
public class BusiException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String errCode;
    private String moudle;

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    private String errorMsg;


    /**
     * 抛出业务异常，方法适用于错误码已经在错误码表中配置，
     * 方法内部会根据错误码查找错误码对应的错误消息
     * @param errorCode 错误代码
     */
    public BusiException(String errorCode) {
        this.errCode = errorCode;
        this.moudle = getMoudle();
    }

    /**
     * 抛出业务异常，方法适用于错误码没有在错误码表中配置，主要适用于公司内部系统抛出错误后进行的异常处理
     * 为了减少错误码的配置，该部分内容不需要配置到错误码表中
     * @param errorCode 错误代码
     * @param errorMsg 错误消息
     */
    public BusiException(String errorCode, String errorMsg) {

        this.errCode = errorCode;
        this.errorMsg=errorMsg;
        this.moudle = getMoudle();
    }


    /**
     * 抛出业务异常，方法适用于错误码已经在错误码表中配置，
     * 方法内部会根据错误码查找错误码对应的错误消息，
     * 并且会将args数组 中的参数组织到错误信息中
     * @param errorCode
     * @param args
     */
    public BusiException(String errorCode, String[] args) {
        this.errCode = errorCode;
        this.errorMsg=args.toString();
        this.moudle = getMoudle();
    }


    /**
     * 抛出业务异常，适用于在外层还有业务代码进行异常捕获的情况，
     * 如果是最外层代码不允许抛出该异常
     * @param errorCode 错误代码
     * @param cause Exception类及所有派生类的对象
     */
    public BusiException(String errorCode, Throwable cause) {
        super(errorCode, cause);
        this.errCode = errorCode;
        this.moudle = getMoudle();
    }

    public String getErrCode() {
        return this.errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getMoudle() {
        return this.moudle;
    }

    public void setMoudle(String moudle) {
        this.moudle = moudle;
    }
    /**
     * 根据错误信息模板和参数数组，组织错误信息,关键字"#"
     * @param errMsg  错误信息模板
     * 模板示例：#不能为空; #格式不正确；交易日期不在协议允许的时间范围内【#】-【#】
     * @param args 参数数组
     * @return 错误信息
     */
    public static String buildErrMsg(String errMsg, String[] args) {
        StringBuffer msgBuf = new StringBuffer();
        int i =0 , inx = 0;
        while (errMsg.length() >0) {
            inx = errMsg.indexOf("#");
            if (inx != -1) {
                msgBuf.append(errMsg.substring(0, inx));
            } else {
                msgBuf.append(errMsg);
                break;
            }
            msgBuf.append(args[i]);
            errMsg = errMsg.substring(errMsg.indexOf("#")+1);
            i = i + 1;
            if (i == args.length) {
                msgBuf.append(errMsg);
                break;
            }
        }

        return msgBuf.toString();
    }
    /**
     * 根据响应码得到响应吗对应的模块简称
     * @param errCode
     * @return
     */
    public static String getMoudle(String errCode) {
        return "";
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("moudle:[");
        sb.append(getMoudle());
        sb.append("]\tErrorCode:[");
        sb.append(getErrCode());
        sb.append("]\tMessage:[");
        sb.append(getMessage());
        sb.append("]");
        return sb.toString();
    }

}
