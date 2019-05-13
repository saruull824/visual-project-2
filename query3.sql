CREATE DATABASE hospital3
CHARACTER SET utf8 COLLATE utf8_general_ci;

USE hospital3;

CREATE TABLE d_huuhed(id int not null auto_increment,
ovog varchar(45) not null, 
ner varchar(45) not null,
huis char(2) not null,
reg_dugaar char(10) not null,
horoo int not null,
hayag varchar(50) not null,
ognoo date not null,
tsag time,
kartnii_burtgel int(1),
PRIMARY KEY(id),
UNIQUE(reg_dugaar),
CHECK(huis in ('эр', 'эм'))
# nas date GENERATED ALWAYS AS (if(convert(substring(reg_dugaar, 3,2), unsigned) < 50,
#  convert(concat('20',substring(reg_dugaar, 3,2),'-', convert(convert(substring(reg_dugaar, 5,2), unsigned)-20, char), '-',substring(reg_dugaar, 7,2)), date), 
#  convert(concat('19',substring(reg_dugaar, 3,2), '-', substring(reg_dugaar, 5,2), '-', substring(reg_dugaar, 7,2)), date)
#)
 );
 
#insert into d_huuhed(ovog, ner, huis, reg_dugaar, horoo, hayag, ognoo, tsag, kartnii_burtgel) values('Эрдэнэжаргал', 'Түвшинцэнгүүн', 'эр', 'УК98020124', 3, '2-r 50000', '2019-04-23', '14:40',0 );
#insert into d_huuhed(ovog, ner, huis, reg_dugaar, horoo, hayag, ognoo, tsag, kartnii_burtgel) values('Лхагвадаш', 'Саруул', 'эм', 'УИ98020124', 3, '2-r 40000', '2019-04-25', '17:40',0 );

#select * from d_huuhed;
#select * from d_vaktsin;
#insert into d_vaktsin(ner, tun, tungiin_nas, tungiin_duration) values('ПЭВ', 3, 2, 4);
#select id
#from d_huuhed
#where reg_dugaar = "UI98020124";
#drop table d_asran_hamgaalagch;
CREATE TABLE d_asran_hamgaalagch(h_id int not null, 
								fom int(1) not null,
                                ner varchar(45) not null,
								utasnii_dugaar int(8) not null,
                                CONSTRAINT d_asran_hamgaalagch_fk_h_id FOREIGN KEY (h_id) REFERENCES d_huuhed(id) ON DELETE CASCADE,
                                PRIMARY KEY(h_id, fom),
                                UNIQUE (utasnii_dugaar));
                                
CREATE TABLE d_ajiltan(id int not null auto_increment, 
					ovog varchar(45) not null,
                    ner varchar(45) not null,
                    tushaal varchar(25) not null,
                    PRIMARY KEY(id));
                    
CREATE TABLE d_vaktsin(id int not null auto_increment, 
                        ner varchar(40) not null, 
                        tun int not null,
                        PRIMARY KEY(id));
                        
CREATE TABLE d_tun(v_id int not null,
					tun int not null,
                    tungiin_nas decimal(4,2) not null,
                    tungiin_duration decimal(4,2) not null,
                    PRIMARY KEY(v_id, tun));
                            
CREATE TABLE d_darhlaa(h_id int not null, 
						e_id int not null,
                        v_id int not null,
                        s_id int not null,
                        ognoo datetime not null,
                        tun int not null,
                        tailbar varchar(50),
                        PRIMARY KEY(h_id, v_id, ognoo));