# trainBookingOnline

create or replace procedure trainFare(tos varchar(15),froms varchar(20),tno int,cls varchar(20))
as $$ 
declare
dist numeric(5,2);
rate numeric(5,2);
surcharge numeric(5,2);
clsFare numeric(5,2);
finalFare numeric(5,2);
begin
clsFare:=(select far_farefactor from gv_traintravelclasses where trn_travelclass=cls and trn_no=tno);
dist:=(select std_distance from gv_train_distances where fst_code=tos and tst_code=froms);
rate:=(select max(far_basefare) from gv_trainfares where far_distance<=dist );
surcharge:=(select trn_surcharge from gv_trains where trn_no=tno);
finalFare:=(rate*clsFare)+surcharge;
raise notice '%',finalFare;
end $$ language plpgsql;
call trainFare('VSKP','SCND',12728,'SLP');




create table ak_stn( stn_code char(10) not null,  stn_name varchar(100), primary key(stn_code));


create table ak_stn_dis(fst_code char(10), tst_code char(10), std_dist int );
ALTER TABLE ak_stn_dis ADD CONSTRAINT fk_fst_customers FOREIGN KEY (fst_code) REFERENCES ak_stn (stn_code);
ALTER TABLE ak_stn_dis ADD CONSTRAINT fk_tst_customers FOREIGN KEY (tst_code) REFERENCES ak_stn (stn_code);

create table ak_trains (tr_no int primary key, tr_name varchar(100), tr_type char(10), tr_start char(10), tr_end char(10), tr_surcharge numeric);
ALTER TABLE ak_trains  ADD CONSTRAINT fk_tr_start FOREIGN KEY (tr_start) REFERENCES ak_stn (stn_code);
ALTER TABLE ak_trains  ADD CONSTRAINT fk_tr_end FOREIGN KEY (tr_end) REFERENCES ak_stn (stn_code);
Alter table ak_trains add column trn_status char(10);

create table ak_trn_stations (tr_no int, stn_code char(10) ,trs_index INT, Primary key (tr_no, stn_code));
create table ak_tr_fares(far_dist int, far_basefare numeric);
create table tn_tvl_classes(tr_no int, tr_tvl_class char(10) , far_factor NUMERIC, primary key (tr_no, tr_tvl_class));
create table ak_tickets(tk_id int primary key, pnr_no int, tk_tr_no int, tk_tr_date date, tk_from char(10), tk_to char(10), tk_tr_class char(3), tk_totalfare numeric);
ALTER TABLE ak_tickets  ADD CONSTRAINT fk_tr_tno FOREIGN KEY (tk_tr_no) REFERENCES ak_trains (tr_no);
ALTER TABLE ak_tickets ADD CONSTRAINT fk_tk_from FOREIGN KEY (tk_from) REFERENCES ak_stn (stn_code);
ALTER TABLE ak_tickets  ADD CONSTRAINT fk_tk_to FOREIGN KEY (tk_to) REFERENCES ak_stn (stn_code);
create table ak_pass (tk_id int, tk_pindex int, tkt_name varchar(100), tkt_age int, tkt_gender char(10), tkt_amt numeric, primary key(tk_id, tk_pindex));
ALTER TABLE ak_pass  ADD CONSTRAINT fk_tk_id FOREIGN KEY (tk_id) REFERENCES ak_tickets (tk_id);

