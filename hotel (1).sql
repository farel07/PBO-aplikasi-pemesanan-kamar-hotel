-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 19 Jun 2025 pada 16.57
-- Versi server: 10.4.32-MariaDB
-- Versi PHP: 8.1.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hotel`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `detail_transaksi`
--

CREATE TABLE `detail_transaksi` (
  `id_transaksi` int(11) NOT NULL,
  `id_menu` int(11) NOT NULL,
  `qty` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `detail_transaksi`
--

INSERT INTO `detail_transaksi` (`id_transaksi`, `id_menu`, `qty`) VALUES
(21, 3, 11),
(23, 3, 2),
(23, 4, 1),
(24, 3, 1),
(24, 5, 2);

-- --------------------------------------------------------

--
-- Struktur dari tabel `kamar`
--

CREATE TABLE `kamar` (
  `idKamar` int(11) NOT NULL,
  `no_kamar` varchar(10) DEFAULT NULL,
  `status` varchar(50) DEFAULT 'kosong'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `kamar`
--

INSERT INTO `kamar` (`idKamar`, `no_kamar`, `status`) VALUES
(1, 'A1', 'kosong'),
(3, 'A12', 'kosong');

-- --------------------------------------------------------

--
-- Struktur dari tabel `menu_restoran`
--

CREATE TABLE `menu_restoran` (
  `id_menu` int(11) NOT NULL,
  `nama` varchar(100) DEFAULT NULL,
  `harga` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `menu_restoran`
--

INSERT INTO `menu_restoran` (`id_menu`, `nama`, `harga`) VALUES
(3, 'Makanan', 12000.00),
(4, 'Nasi Goreng', 10000.00),
(5, 'Es Teh', 10000.00);

-- --------------------------------------------------------

--
-- Struktur dari tabel `tamu`
--

CREATE TABLE `tamu` (
  `idTamu` int(11) NOT NULL,
  `nama` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `tamu`
--

INSERT INTO `tamu` (`idTamu`, `nama`) VALUES
(1, 'Tamu spesial'),
(4, 'Tamu 123'),
(5, 'Farrel Aqeel Danendra'),
(6, 'Tamu aaaa'),
(7, 'Tamu Baru');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tamu_reservasi`
--

CREATE TABLE `tamu_reservasi` (
  `idReservasi` int(11) NOT NULL,
  `idTamu` int(11) DEFAULT NULL,
  `tgl_checkin` date DEFAULT NULL,
  `tgl_checkout` date DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `idKamar` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `tamu_reservasi`
--

INSERT INTO `tamu_reservasi` (`idReservasi`, `idTamu`, `tgl_checkin`, `tgl_checkout`, `status`, `idKamar`) VALUES
(1, 1, '2025-06-11', '2025-06-12', 1, 1),
(16, 1, '2025-06-17', '2025-06-18', 1, 1),
(17, 7, '2025-06-19', '2025-06-20', 1, 3),
(18, 1, '2025-06-19', '2025-06-20', 0, 1);

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi`
--

CREATE TABLE `transaksi` (
  `id_transaksi` int(11) NOT NULL,
  `tgl_transaksi` date DEFAULT NULL,
  `total` decimal(10,2) DEFAULT NULL,
  `idTamu` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `transaksi`
--

INSERT INTO `transaksi` (`id_transaksi`, `tgl_transaksi`, `total`, `idTamu`) VALUES
(19, '2025-06-15', 0.00, 1),
(20, '2025-06-15', 0.00, 1),
(21, '2025-06-15', 132000.00, 1),
(22, '2025-06-15', 220000.00, 1),
(23, '2025-06-17', 34000.00, 1),
(24, '2025-06-19', 32000.00, 4);

-- --------------------------------------------------------

--
-- Struktur dari tabel `user`
--

CREATE TABLE `user` (
  `idUser` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` enum('staff','manager') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `user`
--

INSERT INTO `user` (`idUser`, `username`, `password`, `role`) VALUES
(1, 'admin', 'admin123', 'manager'),
(2, 'staff1', 'staff123', 'staff');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  ADD PRIMARY KEY (`id_transaksi`,`id_menu`),
  ADD KEY `id_menu` (`id_menu`);

--
-- Indeks untuk tabel `kamar`
--
ALTER TABLE `kamar`
  ADD PRIMARY KEY (`idKamar`);

--
-- Indeks untuk tabel `menu_restoran`
--
ALTER TABLE `menu_restoran`
  ADD PRIMARY KEY (`id_menu`);

--
-- Indeks untuk tabel `tamu`
--
ALTER TABLE `tamu`
  ADD PRIMARY KEY (`idTamu`);

--
-- Indeks untuk tabel `tamu_reservasi`
--
ALTER TABLE `tamu_reservasi`
  ADD PRIMARY KEY (`idReservasi`),
  ADD KEY `idTamu` (`idTamu`),
  ADD KEY `idKamar` (`idKamar`);

--
-- Indeks untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id_transaksi`),
  ADD KEY `idTamu` (`idTamu`);

--
-- Indeks untuk tabel `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`idUser`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `kamar`
--
ALTER TABLE `kamar`
  MODIFY `idKamar` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT untuk tabel `menu_restoran`
--
ALTER TABLE `menu_restoran`
  MODIFY `id_menu` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT untuk tabel `tamu`
--
ALTER TABLE `tamu`
  MODIFY `idTamu` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT untuk tabel `tamu_reservasi`
--
ALTER TABLE `tamu_reservasi`
  MODIFY `idReservasi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `id_transaksi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT untuk tabel `user`
--
ALTER TABLE `user`
  MODIFY `idUser` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  ADD CONSTRAINT `detail_transaksi_ibfk_1` FOREIGN KEY (`id_transaksi`) REFERENCES `transaksi` (`id_transaksi`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `detail_transaksi_ibfk_2` FOREIGN KEY (`id_menu`) REFERENCES `menu_restoran` (`id_menu`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `tamu_reservasi`
--
ALTER TABLE `tamu_reservasi`
  ADD CONSTRAINT `tamu_reservasi_ibfk_1` FOREIGN KEY (`idTamu`) REFERENCES `tamu` (`idTamu`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tamu_reservasi_ibfk_2` FOREIGN KEY (`idKamar`) REFERENCES `kamar` (`idKamar`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `transaksi_ibfk_1` FOREIGN KEY (`idTamu`) REFERENCES `tamu` (`idTamu`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
