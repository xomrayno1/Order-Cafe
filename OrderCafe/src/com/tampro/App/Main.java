package com.tampro.App;

import java.awt.Desktop;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;

import com.tampro.Model.CartItem;
import com.tampro.Model.Order;
import com.tampro.Model.Product;
import com.tampro.Model.Revenue;
import com.tampro.Model.User;
import com.tampro.Service.CartItemService;
import com.tampro.Service.OrderService;
import com.tampro.Service.ProductService;
import com.tampro.Service.RevenueService;
import com.tampro.Service.UserService;
import com.tampro.Service.Impl.CartItemServiceImpl;
import com.tampro.Service.Impl.OrderServiceImpl;
import com.tampro.Service.Impl.ProductServiceImpl;
import com.tampro.Service.Impl.RevenueServiceImpl;
import com.tampro.Service.Impl.UserServiceImpl;

public class Main {
	// Project khách hàng đến nơi mua sản phẩm
	// employee , và nhân viên sẽ đăng nhập vào hệ thống để tạo hóa đơn sau đó xuất
	// hóa đơn ra cho khách hàng
	// Quản lý sẽ có thể tạo thêm tài khoản để cho nhân viên khác cũng có thể làm
	// các tác vụ của employee
	// quản lý có thể thêm hoặc xóa , sữa nhân viên , thêm sản phẩm xóa sản phẩm ,
	// thống kê doanh thu theo trong 1 ngày ,thống kê theo 7 ngày gần nhất , thống
	// kê năm, thống kê tháng
	// hoặc từ ngày bao nhiu đến ngày bao nhiu
	// Khách hàng : tới quầy đặt hàng nhận nước ,và nhận hóa đơn
	private static Scanner sc = new Scanner(System.in);

	private static UserService userService = new UserServiceImpl();
	private static ProductService productService = new ProductServiceImpl();
	private static CartItemService cartItemService = new CartItemServiceImpl();
	private static OrderService orderService = new OrderServiceImpl();
	// private static StatisticalService statisticalService = new
	// StatisticalServiceImpl();
	private static RevenueService revenueService = new RevenueServiceImpl();
	private static final String path = "listorder.txt";
	private static final String exportFile = "order.txt";

