package com.webscapper.response;

/** @author ruby.jha Export response. */
public class ExportResponse {
    /** The success. **/
    private boolean success;
    /** The errMsg. **/
    private String errMsg;

    /** @return the success */
    public boolean isSuccess() {
        return success;
    }

    /** @param success
     *            the success to set */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /** @return the errMsg */
    public String getErrMsg() {
        return errMsg;
    }

    /** @param errMsg
     *            the errMsg to set */
    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}