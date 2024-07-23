<?php
// tất cả các biến sẽ có dấu $
// Truy cập thuộc tính của đối tượng dùng dấu ->
// Nối chuỗi dùng dấu .
// Hàm printf: echo: in ra màn hình

$res = array();
// B1: Khai báo thông tin kết nối CSDL
$s = "localhost";
$u = "root";
$p = "";
$db = "a3";

// B2: Kết nối CSDL
$con = new mysqli($s, $u, $p, $db);

$MaSP = $_POST['MaSP'];
$TenSP = $_POST['TenSP'];
$MoTa = $_POST['MoTa'];

// B3: Viết lệnh insert
$sql = "INSERT INTO SanPham (MaSP, TenSP, MoTa) VALUES ('$MaSP','$TenSP','$MoTa')";

// B4: Thực thi lệnh insert
if ($con->query($sql) === TRUE) {
  $res['success'] = 1;
  $res['message'] = "Insert thành công";
  echo json_encode($res);
} else {
  $res['success'] = 0;
  $res['message'] = "Insert thất bại";
  echo json_encode($res);
}

// B5: Đóng kết nối
$con->close();
// http://localhost/000/2024071/insert.php?MaSP=SP3&TenSP=San pham 3&MoTa=Mo ta 3