select * from ak_stn;
insert into ak_stn values('ND','NEW DELHI');
insert into ak_stn values('KNP','KANPUR');
insert into ak_stn values ('PRG','PRYAGRA JUNCTION');    
insert into ak_stn values ('UPJ', 'UPADHYAYA');
insert into ak_stn values('HJL','HIJILI');
insert into ak_stn values('CTK','CUTTACK');
insert into ak_stn values('BBR','BHUBANESWAR');
insert into ak_stn values('HYD','HYDERABAD'); 
insert into ak_stn values('SND','SECUNDERABAD'); 
insert into ak_stn values('WGR','WARANGAL'); 
insert into ak_stn values('KMM','KAMAM');
insert into ak_stn values('VJY','VIJAYAWADA'); 
insert into ak_stn values('ELU','ELLURU');
insert into ak_stn values('TPJ','TADEPALLI GUDEM'); 
insert into ak_stn values('RJY','RAJAHMUNDRY'); 
insert into ak_stn values('ANV','ANNAVARAM'); 
insert into ak_stn values('TUN','TUNI');
insert into ak_stn values('AKP', 'ANAKAPALLI'); 
insert into ak_stn values('VSKP','VISAKHAPATNAM'); 
insert into ak_stn values('NZV', 'NUZVEEDU'); 
insert into ak_stn values('BHD','BHEMADOLU'); 
insert into ak_stn values('NDV', 'NIDAVOLU'); 
insert into ak_stn values('DVP','DVAARAPUDI' ); 
insert into ak_stn values('DUV','DUVVADA')   ; 
insert into ak_stn values('TRP','TIRUPATHI'); 
insert into ak_stn values('KDP','KADAPA'); 
insert into ak_stn values('KDR','KODURU'); 
insert into ak_stn values('PKL','PAKALA'); 
insert into ak_stn values('KDIR','KADIR'); 
insert into ak_stn values('ANT','ANANTHAPURAM'); 
insert into ak_stn values('MBB','MAHABOOB NAGAR');

insert into ak_stn_dis values('ND','KNP', 440);
insert into ak_stn_dis values('KNP','PRG', 194);
insert into ak_stn_dis values('PRG','UPJ',152 );
insert into ak_stn_dis values('UPJ','HJL',698 );
insert into ak_stn_dis values('HJL','CTK', 318 );
insert into ak_stn_dis values('CTK','BBR',28 );
insert into ak_stn_dis values('HYD','SND',7);
insert into ak_stn_dis values('SND','WGR',142 );
insert into ak_stn_dis values('WGR','KMM',108 );
insert into ak_stn_dis values('KMM','VJY', 99 );
insert into ak_stn_dis values('VJY','ELU',  59);
insert into ak_stn_dis values('ELU','TPJ', 48 );
insert into ak_stn_dis values('TPJ','RJY',42  );
insert into ak_stn_dis values('RJY','ANV',87  );
insert into ak_stn_dis values('ANV','TUN',17  );
insert into ak_stn_dis values('TUN','AKP',64  );
insert into ak_stn_dis values('AKP','VSKP',33  );

insert into ak_stn_dis values('TRP','PKL',41  );
insert into ak_stn_dis values('PKL','KDR',161  );
insert into ak_stn_dis values('KDR','ANT',102  );
insert into ak_stn_dis values('ANT','MBB',297  );
insert into ak_stn_dis values('MBB','HYD',106  );

insert into ak_stn_dis values('KDP','KDR',84  );
insert into ak_stn_dis values('KDR','TRP',51  );
insert into ak_stn_dis values('TRP','VJY',386  );
insert into ak_stn_dis values('VJY','NZV',41  );
insert into ak_stn_dis values('NZV','ELU', 19 );
insert into ak_stn_dis values('ELU','TPJ',48  );
insert into ak_stn_dis values('TPJ','NDV',20  );
insert into ak_stn_dis values('NDV','DVP',42  );
insert into ak_stn_dis values('DVP','PTP',42  );
insert into ak_stn_dis values('PTP','ANV',25  );

insert into ak_stn_dis values('AKP','DUV', 15 );
insert into ak_stn_dis values('DUV','VSKP',  18);


