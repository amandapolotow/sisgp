########## INSERTS TABELAS DOMINIO ##########

#status de atividade
insert into status_atividade(status,st_ativo) values ('Não iniciada',1),('Em andamento',1),('Finalizada',1),('Cancelada',1);

#status de atividade do sistema
insert into status_sistema_atividade(status,st_ativo) values ('Dentro do previsto',1),('Em risco',1),('Em atraso',1);

#status_projetos
insert into status_projeto (status, st_ativo) values ('Não Iniciado',1),('Em Andamento',1),('Finalizado',1),('Cancelado',1);

#nivel
insert into nivel (nivel,st_ativo) values ('Junior',1),('Pleno',1),('Sênior',1);

#Grupo acesso
insert into grupos_acesso(grupo_acesso,st_ativo) values ('FUNCIONARIO',1),('GESTOR',1),('GESTOR_GERAL',1);

#status contas
insert into status_contas(status,st_ativo)values('Paga',1),('Em aberto',1),('Cancelada',1);

#categoria contas a pagar
insert into categoria_contas_pagar(categoria,st_ativo)values('PROJETO',1),('Telefonia/Internet',1),('Energia',1),('Água',1),('Salário',1),('Material de Escritório',1),('Cozinha/Limpeza',1),('Serviços',1),('Compra de bens',1);#Observação: somente PROJETO é categoria padrão, as outras são exemplo

#categoria contas a recber
insert into categoria_contas_receber(categoria,st_ativo)values('PROJETO',1),('Serviços/Consultoria',1),('Venda de bens',1);#Observação: somente PROJETO é categoria padrão, as outras são exemplo

########## INSERTS DADOS PARA EXEMPLO ##########

#clientes
insert into clientes(razao_social,nome_fantasia,cnpj,email,telefone,responsavel,observacao,st_ativo) values ('Cliente 1','Cliente 1','51.783.157/0001-71','cliente1@email','(11)9874-5632','Mauro Queiroz','',1);
insert into clientes(razao_social,nome_fantasia,cnpj,email,telefone,responsavel,observacao,st_ativo) values ('Cliente 2','Cliente 2','47.254.250/0001-11','cliente2@email','(11)2356-8947','Angela dos Anjos','',1);
insert into clientes(razao_social,nome_fantasia,cnpj,email,telefone,responsavel,observacao,st_ativo) values ('Cliente 3','Cliente 3','30.091.261/0001-25','cliente3@email','(11)9874-5632','Rita Meireles','',1);
#recursos_humanos
insert into recursos_humanos(nome,sobrenome,email,senha,telefone,cargo,id_nivel,id_grupo_acesso,vl_hora,vl_hora_extra,observacao,st_ativo) values ('Maria','Ferreira','maria@email.com','senha','(11)8523-5478','Programadora',2,1,'75.0','80.00','',1);#1
insert into recursos_humanos(nome,sobrenome,email,senha,telefone,cargo,id_nivel,id_grupo_acesso,vl_hora,vl_hora_extra,observacao,st_ativo) values ('Leonardo','Prado','leonardo@email.com','senha','(11)1236-7854','Programador',1,1,'75.0','80.00','',1);#2
insert into recursos_humanos(nome,sobrenome,email,senha,telefone,cargo,id_nivel,id_grupo_acesso,vl_hora,vl_hora_extra,observacao,st_ativo) values ('Luiza','Talles','luiza@email.com','senha','(11)9632-5478','Programadora',2,1,'75.0','80.00','',1);#3
insert into recursos_humanos(nome,sobrenome,email,senha,telefone,cargo,id_nivel,id_grupo_acesso,vl_hora,vl_hora_extra,observacao,st_ativo) values ('Caio','Mendes','caio@email.com','senha','(11)8951-8293','Programador',3,1,'75.0','80.00','',1);#4
insert into recursos_humanos(nome,sobrenome,email,senha,telefone,cargo,id_nivel,id_grupo_acesso,vl_hora,vl_hora_extra,observacao,st_ativo) values ('Laura','Dantas','laura@email.com','senha','(11)7412-5478','Analista',1,1,'75.0','80.00','',1);#5
insert into recursos_humanos(nome,sobrenome,email,senha,telefone,cargo,id_nivel,id_grupo_acesso,vl_hora,vl_hora_extra,observacao,st_ativo) values ('Pedro','Almeida','pedro@email.com','senha','(11)1236-5136','Analista',3,1,'75.0','80.00','',1);#6
insert into recursos_humanos(nome,sobrenome,email,senha,telefone,cargo,id_nivel,id_grupo_acesso,vl_hora,vl_hora_extra,observacao,st_ativo) values ('Joana','da Silva','joana@email.com','senha','(11)7536-5478','Gestora de Projetos',3,2,'85.0','95.00','',1);#7
insert into recursos_humanos(nome,sobrenome,email,senha,telefone,cargo,id_nivel,id_grupo_acesso,vl_hora,vl_hora_extra,observacao,st_ativo) values ('Ricardo','dos Santos','ricardo@email.com','senha','(11)9874-5632','Gestor de Projetos',2,2,'85.0','95.00','',1);#8
insert into recursos_humanos(nome,sobrenome,email,senha,telefone,cargo,id_nivel,id_grupo_acesso,vl_hora,vl_hora_extra,observacao,st_ativo) values ('João','Mendes','joao@email.com','senha','(11)7412-5896','Gestor Geral',3,3,'95.0','100.00','',1);#9
#projetos
insert into projetos(id_rh_gestor,id_cliente,codigo,nome,descricao,id_status_projeto,st_ativo) values (7,1,'P001','Aplicativo 123','Aplicativo organizador de agenda',2,1);
insert into projetos(id_rh_gestor,id_cliente,codigo,nome,descricao,id_status_projeto,st_ativo) values (8,2,'P002','E_comerce 123','Loja virtual de roupas',1,1);
insert into projetos(id_rh_gestor,id_cliente,codigo,nome,descricao,id_status_projeto,st_ativo) values (7,3,'P003','Sistema 123','Sistema para controle de estoque',1,1);