	public static void main(String[] args) throws IOException {
		boolean flag = true;
		int lc = 0;
		while (flag) {
			MenuFirst();
			// System.out.println("Chọn Phương Án");
			lc = InputScanner();
			switch (lc) {
			case 1: {
				User user = Login();
				if (user == null) {
					System.out.println("Đăng Nhập Thất Bại");
				} else {
					if (user.getRole().equals("admin")) {
						boolean kt = true;
						while (kt) {
							MenuAdmin();
							int option = sc.nextInt();
							switch (option) {
							case 1:
								boolean flaglocal = true;
								while (flaglocal) {
									MenuAdminByUser();
									int option1 = sc.nextInt();
									switch (option1) {
									case 1:
										GetAllUser();
										break;
									case 2:
										DeleteUser();
										break;
									case 3:
										CreateUser();
										break;
									case 4:
										updateUser();
										break;
									case 5:
										ViewPasswordUser();
										break;
									case 6:
										flaglocal = false;
										break;
									}
								}
								break;
							case 2:
								boolean localflag = true;
								while (localflag) {
									MenuAdminByProduct();
									int option2 = sc.nextInt();
									switch (option2) {
									case 1:
										getAllProduct();
										break;
									case 2:
										deleteProduct();
										break;
									case 3:
										addProduct();
										break;
									case 4:
										updateProduct();
										break;
									case 5:
										localflag = false;
										break;
									}
								}
								break;

							case 3:
								break;
							case 4:
								boolean localflag1 = true;
								while (localflag1) {
									MenuAdminByStatical();
									int lchon = InputScanner();
									switch (lchon) {
									case 1:
										sc.nextLine();
										System.out.println("Nhập Ngày Cần Thống kê ,");
										System.out.println("Ví Dụ  : Năm 2019 , tháng 06, ngày 07");
										System.out.println("2019-06-07");
										String time = sc.nextLine();
										Date date = Date.valueOf(time);
										List<Revenue> list = revenueService.getAllByDate(date);
										BigDecimal totalPrice = orderService.TotalPrice(date);
										GetStatisticalByDays(list,totalPrice);		
										boolean flag2 = true;
										while (flag2) {
											MenuExport();
											int lchon1 = InputScanner();
											switch (lchon1) {
											case 1:
												ExportStatisticalExcel(list,totalPrice);
												flag2 = false;
												break;
											case 2:
												ExportStatisticalPDF();
												flag2 = false;
												break;
											case 3:
												ExportStatisticalFile(list,totalPrice);
												flag2 = false;
												break;												
											default:
												flag2 = false;
												break;

											}
										}
										break;
									case 2:
										sc.nextLine();
										System.out.println("Nhập Tháng  Cần Thống kê ,");
										int month = InputScanner();
										System.out.println("Nhập Năm , Tháng của năm nào");
										int years = InputScanner();
										List<Revenue> listMonth = revenueService.getAllByMonth(month, years);
										BigDecimal totalprice = orderService.TotalPriceByMonth(month, years);
										GetStatisticalMonth(listMonth,totalprice);
										boolean flag3 = true;
										while (flag3) {
											MenuExport();
											int lchon1 = InputScanner();
											switch (lchon1) {
											case 1:
												ExportStatisticalExcel(listMonth,totalprice);
												flag3 = false;
												break;
											case 2:
												ExportStatisticalPDF();
												flag3 = false;
												break;
											case 3:
												ExportStatisticalFile(listMonth,totalprice);
												flag3 = false;
												break;												
											default:
												flag3 = false;
												break;

											}
										}
										break;
										
									case 3:
										localflag1 = false;
										break;
									

									default:
										localflag1   = false;
										break;
									}

								}
								break;
							case 5:
								kt = false;
								flag = exit();
							case 6:
								kt = false;
								break;

							}

						}

					} else { // employeee
						boolean local = true;
						while (local) {
							MenuEmployee();
							int option = sc.nextInt();
							switch (option) {
							case 1:
								boolean flagg = true;
								while (flagg) {
									AllProductMenu();
									System.out.println("Chọn sản phẩm");
									int idProduct = sc.nextInt();
									System.out.println("Chọn số lượng ");
									int quantity = sc.nextInt();
									// ###
									CartItem cart = CreateCartItem(idProduct, quantity); //
									List<CartItem> list = listItem(cart); // session in core
									PrintfCartItem(list);
									MenuOut();
									int localoption = sc.nextInt();
									switch (localoption) {
									case 1:
										File file = CheckFile(path); // path session
										File fileExport = CheckFile(exportFile); // path xuat hoa don
										List<CartItem> listCartItem = ReadFile(file); // đọc giá trị
										Order order = CreateOrder(listCartItem); // tạo ra order
										ExportOrderFile(order, fileExport); // viết vào file này
										int idOrder = AddOrder(order); // add vào csdl
										AddCartItem(idOrder, order.getListCartItem()); // add vào csdl

										ClearFile(file); // clearsession

										OpentFile(fileExport);
										flagg = false;
										break;
									case 2:
										break;
									}
								}
								break;
							case 2:
								File file = CheckFile(path);
								List<CartItem> list = ReadFile(file);
								PrintfCartItem(list);
								break;
							case 3:
								local = exit();
								break;
							}

						}
						return;
					}
				}
				break;
			}
			case 2: {
				AllProductMenu();

				break;
			}
			default: {
				flag = exit();
				break;
			}

			}

		}
	}

	public static void MenuFirst() {
		System.out.println("1.Đăng nhập");
		System.out.println("2.Tất cả sản phẩm");
		System.out.println("3.Exit()!");
		System.out.println("");
	}

	public static void MenuAdmin() {
		System.out.println("---------------Manager---------------");
		System.out.println("1.Tài khoản");
		System.out.println("2.Sản phẩm");
		System.out.println("3.Hóa đơn");
		System.out.println("4.Thống kê");
		System.out.println("5.Thoát chương trình");
		System.out.println("6.Về giao diện chính");
		System.out.println("---------------Chọn Phương Án---------------");
		System.out.println("");
	}

	public static void MenuAdminByUser() {
		System.out.println("---------------Manager---------------");
		System.out.println("1.Tất cả tài khoản");
		System.out.println("2.Xóa");
		System.out.println("3.Thêm");
		System.out.println("4.Update");
		System.out.println("5.Xem mật khẩu");
		System.out.println("6.Quay Lại");
		System.out.println("---------Chọn Phương Án-----------");
		System.out.println("---------------Manager---------------");
		System.out.println("");

	}

