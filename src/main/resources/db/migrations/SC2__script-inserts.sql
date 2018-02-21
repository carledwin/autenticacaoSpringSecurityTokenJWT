/*PERFIL*/
INSERT INTO perfil (id, perfil) VALUES(1, 'ADMINISTRADOR');
INSERT INTO perfil (id, perfil) VALUES(2, 'USUARIO');
INSERT INTO perfil (id, perfil) VALUES(3, 'DESENVOLVEDOR');
INSERT INTO perfil (id, perfil) VALUES(4, 'TESTER');
INSERT INTO perfil (id, perfil) VALUES(5, 'VISITANTE');


/*ROLE*/
INSERT INTO role(id, role) VALUES(1, 'ROLE_ADMIN');
INSERT INTO role(id, role) VALUES(2, 'ROLE_USUARIO');
INSERT INTO role(id, role) VALUES(3, 'ROLE_DEV');
INSERT INTO role(id, role) VALUES(4, 'ROLE_CADASTRO');
INSERT INTO role(id, role) VALUES(5, 'ROLE_CONSULTA');
INSERT INTO role(id, role) VALUES(6, 'ROLE_ALTERACAO');
INSERT INTO role(id, role) VALUES(7, 'ROLE_EXCLUSAO');


/*PERFIL-ROLES-USUARIO*/
INSERT INTO role_perfis(perfis_id, roles_id) VALUES(2, 5);


/*PERFIL-ROLES-ADMINSTRADOR*/
INSERT INTO role_perfis(perfis_id, roles_id) VALUES(1, 1);
INSERT INTO role_perfis(perfis_id, roles_id) VALUES(1, 2);


/*PERFIL-ROLES-DESENVOLVEDOR*/
INSERT INTO role_perfis(perfis_id, roles_id) VALUES(3, 1);
INSERT INTO role_perfis(perfis_id, roles_id) VALUES(3, 2);
INSERT INTO role_perfis(perfis_id, roles_id) VALUES(3, 3);
INSERT INTO role_perfis(perfis_id, roles_id) VALUES(3, 4);
INSERT INTO role_perfis(perfis_id, roles_id) VALUES(3, 5);
INSERT INTO role_perfis(perfis_id, roles_id) VALUES(3, 6);
INSERT INTO role_perfis(perfis_id, roles_id) VALUES(3, 7);

/*PERFIL-ROLES-TESTER*/
INSERT INTO role_perfis(perfis_id, roles_id) VALUES(4, 2);
INSERT INTO role_perfis(perfis_id, roles_id) VALUES(4, 3);
INSERT INTO role_perfis(perfis_id, roles_id) VALUES(4, 4);
INSERT INTO role_perfis(perfis_id, roles_id) VALUES(4, 5);
INSERT INTO role_perfis(perfis_id, roles_id) VALUES(4, 6);
INSERT INTO role_perfis(perfis_id, roles_id) VALUES(4, 7);


/*PERFIL-USUARIO*/
INSERT INTO usuario (username, password, enabled, perfil_id) VALUES('@carl', 'senha123', TRUE, 1); 
INSERT INTO usuario (username, password, enabled, perfil_id) VALUES('@marilia', 'senha456', TRUE, 2); 
INSERT INTO usuario (username, password, enabled, perfil_id) VALUES('@flavia', 'senha789', TRUE, 3); 