#atividade
insert into atividades (id_projeto,codigo,nome,descricao,id_status_atividade,id_st_sist_atividade,st_ativo)values(1,'A','Escopo e Requisitos','Definição do escopo e coleta dos requisitos iniciais',2,1,1);
insert into atividades (id_projeto,codigo,nome,descricao,id_status_atividade,id_st_sist_atividade,st_ativo)values(1,'B','Desenvolvimento','Definição das classes, programação, banco de dados',1,1,1);
insert into atividades (id_projeto,codigo,nome,descricao,id_status_atividade,id_st_sist_atividade,st_ativo)values(1,'C','Testes e Entrega','Testes, Revisão e Entrega',1,1,1);
#sub atividades
insert into sub_atividades (id_atividade,id_recurso_humano,nr_horas_previsto,codigo,nome,descricao,id_status_atividade,id_st_sist_atividade,dt_inicio,dt_fim,st_ativo)values(1,5,60,'A1','Escopo','Definição e escrita do escopo',2,1,str_to_date('2018-08-10','%Y-%m-%d'),str_to_date('2018-08-25','%Y-%m-%d'),1);
insert into sub_atividades (id_atividade,id_recurso_humano,nr_horas_previsto,codigo,nome,descricao,id_status_atividade,id_st_sist_atividade,dt_inicio,dt_fim,st_ativo)values(1,6,60,'A2','Requisitos','Definição e escrita dos requisitos',2,1,str_to_date('2018-08-10','%Y-%m-%d'),str_to_date('2018-08-25','%Y-%m-%d'),1);

insert into sub_atividades (id_atividade,id_recurso_humano,nr_horas_previsto,codigo,nome,descricao,id_status_atividade,id_st_sist_atividade,dt_inicio,dt_fim,st_ativo)values(2,1,60,'B1','Classes','Definição das classes do sistema',1,1,str_to_date('2018-08-25','%Y-%m-%d'),str_to_date('2018-09-30','%Y-%m-%d'),1);
insert into sub_atividades (id_atividade,id_recurso_humano,nr_horas_previsto,codigo,nome,descricao,id_status_atividade,id_st_sist_atividade,dt_inicio,dt_fim,st_ativo)values(2,2,300,'B2','Programação','Distribuição de tarefas e programação',1,1,str_to_date('2018-08-30','%Y-%m-%d'),str_to_date('2019-11-30','%Y-%m-%d'),1);
insert into sub_atividades (id_atividade,id_recurso_humano,nr_horas_previsto,codigo,nome,descricao,id_status_atividade,id_st_sist_atividade,dt_inicio,dt_fim,st_ativo)values(2,3,200,'B3','Banco de Dados','Definição e programação do banco de dados',1,1,str_to_date('2018-11-10','%Y-%m-%d'),str_to_date('2019-01-30','%Y-%m-%d'),1);

insert into sub_atividades (id_atividade,id_recurso_humano,nr_horas_previsto,codigo,nome,descricao,id_status_atividade,id_st_sist_atividade,dt_inicio,dt_fim,st_ativo)values(3,6,60,'C1','Testes','Testes e Correções do código',1,1,str_to_date('2019-02-1','%Y-%m-%d'),str_to_date('2019-02-25','%Y-%m-%d'),1);
insert into sub_atividades (id_atividade,id_recurso_humano,nr_horas_previsto,codigo,nome,descricao,id_status_atividade,id_st_sist_atividade,dt_inicio,dt_fim,st_ativo)values(3,5,15,'C2','Aprovação e Entrega','Apresentação e entrega para o cliente',1,1,str_to_date('2019-02-25','%Y-%m-%d'),str_to_date('2019-02-27','%Y-%m-%d'),1);

#controle de horas previstas
insert into apontamento_horas (id_sub_atividade,nr_horas,nr_horas_extras,data,id_conta_pagar,st_pago,st_ativo) values (1,4,0,str_to_date('2018-08-10','%Y-%m-%d'),null,0,1);
insert into apontamento_horas (id_sub_atividade,nr_horas,nr_horas_extras,data,id_conta_pagar,st_pago,st_ativo) values (1,4,0,str_to_date('2018-08-11','%Y-%m-%d'),null,0,1);
insert into apontamento_horas (id_sub_atividade,nr_horas,nr_horas_extras,data,id_conta_pagar,st_pago,st_ativo) values (1,4,0,str_to_date('2018-08-12','%Y-%m-%d'),null,0,1);
insert into apontamento_horas (id_sub_atividade,nr_horas,nr_horas_extras,data,id_conta_pagar,st_pago,st_ativo) values (1,4,0,str_to_date('2018-08-15','%Y-%m-%d'),null,0,1);

