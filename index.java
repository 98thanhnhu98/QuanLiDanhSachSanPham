import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

public class index {
    private static Scanner sc = new Scanner(System.in);
    private static final String FILENAME = "D:\\ProjectOnTeacherTuanCompany\\Java\\QuanLiDanhSachSanPham\\listFile.txt";
    private static final String FILENAME1 = "listFile.txt";
    private static File fileA = new File(FILENAME1);

    public static void menu() {
        System.out.println("1. Input product");
        System.out.println("2. List of product");
        System.out.println("3. Delete a product");
        System.out.println("4. Search product");
        System.out.println("5. Update a product");
        System.out.println("Chose : ");
    }

    public static void listProduct() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String strCurrentLine;
            while ((strCurrentLine = br.readLine()) != null) {
                System.out.print(strCurrentLine);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void listProductWithUtils() {
        try {
            LineIterator iterator = FileUtils.lineIterator(fileA, "UTF-8");
            while (iterator.hasNext()) {
                String line = iterator.nextLine();
                String[] parts = line.substring(2, line.length() - 2).split(" \\| ");
                int id = Integer.parseInt(parts[0].split(" = ")[1]);
                String name = parts[1].split(" = ")[1];
                String nhaSanXuat = parts[2].split(" = ")[1];
                String dongSanPham = parts[3].split(" = ")[1];
                String giaSanPhamStr = parts[4].split(" = ")[1];
                int giaSanPham = -1;
                try {
                    giaSanPham = Integer.parseInt(giaSanPhamStr);
                } catch (NumberFormatException e) {
                    System.out.println(e.getMessage());
                }
                String newLine = id + "," + name + "," + nhaSanXuat + "," + dongSanPham + "," + giaSanPham;
                System.out.println(newLine);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void removeLineFromFile(String filePath, String lineToRemove) throws IOException {
        Path path = Paths.get(filePath);
        List<String> lines = Files.readAllLines(path);

        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).equals(lineToRemove)) {
                lines.remove(i);
                break;
            }
        }

        Files.write(path, lines);
    }

    public static void updateProductFromFile(int idc){
        try {
            String id = String.valueOf(idc);
            String lineToRemove = search(id);
            removeLineFromFile(FILENAME1, lineToRemove);
            System.out.println("Line removed successfully.");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public static void updateProductWithUtils(int idc) {
        try {
            LineIterator iterator = FileUtils.lineIterator(new File(FILENAME), "UTF-8");
            while (iterator.hasNext()) {
                String line = iterator.nextLine();
                String[] parts = line.substring(2, line.length() - 2).split(" \\| ");
                int id = Integer.parseInt(parts[0].split(" = ")[1]);
                if (id == idc) {
//                    line
                    System.out.println("Enter a product name : ");
                    String name = sc.next();
                    System.out.println("Enter the product manufacturer name :");
                    String nhaSanXuat = sc.next();
                    System.out.println("Please select the product line\n1. Iphone \2. Android");
                    String dongSanPham = "";
                    int chose = sc.nextInt();
                    if (chose == 1) {
                        dongSanPham = "Iphone";
                    } else if (chose == 2) {
                        dongSanPham = "Android";
                    }
                    System.out.println("Enter a price for the product : ");
                    long giaSanPham = sc.nextLong();
                    if(giaSanPham < 10000){
                        throw new Exception("price must not be less than 10000");
                    }
                    DienThoai dt = new DienThoai(id, name, dongSanPham, dongSanPham, giaSanPham);
                    String newLine = dt.toString();
                    FileUtils.write(new File(FILENAME), newLine + System.lineSeparator(), "UTF-8", true);
                    System.out.println("Dữ liệu đã được cập nhật thành công.");
                } else {
                    FileUtils.write(new File(FILENAME), line + System.lineSeparator(), "UTF-8", true);
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void search() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            System.out.println("Input value search by id or name : ");
            String strCurrentLine;
            String a = sc.next();
            int lineCount = 0;
            // int max = 0;
            while ((strCurrentLine = br.readLine()) != null) {
                if(strCurrentLine.contains("id = "+a)){
                    System.out.println(strCurrentLine);
                }
            }
            br.close();
            // return
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String search(String a) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String strCurrentLine;
            int lineCount = 0;
            String data = "";
            while ((strCurrentLine = br.readLine()) != null) {
                String[] tokens = strCurrentLine.split("\\|");
                for (String token : tokens) {
                    String[] keyValuePair = token.trim().split(" = ");
                    for (String value : keyValuePair) {
                        if (value.equals(a)) {
                            // System.out.print(value + " - vi tri " + count);
                            data = strCurrentLine;
                            break;
                        }
                    }
                }
            }
            br.close();
            return data;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getMax() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String strCurrentLine;
            int lineCount = 0;
            while ((strCurrentLine = br.readLine()) != null) {
                lineCount++;
            }
            br.close();
            return lineCount;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void addProduct() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME, true))) {
            int id = 0;
            if (getMax() == -1) {
                throw new Exception("ERROR WHEN RETRIEVING THE LARGEST ID");
            } else {
                id = getMax() + 1;
            }
            System.out.println("Enter a product name : ");
            String tenSanPham = sc.next();
            if (search(tenSanPham).equals(tenSanPham) == true) {
                System.out.println("Name is duplicate\nSet your name : ");
                tenSanPham = sc.next();
            }
            System.out.println("Enter the product manufacturer name :");
            String nhaSanPham = sc.next();
            System.out.println("Please select the product line\n1. Iphone \2. Android");
            String dongSanPham = "";
            int chose;
            int count = 0;
            do {
                System.out.println("Chose : ");
                chose = sc.nextInt();
                if (chose == 1) {
                    dongSanPham = "Iphone";
                    break;
                } else if (chose == 2) {
                    dongSanPham = "Android";
                    break;
                } else {
                    count++;
                    System.out.println("You entered the wrong " + count + " times");
                    System.out.println(
                            "Please select only 1 or 2!!!!\n Entering wrong more than 3 times you will be out\n");
                    if (count == 2) {
                        System.out.println("YOU ONLY HAVE 1 IMPORT LEFT PLEASE SELECT 1 OR 2");
                    }
                    if (count == 3) {
                        throw new Exception("YOU HAVE ENTERED THE WRONG MANY TIMES");
                    }
                }
            } while (chose != 1 || chose != 2);
            System.out.println("Enter a price for the product : ");
            long giaSanPham = sc.nextLong();
            if(giaSanPham < 10000){
                throw new Exception("price must not be less than 10000");
            }
            DienThoai dt = new DienThoai(id, tenSanPham, nhaSanPham, dongSanPham, giaSanPham);
            bw.newLine();
            bw.append(dt.toString());
            bw.flush();
            System.out.println("Input Successfully !!!!\n");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteWithUtils(int idc) {
        try {
            String id = String.valueOf(idc);
            String lineToRemove = search(id);
            Path path = Paths.get(FILENAME1);
            List<String> lines = Files.readAllLines(path);
            for (int i = 0; i < lines.size(); i++) {
                if (lines.get(i).equals(lineToRemove)) {
                    lines.remove(i);
                    break;
                }
            }
            Files.write(path, lines);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        boolean flag = true;
        int a = 0;
        while(flag){
            menu();
            a = sc.nextInt();
            switch (a){
                case 1:
                    addProduct();
                    break;
                case 2:
                    listProductWithUtils();
                    break;
                case 3:
                    System.out.println("Input id");
                    int idc = sc.nextInt();
                    updateProductWithUtils(idc);
                    break;
                case 4:
                    System.out.println("Input name or id");
                    String noi = sc.next();
                    System.out.println(search(noi));
                    break;
                case 5:
                    System.out.println("Input id");
                    int id = sc.nextInt();
                    deleteWithUtils(id);
                    break;
                default:
                    System.out.println("Out.....");
                    flag = false;
                    break;
            }
        }
    }
}
