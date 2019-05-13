CREATE DATABASE hospital3
CHARACTER SET utf8 COLLATE utf8_general_ci;

USE hospital3;

CREATE TABLE d_huuhed(id int not null auto_increment,
ovog varchar(45) not null, 
ner varchar(45) not null,
huis char(2) not null,
reg_dugaar char(10) not null,
duureg varchar(14) not null,
horoo int not null,
hayag varchar(50) not null,
ognoo date not null,
tsag time,
kartnii_burtgel int(1),
PRIMARY KEY(id),
UNIQUE(reg_dugaar),
CHECK(huis in ('эр', 'эм')));
 
#insert into d_huuhed(ovog, ner, huis, reg_dugaar, horoo, hayag, ognoo, tsag, kartnii_burtgel) values('Эрдэнэжаргал', 'Түвшинцэнгүүн', 'эр', 'УК98020124', 'Сүхбаатар', 3, '2-r 50000', '2019-04-23', '14:40',0 );
#insert into d_huuhed(ovog, ner, huis, reg_dugaar, horoo, hayag, ognoo, tsag, kartnii_burtgel) values('Лхагвадаш', 'Саруул', 'эм', 'УИ98020124', 'Чингэлтэй', 3, '2-r 40000', '2019-04-25', '17:40',0 );

#select * from d_huuhed;
#select * from d_vaktsin;
#insert into d_vaktsin(ner, tun, tungiin_nas, tungiin_duration) values('ПЭВ', 3, 2, 4);

CREATE TABLE d_asran_hamgaalagch(h_id int not null, 
								fom int(1) not null,
                                ner varchar(45) not null,
								utasnii_dugaar int(8) not null,
                                CONSTRAINT d_asran_hamgaalagch_fk_h_id FOREIGN KEY (h_id) REFERENCES d_huuhed(id) ON DELETE CASCADE,
                                PRIMARY KEY(h_id, fom),
                                UNIQUE (utasnii_dugaar),
                                CHECK(fom in (1, 0)));
                                
CREATE TABLE d_ajiltan(id int not null auto_increment, 
					ovog varchar(45) not null,
                    ner varchar(45) not null,
                    tushaal varchar(25) not null,
                    PRIMARY KEY(id),
                    CHECK(tushaal in('эмч', 'сувилагч')));
                    
CREATE TABLE d_vaktsin(id int not null auto_increment, 
                        ner varchar(40) not null, 
                        tun int not null,
                        PRIMARY KEY(id));
                        
CREATE TABLE d_tun(v_id int not null,
					tun int not null,
                    tungiin_nas int not null,
                    tungiin_duration decimal(4,2) not null,
                    PRIMARY KEY(v_id, tun),
                    CONSTRAINT d_tun_fk_v_id FOREIGN KEY (v_id) REFERENCES d_vaktsin(id) ON DELETE CASCADE);
                            
CREATE TABLE d_darhlaa(h_id int not null, 
						e_id int not null,
                        v_id int not null,
                        s_id int not null,
                        ognoo datetime not null,
                        tun int not null,
                        tailbar varchar(50),
                        PRIMARY KEY(h_id, v_id, ognoo));