	public static void MenuAdminByProduct() {
		System.out.println("---------------Manager---------------");
		System.out.println("1.Tất cả sản phẩm");
		System.out.println("2.Xóa");
		System.out.println("3.Thêm");
		System.out.println("4.Update");
		System.out.println("5.Quay Lại");
		System.out.println("---------Chọn Phương Án-----------");
		System.out.println("---------------Manager---------------");
		System.out.println("");

	}

	public static void MenuAdminByStatical() {
		System.out.println("---------------Manager---------------");
		System.out.println("1.Thống doanh thu ngày ...");
		System.out.println("2.Thống kê doanh thu  tháng .... Năm.... ");
		System.out.println("3.Quay Lại");
		System.out.println("4.Thoát");

		System.out.println("---------Chọn Phương Án-----------");
		System.out.println("---------------Manager---------------");
		System.out.println("");

	}

	public static void MenuEmployee() {
		System.out.println("---------------Employee---------------");
		System.out.println("1.Tạo Order");
		System.out.println("2.Kiểm tra data");
		System.out.println("3.Thoát !");
		System.out.println("---------Chọn Phương Án-----------");
		System.out.println("---------------Employee---------------");
		System.out.println("");
	}

	public static void MenuOrder() {
		System.out.println("1.Đặt sản phẩm");
		System.out.println("2.Quay Lại");
		System.out.println("3.Exit!");
		System.out.println("---------------Chọn Phương Án---------------");
		System.out.println("");

	}

	public static void MenuOut() {
		System.out.println("1.Xuất hóa đơn ");
		System.out.println("2. Tiếp tục");
		System.out.println("");

	}

	public static void MenuExport() {
		System.out.println("1. Xuất Thống Kê Excel");
		System.out.println("2. Xuất Thống Kê PDF");
		System.out.println("3. Xuất Thống Kê File");
		System.out.println("4. Quay Lại");

	}

	public static User Login() {
		System.out.println("Vui Lòng Đăng Nhập Tài Khoản");
		System.out.println("Nhập Tên Tài Khoản :");
		sc.nextLine();
		String username = sc.nextLine();
		System.out.println("Nhập Mật Khẩu");
		String password = sc.nextLine();
		User user = userService.getUserByLogin(username, password);
		return user;
	}

	public static void GetAllUser() {
		System.out.println("<----Tất cả tài khoản ---->");
		List<User> list = userService.getAllUser();
		for (User us : list) {
			System.out.println(us.getPrintln());
		}
		System.out.println("<----Tất cả tài khoản ---->");
		System.out.println(" ");
	}

	public static void getAllProduct() {
		System.out.println("<-----Tất cả sản phẩm----->");
		for (Product product : productService.getAllProduct()) {
			product.getProduct();
		}
		System.out.println("<-----Tất cả sản phẩm----->");
	}

	public static boolean exit() {

		System.out.println("Kết thức chương trình");
		return false;
	}

	public static void AllProductMenu() {
		System.out.println("<----------Menu-------->");
		productService.getAllProduct().forEach(items -> {
			items.getProduct();
		});
		System.out.println("<----------Menu-------->");
		System.out.println("");
	}

	public static void DeleteUser() {
		System.out.println("Nhâp Id Cần Xóa");
		int id = InputScanner();
		User user = userService.getUserById(id);
		boolean flag = userService.deleteUser(user);
		if (flag) {
			System.out.println("Đã Xóa");
		} else {
			System.out.println("Xóa Thất Bại");
		}

	}

	public static void CreateUser() {

		User user = new User();
		sc.nextLine();
		System.out.println("Nhập Tên");
		user.setName(sc.nextLine());
		System.out.println("Nhập Username");
		user.setUsername(sc.nextLine());
		System.out.println("Nhập mật khẩu");
		user.setPassword(sc.nextLine());
		user.setRole("employee");
		userService.addUser(user);

	}

	public static void updateUser() {
		System.out.println("Nhập Id Cần Update");
		int id = InputScanner();

		User user = userService.getUserById(id);
		sc.nextLine();
		if (user == null) {
			System.out.println("User Không Tồn Tại");
		} else {
			System.out.println("Nhập Tên");
			user.setName(sc.nextLine());
			System.out.println("Nhập mật khẩu");
			user.setPassword(sc.nextLine());
			userService.updateUserById(user);
			System.out.println("Update thành công");
		}

	}

