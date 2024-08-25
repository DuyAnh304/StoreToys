-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th8 25, 2024 lúc 10:51 AM
-- Phiên bản máy phục vụ: 10.4.28-MariaDB
-- Phiên bản PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `storetoy`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `accounts`
--

CREATE TABLE `accounts` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `username` varchar(25) NOT NULL,
  `password` varchar(25) NOT NULL,
  `role_id` int(11) NOT NULL,
  `create_at` datetime NOT NULL,
  `update_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `accounts`
--

INSERT INTO `accounts` (`id`, `user_id`, `username`, `password`, `role_id`, `create_at`, `update_at`) VALUES
(1, 1, 'admin', '123456', 1, '2024-08-25 07:24:15', '2024-08-25 07:24:15'),
(2, 2, 'user', '567890', 2, '2024-08-25 07:24:15', '2024-08-25 07:24:15'),
(3, 3, 'abcd', '123456', 2, '2024-08-25 07:25:07', '2024-08-25 07:25:07');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `brands`
--

CREATE TABLE `brands` (
  `brand_id` int(11) NOT NULL,
  `brand_name` varchar(25) NOT NULL,
  `brand_img` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `brands`
--

INSERT INTO `brands` (`brand_id`, `brand_name`, `brand_img`) VALUES
(1, 'Lego', 'lego2.png'),
(2, 'Ania', 'ania.png'),
(3, 'Rubik', 'rubik.png'),
(4, 'Dream Girl', 'dreamgirl.png');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `carts`
--

CREATE TABLE `carts` (
  `cart_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `carts`
--