insert into apontamento_horas (id_sub_atividade,nr_horas,nr_horas_extras,data,id_conta_pagar,st_pago,st_ativo) values (2,4,0,str_to_date('2018-08-10','%Y-%m-%d'),null,0,1);
insert into apontamento_horas (id_sub_atividade,nr_horas,nr_horas_extras,data,id_conta_pagar,st_pago,st_ativo) values (2,4,0,str_to_date('2018-08-11','%Y-%m-%d'),null,0,1);
insert into apontamento_horas (id_sub_atividade,nr_horas,nr_horas_extras,data,id_conta_pagar,st_pago,st_ativo) values (2,4,0,str_to_date('2018-08-12','%Y-%m-%d'),null,0,1);
insert into apontamento_horas (id_sub_atividade,nr_horas,nr_horas_extras,data,id_conta_pagar,st_pago,st_ativo) values (2,4,0,str_to_date('2018-08-15','%Y-%m-%d'),null,0,1);

#atividade
insert into atividades (id_projeto,codigo,nome,descricao,id_status_atividade,id_st_sist_atividade,st_ativo)values(2,'A','Escopo e Requisitos','Definição do escopo e coleta dos requisitos iniciais',1,1,1);
insert into atividades (id_projeto,codigo,nome,descricao,id_status_atividade,id_st_sist_atividade,st_ativo)values(2,'B','Desenvolvimento','Definição das classes, programação, banco de dados',1,1,1);
insert into atividades (id_projeto,codigo,nome,descricao,id_status_atividade,id_st_sist_atividade,st_ativo)values(2,'C','Testes e Entrega','Testes, Revisão e Entrega',1,1,1);

#sub atividades
insert into sub_atividades (id_atividade,id_recurso_humano,nr_horas_previsto,codigo,nome,descricao,id_status_atividade,id_st_sist_atividade,dt_inicio,dt_fim,st_ativo)values(4,5,60,'A1','Escopo','Definição e escrita do escopo',1,1,str_to_date('2018-01-10','%Y-%m-%d'),str_to_date('2018-01-25','%Y-%m-%d'),1);
insert into sub_atividades (id_atividade,id_recurso_humano,nr_horas_previsto,codigo,nome,descricao,id_status_atividade,id_st_sist_atividade,dt_inicio,dt_fim,st_ativo)values(4,6,60,'A2','Requisitos','Definição e escrita dos requisitos',1,1,str_to_date('2018-01-10','%Y-%m-%d'),str_to_date('2018-01-25','%Y-%m-%d'),1);

insert into sub_atividades (id_atividade,id_recurso_humano,nr_horas_previsto,codigo,nome,descricao,id_status_atividade,id_st_sist_atividade,dt_inicio,dt_fim,st_ativo)values(5,1,100,'B1','Classes','Definição das classes do sistema',1,1,str_to_date('2018-01-25','%Y-%m-%d'),str_to_date('2018-04-30','%Y-%m-%d'),1);
insert into sub_atividades (id_atividade,id_recurso_humano,nr_horas_previsto,codigo,nome,descricao,id_status_atividade,id_st_sist_atividade,dt_inicio,dt_fim,st_ativo)values(5,2,100,'B2','Programação','Distribuição de tarefas e programação',1,1,str_to_date('2018-01-30','%Y-%m-%d'),str_to_date('2019-04-30','%Y-%m-%d'),1);
insert into sub_atividades (id_atividade,id_recurso_humano,nr_horas_previsto,codigo,nome,descricao,id_status_atividade,id_st_sist_atividade,dt_inicio,dt_fim,st_ativo)values(5,3,100,'B3','Banco de Dados','Definição e programação do banco de dados',1,1,str_to_date('2018-02-10','%Y-%m-%d'),str_to_date('2019-04-30','%Y-%m-%d'),1);

insert into sub_atividades (id_atividade,id_recurso_humano,nr_horas_previsto,codigo,nome,descricao,id_status_atividade,id_st_sist_atividade,dt_inicio,dt_fim,st_ativo)values(6,6,60,'C1','Testes','Testes e Correções do código',1,1,str_to_date('2018-05-1','%Y-%m-%d'),str_to_date('2018-07-25','%Y-%m-%d'),1);
insert into sub_atividades (id_atividade,id_recurso_humano,nr_horas_previsto,codigo,nome,descricao,id_status_atividade,id_st_sist_atividade,dt_inicio,dt_fim,st_ativo)values(6,5,30,'C2','Aprovação e Entrega','Apresentação e entrega para o cliente',1,1,str_to_date('2018-05-25','%Y-%m-%d'),str_to_date('2018-07-27','%Y-%m-%d'),1);

#controle de horas previstas
insert into apontamento_horas (id_sub_atividade,nr_horas,nr_horas_extras,data,id_conta_pagar,st_pago,st_ativo) values (8,2,0,str_to_date('2018-01-10','%Y-%m-%d'),null,1,1);
insert into apontamento_horas (id_sub_atividade,nr_horas,nr_horas_extras,data,id_conta_pagar,st_pago,st_ativo) values (8,1,0,str_to_date('2018-01-11','%Y-%m-%d'),null,1,1);
insert into apontamento_horas (id_sub_atividade,nr_horas,nr_horas_extras,data,id_conta_pagar,st_pago,st_ativo) values (8,3,0,str_to_date('2018-01-12','%Y-%m-%d'),null,1,1);
insert into apontamento_horas (id_sub_atividade,nr_horas,nr_horas_extras,data,id_conta_pagar,st_pago,st_ativo) values (8,2,0,str_to_date('2018-01-15','%Y-%m-%d'),null,1,1);

