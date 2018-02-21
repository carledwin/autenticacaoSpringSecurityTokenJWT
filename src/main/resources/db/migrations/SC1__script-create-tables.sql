drop table perfil if exists;
drop table role if exists;
drop table role_perfis if exists;
drop table usuario if exists;
create table perfil (id bigint generated by default as identity, perfil varchar(255), primary key (id));
create table role (id bigint generated by default as identity, role varchar(255), primary key (id));
create table role_perfis (roles_id bigint not null, perfis_id bigint not null);
create table usuario (id bigint generated by default as identity, enabled boolean not null, password varchar(255), username varchar(255), perfil_id bigint, primary key (id));
alter table role_perfis add constraint FK8ikli6hilmgqn9yshsk52aqof foreign key (perfis_id) references perfil;
alter table role_perfis add constraint FK7ysgddgwkpmx5w25xod3iisip foreign key (roles_id) references role;
alter table usuario add constraint FK9po12ytp6krwvwht1kmd0qgxf foreign key (perfil_id) references perfil;