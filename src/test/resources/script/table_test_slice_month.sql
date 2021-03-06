ALTER TABLE `TABLE_TEST_SLICE_MONTH`DROP PRIMARY KEY;

DROP TABLE `TABLE_TEST_SLICE_MONTH`;

CREATE TABLE `TABLE_TEST_SLICE_MONTH` (
`ID` bigint(20) NOT NULL AUTO_INCREMENT,
`SLICE_MONTH_ID` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
`JACKSON_ID1` bigint(20) NOT NULL,
`VERSION` bigint(32) NOT NULL DEFAULT 0,
`JACKSON_ID2` varchar(255) NOT NULL,
`JACKSON_TIME` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
`COULD_SUM_COL` int(11) NOT NULL DEFAULT 0,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=1;

