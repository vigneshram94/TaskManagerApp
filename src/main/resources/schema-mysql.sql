CREATE TABLE `task` (
  `task_id` bigint(20) NOT NULL,
  `end_date` datetime DEFAULT NULL,
  `is_active` bit(1) NOT NULL,
  `parent_task` varchar(255) DEFAULT NULL,
  `priority` int(11) NOT NULL,
  `start_date` datetime DEFAULT NULL,
  `task_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`task_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;