insert into apontamento_horas (id_sub_atividade,nr_horas,nr_horas_extras,data,id_conta_pagar,st_pago,st_ativo) values (9,3,0,str_to_date('2018-01-10','%Y-%m-%d'),null,1,1);
insert into apontamento_horas (id_sub_atividade,nr_horas,nr_horas_extras,data,id_conta_pagar,st_pago,st_ativo) values (9,2,0,str_to_date('2018-01-11','%Y-%m-%d'),null,1,1);
insert into apontamento_horas (id_sub_atividade,nr_horas,nr_horas_extras,data,id_conta_pagar,st_pago,st_ativo) values (9,1,0,str_to_date('2018-01-12','%Y-%m-%d'),null,1,1);
insert into apontamento_horas (id_sub_atividade,nr_horas,nr_horas_extras,data,id_conta_pagar,st_pago,st_ativo) values (9,2,0,str_to_date('2018-01-15','%Y-%m-%d'),null,1,1);

insert into apontamento_horas (id_sub_atividade,nr_horas,nr_horas_extras,data,id_conta_pagar,st_pago,st_ativo) values (10,8,0,str_to_date('2018-02-10','%Y-%m-%d'),null,1,1);
insert into apontamento_horas (id_sub_atividade,nr_horas,nr_horas_extras,data,id_conta_pagar,st_pago,st_ativo) values (10,5,0,str_to_date('2018-03-11','%Y-%m-%d'),null,1,1);
insert into apontamento_horas (id_sub_atividade,nr_horas,nr_horas_extras,data,id_conta_pagar,st_pago,st_ativo) values (10,5,0,str_to_date('2018-03-12','%Y-%m-%d'),null,1,1);
insert into apontamento_horas (id_sub_atividade,nr_horas,nr_horas_extras,data,id_conta_pagar,st_pago,st_ativo) values (10,8,0,str_to_date('2018-04-15','%Y-%m-%d'),null,1,1);

insert into apontamento_horas (id_sub_atividade,nr_horas,nr_horas_extras,data,id_conta_pagar,st_pago,st_ativo) values (11,6,0,str_to_date('2018-02-10','%Y-%m-%d'),null,1,1);
insert into apontamento_horas (id_sub_atividade,nr_horas,nr_horas_extras,data,id_conta_pagar,st_pago,st_ativo) values (11,6,0,str_to_date('2018-03-11','%Y-%m-%d'),null,1,1);
insert into apontamento_horas (id_sub_atividade,nr_horas,nr_horas_extras,data,id_conta_pagar,st_pago,st_ativo) values (11,8,0,str_to_date('2018-03-12','%Y-%m-%d'),null,1,1);
insert into apontamento_horas (id_sub_atividade,nr_horas,nr_horas_extras,data,id_conta_pagar,st_pago,st_ativo) values (11,8,0,str_to_date('2018-04-15','%Y-%m-%d'),null,1,1);

insert into apontamento_horas (id_sub_atividade,nr_horas,nr_horas_extras,data,id_conta_pagar,st_pago,st_ativo) values (12,5,0,str_to_date('2018-02-10','%Y-%m-%d'),null,1,1);
insert into apontamento_horas (id_sub_atividade,nr_horas,nr_horas_extras,data,id_conta_pagar,st_pago,st_ativo) values (12,6,0,str_to_date('2018-03-11','%Y-%m-%d'),null,1,1);
insert into apontamento_horas (id_sub_atividade,nr_horas,nr_horas_extras,data,id_conta_pagar,st_pago,st_ativo) values (12,7,0,str_to_date('2018-03-12','%Y-%m-%d'),null,1,1);
insert into apontamento_horas (id_sub_atividade,nr_horas,nr_horas_extras,data,id_conta_pagar,st_pago,st_ativo) values (12,8,0,str_to_date('2018-04-15','%Y-%m-%d'),null,1,1);

insert into apontamento_horas (id_sub_atividade,nr_horas,nr_horas_extras,data,id_conta_pagar,st_pago,st_ativo) values (13,6,0,str_to_date('2018-05-10','%Y-%m-%d'),null,1,1);
insert into apontamento_horas (id_sub_atividade,nr_horas,nr_horas_extras,data,id_conta_pagar,st_pago,st_ativo) values (13,4,0,str_to_date('2018-05-11','%Y-%m-%d'),null,1,1);
insert into apontamento_horas (id_sub_atividade,nr_horas,nr_horas_extras,data,id_conta_pagar,st_pago,st_ativo) values (13,4,0,str_to_date('2018-06-12','%Y-%m-%d'),null,1,1);
insert into apontamento_horas (id_sub_atividade,nr_horas,nr_horas_extras,data,id_conta_pagar,st_pago,st_ativo) values (13,6,0,str_to_date('2018-07-15','%Y-%m-%d'),null,1,1);

