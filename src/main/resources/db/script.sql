drop table if exists prodalle;
drop table if exists alle;
drop table if exists prod;
drop table if exists cat;
drop table if exists contact;

create table cntcstate (
    cntcstate_id int not null auto_increment,
    cntcstate_name varchar(32),
	constraint cntcstate_pk primary key (cntcstate_id)
);

create table contact (
    contact_id int not null auto_increment,
    contact_name varchar(32),
    contact_email varchar(32),
    contact_msg varchar(2000),
    contact_date timestamp,
    cntcstate_id int,
	constraint contact_pk primary key (contact_id),
    constraint contact_cntcstate_fk foreign key (cntcstate_id) 
        references cntcstate(cntcstate_id) on delete cascade
);

create table cat (
    cat_id int not null auto_increment,
    cat_name_en varchar(32),
    cat_name_es varchar(32),
	constraint cat_pk primary key (cat_id)
);
create table prod (
    prod_id int not null auto_increment,
    prod_name_en varchar(32),
    prod_name_es varchar(32),
    prod_desc_en varchar(120),
    prod_desc_es varchar(120),
    prod_price float(4,2),
    cat_id int,
	constraint prod_pk primary key (prod_id),
    constraint prod_cat_fk foreign key (cat_id) 
        references cat(cat_id) on delete cascade
);
create table alle (
    alle_id int not null auto_increment,
    alle_name_en varchar(32),
    alle_name_es varchar(32),
	constraint alle_pk primary key (alle_id)
);
create table prodalle (
    prod_id int not null,
    alle_id int not null,
	constraint prodalle_pk primary key (prod_id, alle_id),
    constraint prodalle_fk_prod foreign key (prod_id) 
        references prod(prod_id) on delete cascade,
    constraint prodalle_fk_alle foreign key (alle_id) 
        references alle(alle_id) on delete cascade
);

insert into cntcstate values (1, 'delivered');
insert into cntcstate values (2, 'opened');
insert into cntcstate values (3, 'clicked');
insert into cntcstate values (4, 'unsubscribed');
insert into cntcstate values (5, 'spam');
insert into cntcstate values (6, 'bounced');
insert into cntcstate values (7, 'blocked');
insert into cntcstate values (8, 'retrying');


insert into cat values (1, 'en1', 'es1');
insert into cat values (2, 'en2', 'es2');
insert into cat values (3, 'en3', 'es3');

insert into prod values (1, 'en1', 'es1', 'des1', 'des1', 0.0, 1);
insert into prod values (2, 'en2', 'es2', 'des2', 'des2', 0.0, 1);
insert into prod values (3, 'en3', 'es3', 'des3', 'des3', 0.0, 2);
insert into prod values (4, 'en4', 'es4', 'des4', 'des4', 0.0, 3);

insert into alle values (1, 'celery', 'apio');
insert into alle values (2, 'crustaceans', 'crustaceos');
insert into alle values (3, 'eggs', 'huevos');
insert into alle values (4, 'fish', 'pescado');
insert into alle values (5, 'gluten', 'gluten');
insert into alle values (6, 'lupin', 'altramuz');
insert into alle values (7, 'milk', 'lácteos');
insert into alle values (8, 'moluscs', 'moluscos');
insert into alle values (9, 'mustard', 'mostaza');
insert into alle values (10, 'nuts', 'frutos secos');
insert into alle values (11, 'peanuts', 'cacahuetes');
insert into alle values (12, 'sesame', 'sésamo');
insert into alle values (13, 'soya', 'soja');
insert into alle values (14, 'sulphites', 'sulfitos');

insert into prodalle values (1, 10);
insert into prodalle values (2, 5);
insert into prodalle values (3, 10);
insert into prodalle values (3, 11);
