package hainb21127.poly.appdienthoai.model;

public class Product {
    private String _id;
    private String tensp;
    private int giasp;
    private String mota;
    private int tonkho;
    private String image;
    private Theloai id_theloai;

//    public Product(String _id, String tensp, int giasp, String mota, int tonkho, String image, Theloai id_theloai) {
//        this._id = _id;
//        this.tensp = tensp;
//        this.giasp = giasp;
//        this.mota = mota;
//        this.tonkho = tonkho;
//        this.image = image;
//        this.id_theloai = id_theloai;
//    }
//
//    public Product() {
//    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public int getGiasp() {
        return giasp;
    }

    public void setGiasp(int giasp) {
        this.giasp = giasp;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public int getTonkho() {
        return tonkho;
    }

    public void setTonkho(int tonkho) {
        this.tonkho = tonkho;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Theloai getId_theloai() {
        return id_theloai;
    }

    public void setId_theloai(Theloai id_theloai) {
        this.id_theloai = id_theloai;
    }
}