insert into apontamento_horas (id_sub_atividade,nr_horas,nr_horas_extras,data,id_conta_pagar,st_pago,st_ativo) values (14,5,0,str_to_date('2018-05-10','%Y-%m-%d'),null,1,1);
insert into apontamento_horas (id_sub_atividade,nr_horas,nr_horas_extras,data,id_conta_pagar,st_pago,st_ativo) values (14,6,0,str_to_date('2018-06-11','%Y-%m-%d'),null,1,1);
insert into apontamento_horas (id_sub_atividade,nr_horas,nr_horas_extras,data,id_conta_pagar,st_pago,st_ativo) values (14,4,0,str_to_date('2018-07-12','%Y-%m-%d'),null,1,1);
insert into apontamento_horas (id_sub_atividade,nr_horas,nr_horas_extras,data,id_conta_pagar,st_pago,st_ativo) values (14,8,0,str_to_date('2018-07-15','%Y-%m-%d'),null,1,1);

#contas a pagar
insert into contas_pagar(id_cat_contas_pagar,id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,contrato,observacao,id_projeto,st_ativo) values (2,1,str_to_date('2018-01-05','%Y-%m-%d'),str_to_date('2018-01-10','%Y-%m-%d'),60.00,1,null,null,null,1);
insert into contas_pagar(id_cat_contas_pagar,id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,contrato,observacao,id_projeto,st_ativo) values (3,1,str_to_date('2018-01-05','%Y-%m-%d'),str_to_date('2018-01-10','%Y-%m-%d'),70.00,1,null,null,null,1);
insert into contas_pagar(id_cat_contas_pagar,id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,contrato,observacao,id_projeto,st_ativo) values (4,1,str_to_date('2018-01-05','%Y-%m-%d'),str_to_date('2018-01-10','%Y-%m-%d'),30.00,1,null,null,null,1);
insert into contas_pagar(id_cat_contas_pagar,id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,contrato,observacao,id_projeto,st_ativo) values (6,1,str_to_date('2018-01-05','%Y-%m-%d'),str_to_date('2018-01-10','%Y-%m-%d'),40.00,1,null,null,null,1);
insert into contas_pagar(id_cat_contas_pagar,id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,contrato,observacao,id_projeto,st_ativo) values (7,1,str_to_date('2018-01-05','%Y-%m-%d'),str_to_date('2018-01-10','%Y-%m-%d'),30.00,1,null,null,null,1);

insert into contas_pagar(id_cat_contas_pagar,id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,contrato,observacao,id_projeto,st_ativo) values (2,1,str_to_date('2018-02-05','%Y-%m-%d'),str_to_date('2018-02-10','%Y-%m-%d'),30.00,1,null,null,null,1);
insert into contas_pagar(id_cat_contas_pagar,id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,contrato,observacao,id_projeto,st_ativo) values (3,1,str_to_date('2018-02-05','%Y-%m-%d'),str_to_date('2018-02-10','%Y-%m-%d'),50.00,1,null,null,null,1);
insert into contas_pagar(id_cat_contas_pagar,id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,contrato,observacao,id_projeto,st_ativo) values (4,1,str_to_date('2018-02-05','%Y-%m-%d'),str_to_date('2018-02-10','%Y-%m-%d'),30.00,1,null,null,null,1);

insert into contas_pagar(id_cat_contas_pagar,id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,contrato,observacao,id_projeto,st_ativo) values (2,1,str_to_date('2018-03-05','%Y-%m-%d'),str_to_date('2018-03-10','%Y-%m-%d'),40.00,1,null,null,null,1);
insert into contas_pagar(id_cat_contas_pagar,id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,contrato,observacao,id_projeto,st_ativo) values (3,1,str_to_date('2018-03-05','%Y-%m-%d'),str_to_date('2018-03-10','%Y-%m-%d'),100.00,1,null,null,null,1);
insert into contas_pagar(id_cat_contas_pagar,id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,contrato,observacao,id_projeto,st_ativo) values (4,1,str_to_date('2018-03-05','%Y-%m-%d'),str_to_date('2018-03-10','%Y-%m-%d'),35.00,1,null,null,null,1);
insert into contas_pagar(id_cat_contas_pagar,id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,contrato,observacao,id_projeto,st_ativo) values (8,1,str_to_date('2018-03-05','%Y-%m-%d'),str_to_date('2018-03-10','%Y-%m-%d'),20.00,1,null,null,null,1);
insert into contas_pagar(id_cat_contas_pagar,id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,contrato,observacao,id_projeto,st_ativo) values (9,1,str_to_date('2018-03-05','%Y-%m-%d'),str_to_date('2018-03-10','%Y-%m-%d'),50.00,1,null,null,null,1);

insert into contas_pagar(id_cat_contas_pagar,id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,contrato,observacao,id_projeto,st_ativo) values (2,1,str_to_date('2018-04-05','%Y-%m-%d'),str_to_date('2018-04-10','%Y-%m-%d'),66.00,1,null,null,null,1);
insert into contas_pagar(id_cat_contas_pagar,id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,contrato,observacao,id_projeto,st_ativo) values (3,1,str_to_date('2018-04-05','%Y-%m-%d'),str_to_date('2018-04-10','%Y-%m-%d'),75.00,1,null,null,null,1);
insert into contas_pagar(id_cat_contas_pagar,id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,contrato,observacao,id_projeto,st_ativo) values (4,1,str_to_date('2018-04-05','%Y-%m-%d'),str_to_date('2018-04-10','%Y-%m-%d'),10.00,1,null,null,null,1);