insert into ak_trains values(22824,'RAJDHANI', 'EXPR','ND','BBR',20 );
insert into ak_trains values(12788,'GODAVARI', 'GEN','HYD','VJY',15 );
insert into ak_trains values(12774,'SHALIMAR', 'SPFST/AC','SND','VSKP',40 );
insert into ak_trains values(17487,'TIRUMALA', 'EXPR','KDP','HYD', 23);
insert into ak_trains values(07605,'AKOLA', 'SPCL','TRP','HYD',30 );
UPDATE ak_TRAINS SET TR_START='SND' WHERE TR_NO=12774;
UPDATE ak_TRAINS SET TRN_STATUS='RN' WHERE TR_NO=12774;
UPDATE ak_TRAINS SET TRN_STATUS='RN' WHERE TR_NO=22824;
UPDATE ak_TRAINS SET TRN_STATUS='STP' WHERE TR_NO=12788;
UPDATE ak_TRAINS SET TRN_STATUS='RN' WHERE TR_NO=17487;
insert into ak_trn_sTATIONS(tr_no , stn_code, trs_index  ) values(22824,'ND',0);
insert into ak_trn_sTATIONS(tr_no , stn_code ,trs_index ) values(22824,'KNP',1);
insert into ak_trn_sTATIONS(tr_no , stn_code ,trs_index ) values(22824,'PRG',2);
insert into ak_trn_sTATIONS(tr_no , stn_code ,trs_index ) values(22824,'UPJ',3);
insert into ak_trn_sTATIONS(tr_no , stn_code ,trs_index ) values(22824,'HJL',4);
insert into ak_trn_sTATIONS(tr_no , stn_code ,trs_index ) values(22824,'CTK',5);
insert into ak_trn_sTATIONS(tr_no , stn_code ,trs_index ) values(22824,'BBR',6);
                                                                  
insert into ak_trn_sTATIONS(tr_no , stn_code,trs_index  ) values(12728,'HYD',0);
insert into ak_trn_sTATIONS(tr_no , stn_code,trs_index  ) values(12728,'SND',1);
insert into ak_trn_sTATIONS(tr_no , stn_code ,trs_index ) values(12728,'WGL',2);
insert into ak_trn_sTATIONS(tr_no , stn_code ,trs_index ) values(12728,'KMM',3);
insert into ak_trn_sTATIONS(tr_no , stn_code,trs_index ) values(12728,'VJY',4);
insert into ak_trn_sTATIONS(tr_no , stn_code ,trs_index ) values(12728,'ELU',5);
insert into ak_trn_sTATIONS(tr_no , stn_code ,trs_index ) values(12728,'TPJ',6);
insert into ak_trn_sTATIONS(tr_no , stn_code ,trs_index ) values(12728,'RJY',7);
insert into ak_trn_sTATIONS(tr_no , stn_code ,trs_index ) values(12728,'ANV',8);
insert into ak_trn_sTATIONS(tr_no , stn_code ,trs_index ) values(12728,'TUN',9);
insert into ak_trn_sTATIONS(tr_no , stn_code ,trs_index  ) values(12728,'ANK',10);
insert into ak_trn_sTATIONS(tr_no , stn_code ,trs_index ) values(12728,'VSKP',11);
insert into ak_trn_sTATIONS(tr_no , stn_code,trs_index  ) values(12774,'SND',0);
insert into ak_trn_sTATIONS(tr_no , stn_code ,trs_index ) values(12774,'RJY',2);
insert into ak_trn_sTATIONS(tr_no , stn_code,trs_index  ) values(12774,'WGL',1);
insert into ak_trn_sTATIONS(tr_no , stn_code ,trs_index ) values(12774,'VSKP',3);
insert into ak_trn_sTATIONS(tr_no , stn_code,trs_index  ) values(07605,'TRP',0);
insert into ak_trn_sTATIONS(tr_no , stn_code ,trs_index ) values(07605,'PKL',1);
insert into ak_trn_sTATIONS(tr_no , stn_code ,trs_index ) values(07605,'KDR',2);
insert into ak_trn_sTATIONS(tr_no , stn_code,trs_index  ) values(07605,'ANT',3);
insert into ak_trn_sTATIONS(tr_no , stn_code,trs_index  ) values(07605,'MBB',4);
insert into ak_trn_sTATIONS(tr_no , stn_code ,trs_index ) values(07605,'HYD',5);
insert into ak_trn_sTATIONS(tr_no , stn_code ,trs_index ) values(17487,'KDP',1);
insert into ak_trn_sTATIONS(tr_no , stn_code ,trs_index ) values(17487,'KDR',2);
insert into ak_trn_sTATIONS(tr_no , stn_code ,trs_index ) values(17487,'TRP',3);
insert into ak_trn_sTATIONS(tr_no , stn_code,trs_index  ) values(17487,'VJY',4);
insert into ak_trn_sTATIONS(tr_no , stn_code ,trs_index ) values(17487,'NZV',5);
insert into ak_trn_sTATIONS(tr_no , stn_code ,trs_index ) values(17487,'NDV',8);
insert into ak_trn_sTATIONS(tr_no , stn_code ,trs_index  ) values(17487,'PTP',11);
insert into ak_trn_sTATIONS(tr_no , stn_code ,trs_index ) values(17487,'TUN',13);
insert into ak_trn_sTATIONS(tr_no , stn_code ,trs_index ) values(17487,'ANV',12);
insert into ak_trn_sTATIONS(tr_no , stn_code ,trs_index ) values(17487,'DRP',10);
insert into ak_trn_sTATIONS(tr_no , stn_code ,trs_index ) values(17487,'TPJ',7);
insert into ak_trn_sTATIONS(tr_no , stn_code ,trs_index ) values(17487,'ANK',14);
insert into ak_trn_sTATIONS(tr_no , stn_code ,trs_index ) values(17487,'DUV',15);
insert into ak_trn_sTATIONS(tr_no , stn_code ,trs_index ) values(17487,'RJY',9);
insert into ak_trn_sTATIONS(tr_no , stn_code ,trs_index ) values(17487,'ELU',6);
insert into ak_trn_sTATIONS(tr_no , stn_code ,trs_index ) values(17487,'VSKP',16);

