/* articles */
INSERT INTO `article` (`id`, `description`, `title`) VALUES (10, 'article description 1', 'article title 1');
INSERT INTO `article` (`id`, `description`, `title`) VALUES (20, 'article description 2', 'article title 2');
INSERT INTO `article` (`id`, `description`, `title`) VALUES (30, 'article description 3', 'article title 3');
/* comments */
INSERT INTO `comment` (`id`, `content`, `article_id`) VALUES (11, 'comment number 1', 10);
INSERT INTO `comment` (`id`, `content`, `article_id`) VALUES (12, 'comment number 2', 20);
INSERT INTO `comment` (`id`, `content`, `article_id`) VALUES (13, 'comment number 3', 20);
INSERT INTO `comment` (`id`, `content`, `article_id`) VALUES (14, 'comment number 4', 30);
INSERT INTO `comment` (`id`, `content`, `article_id`) VALUES (15, 'comment number 5', 30);
INSERT INTO `comment` (`id`, `content`, `article_id`) VALUES (16, 'comment number 6', 30);