INSERT INTO `carts` (`cart_id`, `user_id`, `product_id`, `quantity`) VALUES
(20, 2, 1, 3),
(25, 2, 15, 2),
(28, 1, 1, 2),
(29, 1, 2, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `categories`
--

CREATE TABLE `categories` (
  `category_id` int(11) NOT NULL,
  `category_name` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `categories`
--

INSERT INTO `categories` (`category_id`, `category_name`) VALUES
(1, 'Lego'),
(2, 'Đồ chơi trí tuệ'),
(3, 'Thế giới động vật'),
(4, 'Búp bê'),
(21, 'c');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `orders`
--

CREATE TABLE `orders` (
  `order_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `total_money` int(11) NOT NULL,
  `order_date` date NOT NULL,
  `note` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `orders`
--

INSERT INTO `orders` (`order_id`, `user_id`, `total_money`, `order_date`, `note`) VALUES
(2, 2, 1700000, '2023-12-01', 'giao nhanh'),
(3, 1, 60000, '2024-04-04', 'a'),
(4, 1, 60000, '2024-04-03', 'a'),
(5, 1, 310000, '2024-04-24', 'aa'),
(6, 1, 310000, '2024-04-18', 'bcd'),
(7, 1, 310000, '2024-04-11', 'ooo');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `order_details`
--

CREATE TABLE `order_details` (
  `id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `total_price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `order_details`
--

INSERT INTO `order_details` (`id`, `order_id`, `product_id`, `quantity`, `total_price`) VALUES
(1, 2, 1, 2, 120000),
(2, 2, 5, 1, 50000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `products`
--

CREATE TABLE `products` (
  `product_id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,
  `brand_id` int(11) NOT NULL,
  `product_name` varchar(50) NOT NULL,
  `product_quantity` int(11) NOT NULL,
  `product_img` varchar(50) NOT NULL,
  `product_sex` varchar(10) NOT NULL,
  `product_price` float NOT NULL,
  `create_at` datetime NOT NULL,
  `update_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `products`
--

INSERT INTO `products` (`product_id`, `category_id`, `brand_id`, `product_name`, `product_quantity`, `product_img`, `product_sex`, `product_price`, `create_at`, `update_at`) VALUES
(1, 3, 2, 'Chim cánh cụt', 254, 'canhcut.png', 'Unisex', 60000, '2024-08-22 08:39:47', '2024-08-25 12:00:08'),
(2, 2, 1, 'Cảnh sát lego', 200, 'canhsatlego.jpg', 'Nam', 250000, '2024-08-24 21:44:24', '2024-08-24 21:44:24'),
(3, 4, 2, 'Báo săn mồi', 100, 'conbao.png', 'Unisex', 50000, '2024-08-25 00:27:40', '2024-08-25 00:27:40'),
(4, 2, 1, 'Con quay ninja-go', 300, 'conquayninja.jpg', 'Nam', 80000, '2024-08-25 00:30:38', '2024-08-25 00:30:38'),
(5, 4, 2, 'Con voi', 250, 'convoi.png', 'Unisex', 50000, '2024-08-25 00:32:17', '2024-08-25 00:32:17'),
(6, 2, 1, 'Khỉ-lego', 250, 'khilego.jpg', 'Nam', 150000, '2024-08-25 00:36:51', '2024-08-25 00:36:51'),
(7, 2, 1, 'Rồng lửa ninjago', 450, 'rongluaninja.png', 'Nam', 150000, '2024-08-25 00:38:14', '2024-08-25 00:38:14'),
(8, 4, 2, 'Sử tử', 176, 'sutu.png', 'Unisex', 50000, '2024-08-25 00:39:14', '2024-08-25 00:39:14'),
(9, 2, 1, 'Xe tăng lego', 274, 'xetanglego.jpg', 'Nam', 250000, '2024-08-25 00:40:22', '2024-08-25 00:40:22'),
(10, 3, 3, 'Rubik 3x3', 235, 'rubikgan.png', 'Unisex', 150000, '2024-08-25 00:41:53', '2024-08-25 00:41:53'),
(11, 2, 3, 'Rubik 4x4', 235, 'rubik4x4.png', 'Unisex', 80000, '2024-08-25 00:44:18', '2024-08-25 00:44:18'),
(12, 2, 3, 'Rubik tam giác', 135, 'rubiktamgiac.png', 'Unisex', 75000, '2024-08-25 00:45:41', '2024-08-25 00:45:41'),
(13, 2, 3, 'Rubik Megaminx', 255, 'rubikmeaminx.png', 'Unisex', 149000, '2024-08-25 00:46:22', '2024-08-25 00:46:22'),
(14, 4, 4, 'Búp bê gia đình', 199, 'bupbegiadinh.png', 'Nữ', 320000, '2024-08-25 00:47:49', '2024-08-25 00:47:49'),
(15, 4, 4, 'Búp bê công chúa', 254, 'congchua.png', 'Nữ', 120000, '2024-08-25 00:48:40', '2024-08-25 00:48:40');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `roles`
--

CREATE TABLE `roles` (
  `role_id` int(11) NOT NULL,
  `name` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `roles`
--

INSERT INTO `roles` (`role_id`, `name`) VALUES
(1, 'Admin'),
(2, 'User');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `fullname` varchar(25) NOT NULL,
  `phone` varchar(25) NOT NULL,
  `address` varchar(255) NOT NULL,
  `sex` varchar(11) NOT NULL,
  `email` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `users`
--

INSERT INTO `users` (`user_id`, `fullname`, `phone`, `address`, `sex`, `email`) VALUES
(1, 'Hải Lê', '0938413412', 'Triều Khúc, Hà Nội', 'Nam', 'haido123@gmail.com'),
(2, 'Báchh', '0984372445', 'Thanh Oai, Hà Nội', 'Nam', 'bach@gmail.com'),
(3, 'Thắng', '0938413538', 'Triều Khúc, Hà Nội', 'Nam', 'thang123@gmail.com');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `accounts`
--
ALTER TABLE `accounts`
  ADD PRIMARY KEY (`id`),
  ADD KEY `role_id` (`role_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Chỉ mục cho bảng `brands`
--
ALTER TABLE `brands`
  ADD PRIMARY KEY (`brand_id`);

--
-- Chỉ mục cho bảng `carts`
--
ALTER TABLE `carts`
  ADD PRIMARY KEY (`cart_id`);

--
-- Chỉ mục cho bảng `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`category_id`);

--
-- Chỉ mục cho bảng `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`order_id`);

--
-- Chỉ mục cho bảng `order_details`
--
ALTER TABLE `order_details`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`product_id`);

--
-- Chỉ mục cho bảng `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`role_id`);

--
-- Chỉ mục cho bảng `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `accounts`
--
ALTER TABLE `accounts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `brands`
--
ALTER TABLE `brands`
  MODIFY `brand_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `carts`
--
ALTER TABLE `carts`
  MODIFY `cart_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT cho bảng `categories`
--
ALTER TABLE `categories`
  MODIFY `category_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT cho bảng `orders`
--
ALTER TABLE `orders`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT cho bảng `order_details`
--
ALTER TABLE `order_details`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `products`
--
ALTER TABLE `products`
  MODIFY `product_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT cho bảng `roles`
--
ALTER TABLE `roles`
  MODIFY `role_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `accounts`
--
ALTER TABLE `accounts`
  ADD CONSTRAINT `accounts_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `accounts_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
