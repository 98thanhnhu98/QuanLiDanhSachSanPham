
public class DienThoai {
    private int id;
    private String name;
    private String nhaSanXuat;
    private String dongSanPham;
    private long giaSanPham;

    public DienThoai(int id, String name, String nhaSanXuat, String dongSanPham, long giaSanPham) throws Exception {
        if(name.length() > 8 && nhaSanXuat.length() > 5 && dongSanPham.length() > 5 && giaSanPham > 10000) {
            this.id = id;
            this.name = name;
            this.nhaSanXuat = nhaSanXuat;
            this.dongSanPham = dongSanPham;
            this.giaSanPham = giaSanPham;
        }else {
            throw new Exception("validation is working!!!");
        }
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        if(name.length() > 8){
            System.out.println("name length must be greater than 8");
        }else {
            this.name = name;
        }
    }
    public String getNhaSanXuat() {
        return nhaSanXuat;
    }
    public void setNhaSanXuat(String nhaSanXuat) {
        if(name.length() > 5){
            System.out.println("name length must be greater than 8");
        }else {
            this.nhaSanXuat = nhaSanXuat;
        }
    }
    public String getDongSanPham() {
        return dongSanPham;
    }
    public void setDongSanPham(String dongSanPham) {
        if(name.length() > 5){
            System.out.println("name length must be greater than 8");
        }else {
            this.dongSanPham = dongSanPham;
        }
    }
    public long getGiaSanPham() {
        return giaSanPham;
    }
    public void setGiaSanPham(long giaSanPham) {
        if(giaSanPham < 10000){
            System.out.println("price must not be less than 10000");
        }else {
            this.giaSanPham = giaSanPham;
        }
    }

    @Override
    public String toString() {
        return "[ id = " + id + " | name = " + name + " | nhaSanXuat = " + nhaSanXuat + " | dongSanPham = " + dongSanPham + " | giaSanPham = " + giaSanPham + " ]";
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((nhaSanXuat == null) ? 0 : nhaSanXuat.hashCode());
        result = prime * result + ((dongSanPham == null) ? 0 : dongSanPham.hashCode());
        result = prime * result + (int) (giaSanPham ^ (giaSanPham >>> 32));
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
            DienThoai other = (DienThoai) obj;
        if (id != other.id)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (nhaSanXuat == null) {
            if (other.nhaSanXuat != null)
                return false;
        } else if (!nhaSanXuat.equals(other.nhaSanXuat))
            return false;
        if (dongSanPham == null) {
            if (other.dongSanPham != null)
                return false;
        } else if (!dongSanPham.equals(other.dongSanPham))
            return false;
        if (giaSanPham != other.giaSanPham)
            return false;
        return true;
    }

    
}