	public static void deleteProduct() {
		System.out.println("Nhập Id Cần xóa");
		int id = InputScanner();
		Product product = productService.getProductById(id);
		if (product == null) {
			System.out.println("Sản Phẩm không tồn tại");
		} else {
			productService.deleteProductById(product);
			System.out.println("Xóa sản phẩm thành công");
		}

	}

	public static void ViewPasswordUser() {
		System.out.println("Nhập Id Cần Xem");
		int id = InputScanner();
		User user = userService.getUserById(id);
		System.out.println(user.getInfo());
	}

	public static void addProduct() {
		Product product = new Product();
		sc.nextLine();
		System.out.println("Nhập tên sản phẩm");
		product.setName(sc.nextLine());
		System.out.println("Nhập giá tiền");
		product.setPrice(sc.nextDouble());
		productService.addProduct(product);
		System.out.println("Thêm Thành Công");

	}

	public static void updateProduct() {
		System.out.println("Nhập Id Cần Update");
		int id = InputScanner();
		sc.nextLine();
		Product product = productService.getProductById(id);
		if (product == null) {
			System.out.println("Product Không Tồn Tại");
		} else {
			System.out.println("Nhập tên sản phẩm");
			product.setName(sc.nextLine());
			System.out.println("Nhập giá tiền");
			product.setPrice(sc.nextDouble());

			productService.updateProductById(product);
			System.out.println("Update thành công");
		}

	}

	public static CartItem CreateCartItem(int idProduct, int quantity) {
		CartItem cartItem = new CartItem();
		Product product = productService.getProductById(idProduct);
		double price = product.getPrice() * quantity;
		BigDecimal totalPrice = new BigDecimal(price);
		Date createDate = Date.valueOf(LocalDate.now());
		cartItem.setIdProduct(idProduct);
		cartItem.setQuantity(quantity);
		cartItem.setTotalPrice(totalPrice);
		cartItem.setCreateDate(createDate);

		return cartItem;
	}

	public static List<CartItem> listItem(CartItem cartItem) throws IOException {
		File file = CheckFile(path);
		List<CartItem> listItem = ReadFile(file);

		if (listItem.isEmpty()) { // chưa có cartItem nào
			listItem = new ArrayList<CartItem>();
			listItem.add(cartItem);
		} else {
			boolean flag = true;
			for (CartItem item : listItem) {
				if (item.check(cartItem.getIdProduct())) {
					item.setQuantity(item.getQuantity() + cartItem.getQuantity());
					item.setTotalPrice(item.getTotalPrice().add(cartItem.getTotalPrice()));
					flag = false;
				}
			}
			if (flag) {
				listItem.add(cartItem);
			}

		}
		WriteFile(listItem, file);
		return listItem;

	}

	public static File CheckFile(String path) throws IOException {
		File file = new File(path);
		if (!file.exists()) {
			file.createNewFile();
			System.out.println(file.getAbsolutePath());
		}
		return file;
	}

	public static void WriteFile(List<CartItem> list, File path) throws IOException {
		// lấy file nếu file ko có thì sẽ tạo file và gán vào
		FileOutputStream fileOutputStream = new FileOutputStream(path);
		ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
		outputStream.writeObject(list);
		outputStream.close();
		fileOutputStream.close();

	}

	public static List<CartItem> ReadFile(File file) {
		List<CartItem> list = new ArrayList<CartItem>();
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);