insert into contas_pagar(id_cat_contas_pagar,id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,contrato,observacao,id_projeto,st_ativo) values (2,1,str_to_date('2018-05-05','%Y-%m-%d'),str_to_date('2018-05-10','%Y-%m-%d'),20.00,1,null,null,null,1);
insert into contas_pagar(id_cat_contas_pagar,id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,contrato,observacao,id_projeto,st_ativo) values (3,1,str_to_date('2018-05-05','%Y-%m-%d'),str_to_date('2018-05-10','%Y-%m-%d'),40.00,1,null,null,null,1);
insert into contas_pagar(id_cat_contas_pagar,id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,contrato,observacao,id_projeto,st_ativo) values (4,1,str_to_date('2018-05-05','%Y-%m-%d'),str_to_date('2018-05-10','%Y-%m-%d'),30.00,1,null,null,null,1);

insert into contas_pagar(id_cat_contas_pagar,id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,contrato,observacao,id_projeto,st_ativo) values (2,1,str_to_date('2018-06-05','%Y-%m-%d'),str_to_date('2018-06-10','%Y-%m-%d'),65.00,1,null,null,null,1);
insert into contas_pagar(id_cat_contas_pagar,id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,contrato,observacao,id_projeto,st_ativo) values (3,1,str_to_date('2018-06-05','%Y-%m-%d'),str_to_date('2018-06-10','%Y-%m-%d'),85.00,1,null,null,null,1);
insert into contas_pagar(id_cat_contas_pagar,id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,contrato,observacao,id_projeto,st_ativo) values (4,1,str_to_date('2018-06-05','%Y-%m-%d'),str_to_date('2018-06-10','%Y-%m-%d'),35.00,1,null,null,null,1);
insert into contas_pagar(id_cat_contas_pagar,id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,contrato,observacao,id_projeto,st_ativo) values (6,1,str_to_date('2018-06-05','%Y-%m-%d'),str_to_date('2018-06-10','%Y-%m-%d'),25.00,1,null,null,null,1);
insert into contas_pagar(id_cat_contas_pagar,id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,contrato,observacao,id_projeto,st_ativo) values (8,1,str_to_date('2018-06-05','%Y-%m-%d'),str_to_date('2018-06-10','%Y-%m-%d'),33.00,1,null,null,null,1);

insert into contas_pagar(id_cat_contas_pagar,id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,contrato,observacao,id_projeto,st_ativo) values (2,1,str_to_date('2018-07-05','%Y-%m-%d'),str_to_date('2018-07-10','%Y-%m-%d'),100.00,1,null,null,null,1);
insert into contas_pagar(id_cat_contas_pagar,id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,contrato,observacao,id_projeto,st_ativo) values (3,1,str_to_date('2018-07-05','%Y-%m-%d'),str_to_date('2018-07-10','%Y-%m-%d'),70.00,1,null,null,null,1);
insert into contas_pagar(id_cat_contas_pagar,id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,contrato,observacao,id_projeto,st_ativo) values (4,1,str_to_date('2018-07-05','%Y-%m-%d'),str_to_date('2018-07-10','%Y-%m-%d'),30.00,1,null,null,null,1);

insert into contas_pagar(id_cat_contas_pagar,id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,contrato,observacao,id_projeto,st_ativo) values (2,1,str_to_date('2018-08-05','%Y-%m-%d'),str_to_date('2018-08-10','%Y-%m-%d'),69.00,1,null,null,null,1);
insert into contas_pagar(id_cat_contas_pagar,id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,contrato,observacao,id_projeto,st_ativo) values (3,1,str_to_date('2018-08-05','%Y-%m-%d'),str_to_date('2018-08-10','%Y-%m-%d'),74.00,1,null,null,null,1);
insert into contas_pagar(id_cat_contas_pagar,id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,contrato,observacao,id_projeto,st_ativo) values (4,1,str_to_date('2018-08-05','%Y-%m-%d'),str_to_date('2018-08-10','%Y-%m-%d'),30.00,1,null,null,null,1);
insert into contas_pagar(id_cat_contas_pagar,id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,contrato,observacao,id_projeto,st_ativo) values (7,1,str_to_date('2018-08-05','%Y-%m-%d'),str_to_date('2018-08-10','%Y-%m-%d'),36.00,1,null,null,null,1);
insert into contas_pagar(id_cat_contas_pagar,id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,contrato,observacao,id_projeto,st_ativo) values (9,1,str_to_date('2018-08-05','%Y-%m-%d'),str_to_date('2018-08-10','%Y-%m-%d'),19.00,1,null,null,null,1);

insert into contas_pagar(id_cat_contas_pagar,id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,contrato,observacao,id_projeto,st_ativo) values (2,1,str_to_date('2018-09-05','%Y-%m-%d'),str_to_date('2018-09-10','%Y-%m-%d'),40.00,1,null,null,null,1);
insert into contas_pagar(id_cat_contas_pagar,id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,contrato,observacao,id_projeto,st_ativo) values (3,1,str_to_date('2018-09-05','%Y-%m-%d'),str_to_date('2018-09-10','%Y-%m-%d'),20.00,1,null,null,null,1);
insert into contas_pagar(id_cat_contas_pagar,id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,contrato,observacao,id_projeto,st_ativo) values (4,1,str_to_date('2018-09-05','%Y-%m-%d'),str_to_date('2018-09-10','%Y-%m-%d'),10.00,1,null,null,null,1);

