
# 项目需要的库、表、等结构的sql语句

# 库名
create database if not exists college_entrance_examination_volunteer_consultation DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

# 选择该库
use college_entrance_examination_volunteer_consultation;

# 学校录取信息表
create table if not exists obtaining_score_data(
  `id` int(11) primary key auto_increment comment '主键',
  `year` varchar(255) not null comment '年份',
  `provinces` varchar(255) not null comment '省份',
  `admission_category` varchar(255) comment '录取类别',
  `division_of_class` varchar(255) not null comment '科类',
  `batch` varchar(255) not null comment '批次',
  `the_name_of_the_professional` varchar(255) not null comment '专业名称',
  `professional_description` varchar(255) comment '专业描述',
  `enrollment` int(11) not null comment '录取人数',
  `highest_score` decimal(5, 2) not null comment '最高分',
  `lowest_score` decimal(5, 2) not null comment '最低分',
  `average_score` decimal(5, 2) not null comment '平均分',
  `control_score` decimal(5, 2) not null comment '控制分数线',
  `the_minimum_gap` decimal(5, 2) not null comment '最低分差',
  `difference_of_average` decimal(5, 2) not null comment '平均分差',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  key idx_year_provinces_division_of_class(`year`, `provinces`, `division_of_class`) comment '年份、省份、科类 普通索引'
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;