INSERT INTO tn_tvl_classes VALUES(22824,'1AC',1.7);
INSERT INTO tn_tvl_classes VALUES(22824,'2AC',2.2);
INSERT INTO tn_tvl_classes VALUES(22824,'3AC',3.6);
INSERT INTO tn_tvl_classes VALUES(22824,'SLP',4.8);

INSERT INTO tn_tvl_classes VALUES(12728,'1AC',1.7);
INSERT INTO tn_tvl_classes VALUES(12728,'2AC',2.2);
INSERT INTO tn_tvl_classes VALUES(12728,'3AC',3.6);
INSERT INTO tn_tvl_classes VALUES(12728,'SLP',4.8);

INSERT INTO tn_tvl_classes VALUES(12774,'1AC',1.7);
INSERT INTO tn_tvl_classes VALUES(12774,'2AC',2.2);
INSERT INTO tn_tvl_classes VALUES(12774,'3AC',3.6);

INSERT INTO tn_tvl_classes VALUES(07605,'1AC',1.7);
INSERT INTO tn_tvl_classes VALUES(07605,'2AC',2.2);
INSERT INTO tn_tvl_classes VALUES(07605,'3AC',3.6);


INSERT INTO tn_tvl_classes VALUES(17487,'1AC',1.7);
INSERT INTO tn_tvl_classes VALUES(17487,'2AC',2.2);
INSERT INTO tn_tvl_classes VALUES(17487,'3AC',3.6);
INSERT INTO tn_tvl_classes VALUES(17487,'SLP',4.4);


INSERT INTO ak_tr_fares VALUES(10,12);
INSERT INTO ak_tr_fares VALUES(50,22);
INSERT INTO ak_tr_fares VALUES(100,40);
INSERT INTO ak_tr_fares VALUES(150,68);
INSERT INTO ak_tr_fares VALUES(200,75);
INSERT INTO ak_tr_fares VALUES(500,90);
INSERT INTO ak_tr_fares VALUES(1000,125);

SELECT * from ak_stn;
SELECT * from ak_stn_dis;
SELECT * from ak_trains;
SELECT * from ak_trn_stations;
SELECT * from ak_tr_fares;
SELECT * from ak_tickets;
SELECT * from ak_pass;
SELECT * from tn_tvl_classes;
drop table ak_pass;
drop table ak_stn;
drop table ak_stn_dis;
drop table ak_tickets;
drop table ak_tr_fares;
drop table ak_trains;
drop table ak_trn_stations;

drop table ak_trn_stations;
drop table ak_pass;
drop table ak_tickets;
drop table tn_tvl_classes;
drop table ak_tr_fares;
drop table ak_trains;
drop table ak_stn_dis;
