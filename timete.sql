-- phpMyAdmin SQL Dump
-- version 3.4.5deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Dec 27, 2012 at 08:56 AM
-- Server version: 5.1.66
-- PHP Version: 5.3.6-13ubuntu3.9

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `timete`
--

-- --------------------------------------------------------

--
-- Table structure for table `timete_comment`
--

CREATE TABLE IF NOT EXISTS `timete_comment` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `datetime` datetime NOT NULL,
  `event_id` int(11) NOT NULL,
  `comment` text NOT NULL,
  KEY `id_index` (`id`) USING BTREE,
  KEY `user_id_index` (`user_id`) USING BTREE,
  KEY `event_id_index` (`event_id`) USING BTREE,
  KEY `datetime_index` (`datetime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin5;

--
-- Dumping data for table `timete_comment`
--

INSERT INTO `timete_comment` (`id`, `user_id`, `datetime`, `event_id`, `comment`) VALUES
(100007, 7, '2012-12-20 15:02:52', 1000033, 'Test1'),
(100008, 7, '2012-12-20 15:10:31', 1000012, 'kjböjblk'),
(100009, 6, '2012-12-20 18:24:15', 1000021, 'adfadsfasddasf'),
(100010, 11, '2012-12-20 18:25:51', 1000014, 'sFASAFD'),
(100011, 6, '2012-12-20 18:46:23', 1000012, 'hjhgjghjhgj'),
(100012, 17, '2012-12-22 12:50:25', 17, 'test'),
(100013, 17, '2012-12-22 12:50:36', 17, 'nice feature'),
(100014, 17, '2012-12-22 14:50:44', 17, 'working great'),
(100015, 17, '2012-12-22 12:50:53', 17, 'commenting and commenting'),
(100016, 17, '2012-12-22 12:51:00', 17, 'one more'),
(100017, 17, '2012-12-22 12:51:06', 17, 'last one'),
(100018, 17, '2012-12-22 14:52:11', 17, 'asldal'),
(100019, 17, '2012-12-22 12:52:28', 17, 'sxasdadsdasd'),
(100020, 5, '2012-12-22 12:57:48', 1000012, 'heyt'),
(100021, 17, '2012-12-22 12:59:25', 1000012, 'this is a ttest comment'),
(100022, 17, '2012-12-22 12:59:32', 1000012, 'one more test comment'),
(100023, 17, '2012-12-22 12:59:37', 1000012, 'one more'),
(100024, 17, '2012-12-22 12:59:47', 1000012, 'one one last more'),
(100025, 17, '2012-12-22 13:00:53', 1000012, 'one more test and test and test'),
(100026, 17, '2012-12-22 13:28:54', 1000022, 'this is a test'),
(100027, 22, '2012-12-25 11:58:28', 1000021, 'sadsad'),
(100028, 22, '2012-12-25 11:58:35', 1000021, '1231231'),
(100029, 22, '2012-12-25 11:58:37', 1000021, '123123'),
(100030, 23, '2012-12-25 19:14:42', 1000038, 'wertyuı');

-- --------------------------------------------------------

--
-- Table structure for table `timete_events`
--

CREATE TABLE IF NOT EXISTS `timete_events` (
  `id` int(11) NOT NULL,
  `title` text NOT NULL,
  `location` text NOT NULL,
  `description` text NOT NULL,
  `startDateTime` datetime NOT NULL,
  `endDateTime` datetime NOT NULL,
  `reminderType` text NOT NULL,
  `reminderUnit` text NOT NULL,
  `reminderValue` int(11) NOT NULL,
  `privacy` int(11) NOT NULL,
  `allday` int(11) NOT NULL,
  `repeat_` int(11) NOT NULL,
  `addsocial_fb` int(11) NOT NULL,
  `addsocial_gg` int(11) NOT NULL,
  `addsocial_fq` int(11) NOT NULL,
  `addsocial_tw` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_index` (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin5;

--
-- Dumping data for table `timete_events`
--

INSERT INTO `timete_events` (`id`, `title`, `location`, `description`, `startDateTime`, `endDateTime`, `reminderType`, `reminderUnit`, `reminderValue`, `privacy`, `allday`, `repeat_`, `addsocial_fb`, `addsocial_gg`, `addsocial_fq`, `addsocial_tw`) VALUES
(1000010, 'Robi açılış', 'In The Van', 'Robi konser', '2012-12-22 19:00:00', '2012-12-22 19:00:00', '', '', 0, 1, 0, 0, 0, 0, 0, 0),
(1000011, 'Aynen Johnny', 'Maslak', 'Johnny Cash The Man In over 100 hit singles in the United States, he was to become one of the...', '2012-12-05 19:00:00', '2012-12-13 19:00:00', '', '', 0, 1, 0, 0, 0, 0, 0, 0),
(1000012, 'Johnny Cash Concert', 'Maslak', '"Johnny Cash -- The Man In Black -- His Early Years Johnny Cash has been described as one of the most influential figures in country music history. Achieving over 100 hit singles in the United States, he was to become one of the biggest stars through the 50''s to the 70''s. This programme features performances"', '2012-12-19 19:15:00', '2012-12-19 19:15:00', '', '', 0, 1, 0, 0, 0, 0, 0, 0),
(1000013, 'Yılın Ofis Partisi', 'Youth Republic', 'Yeni yıla merhaba', '2012-12-24 20:15:00', '2012-12-24 20:15:00', 'email', 'min', 30, 1, 0, 0, 0, 0, 0, 1),
(1000014, 'Test Mest', 'Qubist', 'Çalışıyoz biz yaaaa', '2013-01-01 20:30:00', '0000-00-00 21:30:00', 'sms', 'min', 0, 1, 1, 0, 0, 0, 0, 0),
(1000015, 'Aynen Johnny', 'Maslakk', '"Johnny Cash -- The Man In Black -- His Early Years Johnny Cash has been described as one of the most influential figures in country music history. Achieving over 100 hit singles in the United States, he was to become one of the biggest stars through the 50''s to the 70''s. This programme features performances from Johnny Cash, where his raw talent is evident and distinctive voice.  The show features sixteen memorable songs, from ''Five Feet High and Rising'' to a humorous parody of Elvis Presley on ''Heartbreak Hotel.''"', '2012-12-26 20:45:00', '0000-00-00 20:45:00', '', '', 0, 1, 0, 0, 0, 0, 0, 0),
(1000016, 'Fetiş Yaşlandı', 'Meyhane', 'Yaşlandı, hala doğum günü yapıyor.', '2012-12-21 20:45:00', '2012-12-22 01:45:00', '', '', 0, 1, 0, 0, 0, 0, 0, 0),
(1000017, 'İstanbul Caz Festivali', 'bulamadım', 'İstanbul Kültür Sanat Vakfı tarafından 19 yıldır düzenlenen İstanbul Caz Festivali, 15 yıldan bu yana aralıksız destek veren Garanti Bankası?nın', '2013-02-07 21:15:00', '2013-04-11 21:15:00', '', '', 0, 1, 0, 0, 0, 0, 0, 0),
(1000018, 'Anadolu Efes Maçı', 'Abdi İpekçi', '#haydianadoluefes', '2012-12-21 18:15:00', '0000-00-00 20:15:00', 'email', 'hour', 2, 1, 0, 0, 0, 0, 0, 0),
(1000019, 'Zakkum Konseri', 'Jolly Joker İstanbul', 'Amazon kokarken sofralar...', '2012-12-30 21:30:00', '2012-12-31 21:30:00', 'email', 'day', 1, 1, 0, 0, 0, 0, 0, 0),
(1000020, 'Git Açılış', 'Kadıköy', 'Başar nihayet stüdyosunu açılıyor!', '2012-12-25 21:30:00', '0000-00-00 23:30:00', 'sms', 'min', 40, 1, 0, 0, 0, 0, 0, 0),
(1000021, 'Git Açılış', 'Kadıköy', 'Başar nihayet stüdyosunu açılıyor!', '2012-12-25 21:30:00', '2012-12-25 23:30:00', 'sms', 'min', 40, 1, 0, 0, 0, 0, 0, 0),
(1000022, 'Git Açılış', 'Kadıköy', 'Başar nihayet stüdyosunu açılıyor!', '2012-12-25 21:30:00', '2012-12-25 23:30:00', 'sms', 'min', 40, 1, 0, 0, 0, 0, 0, 0),
(1000023, 'GSMA Mobile World Congress', 'Barcelona, Spain', 'New at MWC 2013 is the NFC Experience. Use a NFC enabled phone at the event as your badge and to conduct transactions, get discounts and access valuable information. Learn More about the NFC Experience or check your phone?s compatibility now.', '2013-02-25 21:30:00', '2013-02-28 21:30:00', '', '', 0, 1, 0, 0, 0, 0, 0, 0),
(1000024, 'GSMA Mobile World Congress', 'Barcelona, Spain', 'New at MWC 2013 is the NFC Experience. Use a NFC enabled phone at the event as your badge and to conduct transactions, get discounts and access valuable information. Learn More about the NFC Experience or check your phone?s compatibility now.', '2013-02-25 21:30:00', '2013-02-28 21:30:00', '', '', 0, 1, 0, 0, 0, 0, 0, 0),
(1000025, 'GSMA Mobile World Congress', 'Barcelona, Spain', 'New at MWC 2013 is the NFC Experience. Use a NFC enabled phone at the event as your badge and to conduct transactions, get discounts and access valuable information. Learn More about the NFC Experience or check your phone?s compatibility now.', '2013-02-25 21:30:00', '2013-02-28 21:30:00', '', '', 0, 1, 0, 0, 0, 0, 0, 0),
(1000026, 'GSMA Mobile World Congress', 'Barcelona, Spain', 'New at MWC 2013 is the NFC Experience. Use a NFC enabled phone at the event as your badge and to conduct transactions, get discounts and access valuable information. Learn More about the NFC Experience or check your phone?s compatibility now.', '2013-02-25 21:30:00', '2013-02-28 21:30:00', '', '', 0, 1, 0, 0, 0, 0, 0, 0),
(1000027, 'GSMA Mobile World Congress', 'Barcelona, Spain', 'New at MWC 2013 is the NFC Experience. Use a NFC enabled phone at the event as your badge and to conduct transactions, get discounts and access valuable information. Learn More about the NFC Experience or check your phone?s compatibility now.', '2013-02-25 21:30:00', '2013-02-28 21:30:00', '', '', 0, 1, 0, 0, 0, 0, 0, 0),
(1000028, 'Google IO', 'SF', 'Google IO Summit', '2013-10-16 15:15:00', '2013-10-31 20:00:00', 'sms', 'min', 0, 1, 0, 0, 0, 1, 0, 0),
(1000029, 'GDG Grup Toplantısı', 'Kanyon', 'GDG Grup Toplantısı', '2013-01-19 16:00:00', '0000-00-00 23:00:00', '', '', 0, 1, 0, 0, 0, 0, 0, 0),
(1000030, 'dasdasdsad', 'sdasd', 'asdsad', '2012-12-21 16:15:00', '0000-00-00 18:15:00', '', '', 0, 1, 0, 0, 0, 0, 0, 0),
(1000031, 'GDG grup içi toplantı2', 'asdsad', 'sadas', '2012-12-28 16:15:00', '0000-00-00 18:15:00', '', '', 0, 1, 0, 0, 0, 0, 0, 0),
(1000032, 'Google IO', 'SF', 'Google IO Summit', '2013-06-21 16:15:00', '2013-09-19 16:15:00', '', '', 0, 1, 0, 0, 0, 0, 0, 0),
(1000033, 'Deneme1', 'istanbbul', '"Johnny Cash -- The Man In Black -- His Early Years Johnny Cash has been described as one of the most influential figures in country music history. Achieving over 100 hit singles in the United States, he was to become one of the biggest stars through the 50''s to the 70''s. This programme features performances from Johnny Cash, where his raw talent is evident and distinctive voice.  The show features sixteen memorable songs, from ''Five Feet High and Rising'' to a humorous parody of Elvis Presley on ''Heartbreak Hotel.''"', '2012-12-21 16:15:00', '0000-00-00 21:15:00', '', '', 0, 1, 0, 0, 0, 0, 0, 0),
(1000034, 'Google IO', 'SF', 'Google IO Summit', '2012-12-21 16:15:00', '2012-12-30 16:15:00', '', '', 0, 1, 0, 0, 0, 0, 0, 0),
(1000035, 'Brain Week', 'NYC', 'This wee is a brain week', '2014-02-01 16:15:00', '2014-04-30 16:15:00', '', '', 0, 1, 0, 0, 0, 0, 0, 0),
(1000036, 'OSCAR AWARDS - HOLLYWOOD MAY 2013 ', 'Hollywood', 'Oscar awards for 2012', '2013-06-14 18:00:00', '2013-06-26 21:00:00', '', '', 0, 1, 0, 0, 0, 0, 0, 0),
(1000037, 'Christmas Tour in Palma de Mallorca ', 'Palma, Spain', 'A wonderful Christmas shopping experience in one of the world''s most beautiful citys', '2013-02-20 20:30:00', '2013-02-28 22:15:00', '', '', 0, 1, 0, 0, 0, 0, 0, 0),
(1000038, 'Aynen Johnny', 'Bostancı', 'asdasda', '2013-03-07 13:45:00', '2013-06-18 17:45:00', 'email', 'hour', 212, 1, 0, 1, 1, 1, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `timete_fq_category_mapping`
--

CREATE TABLE IF NOT EXISTS `timete_fq_category_mapping` (
  `fq_category_name` varchar(500) NOT NULL,
  `fb_category_name` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin5;

--
-- Dumping data for table `timete_fq_category_mapping`
--

INSERT INTO `timete_fq_category_mapping` (`fq_category_name`, `fb_category_name`) VALUES
('Travel Agency', 'Airport'),
('Airport', 'Airport'),
('Airport Food Court', 'Airport'),
('Airport Gate', 'Airport'),
('Airport Lounge', 'Airport'),
('Airport Terminal', 'Airport'),
('Airport Tram', 'Airport'),
('Plane', 'Airport'),
('Bike Rental / Bike Share', 'Airport'),
('Bus Station', 'Airport'),
('Bus Line', 'Airport'),
('Embassy/Consulate', 'Airport'),
('Ferry', 'Airport'),
('Boat or Ferry', 'Airport'),
('Pier', 'Airport'),
('General Travel', 'Airport'),
('College Academic Building', 'Arts/Entertainment/Nightlife'),
('College Arts Building', 'Arts/Entertainment/Nightlife'),
('Arts & Crafts Store', 'Arts/Entertainment/Nightlife'),
('Parking', 'Automotive'),
('Rental Car Location', 'Automotive'),
('General Entertainment', 'Bar'),
('Music Venue', 'Bar'),
('Concert Hall', 'Bar'),
('Juice Bar', 'Bar'),
('Bar', 'Bar'),
('Beer Garden', 'Bar'),
('Brewery', 'Bar'),
('Cocktail Bar', 'Bar'),
('Dive Bar', 'Bar'),
('Gay Bar', 'Bar'),
('Hookah Bar', 'Bar'),
('Hotel Bar', 'Bar'),
('Karaoke Bar', 'Bar'),
('Lounge', 'Bar'),
('Nightclub', 'Bar'),
('Other Nightlife', 'Bar'),
('Pub', 'Bar'),
('Sake Bar', 'Bar'),
('Speakeasy', 'Bar'),
('Sports Bar', 'Bar'),
('Strip Club', 'Bar'),
('Whisky Bar', 'Bar'),
('Wine Bar', 'Bar'),
('Bookstore', 'Book Store'),
('Casino', 'Club'),
('Comedy Club', 'Club'),
('Performing Arts Venue', 'Club'),
('Dance Studio', 'Club'),
('Event Space', 'Club'),
('Auditorium', 'Concert Venue'),
('Spa or Massage', 'Health/Medical/Pharmacy'),
('Medical Center', 'Hospital/Clinic'),
('Dentists Office', 'Hospital/Clinic'),
('Doctors Office', 'Hospital/Clinic'),
('Emergency Room', 'Hospital/Clinic'),
('Hospital', 'Hospital/Clinic'),
('Laboratory', 'Hospital/Clinic'),
('Optical Shop', 'Hospital/Clinic'),
('Veterinarian', 'Hospital/Clinic'),
('Hotel', 'Hotel'),
('Bed & Breakfast', 'Hotel'),
('Boarding House', 'Hotel'),
('Hostel', 'Hotel'),
('Hotel Pool', 'Hotel'),
('Motel', 'Hotel'),
('Resort', 'Hotel'),
('Roof Deck', 'Hotel'),
('Movie Theater', 'Movie Theatre'),
('Indie Movie Theater', 'Movie Theatre'),
('Multiple', 'Movie Theatre'),
('Art Gallery', 'Museum/Art Gallery'),
('Museum', 'Museum/Art Gallery'),
('Art Museum', 'Museum/Art Gallery'),
('History Museum', 'Museum/Art Gallery'),
('Planetarium', 'Museum/Art Gallery'),
('Science Museum', 'Museum/Art Gallery'),
('Zoo', 'Pet Services'),
('Animal Shelter', 'Pet Services'),
('Pet Store', 'Pet Services'),
('Burrito Place', 'Public Places'),
('Salad Place', 'Public Places'),
('Sandwich Place', 'Public Places'),
('Snack Place', 'Public Places'),
('Soup Place', 'Public Places'),
('Taco Place', 'Public Places'),
('African Restaurant', 'Restaurant/Cafe'),
('American Restaurant', 'Restaurant/Cafe'),
('Arepa Restaurant', 'Restaurant/Cafe'),
('Argentinian Restaurant', 'Restaurant/Cafe'),
('Asian Restaurant', 'Restaurant/Cafe'),
('Australian Restaurant', 'Restaurant/Cafe'),
('BBQ Joint', 'Restaurant/Cafe'),
('Bagel Shop', 'Restaurant/Cafe'),
('Bakery', 'Restaurant/Cafe'),
('Brazilian Restaurant', 'Restaurant/Cafe'),
('Breakfast Spot', 'Restaurant/Cafe'),
('Burger Joint', 'Restaurant/Cafe'),
('Cafe', 'Restaurant/Cafe'),
('Cajun/Creole Restaurant', 'Restaurant/Cafe'),
('Caribbean Restaurant', 'Restaurant/Cafe'),
('Chinese Restaurant', 'Restaurant/Cafe'),
('Coffee Shop', 'Restaurant/Cafe'),
('Cuban Restaurant', 'Restaurant/Cafe'),
('Cupcake Shop', 'Restaurant/Cafe'),
('Deli/Bodega', 'Restaurant/Cafe'),
('Dessert Shop', 'Restaurant/Cafe'),
('Dim Sum Restaurant', 'Restaurant/Cafe'),
('Diner', 'Restaurant/Cafe'),
('Distillery', 'Restaurant/Cafe'),
('Donut Shop', 'Restaurant/Cafe'),
('Dumpling Restaurant', 'Restaurant/Cafe'),
('Eastern European Restaurant', 'Restaurant/Cafe'),
('Ethiopian Restaurant', 'Restaurant/Cafe'),
('Falafel Restaurant', 'Restaurant/Cafe'),
('Fast Food Restaurant', 'Restaurant/Cafe'),
('Filipino Restaurant', 'Restaurant/Cafe'),
('Fish & Chips Shop', 'Restaurant/Cafe'),
('Food Court', 'Restaurant/Cafe'),
('Food Truck', 'Restaurant/Cafe'),
('French Restaurant', 'Restaurant/Cafe'),
('Fried Chicken Joint', 'Restaurant/Cafe'),
('Gastropub', 'Restaurant/Cafe'),
('German Restaurant', 'Restaurant/Cafe'),
('Gluten-free Restaurant', 'Restaurant/Cafe'),
('Greek Restaurant', 'Restaurant/Cafe'),
('Hot Dog Joint', 'Restaurant/Cafe'),
('Ice Cream Shop', 'Restaurant/Cafe'),
('Indian Restaurant', 'Restaurant/Cafe'),
('Indonesian Restaurant', 'Restaurant/Cafe'),
('Italian Restaurant', 'Restaurant/Cafe'),
('Japanese Restaurant', 'Restaurant/Cafe'),
('Korean Restaurant', 'Restaurant/Cafe'),
('Latin American Restaurant', 'Restaurant/Cafe'),
('Mac & Cheese Joint', 'Restaurant/Cafe'),
('Malaysian Restaurant', 'Restaurant/Cafe'),
('Mediterranean Restaurant', 'Restaurant/Cafe'),
('Mexican Restaurant', 'Restaurant/Cafe'),
('Middle Eastern Restaurant', 'Restaurant/Cafe'),
('Molecular Gastronomy Restaurant', 'Restaurant/Cafe'),
('Mongolian Restaurant', 'Restaurant/Cafe'),
('Moroccan Restaurant', 'Restaurant/Cafe'),
('New American Restaurant', 'Restaurant/Cafe'),
('Peruvian Restaurant', 'Restaurant/Cafe'),
('Pizza Place', 'Restaurant/Cafe'),
('Portuguese Restaurant', 'Restaurant/Cafe'),
('Ramen / Noodle House', 'Restaurant/Cafe'),
('Restaurant', 'Restaurant/Cafe'),
('Scandinavian Restaurant', 'Restaurant/Cafe'),
('Seafood Restaurant', 'Restaurant/Cafe'),
('South American Restaurant', 'Restaurant/Cafe'),
('Southern / Soul Food Restaurant', 'Restaurant/Cafe'),
('Spanish Restaurant', 'Restaurant/Cafe'),
('Paella Restaurant', 'Restaurant/Cafe'),
('Steakhouse', 'Restaurant/Cafe'),
('Sushi Restaurant', 'Restaurant/Cafe'),
('Swiss Restaurant', 'Restaurant/Cafe'),
('Tapas Restaurant', 'Restaurant/Cafe'),
('Tea Room', 'Restaurant/Cafe'),
('Thai Restaurant', 'Restaurant/Cafe'),
('Turkish Restaurant', 'Restaurant/Cafe'),
('Vegetarian / Vegan Restaurant', 'Restaurant/Cafe'),
('Vietnamese Restaurant', 'Restaurant/Cafe'),
('Winery', 'Restaurant/Cafe'),
('Wings Joint', 'Restaurant/Cafe'),
('School', 'School'),
('Elementary School', 'School'),
('College Communications Building', 'School'),
('College Engineering Building', 'School'),
('College History Building', 'School'),
('College Math Building', 'School'),
('College Science Building', 'School'),
('College Technology Building', 'School'),
('College Administrative Building', 'School'),
('College Auditorium', 'School'),
('College Bookstore', 'School'),
('College Cafeteria', 'School'),
('College Classroom', 'School'),
('College Gym', 'School'),
('College Lab', 'School'),
('College Library', 'School'),
('College Quad', 'School'),
('College Rec Center', 'School'),
('College Residence Hall', 'School'),
('College Stadium', 'School'),
('College Baseball Diamond', 'School'),
('College Basketball Court', 'School'),
('College Cricket Pitch', 'School'),
('College Football Field', 'School'),
('College Hockey Rink', 'School'),
('College Soccer Field', 'School'),
('College Tennis Court', 'School'),
('College Track', 'School'),
('College Theater', 'School'),
('Community College', 'School'),
('Fraternity House (Dernek)', 'School'),
('General College & University', 'School'),
('Law School', 'School'),
('Medical School', 'School'),
('Sorority House', 'School'),
('Student Center', 'School'),
('Trade School', 'School'),
('University', 'School'),
('High School', 'School'),
('Middle School', 'School'),
('Music School', 'School'),
('Nursery School', 'School'),
('Voting Booth', 'School'),
('Arcade', 'Shopping/Retail'),
('Convenience Store', 'Shopping/Retail'),
('Gift Shop', 'Shopping/Retail'),
('Hobby Shop', 'Shopping/Retail'),
('Mall', 'Shopping/Retail'),
('Paper / Office Supplies Store', 'Shopping/Retail'),
('Record Shop', 'Shopping/Retail'),
('Salon / Barbershop', 'Shopping/Retail'),
('Smoke Shop', 'Shopping/Retail'),
('Sporting Goods Shop', 'Shopping/Retail'),
('Cosmetics Shop', 'Shopping/Retail'),
('Daycare', 'Shopping/Retail'),
('Department Store', 'Shopping/Retail'),
('Design Studio', 'Shopping/Retail'),
('Drugstore / Pharmacy', 'Shopping/Retail'),
('Electronics Store', 'Shopping/Retail'),
('Flea Market', 'Shopping/Retail'),
('Flower Shop', 'Shopping/Retail'),
('Bowling Alley', 'Sports Venue'),
('Pool Hall', 'Sports Venue'),
('Racetrack', 'Sports Venue'),
('Stadium', 'Sports Venue'),
('Baseball Stadium', 'Sports Venue'),
('Basketball Stadium', 'Sports Venue'),
('Cricket Ground', 'Sports Venue'),
('Football Stadium', 'Sports Venue'),
('Hockey Arena', 'Sports Venue'),
('Soccer Stadium', 'Sports Venue'),
('Tennis', 'Sports Venue'),
('Track Stadium', 'Sports Venue'),
('Automotive Shop', 'Automobiles and Parts'),
('Car Dealership', 'Automobiles and Parts'),
('Car Wash', 'Automobiles and Parts'),
('Bank', 'Bank/Financial Institution'),
('Food & Drink Shop', 'Food/Beverages'),
('Wine Shop', 'Food/Beverages'),
('Furniture / Home Store', 'Food/Beverages'),
('Aquarium', 'Non-Governmental Organization (NGO)'),
('Light Rail', 'Travel/Leisure'),
('Moving Target', 'Travel/Leisure'),
('Rest Area', 'Travel/Leisure'),
('Road', 'Travel/Leisure'),
('Subway', 'Travel/Leisure'),
('Taxi', 'Travel/Leisure'),
('Tourist Information Center', 'Travel/Leisure'),
('Train Station', 'Travel/Leisure'),
('Platform', 'Travel/Leisure'),
('Train', 'Travel/Leisure'),
('Travel Lounge', 'Travel/Leisure'),
('Building', 'Building Materials'),
('Camera Store', 'Camera/Photo'),
('Clothing Store', 'Clothing'),
('Accessories Store', 'Clothing'),
('Boutique', 'Clothing'),
('Kids Store', 'Clothing'),
('Lingerie Store', 'Clothing'),
('Mens Store', 'Clothing'),
('Shoe Store', 'Clothing'),
('Womens Store', 'Clothing'),
('Toy / Game Store', 'Games/Toys'),
('Gaming Cafe', 'Games/Toys'),
('Jazz Club', 'Movie/Music'),
('Piano Bar', 'Movie/Music'),
('Rock Club', 'Movie/Music'),
('Office', 'Office Supplies'),
('Cafeteria', 'Office Supplies'),
('Conference Room', 'Office Supplies'),
('Coworking Space', 'Office Supplies'),
('Tech Startup', 'Office Supplies'),
('Gym / Fitness Center', 'Professional Sports Team'),
('Gym Pool', 'Professional Sports Team'),
('Gym', 'Professional Sports Team'),
('Martial Arts Dojo', 'Professional Sports Team'),
('Track', 'Professional Sports Team'),
('Yoga Studio', 'Professional Sports Team'),
('Baseball Field', 'Sport Venue'),
('Basketball Court', 'Sport Venue'),
('Ski Area', 'Sport Venue'),
('Apres Ski Bar', 'Sport Venue'),
('Ski Chairlift', 'Sport Venue'),
('Ski Chalet', 'Sport Venue'),
('Ski Lodge', 'Sport Venue'),
('Ski Trail', 'Sport Venue'),
('Sport', 'Sport Venue'),
('Hockey Field', 'Sport Venue'),
('Skate Park', 'Sport Venue'),
('Skating Rink', 'Sport Venue'),
('Soccer Field', 'Sport Venue'),
('Tennis Court', 'Sport Venue'),
('Volleyball Court', 'Sport Venue'),
('Stable', 'Sport Venue');

-- --------------------------------------------------------

--
-- Table structure for table `timete_fq_category_weight_score`
--

CREATE TABLE IF NOT EXISTS `timete_fq_category_weight_score` (
  `source` varchar(150) NOT NULL,
  `category_name` varchar(200) NOT NULL,
  `time` int(11) NOT NULL,
  `checkin` int(11) NOT NULL,
  `total` double NOT NULL,
  `weight` double NOT NULL,
  `constant` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin5;

--
-- Dumping data for table `timete_fq_category_weight_score`
--

INSERT INTO `timete_fq_category_weight_score` (`source`, `category_name`, `time`, `checkin`, `total`, `weight`, `constant`) VALUES
('Facebook', 'School', 6, 30, 50, 1.67, 0),
('Facebook', 'University', 6, 30, 50, 1.67, 0),
('Facebook', 'Airport', 6, 6, 50, 8.33, 0),
('Facebook', 'Arts/Entertainment/Nightlife', 6, 6, 50, 8.33, 0),
('Facebook', 'Attractions/Things to Do', 6, 2, 50, 25, 0),
('Facebook', 'Automotive', 6, 2, 50, 25, 0),
('Facebook', 'Bank/Financial Services', 6, 12, 50, 4.17, 0),
('Facebook', 'Bar', 6, 6, 50, 8.33, 0),
('Facebook', 'Book Store', 6, 3, 50, 16.67, 0),
('Facebook', 'Church/Religious Organization', 6, 12, 50, 4.17, 0),
('Facebook', 'Club', 6, 6, 50, 8.33, 0),
('Facebook', 'Concert Venue', 6, 3, 50, 16.67, 0),
('Facebook', 'Education', 6, 24, 50, 2.08, 0),
('Facebook', 'Event Planning/Event Services', 6, 6, 50, 8.33, 0),
('Facebook', 'Food/Grocery', 6, 12, 50, 4.17, 0),
('Facebook', 'Health/Medical/Pharmacy', 6, 2, 50, 25, 0),
('Facebook', 'Hospital/Clinic', 6, 2, 50, 25, 0),
('Facebook', 'Hotel', 6, 2, 50, 25, 0),
('Facebook', 'Library', 6, 6, 50, 8.33, 0),
('Facebook', 'Local Business', 6, 30, 50, 1.67, 0),
('Facebook', 'Movie Theatre', 6, 3, 50, 16.67, 0),
('Facebook', 'Museum/Art Gallery', 6, 2, 50, 25, 0),
('Facebook', 'Outdoor Gear/Sporting Goods', 6, 2, 50, 25, 0),
('Facebook', 'Pet Services', 6, 3, 50, 16.67, 0),
('Facebook', 'Real Estate', 6, 5, 50, 10, 0),
('Facebook', 'Restaurant/Cafe', 6, 12, 50, 4.17, 0),
('Facebook', 'Shopping/Retail', 6, 12, 50, 4.17, 0),
('Facebook', 'Spas/Beauty/Personel Care', 6, 2, 50, 25, 0),
('Facebook', 'Sports Venue', 6, 6, 50, 8.33, 0),
('Facebook', 'Sports/Recreation/Activities', 6, 6, 50, 8.33, 0),
('Facebook', 'Tour/Sightseeing', 6, 2, 50, 25, 0),
('Facebook', 'Automobiles and Parts', 6, 2, 50, 25, 0),
('Facebook', 'Bank/Financial Institution', 6, 9, 50, 5.56, 0),
('Facebook', 'Company', 6, 30, 50, 1.67, 0),
('Facebook', 'Consulting/Business Services', 6, 24, 50, 2.08, 0),
('Facebook', 'Engineering/Construction', 6, 24, 50, 2.08, 0),
('Facebook', 'Food/Beverages', 6, 30, 50, 1.67, 0),
('Facebook', 'Health/Beauty', 6, 3, 50, 16.67, 0),
('Facebook', 'Health/Medical/Pharmaceuticals', 6, 3, 50, 16.67, 0),
('Facebook', 'Insurance Company', 6, 6, 50, 8.33, 0),
('Facebook', 'Legal/Law', 6, 6, 50, 8.33, 0),
('Facebook', 'Media/News/Publishing', 6, 12, 50, 4.17, 0),
('Facebook', 'Organization', 6, 6, 50, 8.33, 0),
('Facebook', 'Political Organization', 6, 2, 50, 25, 0),
('Facebook', 'Political Party', 6, 6, 50, 8.33, 0),
('Facebook', 'Small Business', 6, 12, 50, 4.17, 0),
('Facebook', 'Baby Goods/Kid Goods', 6, 6, 50, 8.33, 0),
('Facebook', 'Building Materials', 6, 5, 50, 10, 0),
('Facebook', 'Camera/Photo', 6, 2, 50, 25, 0),
('Facebook', 'Cars', 6, 2, 50, 25, 0),
('Facebook', 'Clothing', 6, 12, 50, 4.17, 0),
('Facebook', 'Commercials Equipment', 6, 2, 50, 25, 0),
('Facebook', 'Computers', 6, 2, 50, 25, 0),
('Facebook', 'Electronics', 6, 3, 50, 16.67, 0),
('Facebook', 'Furniture', 6, 3, 50, 16.67, 0),
('Facebook', 'Games/Toys', 6, 6, 50, 8.33, 0),
('Facebook', 'Home Decor', 6, 3, 50, 16.67, 0),
('Facebook', 'Household Supplies', 6, 6, 50, 8.33, 0),
('Facebook', 'Jewelry/Watches', 6, 2, 50, 25, 0),
('Facebook', 'Kitchen/Cooking', 6, 8, 50, 6.25, 0),
('Facebook', 'Movie/Music', 6, 12, 50, 4.17, 0),
('Facebook', 'Office Supplies', 6, 2, 50, 25, 0),
('Facebook', 'Pet Supplies', 6, 7, 50, 7.14, 0),
('Facebook', 'Wine/Spirits', 6, 5, 50, 10, 0),
('Facebook', 'Comedian', 6, 2, 50, 25, 0),
('Facebook', 'Doctor', 6, 2, 50, 25, 0),
('Facebook', 'Government Official', 6, 30, 50, 1.67, 0),
('Facebook', 'Album', 6, 5, 50, 10, 0),
('Facebook', 'Amateur Sports Team', 6, 3, 50, 16.67, 0),
('Facebook', 'Book', 6, 3, 50, 16.67, 0),
('Facebook', 'Concert Tour', 6, 4, 50, 12.5, 0),
('Facebook', 'Movie', 6, 12, 50, 4.17, 0),
('Facebook', 'Music Award', 6, 2, 50, 25, 0),
('Facebook', 'Music Video', 6, 12, 50, 4.17, 0),
('Facebook', 'Studio', 6, 24, 50, 2.08, 0);

-- --------------------------------------------------------

--
-- Table structure for table `timete_images`
--

CREATE TABLE IF NOT EXISTS `timete_images` (
  `id` int(11) NOT NULL,
  `url` text NOT NULL,
  `header` int(11) NOT NULL,
  `eventId` int(11) NOT NULL,
  `width` int(11) NOT NULL DEFAULT '180',
  `height` int(11) NOT NULL,
  KEY `id_index` (`id`) USING BTREE,
  KEY `eventId_index` (`eventId`) USING BTREE,
  KEY `header_index` (`header`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin5;

--
-- Dumping data for table `timete_images`
--

INSERT INTO `timete_images` (`id`, `url`, `header`, `eventId`, `width`, `height`) VALUES
(1000010, 'uploads/events/1000010/ImageEventHeader6_1699713.png', 1, 1000010, 186, 124),
(1000011, 'uploads/events/1000011/ImageEventHeader8_1106983.png', 1, 1000011, 186, 183),
(1000012, 'uploads/events/1000012/ImageEventHeader8_1106983.png', 1, 1000012, 186, 183),
(1000013, 'uploads/events/1000013/ImageEventHeader10_3878005.png', 1, 1000013, 186, 123),
(1000014, 'uploads/events/1000014/ImageEventHeader11_9377253.png', 1, 1000014, 186, 143),
(1000015, 'uploads/events/1000015/ImageEventHeader7_83333.png', 1, 1000015, 186, 183),
(1000016, 'uploads/events/1000016/ImageEventHeader11_8032925.png', 1, 1000016, 186, 124),
(1000017, 'uploads/events/1000017/ImageEventHeader7_446259.png', 1, 1000017, 186, 106),
(1000018, 'uploads/events/1000018/ImageEventHeader10_6178285.png', 1, 1000018, 186, 219),
(1000019, 'uploads/events/1000019/ImageEventHeader10_6178285.png', 1, 1000019, 186, 170),
(1000020, 'uploads/events/1000020/ImageEventHeader10_6178285.png', 1, 1000020, 186, 186),
(1000021, 'uploads/events/1000021/ImageEventHeader10_6178285.png', 1, 1000021, 186, 186),
(1000022, 'uploads/events/1000022/ImageEventHeader10_6178285.png', 1, 1000022, 186, 186),
(1000023, 'uploads/events/1000023/ImageEventHeader7_2812827.png', 1, 1000023, 186, 41),
(1000024, 'uploads/events/1000024/ImageEventHeader7_2812827.png', 1, 1000024, 186, 41),
(1000025, 'uploads/events/1000025/ImageEventHeader7_2812827.png', 1, 1000025, 186, 41),
(1000026, 'uploads/events/1000026/ImageEventHeader7_2812827.png', 1, 1000026, 186, 41),
(1000027, 'uploads/events/1000027/ImageEventHeader7_2812827.png', 1, 1000027, 186, 41),
(1000028, 'uploads/events/1000028/ImageEventHeader7_3841422.png', 1, 1000028, 186, 120),
(1000029, 'uploads/events/1000029/ImageEventHeader5_9150914.png', 1, 1000029, 186, 189),
(1000030, 'uploads/events/1000030/ImageEventHeader5_226023.png', 1, 1000030, 186, 189),
(1000031, 'uploads/events/1000031/ImageEventHeader5_2963690.png', 1, 1000031, 186, 189),
(1000032, 'uploads/events/1000032/ImageEventHeader7_4913261.png', 1, 1000032, 186, 120),
(1000033, 'uploads/events/1000033/ImageEventHeader7_5271511.png', 1, 1000033, 186, 104),
(1000034, 'uploads/events/1000034/ImageEventHeader7_999274.png', 1, 1000034, 186, 120),
(1000035, 'uploads/events/1000035/ImageEventHeader7_8946022.png', 1, 1000035, 186, 164),
(1000036, 'uploads/events/1000036/ImageEventHeader17_3984246.png', 1, 1000036, 186, 116),
(1000037, 'uploads/events/1000037/ImageEventHeader17_2521122.png', 1, 1000037, 186, 138),
(1000038, 'uploads/events/1000038/ImageEventHeader1_3509304.png', 1, 1000038, 186, 189);

-- --------------------------------------------------------

--
-- Table structure for table `timete_key_generator`
--

CREATE TABLE IF NOT EXISTS `timete_key_generator` (
  `PK_COLUMN` varchar(255) DEFAULT NULL,
  `VALUE_COLUMN` int(11) DEFAULT NULL,
  KEY `PK_COLUMN_index` (`PK_COLUMN`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin5;

--
-- Dumping data for table `timete_key_generator`
--

INSERT INTO `timete_key_generator` (`PK_COLUMN`, `VALUE_COLUMN`) VALUES
('EVENT_ID', 1000038),
('IMAGE_ID', 1000038),
('COMMENT_ID', 100030);

-- --------------------------------------------------------

--
-- Table structure for table `timete_lost_pass`
--

CREATE TABLE IF NOT EXISTS `timete_lost_pass` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL,
  `guid` varchar(100) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `valid` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_index` (`id`) USING BTREE,
  KEY `guid` (`guid`) USING BTREE,
  KEY `user_id_index` (`user_id`) USING BTREE,
  KEY `valid_index` (`valid`) USING BTREE
) ENGINE=InnoDB  DEFAULT CHARSET=latin5 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `timete_lost_pass`
--

INSERT INTO `timete_lost_pass` (`id`, `date`, `guid`, `user_id`, `valid`) VALUES
(1, '2012-12-18 00:00:00', '84b2704e-c036-4ffc-b1e7-dfc9e0817d67', 10, b'1');

-- --------------------------------------------------------

--
-- Table structure for table `timete_unknown_category`
--

CREATE TABLE IF NOT EXISTS `timete_unknown_category` (
  `categoryName` varchar(255) NOT NULL,
  `userId` varchar(255) NOT NULL,
  `eventId` varchar(255) NOT NULL,
  `socialType` varchar(50) NOT NULL,
  `status` varchar(255) NOT NULL,
  PRIMARY KEY (`categoryName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin5;

--
-- Dumping data for table `timete_unknown_category`
--

INSERT INTO `timete_unknown_category` (`categoryName`, `userId`, `eventId`, `socialType`, `status`) VALUES
('Arts/humanities website', '11', '115222486880', 'facebook', '0'),
('Book genre', '11', '103091413063976', 'facebook', '0'),
('Business/economy website', '11', '133030005816', 'facebook', '0'),
('Café', '14', '4dce7ed3d164679b8d003961', 'foursquare', '0'),
('City', '12', '107968765903327', 'facebook', '0'),
('Community', '5', '299029716825079', 'facebook', '0'),
('Community organization', '11', '167704353357927', 'facebook', '0'),
('Computers/internet website', '5', '147589488595196', 'facebook', '0'),
('Computers/technology', '5', '259655700571', 'facebook', '0'),
('Entertainment website', '5', '119309631477965', 'facebook', '0'),
('Field of study', '5', '106057162768533', 'facebook', '0'),
('Harbor / Marina', '14', '4dd769f31838b8561ce6a481', 'foursquare', '0'),
('Historic Site', '14', '4e119eb61495c8d31bc7fdd8', 'foursquare', '0'),
('Interest', '5', '109595959065931', 'facebook', '0'),
('Local/travel website', '11', '129844753698093', 'facebook', '0'),
('Movie general', '5', '356775114716', 'facebook', '0'),
('Movies/music', '11', '105528216433', 'facebook', '0'),
('Musical genre', '11', '109626049064516', 'facebook', '0'),
('News/media website', '5', '158273552671', 'facebook', '0'),
('Non-profit organization', '7', '102538116544338', 'facebook', '0'),
('Other', '5', '144942542266210', 'facebook', '0'),
('Personal blog', '5', '113609478710158', 'facebook', '0'),
('Personal website', '11', '8110274206', 'facebook', '0'),
('Plaza', '14', '4b64c88af964a520d4cf2ae3', 'foursquare', '0'),
('Reference website', '11', '15280752749', 'facebook', '0'),
('Society/culture website', '11', '276061079180772', 'facebook', '0'),
('Sport', '12', '105992172764394', 'facebook', '0'),
('Teens/kids website', '5', '117411191678', 'facebook', '0'),
('Tours/sightseeing', '11', '90653120948', 'facebook', '0'),
('Video', '5', '386467058073901', 'facebook', '0');

-- --------------------------------------------------------

--
-- Table structure for table `timete_users`
--

CREATE TABLE IF NOT EXISTS `timete_users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(100) DEFAULT NULL,
  `userName` varchar(100) NOT NULL,
  `firstName` varchar(100) DEFAULT NULL,
  `lastName` varchar(100) DEFAULT NULL,
  `birthdate` date DEFAULT NULL,
  `hometown` varchar(100) DEFAULT NULL,
  `status` int(11) NOT NULL,
  `saved` int(11) NOT NULL,
  `password` varchar(255) NOT NULL,
  `type` int(11) NOT NULL DEFAULT '0',
  `confirm` int(11) NOT NULL DEFAULT '0',
  `userPicture` text NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`,`userName`),
  KEY `status` (`status`),
  KEY `id_index` (`id`) USING BTREE,
  KEY `email_index` (`email`) USING BTREE,
  KEY `userName_index` (`userName`) USING BTREE,
  KEY `status_index` (`status`) USING BTREE,
  KEY `password_index` (`password`) USING BTREE
) ENGINE=MyISAM  DEFAULT CHARSET=latin5 AUTO_INCREMENT=24 ;

--
-- Dumping data for table `timete_users`
--

INSERT INTO `timete_users` (`id`, `email`, `userName`, `firstName`, `lastName`, `birthdate`, `hometown`, `status`, `saved`, `password`, `type`, `confirm`, `userPicture`) VALUES
(5, 'keklikhasan@gmail.com', 'keklikhasan', 'Hasan', 'Keklik', '0000-00-00', 'Mersin', 3, 1, '058a92af5fb207b0cbe88730a381268f162cd40e', 0, 0, ''),
(6, 'mecevit@gmail.com', 'm3c3vit', 'Mehmet', 'Ecevit', '0000-00-00', 'Mersin', 3, 1, 'ee533eb7b465c2fa0de4de299da499962abb3187', 0, 0, ''),
(7, 'arman.eker@gmail.com', 'armaneker', 'Arman', 'Eker', '2012-12-20', 'Izmir', 3, 1, 'b4ed431f46a49562084e950d77236f06d6fdbbe8', 0, 0, ''),
(8, 'arman.eker@gmail.com', 'armaneker', 'Arman', 'Eker', '0000-00-00', 'Turkey', 3, 1, '7c4a8d09ca3762af61e59520943dc26494f8941b', 0, 0, ''),
(9, 'mecevit@gmail.com', 'mecevit', 'Mehmet', 'Ecevit', '0000-00-00', 'istanbul', 3, 1, 'ee533eb7b465c2fa0de4de299da499962abb3187', 0, 0, ''),
(10, 'sedefecevit_1@gmail.com', 'sedef____ecevit_', 'Sedef', 'Ecevit', '0000-00-00', 'Mersin', 3, 1, '2788e2f28713ae41a37e5683632776c414bde08e', 0, 0, ''),
(11, 'sedefecevit_2@gmail.com', 'secevitdasd__asdpsdkasd_Asd', 'Sedef', 'Ecevit', '0000-00-00', 'Mersin', 3, 1, '2788e2f28713ae41a37e5683632776c414bde08e', 0, 0, ''),
(12, 'fndpehlivan@hotmail.com', 'fndpehlivan', 'Funda', 'PehlivanEker', '0000-00-00', 'izmir', 3, 1, '16268c05fe8e18af700196f2518b988936065ead', 0, 0, ''),
(13, 'salim@salim.co', 'salim1', 'salim', 'salim', '2012-12-20', 'ankara', 3, 1, '88ea39439e74fa27c09a4fc0bc8ebe6d00978392', 0, 0, ''),
(14, 'keklikhaddsan@gmail.com', 'hasankeklik', 'Hasan', 'Keklik', '2012-12-20', 'Istanbul Türkiye', 3, 1, '058a92af5fb207b0cbe88730a381268f162cd40e', 0, 0, ''),
(15, '', 'keklikhasan56', 'Hasan', 'Keklik', '0000-00-00', '', 0, 1, '', 0, 0, ''),
(16, 'armeker1@qubist.io', 'armaneker1123', '123', '3211', '2012-12-20', 'İstanbul', 3, 1, 'bd5e5eb049f3907175f54f5a571ba6b9fdea36ab', 0, 0, 'images/anonymous.jpg'),
(17, 'arman.eker@gmail.com', 'armaneker000', 'Arman', 'Eker', '2012-12-20', 'İzmir Turkey', 3, 1, 'bd5e5eb049f3907175f54f5a571ba6b9fdea36ab', 0, 1, 'http://graph.facebook.com/100002075187958/picture?type=large'),
(18, 'sedefecevit@gmail.com', 'sedefecevit', 'Sedef', 'Ecevit', '0000-00-00', 'Mersin', 3, 1, '84dff5608d0da77b39e40180fc5ac0ad17f8cc17', 0, 1, 'images/anonymous.jpg'),
(19, 'volkan.dinc@gmail.com', 'volkandinc', 'Volkan', 'Dinç', '2012-10-20', 'İstanbul', 3, 1, '0a5b14a805be2b7172781accd189beebd00bcd50', 0, 1, 'images/anonymous.jpg'),
(20, 'asasasa@gmail.com', 'keklikhasan22', 'Hasan', 'Keklik', '2012-12-20', 'Mersin', 3, 1, '058a92af5fb207b0cbe88730a381268f162cd40e', 0, 0, 'http://graph.facebook.com/681274420/picture?type=large'),
(21, 'salim@salim.co', 'salimkayabasi', 'Salim', 'Kayabasi', '2012-12-20', 'ankara', 3, 1, '88ea39439e74fa27c09a4fc0bc8ebe6d00978392', 0, 0, 'http://graph.facebook.com/1232605293/picture?type=large'),
(22, 'aldmalsd@cac.com', 'armanekekr13', 'qweq', 'qwe', '2012-12-20', 'aasdasd', 3, 1, '322f2bc357ca436b046f3b1abe644a769b0af8e0', 0, 0, 'images/anonymous.jpg'),
(23, 'asda@google.com', 'alskdalskdj', 'Arman', 'Eker', '0000-00-00', 'Izmir', 3, 1, 'bd5e5eb049f3907175f54f5a571ba6b9fdea36ab', 0, 0, 'images/anonymous.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `timete_user_socialprovider`
--

CREATE TABLE IF NOT EXISTS `timete_user_socialprovider` (
  `user_id` int(11) NOT NULL,
  `oauth_uid` varchar(200) NOT NULL,
  `oauth_provider` varchar(50) NOT NULL,
  `oauth_token` varchar(255) NOT NULL,
  `oauth_token_secret` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL,
  KEY `user_id` (`user_id`,`oauth_uid`,`oauth_provider`),
  KEY `user_id_index` (`user_id`) USING BTREE,
  KEY `oauth_uid_index` (`oauth_uid`) USING BTREE,
  KEY `oauth_provider_index` (`oauth_provider`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin5;

--
-- Dumping data for table `timete_user_socialprovider`
--

INSERT INTO `timete_user_socialprovider` (`user_id`, `oauth_uid`, `oauth_provider`, `oauth_token`, `oauth_token_secret`, `status`) VALUES
(5, '681274420__', 'facebook', 'AAAHzZCcPdaK0BAIlBN2Ouzg8OyC8fW3b18O6psZBUTckZCWmj3KB0jBmBYHSZBRqLeSrHTIrRdoFarRSmoLeZBljPporlGxtZCTfNihT3bKQZDZD', '', 2),
(6, '618913134', 'facebook', 'AAAHzZCcPdaK0BAEHWzhqUtqngTtBGZCb9kAbitLMaWjZCU3aqtPeiVdZC9vSKmfta7G0bWQ2dOdXOjJzNWDYWUPv8o5VyNZANhGPFI6YZApQZDZD', '', 2),
(7, '100002075187958_1', 'facebook', 'AAAHzZCcPdaK0BANvKgu81N6qPXhg2aqZCMP4iNXE9xe6WPUJcCjzjZAJhOZAbX3cgM4entGyLFS63w4d2ZArGrfTs4h2bkSTyuRbrHtF6EAZDZD', '', 2),
(8, '230417411', 'twitter', '230417411-oUzZzMMXbGEJFWad08A19NdExcdv1EpRsPeapNnd', 'yQ6lJ9ngZsVr2VyNf536ZiGRKpqGwYmnxriXFJtHw', 1),
(9, '6173312', 'twitter', '6173312-lQPamsvCSD6kNCqrEG5ywhdkczAfi1MXwvzJotxKOy', '9IcZ0gZdqQbjKe8eNCIwkpZqF2TM1d4MckKBOZ5o', 1),
(8, '100002075187958_', 'facebook', 'AAAHzZCcPdaK0BANvKgu81N6qPXhg2aqZCMP4iNXE9xe6WPUJcCjzjZAJhOZAbX3cgM4entGyLFS63w4d2ZArGrfTs4h2bkSTyuRbrHtF6EAZDZD', '', 2),
(9, '618913134', 'facebook', 'AAAHzZCcPdaK0BAEHWzhqUtqngTtBGZCb9kAbitLMaWjZCU3aqtPeiVdZC9vSKmfta7G0bWQ2dOdXOjJzNWDYWUPv8o5VyNZANhGPFI6YZApQZDZD', '', 2),
(8, '6632766', 'foursquare', '32YPM2BHYEPWSL321D2XKEWQECSJDI4XQYRX0CCEKR52FXEK', '', 2),
(11, '581337366____', 'facebook', 'AAAHzZCcPdaK0BAM6kos5KZCZCNtGBbuwJx3t1V1iY7t274pQTtiEorG66SJS1POCiXtNfBZAKYB3TFsBfb7uGBl36MX6n1lW9Aymwz4QOQZDZD', '', 2),
(11, '94040275____', 'twitter', '94040275-pEy9D7QVYoOuM8IwRS2u36BQffgI7yLVu9tqOoTLk', 'MzARuKkSKVazx2QrksjZrxudcwMYagftl8Vq4Ir3JY', 1),
(12, '100002699142906', 'facebook', 'AAAHzZCcPdaK0BAIpC9B1euUVBtTMpDKTnXoHTDVqCNZCadaZApy4D2IVh916apgmMAv0fFk2mHMpboz6OldAUSCitlhZBEa7brUdE6cHBAZDZD', '', 2),
(14, '7376228', 'foursquare', '11JYMJFXVQSBQ0WPQPLX2Q23IGYE1PK5DIHGZRXBNRMPF5NP', '', 2),
(15, '108707879', 'twitter', '108707879-ouzJ8EL2PJIqU9qfIZmWcDs95Q3v0K1tWzYetw0z', 'RJuN2CKuJiQh69gRX8KvuLAdbmfJ4eWc5hpkyqvlwo', 1),
(16, '100002075187958_2', 'facebook', 'AAAHzZCcPdaK0BANvKgu81N6qPXhg2aqZCMP4iNXE9xe6WPUJcCjzjZAJhOZAbX3cgM4entGyLFS63w4d2ZArGrfTs4h2bkSTyuRbrHtF6EAZDZD', '', 2),
(17, '100002075187958', 'facebook', 'AAAHzZCcPdaK0BANvKgu81N6qPXhg2aqZCMP4iNXE9xe6WPUJcCjzjZAJhOZAbX3cgM4entGyLFS63w4d2ZArGrfTs4h2bkSTyuRbrHtF6EAZDZD', '', 2),
(18, '581337366', 'facebook', 'AAAHzZCcPdaK0BAM6kos5KZCZCNtGBbuwJx3t1V1iY7t274pQTtiEorG66SJS1POCiXtNfBZAKYB3TFsBfb7uGBl36MX6n1lW9Aymwz4QOQZDZD', '', 2),
(18, '94040275', 'twitter', '94040275-pEy9D7QVYoOuM8IwRS2u36BQffgI7yLVu9tqOoTLk', 'MzARuKkSKVazx2QrksjZrxudcwMYagftl8Vq4Ir3JY', 1),
(20, '681274420', 'facebook', 'AAAHzZCcPdaK0BAIlBN2Ouzg8OyC8fW3b18O6psZBUTckZCWmj3KB0jBmBYHSZBRqLeSrHTIrRdoFarRSmoLeZBljPporlGxtZCTfNihT3bKQZDZD', '', 2),
(21, '1232605293', 'facebook', 'AAAHzZCcPdaK0BAH2mZAXntulvr8YOp8lZAwQJ0SV0xUsZBLKRyDUZCZCpAZC96FVD0FR808woMVuVYIyLo7iNrlv6fkjWi9aZALIJOmytDr5WAZDZD', '', 2);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
