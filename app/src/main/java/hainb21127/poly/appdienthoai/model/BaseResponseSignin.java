package hainb21127.poly.appdienthoai.model;

public class BaseResponseSignin {
    private int status;
    private String msg;
    private User obj;

    public BaseResponseSignin() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public User getObj() {
        return obj;
    }

    public void setObj(User obj) {
        this.obj = obj;
    }
}
