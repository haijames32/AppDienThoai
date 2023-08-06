package hainb21127.poly.appdienthoai.model;

public class AddOrder {
    private String _id;
//    @SerializedName("id_khachhang")
    private String id_khachhang;
//    @SerializedName("id_sanpham")
    private String id_sanpham;
    private int soluong;
    private int tongtien;
    private String trangthai;
    private String ngaymua;

    public AddOrder() {
    }

    public AddOrder(String id_khachhang, String id_sanpham, int soluong, int tongtien, String trangthai, String ngaymua) {
        this.id_khachhang = id_khachhang;
        this.id_sanpham = id_sanpham;
        this.soluong = soluong;
        this.tongtien = tongtien;
        this.trangthai = trangthai;
        this.ngaymua = ngaymua;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getId_khachhang() {
        return id_khachhang;
    }

    public void setId_khachhang(String id_khachhang) {
        this.id_khachhang = id_khachhang;
    }

    public String getId_sanpham() {
        return id_sanpham;
    }

    public void setId_sanpham(String id_sanpham) {
        this.id_sanpham = id_sanpham;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getTongtien() {
        return tongtien;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public String getNgaymua() {
        return ngaymua;
    }

    public void setNgaymua(String ngaymua) {
        this.ngaymua = ngaymua;
    }
}