insert into contas_pagar(id_cat_contas_pagar,id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,contrato,observacao,id_projeto,st_ativo) values (2,1,str_to_date('2018-10-05','%Y-%m-%d'),str_to_date('2018-10-10','%Y-%m-%d'),20.00,1,null,null,null,1);
insert into contas_pagar(id_cat_contas_pagar,id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,contrato,observacao,id_projeto,st_ativo) values (3,1,str_to_date('2018-10-05','%Y-%m-%d'),str_to_date('2018-10-10','%Y-%m-%d'),25.00,1,null,null,null,1);
insert into contas_pagar(id_cat_contas_pagar,id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,contrato,observacao,id_projeto,st_ativo) values (4,1,str_to_date('2018-10-05','%Y-%m-%d'),str_to_date('2018-10-10','%Y-%m-%d'),10.00,1,null,null,null,1);

insert into contas_pagar(id_cat_contas_pagar,id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,contrato,observacao,id_projeto,st_ativo) values (2,2,str_to_date('2018-11-05','%Y-%m-%d'),str_to_date('2018-11-10','%Y-%m-%d'),65.00,1,null,null,null,1);
insert into contas_pagar(id_cat_contas_pagar,id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,contrato,observacao,id_projeto,st_ativo) values (3,2,str_to_date('2018-11-05','%Y-%m-%d'),str_to_date('2018-11-10','%Y-%m-%d'),80.00,1,null,null,null,1);
insert into contas_pagar(id_cat_contas_pagar,id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,contrato,observacao,id_projeto,st_ativo) values (4,2,str_to_date('2018-11-05','%Y-%m-%d'),str_to_date('2018-11-10','%Y-%m-%d'),94.00,1,null,null,null,1);
insert into contas_pagar(id_cat_contas_pagar,id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,contrato,observacao,id_projeto,st_ativo) values (6,2,str_to_date('2018-11-05','%Y-%m-%d'),str_to_date('2018-11-10','%Y-%m-%d'),25.00,1,null,null,null,1);

insert into contas_pagar(id_cat_contas_pagar,id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,contrato,observacao,id_projeto,st_ativo) values (2,2,str_to_date('2018-12-05','%Y-%m-%d'),str_to_date('2018-12-10','%Y-%m-%d'),25.00,1,null,null,null,1);
insert into contas_pagar(id_cat_contas_pagar,id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,contrato,observacao,id_projeto,st_ativo) values (3,2,str_to_date('2018-12-05','%Y-%m-%d'),str_to_date('2018-12-10','%Y-%m-%d'),10.00,1,null,null,null,1);
insert into contas_pagar(id_cat_contas_pagar,id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,contrato,observacao,id_projeto,st_ativo) values (4,2,str_to_date('2018-12-05','%Y-%m-%d'),str_to_date('2018-12-10','%Y-%m-%d'),15.00,1,null,null,null,1);

#contas a pagar funcionario
insert into contas_pagar(id_cat_contas_pagar,id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,contrato,observacao,id_projeto,st_ativo) values (1,1,str_to_date('2018-01-10','%Y-%m-%d'),str_to_date('2018-01-10','%Y-%m-%d'),4500.00,1,null,'id rh 5',2,1);
insert into contas_pagar(id_cat_contas_pagar,id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,contrato,observacao,id_projeto,st_ativo) values (1,1,str_to_date('2018-01-10','%Y-%m-%d'),str_to_date('2018-02-10','%Y-%m-%d'),4500.00,1,null,'id rh 6',2,1);
insert into contas_pagar(id_cat_contas_pagar,id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,contrato,observacao,id_projeto,st_ativo) values (1,1,str_to_date('2018-01-10','%Y-%m-%d'),str_to_date('2018-03-10','%Y-%m-%d'),7500.00,1,null,'id rh 1',2,1);
insert into contas_pagar(id_cat_contas_pagar,id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,contrato,observacao,id_projeto,st_ativo) values (1,1,str_to_date('2018-01-10','%Y-%m-%d'),str_to_date('2018-04-10','%Y-%m-%d'),7500.00,1,null,'id rh 2',2,1);
insert into contas_pagar(id_cat_contas_pagar,id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,contrato,observacao,id_projeto,st_ativo) values (1,1,str_to_date('2018-01-10','%Y-%m-%d'),str_to_date('2018-05-10','%Y-%m-%d'),7500.00,1,null,'id rh 3',2,1);
insert into contas_pagar(id_cat_contas_pagar,id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,contrato,observacao,id_projeto,st_ativo) values (1,1,str_to_date('2018-01-10','%Y-%m-%d'),str_to_date('2018-06-10','%Y-%m-%d'),4500.00,1,null,'id rh 6',2,1);
insert into contas_pagar(id_cat_contas_pagar,id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,contrato,observacao,id_projeto,st_ativo) values (1,1,str_to_date('2018-01-10','%Y-%m-%d'),str_to_date('2018-06-10','%Y-%m-%d'),2250.00,1,null,'id rh 5',2,1);

#contas a receber

