package com.company.project.vo;

public class ResultVo<T> {
    private static final long serialVersionUID = 1L;
    private boolean success = true;
    private String message ="操作成功";
    private Integer code;
    private T result;
    private long timestamp;

    public ResultVo() {
        this.code = 200;
        this.timestamp = System.currentTimeMillis();
    }

    public ResultVo<T> success(String message) {
        this.message = message;
        this.code = 200;
        this.success = true;
        return this;
    }

    public static ResultVo<Object> ok() {
        ResultVo<Object> r = new ResultVo();
        r.setSuccess(true);
        r.setCode(200);
        r.setMessage("成功");
        return r;
    }

    public static ResultVo<Object> ok(String msg) {
        ResultVo<Object> r = new ResultVo();
        r.setSuccess(true);
        r.setCode(200);
        r.setMessage(msg);
        return r;
    }

    public static ResultVo<Object> ok(Object data) {
        ResultVo<Object> r = new ResultVo();
        r.setSuccess(true);
        r.setCode(200);
        r.setResult(data);
        return r;
    }

    public static ResultVo<Object> ok(String msg, Object data) {
        ResultVo<Object> r = new ResultVo();
        r.setSuccess(true);
        r.setCode(200);
        r.setResult(data);
        r.setMessage(msg);
        return r;
    }

    public static ResultVo<Object> error(String msg, Object obj) {
        return error(500, msg, obj);
    }

    public static ResultVo<Object> error(String msg) {
        return error(500, msg);
    }

    public static ResultVo<Object> error(int code, String msg) {
        ResultVo<Object> r = new ResultVo();
        r.setCode(code);
        r.setMessage(msg);
        r.setSuccess(false);
        return r;
    }

    public static ResultVo<Object> error(int code, String msg, Object obj) {
        ResultVo<Object> r = new ResultVo();
        r.setCode(code);
        r.setMessage(msg);
        r.setSuccess(false);
        r.setResult(obj);
        return r;
    }

    public ResultVo<T> error500(String message) {
        this.message = message;
        this.code = 500;
        this.success = false;
        return this;
    }

    public static ResultVo<Object> noAuth(String msg) {
        return error(510, msg);
    }

    public static ResultVo<Object> tokenExpire(String msg) {
        return error(520, msg);
    }

    public boolean isSuccess() {
        return this.success;
    }

    public String getMessage() {
        return this.message;
    }

    public Integer getCode() {
        return this.code;
    }

    public T getResult() {
        return this.result;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setSuccess(final boolean success) {
        this.success = success;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public void setCode(final Integer code) {
        this.code = code;
    }

    public void setResult(final T result) {
        this.result = result;
    }

    public void setTimestamp(final long timestamp) {
        this.timestamp = timestamp;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof ResultVo)) {
            return false;
        } else {
            ResultVo<?> other = (ResultVo)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (this.isSuccess() != other.isSuccess()) {
                return false;
            } else if (this.getTimestamp() != other.getTimestamp()) {
                return false;
            } else {
                label52: {
                    Object this$code = this.getCode();
                    Object other$code = other.getCode();
                    if (this$code == null) {
                        if (other$code == null) {
                            break label52;
                        }
                    } else if (this$code.equals(other$code)) {
                        break label52;
                    }

                    return false;
                }

                Object this$message = this.getMessage();
                Object other$message = other.getMessage();
                if (this$message == null) {
                    if (other$message != null) {
                        return false;
                    }
                } else if (!this$message.equals(other$message)) {
                    return false;
                }

                Object this$result = this.getResult();
                Object other$result = other.getResult();
                if (this$result == null) {
                    if (other$result != null) {
                        return false;
                    }
                } else if (!this$result.equals(other$result)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ResultVo;
    }

    public int hashCode() {
        int result = 1;
        result = result * 59 + (this.isSuccess() ? 79 : 97);
        long $timestamp = this.getTimestamp();
        result = result * 59 + (int)($timestamp >>> 32 ^ $timestamp);
        Object $code = this.getCode();
        result = result * 59 + ($code == null ? 43 : $code.hashCode());
        Object $message = this.getMessage();
        result = result * 59 + ($message == null ? 43 : $message.hashCode());
        Object $result = this.getResult();
        result = result * 59 + ($result == null ? 43 : $result.hashCode());
        return result;
    }

    public String toString() {
        return "Result(success=" + this.isSuccess() + ", message=" + this.getMessage() + ", code=" + this.getCode() + ", result=" + this.getResult() + ", timestamp=" + this.getTimestamp() + ")";
    }
}
