
create user j2ee identified by j2ee default tablespace users temporary tablespace Temp;
grant connect,resource,dba to j2ee;
conn j2ee/j2ee;
create table product(
	product_id number(10) constraint product_PK primary key,
	name varchar2(80) not null,
	authors varchar2(40),
	price number(6,2) constraint ch_product_price check(price>0 and price<30000),
	description varchar2(1000),
	full varchar2(100),
	thumb varchar2(100)
);

insert into product(product_id, name, authors, price, description, full, thumb) 
  values(1, 'AngularJS: Up and Running: Enhanced Productivity with Structured Web Apps', 'Shyam Seshadri and Brad Green', 
    28.88, 'AngularJS is the leading framework for building dynamic JavaScript applications that take advantage of the capabilities of modern browsers and devices. AngularJS, which is maintained by Google, brings the power of the Model-View-Controller (MVC) pattern to the client, providing the foundation for complex and rich web apps.', 
    '../images/23358634-1_b_4.jpg', '../images/23358634-1_m.jpg');

insert into product(product_id, name, authors, price, description, full, thumb) 
  values(2, 'Mastering Web Application Development with AngularJS', 'Shyam Seshadri and Brad Green', 
    30.15, 'AngularJS is the leading framework for building dynamic JavaScript applications that take advantage of the capabilities of modern browsers and devices. AngularJS, which is maintained by Google, brings the power of the Model-View-Controller (MVC) pattern to the client, providing the foundation for complex and rich web apps.', 
    '../images/23587516-1_b_1.jpg', '../images/23587516-1_m.jpg');
    
insert into product(product_id, name, authors, price, description, full, thumb) 
  values(3, 'Pro AngularJS (Expert' || '''' || 's Voice in Web Development)', 'Shyam Seshadri and Brad Green', 
    35.78, 'AngularJS is the leading framework for building dynamic JavaScript applications that take advantage of the capabilities of modern browsers and devices. AngularJS, which is maintained by Google, brings the power of the Model-View-Controller (MVC) pattern to the client, providing the foundation for complex and rich web apps.', 
    '../images/23751084-1_b_1.jpg', '../images/23751084-1_m.jpg');

insert into product(product_id, name, authors, price, description, full, thumb) 
  values(4, 'Learning AngularJS: A Guide to AngularJS Development', 'Shyam Seshadri and Brad Green', 
    27.68, 'AngularJS is the leading framework for building dynamic JavaScript applications that take advantage of the capabilities of modern browsers and devices. AngularJS, which is maintained by Google, brings the power of the Model-View-Controller (MVC) pattern to the client, providing the foundation for complex and rich web apps.', 
    '../images/23815176-1_b_6.jpg', '../images/23815176-1_m.jpg');

commit;

create table shopping_cart(
  shopping_cart_id number(10) constraint shopping_cart_PK primary key,
  user_id varchar2(100),
  total_count number(4),
  total_cost number(10,2)
);

create sequence shopping_cart_id_seq  
        minvalue 1  
        maxvalue 999999999999999999999999999  
        start with 1  
        increment by 1  
        cache 20  
        order;

create table cart_item(
  SHOPPING_CART_ID NUMBER(10),
  product_id number(10),
  product_count number(4) constraint ch_cart_item_product_count check(product_count>=0 and product_count <=9999),
  total_price number(10,2) constraint ch_cart_item_total_price check(total_price>0),
  checked char check(checked in('Y','N')), 
  constraint product_item_PK primary key(SHOPPING_CART_ID, product_id),
  constraint cart_item_SHOPPING_CART_ID_FK foreign key(SHOPPING_CART_ID) references shopping_cart(SHOPPING_CART_ID),
  constraint cart_item_product_id_FK foreign key(product_id) references product(product_id)
);

commit;

exit;