			list = (List<CartItem>) inputStream.readObject();
			inputStream.close();
			fileInputStream.close();
			return list;
		} catch (EOFException eofException) {

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;

	}

	public static Order CreateOrder(List<CartItem> listItems) {
		Order order = new Order();
		String date = LocalDate.now().toString();
		Date dateTime = Date.valueOf(date);
		BigDecimal total = new BigDecimal(0);
		for (CartItem cart : listItems) {
			total = total.add(cart.getTotalPrice());
		}

		order.setTotalPrice(total);
		order.setListCartItem(listItems);
		order.setCreateDate(dateTime);

		return order;
	}

	public static int AddOrder(Order order) {
		int key = orderService.addOrder(order);
		return key;
	}

	public static void AddCartItem(int idOrder, List<CartItem> list) {
		Date date = Date.valueOf(LocalDate.now());
		List<Revenue> revenues = revenueService.getAllByDate(date);
		boolean flag = true;
		for (CartItem cart : list) {
			Product product = productService.getProductById(cart.getIdProduct());
			cart.setIdOrder(idOrder);
			cartItemService.addCartItem(cart);
			for (Revenue revenue : revenues) { // bang doanh thu
				// nếu sản phẩm đã xuất hiện trong bảng ngày hôm đó , thì ta chỉ cần tăng lên
				// quantity
				// nếu hôm đó sản phẩm đó chưa ai mua thì sẽ add mới vào
				// bảng doanh thu để tính Doanh Thu Tháng
				if (revenue.getProduct().getId() == cart.getIdProduct()) {
					Revenue revenue2 = new Revenue();
					revenue2.setCreateDate(cart.getCreateDate());
					revenue2.setProduct(product);
					revenue2.setQuantity(revenue.getQuantity() + cart.getQuantity());
					revenueService.updateRevenue(revenue2);
					flag = false;
				}

			}
			if (flag) {
				Revenue revenue2 = new Revenue();
				revenue2.setCreateDate(cart.getCreateDate());
				revenue2.setProduct(product);
				revenue2.setQuantity(cart.getQuantity());
				revenueService.addRevenue(revenue2);
			}
		}

	}

	public static void PrintfCartItem(List<CartItem> cart) {

		System.out.println("<--Hóa Đơn Đang Có--->");
		System.out.println("");
		NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("nv", "VN"));
		BigDecimal bigDecimal = new BigDecimal(0);
		if (cart.isEmpty()) {
			System.out.println("Cart is empty");
		} else {

			for (CartItem cartitem : cart) {
				Product product = productService.getProductById(cartitem.getIdProduct());
				System.out.println(product.getProductValues() + "  Số lượng :" + cartitem.getQuantity() + "   Total :"
						+ format.format(cartitem.getTotalPrice().doubleValue()));
				bigDecimal = bigDecimal.add(cartitem.getTotalPrice());
			}
			System.out.println("Tổng tiền : " + format.format(bigDecimal.doubleValue()));
		}
		System.out.println("");
		System.out.println("<--Hóa Đơn Đang Có--->");

	}

	public static void ExportOrderFile(Order order, File file) {
		try {
			FileWriter fileWriter = new FileWriter(file);
			PrintWriter printWriter = new PrintWriter(fileWriter);

			String title = "<-----Tâm cafe----->";
			String header = "Hóa Đơn";
			String DateTime = "Date :" + order.getCreateDate().toString();
			String address = "Tâm Cafe, đông hòa";
			printWriter.println(title);
			printWriter.println(header);
			printWriter.println(address);
			printWriter.println(DateTime);

			NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("nv", "VN"));
			for (CartItem cart : order.getListCartItem()) {
				Product product = productService.getProductById(cart.getIdProduct());
				printWriter.println(product.getProductValues() + " Số Lượng : " + cart.getQuantity() + "  Total : "
						+ format.format(cart.getTotalPrice().doubleValue()));

			}

			printWriter.println("Tổng Tiền : " + format.format(order.getTotalPrice().doubleValue()));

			printWriter.flush();
			printWriter.close();
			fileWriter.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("đã xuất");
	}

	public static void OpentFile(File file) {
		try {
			Desktop.getDesktop().open(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void ClearFile(File file) {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(file);

			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeChars("");
			objectOutputStream.close();
			fileOutputStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("đã clear");
	}

	public static int InputScanner() {
		int number = 0;
		do {
			try {
				System.out.println("Nhập   :");
				number = sc.nextInt();
			} catch (Exception e) {
				System.out.println("Nhập sai , nhập số nguyên ");
				sc.nextLine(); // nếu k có sẽ bị trôi và lặp vô hạn
			}
		} while (number == 0);

		return number;
	}

	/*
	 * SELECT ordercafe.order.id,ordercafe.product.name ,
	 * ordercafe.cartitem.quantity
	 * ,ordercafe.cartitem.totalPrice,ordercafe.order.supertotalPrice,ordercafe.
	 * order.createDate FROM ordercafe.cartitem inner join product on
	 * cartitem.idproduct = product.id inner join ordercafe.order on
	 * cartitem.idorder = ordercafe.order.id;
	 */
	public static void GetStatisticalByDays(List<Revenue> list,BigDecimal totalPrice) {
		NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("nv", "VN"));
		if (!list.isEmpty()) {
			for (Revenue revenue : list) {
				System.out.println("[Tên sản phẩm] : " + revenue.getProduct().getName() + " [Tổng tiền bán được] : "
						+ format.format(revenue.getProduct().getPrice() * revenue.getQuantity())
						+ "  [Số lượng bán được] : " + revenue.getQuantity() + " [Thời Gian] "
						+ revenue.getCreateDate());
			}
			
			System.out.println("Tổng doanh Thu : " + format.format(totalPrice.doubleValue()));
		} else {
			System.out.println("Chưa Có Doanh thu !");
		}

	}

	public static void GetStatisticalMonth(List<Revenue> list,BigDecimal totalPrice) {
		// SELECT * FROM ordercafe.revenue where month(createdate) = '06' and
		// year(createdate) = '2019';
		NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("nv", "VN"));
		if (!list.isEmpty()) {
			for (Revenue revenue : list) {
				System.out.println("[Tên sản phẩm] : " + revenue.getProduct().getName() + " [Tổng tiền bán được] : "
						+ format.format(revenue.getProduct().getPrice() * revenue.getQuantity())
						+ "  [Số lượng bán được] : " + revenue.getQuantity() + " [Thời Gian] "
						+ revenue.getCreateDate());
			}
			
			System.out.println("Tổng doanh Thu : " + format.format(totalPrice.doubleValue()));
		} else {
			System.out.println("Chưa Có Doanh thu !");
		}

	}
	public static void ExportStatisticalFile(List<Revenue> list ,BigDecimal totalPrice) {
		NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("nv", "VN"));
		try {
			FileWriter fileWriter = new FileWriter(new File("thongke.txt"));
			PrintWriter printWriter = new PrintWriter(fileWriter);
			
			
			for(Revenue revenue : list) {
				printWriter.println("Tên Sản Phẩm      Tổng Tiền       Số Lượng Bán Được        Thời Gian"); 
				printWriter.println(revenue.getProduct().getName()+"             "+format.format(revenue.getProduct().getPrice()*revenue.getQuantity())+"                    "+revenue.getQuantity()+"        "+revenue.getCreateDate());
			}
			printWriter.println("Tổng Doanh Thu"+format.format(totalPrice.doubleValue()));
			printWriter.flush();
			printWriter.close();
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		OpentFile(new File("thongke.txt"));
		System.out.println("Đã xuất");
		
		
	}

	public static void ExportStatisticalExcel(List<Revenue> list,BigDecimal totalPrice) throws IOException {
		NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("nv", "VN"));
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFFont font = workbook.createFont();
		font.setColor(IndexedColors.RED.getIndex());
		font.setBold(true);
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setFont(font);
		HSSFSheet sheet = workbook.createSheet("order");
		List<String> listHeader = new ArrayList<String>();
		listHeader.add("Tên sản Phẩm ");
		listHeader.add("Tổng Tiền ");
		listHeader.add("Số Lượng Bán được");
		listHeader.add("Thời gian");
		Row headerRow = sheet.createRow(0);
		for (int i = 0; i < listHeader.size(); i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(listHeader.get(i));
			cell.setCellStyle(cellStyle);
		}
		int rownum = 1;
		int totalrow = 0;
		
		//CellStyle HSSFCellStyle = workbook.createCellStyle();
		//CreationHelper createHelper = workbook.getCreationHelper();
		//HSSFCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));
		
		for (Revenue revenue : list) {
			Row row = sheet.createRow(rownum++);
			row.createCell(0).setCellValue(revenue.getProduct().getName());
			row.createCell(1).setCellValue(format.format(revenue.getProduct().getPrice() * revenue.getQuantity()));
			row.createCell(2).setCellValue(revenue.getQuantity());
			Cell cell = row.createCell(3);
			cell.setCellValue(revenue.getCreateDate().toString());
			//cell.setCellStyle(HSSFCellStyle);
			totalrow = rownum;
		}

		Row row = sheet.createRow(totalrow);
		row.createCell(0).setCellValue("Tổng Doanh Thu  : ");
		row.createCell(1).setCellValue(format.format(totalPrice.doubleValue()));

		workbook.write(new FileOutputStream("thongke.xls"));
		workbook.close();
		Desktop.getDesktop().open(new File("thongke.xls"));
		System.out.println("Đã Xuất");
	}
	public static void ExportStatisticalPDF(/*List<Revenue> list,BigDecimal totalPrice*/) {
		System.out.println("Đang Phát triển");
	}
	/*
	 * SELECT sum(ordercafe.cartitem.totalPrice) FROM ordercafe.cartitem inner join
	 * product on cartitem.idproduct = product.id inner join ordercafe.order on
	 * cartitem.idorder = ordercafe.order.id where ordercafe.order.createDate = ?;
	 */

}