insert into contas_receber(id_cat_contas_receber,id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,contrato,observacao,id_projeto,st_ativo) values (3,1,str_to_date('2018-07-05','%Y-%m-%d'),str_to_date('2018-07-10','%Y-%m-%d'),2000.00,1,null,null,null,1);
insert into contas_receber(id_cat_contas_receber,id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,contrato,observacao,id_projeto,st_ativo) values (3,1,str_to_date('2018-07-05','%Y-%m-%d'),str_to_date('2018-07-10','%Y-%m-%d'),30.00,1,null,null,null,1);

insert into contas_receber(id_cat_contas_receber,id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,contrato,observacao,id_projeto,st_ativo) values (3,1,str_to_date('2018-08-05','%Y-%m-%d'),str_to_date('2018-08-10','%Y-%m-%d'),2000.00,1,null,null,null,1);

insert into contas_receber(id_cat_contas_receber,id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,contrato,observacao,id_projeto,st_ativo) values (3,1,str_to_date('2018-09-05','%Y-%m-%d'),str_to_date('2018-09-10','%Y-%m-%d'),2000.00,1,null,null,null,1);

insert into contas_receber(id_cat_contas_receber,id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,contrato,observacao,id_projeto,st_ativo) values (2,1,str_to_date('2018-10-05','%Y-%m-%d'),str_to_date('2018-10-10','%Y-%m-%d'),25.00,1,null,null,null,1);

insert into contas_receber(id_cat_contas_receber,id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,contrato,observacao,id_projeto,st_ativo) values (2,2,str_to_date('2018-11-05','%Y-%m-%d'),str_to_date('2018-11-10','%Y-%m-%d'),1000.00,1,null,null,null,1);
insert into contas_receber(id_cat_contas_receber,id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,contrato,observacao,id_projeto,st_ativo) values (3,2,str_to_date('2018-11-05','%Y-%m-%d'),str_to_date('2018-11-10','%Y-%m-%d'),25.00,1,null,null,null,1);

insert into contas_receber(id_cat_contas_receber,id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,contrato,observacao,id_projeto,st_ativo) values (2,2,str_to_date('2018-12-05','%Y-%m-%d'),str_to_date('2018-12-10','%Y-%m-%d'),1000.00,1,null,null,null,1);

#contas receber projeto
insert into contas_receber(id_cat_contas_receber,id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,contrato,observacao,id_projeto,st_ativo) values (1,1,str_to_date('2018-01-10','%Y-%m-%d'),str_to_date('2018-01-10','%Y-%m-%d'),7000.00,6,'P0123',null,2,1);
insert into contas_receber(id_cat_contas_receber,id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,contrato,observacao,id_projeto,st_ativo) values (1,1,str_to_date('2018-02-10','%Y-%m-%d'),str_to_date('2018-02-10','%Y-%m-%d'),7000.00,6,'P0123',null,2,1);
insert into contas_receber(id_cat_contas_receber,id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,contrato,observacao,id_projeto,st_ativo) values (1,1,str_to_date('2018-03-10','%Y-%m-%d'),str_to_date('2018-03-10','%Y-%m-%d'),7000.00,6,'P0123',null,2,1);
insert into contas_receber(id_cat_contas_receber,id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,contrato,observacao,id_projeto,st_ativo) values (1,1,str_to_date('2018-04-10','%Y-%m-%d'),str_to_date('2018-04-10','%Y-%m-%d'),7000.00,6,'P0123',null,2,1);
insert into contas_receber(id_cat_contas_receber,id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,contrato,observacao,id_projeto,st_ativo) values (1,1,str_to_date('2018-05-10','%Y-%m-%d'),str_to_date('2018-05-10','%Y-%m-%d'),7000.00,6,'P0123',null,2,1);
insert into contas_receber(id_cat_contas_receber,id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,contrato,observacao,id_projeto,st_ativo) values (1,1,str_to_date('2018-06-10','%Y-%m-%d'),str_to_date('2018-06-10','%Y-%m-%d'),7000.00,6,'P0123',null,2,1);

insert into contas_receber(id_cat_contas_receber,id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,contrato,observacao,id_projeto,st_ativo) values (1,2,str_to_date('2018-10-10','%Y-%m-%d'),str_to_date('2018-10-10','%Y-%m-%d'),5000.00,3,'P0456',null,1,1);
insert into contas_receber(id_cat_contas_receber,id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,contrato,observacao,id_projeto,st_ativo) values (1,2,str_to_date('2018-11-10','%Y-%m-%d'),str_to_date('2018-11-10','%Y-%m-%d'),5000.00,3,'P0456',null,1,1);
insert into contas_receber(id_cat_contas_receber,id_status_conta,dt_lancamento,dt_vencimento,valor,nr_parcelas,contrato,observacao,id_projeto,st_ativo) values (1,2,str_to_date('2018-12-10','%Y-%m-%d'),str_to_date('2018-12-10','%Y-%m-%d'),5000.00,3,'P0456',null,1,1);

#recursos
insert into recursos (descricao,qtd_estoque,observacao,st_ativo) values ('NOTEBOOK WINDOWS',10,null,1);
insert into recursos (descricao,qtd_estoque,observacao,st_ativo) values ('MACBOOK',10,null,1);
insert into recursos (descricao,qtd_estoque,observacao,st_ativo) values ('IPHONE',5,null,1);
insert into recursos (descricao,qtd_estoque,observacao,st_ativo) values ('ANDROID',5,null,1);
insert into recursos (descricao,qtd_estoque,observacao,st_ativo) values ('IPAD',5,null,1);

