-- phpMyAdmin SQL Dump
-- version 4.7.7
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1:3306
-- Время создания: Янв 28 2018 г., 00:06
-- Версия сервера: 5.7.20
-- Версия PHP: 7.1.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `bad_words`
--

-- --------------------------------------------------------

--
-- Структура таблицы `categories`
--

CREATE TABLE `categories` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `categories`
--

INSERT INTO `categories` (`id`, `name`) VALUES
(1, 'подлежащее'),
(2, 'сказуемые'),
(3, 'определение'),
(4, 'дополнение'),
(5, 'обстоятельство'),
(6, 'мат'),
(7, 'паразит');

-- --------------------------------------------------------

--
-- Структура таблицы `words`
--

CREATE TABLE `words` (
  `id` int(11) NOT NULL,
  `parasite_words` varchar(200) DEFAULT NULL,
  `alternatives` varchar(100) DEFAULT NULL,
  `can_deleted` tinyint(1) DEFAULT NULL,
  `categories_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `words`
--

INSERT INTO `words` (`id`, `parasite_words`, `alternatives`, `can_deleted`, `categories_id`) VALUES
(1, 'охуеть', 'я поражен', NULL, 6),
(2, 'пошел нахуй', 'не отвлекайте меня, я занят', NULL, 6),
(3, 'заебали', 'простите, вы слишком назойливы', NULL, 6),
(4, 'твою мать', 'вау', NULL, 6),
(5, 'проебали', 'кажется, мы что-то упустили из виду', NULL, 6),
(6, 'распиздяй', 'ваша мера ответственности оставляет желать лучшего', NULL, 6),
(7, 'блядь', 'меня переполняют эмоции', NULL, 6),
(8, 'что за хуйня', 'мне кажется, где-то ошибка', NULL, 6),
(9, 'короче', 'подытожим;в кратце', NULL, 7),
(10, 'короче говоря', 'в кратце излагая', NULL, 7),
(11, 'дофига', 'много', NULL, 7),
(12, 'хули', 'зачем?;по какой причине?', NULL, 6),
(13, 'типа', 'по анологии с; будто бы; якобы', NULL, 7),
(14, 'прикинь', 'подумай только', NULL, 7),
(15, 'так сказать', 'если сделать именно так, то будет лучше', NULL, 7),
(16, 'ну', 'в некотором роде', NULL, 7),
(17, 'блин', 'меня переполняют эмоции', NULL, 7),
(18, 'кстати', 'а вы знаете', NULL, 7),
(19, 'так сказать', 'если быть точным', NULL, 7),
(20, 'ё-моё', 'я удивлен', NULL, 7),
(21, 'вообще-то', 'если быть точным', NULL, 7),
(22, 'ёлкин', 'я удивлен', NULL, 7),
(23, 'просто', 'кратко говоря', NULL, 7),
(24, 'как бы', 'если подумать', NULL, 7),
(25, 'ладненько', 'я с вами соглашусь', NULL, 7),
(26, 'так', NULL, 1, 7),
(27, 'в общем-то', 'если быть точным', 1, 7),
(28, 'то есть', 'если быть точным', NULL, 7),
(29, 'допустим', 'если принять во внимание', NULL, 7),
(30, 'офигеть', 'я удивлен', NULL, 7),
(31, 'афигеть', 'я удивлен', NULL, 7),
(32, 'конкретно', 'так и есть', NULL, 7),
(33, 'походу', 'по моему скромному мнению', NULL, 7),
(34, 'по моему', 'по моему скромному мнению', NULL, 7);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `words`
--
ALTER TABLE `words`
  ADD PRIMARY KEY (`id`),
  ADD KEY `categories_id` (`categories_id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `categories`
--
ALTER TABLE `categories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT для таблицы `words`
--
ALTER TABLE `words`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `words`
--
ALTER TABLE `words`
  ADD CONSTRAINT `words_ibfk_1` FOREIGN KEY (`categories_id`) REFERENCES `categories` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
