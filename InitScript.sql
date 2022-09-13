--worked and completed
DROP DATABASE ADE;

CREATE DATABASE ADE;

USE ADE;

DROP TABLE IF EXISTS TOKEN_STORE;

CREATE TABLE TOKEN_STORE
(
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    token TEXT CHARACTER SET ascii COLLATE ascii_bin,
    username VARCHAR(64),
    expiry_time DATETIME,
    valid BOOLEAN
);

DROP TABLE IF EXISTS USER;

CREATE TABLE USER
(
	id INTEGER UNIQUE KEY AUTO_INCREMENT,
	username VARCHAR(64) PRIMARY KEY,
    password CHAR(60) CHARACTER SET latin1 COLLATE latin1_bin,
	email VARCHAR(320),
    enabled BOOLEAN DEFAULT TRUE
);

INSERT INTO USER(username, password) VALUES ('sunny','$2y$10$MbVJKUrjUFs5pCkvVSQzH.oty2Fzko..0fERtZI35i62eZhgaJOhu');


CREATE TABLE PROFILE
(
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(64),
    name VARCHAR(256),
    FOREIGN KEY (username) REFERENCES USER(username)
);

DROP TABLE IF EXISTS ROLE;

CREATE TABLE ROLE
(
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(64)
);

INSERT INTO ROLE(name)
VALUES
('ADMIN'),
('USER');

DROP TABLE IF EXISTS USER_ROLE;

CREATE TABLE USER_ROLE
(
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(64),
    role INTEGER,
    UNIQUE KEY unique_user_role (role, username),
    FOREIGN KEY (username) REFERENCES USER(username),
    FOREIGN KEY (role) REFERENCES ROLE(id)
);

DROP TABLE IF EXISTS PROFILE;

CREATE TABLE PROFILE
(
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(256),
    username VARCHAR(64),
    FOREIGN KEY (username) REFERENCES USER(username) ON DELETE CASCADE
);

DROP TABLE IF EXISTS PROJECT;

CREATE TABLE PROJECT
(
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	contract_no VARCHAR(64) NOT NULL UNIQUE,
    contract_title VARCHAR(256) NOT NULL,
    client VARCHAR(256) NOT NULL,
    consultant VARCHAR(256) NOT NULL,
    type_of_contract VARCHAR(128) NOT NULL,
    form_of_contract VARCHAR(256) NOT NULL,
    contract_value DECIMAL(20,2) NOT NULL,
    start_date DATE NOT NULL,
    completion_date DATE NOT NULL,
    duration INTEGER GENERATED ALWAYS AS (DATEDIFF(completion_date, start_date) + 1),
    maintenance_period INTEGER DEFAULT 0,
    status VARCHAR(64),
    created_on DATETIME DEFAULT CURRENT_TIMESTAMP,
    added_by VARCHAR(64),
    FOREIGN KEY (added_by) REFERENCES USER(username) ON DELETE SET NULL
);

DROP TABLE IF EXISTS PROJECT_PROPERTY;

CREATE TABLE PROJECT_PROPERTY
(
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(128),
	description VARCHAR(256),
    value VARCHAR(256),
    project INTEGER,
    FOREIGN KEY (project) REFERENCES PROJECT(id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS PROJECT_PLAN;

CREATE TABLE PROJECT_PLAN
(
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	description VARCHAR(256),
    project INTEGER,
    FOREIGN KEY (project) REFERENCES PROJECT(id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS PROJECT_PLAN_ITEM;

CREATE TABLE PROJECT_PLAN_ITEM
(
	day DATE,
    planned_value DECIMAL(20,2),
    cum_pv DECIMAL(20,2),
    cum_pv_percent DECIMAL(5,2),
    plan INTEGER,
    FOREIGN KEY (plan) REFERENCES PROJECT_PLAN(id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS ANTICIPATED_BUDGET;

CREATE TABLE ANTICIPATED_BUDGET
(
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	updated_on DATETIME DEFAULT CURRENT_TIMESTAMP,
	total_budget DECIMAL(20,2),
    data_date DATE,
    project INTEGER,
    FOREIGN KEY (project) REFERENCES PROJECT(id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS ANTICIPATED_BUDGET_COMPONENT;

CREATE TABLE ANTICIPATED_BUDGET_COMPONENT
(
	description VARCHAR(128),
    cost DECIMAL(20,2) DEFAULT 0,
    budget INTEGER,
    FOREIGN KEY (budget) REFERENCES ANTICIPATED_BUDGET(id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS OPERATING_BUDGET;

CREATE TABLE OPERATING_BUDGET
(
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	total_budget DECIMAL(20,2),
    project INTEGER,
    FOREIGN KEY (project) REFERENCES PROJECT(id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS OPERATING_BUDGET_COMPONENT;

CREATE TABLE OPERATING_BUDGET_COMPONENT
(
	description VARCHAR(128),
    cost DECIMAL(20,2) DEFAULT 0,
    budget INTEGER,
    FOREIGN KEY (budget) REFERENCES OPERATING_BUDGET(id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS INVOICING_PLAN;

CREATE TABLE INVOICING_PLAN
(
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
    description VARCHAR(256),
    project INTEGER,
    FOREIGN KEY (project) REFERENCES PROJECT(id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS INVOICING_PLAN_ITEM;

CREATE TABLE INVOICING_PLAN_ITEM
(
	day DATE,
    amount DECIMAL(20,2),
    cum_amount DECIMAL(20,2),
    invoicing_plan INTEGER,
    FOREIGN KEY (invoicing_plan) REFERENCES INVOICING_PLAN(id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS INVOICE;

CREATE TABLE INVOICE
(
	data_date DATE,
	invoice_amount DECIMAL(20,2),
    certified_amount DECIMAL(20,2),
    project INTEGER,
    FOREIGN KEY (project) REFERENCES PROJECT(id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS ISSUE;

CREATE TABLE ISSUE
(
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	created_on DATETIME DEFAULT CURRENT_TIMESTAMP,
	reporting_date DATE,
    description VARCHAR(1024),
    planned_close_date DATE,
    action_party VARCHAR(256),
    status VARCHAR(128),
    project INTEGER,
    added_by INTEGER,
    FOREIGN KEY (project) REFERENCES PROJECT(id) ON DELETE CASCADE,
    FOREIGN KEY (added_by) REFERENCES USER(id) ON DELETE SET NULL
);

DROP TABLE IF EXISTS DRIVING_ACTIVITY;

CREATE TABLE DRIVING_ACTIVITY
(
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	data_date DATE,
    name VARCHAR(128),
    status VARCHAR(128),
    issues VARCHAR(1024),
    action_party VARCHAR(256),
    project INTEGER,
    added_by INTEGER,
    FOREIGN KEY (project) REFERENCES PROJECT(id) ON DELETE CASCADE,
    FOREIGN KEY (added_by) REFERENCES USER(id) ON DELETE SET NULL
);

DROP TABLE IF EXISTS PROJECT_PROGRESS;

CREATE TABLE PROJECT_PROGRESS
(
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	data_date DATE,
    progress_percentage DECIMAL(5,2),
    forecast_completion_date DATE,
    project INTEGER,
    FOREIGN KEY (project) REFERENCES PROJECT(id) ON DELETE CASCADE
);


DROP TABLE IF EXISTS WBS;

CREATE TABLE WBS
(
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
    project INTEGER,
    FOREIGN KEY (project) REFERENCES PROJECT(id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS WBS_COMPONENT;

CREATE TABLE WBS_COMPONENT
(
	name VARCHAR(128),
    percent DECIMAL(5,2),
    wbs INTEGER,
    FOREIGN KEY (wbs) REFERENCES WBS(id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS PROJECT_PAYMENT;

CREATE TABLE PROJECT_PAYMENT
(
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	ipc_no INTEGER,
	value_date DATE NOT NULL,
	submitted_date DATE NOT NULL,
    gross_amount DECIMAL(20,2) NOT NULL,
    accumulated_val_amount DECIMAL(20,2) NOT NULL,
    net_payable_amount DECIMAL(20,2) NOT NULL,
    certified_date DATE NOT NULL,
    certified_amount DECIMAL(20,2) NOT NULL,
    payment_due_date DATE NOT NULL,
    receivable_amount DECIMAL(20,2) NOT NULL,
    received_amount DECIMAL(20,2) NOT NULL,
    received_date DATE NOT NULL,
    remarks VARCHAR(256) NOT NULL,
    project_id INTEGER,
    FOREIGN KEY (project_id) REFERENCES PROJECT(id) ON DELETE CASCADE
);

drop procedure if exists get_forecast_completion_data;

delimiter $$
create procedure get_forecast_completion_data(IN projectId INTEGER)
BEGIN
	drop temporary table if exists temp_result_forecast;
	drop temporary table if exists tmp_latest_for_project;
	drop temporary table if exists tmp_latest_per_day;
	drop temporary table if exists progress;
	drop temporary table if exists tmp_latest_progress;

	create temporary table tmp_latest_per_day
	select max(id) latest_id, data_date, project from project_progress PPG group by data_date, project;

	create temporary table tmp_latest_for_project
	select max(data_date) latest_data_date, project from tmp_latest_per_day group by project; 

	-- select * from tmp_latest_for_project;
	create temporary table tmp_latest_progress
	select latest_id from tmp_latest_for_project tlfp inner join tmp_latest_per_day tlpd on tlfp.latest_data_date = tlpd.data_date and tlfp.project = tlpd.project;

	create temporary table progress
	select * from tmp_latest_progress tlp inner join project_progress PPG on tlp.latest_id = PPG.id;

	create temporary table temp_result_forecast
	select P.id, P.contract_no, P.contract_title, P.completion_date, coalesce(PG.forecast_completion_date, P.completion_date) forecast_completion, PG.data_date, DATEDIFF(P.completion_date, coalesce(PG.forecast_completion_date, P.completion_date)) delay from project P left join progress PG
	on P.id = PG.project;
    
	if projectId = 0 then
		select * from temp_result_forecast;
	else
		select * from temp_result_forecast
        where id = projectId;
    end if;
    drop temporary table if exists temp_result_forecast;
	drop temporary table if exists tmp_latest_for_project;
	drop temporary table if exists tmp_latest_per_day;
	drop temporary table if exists progress;
    drop temporary table if exists temp_result_forecast;
END $$
delimiter ;

drop procedure if exists get_payment_data;

delimiter $$
create procedure get_payment_data(IN pid INTEGER)
BEGIN
	drop table if exists temp_inv_plan;
	drop table if exists temp_payment;
	drop temporary table if exists temp_cum_received_amount;
	drop temporary table if exists res;
	drop temporary table if exists temp_earned;
    drop temporary table if exists temp_final_res;

	create temporary table temp_cum_received_amount
	select project_id pid, sum(received_amount) cum_received_amt from project_payment group by project_id;

	create temporary table temp_inv_plan
	select P.id projectId, IPI.day, IPI.cum_amount cum_planned_amount
	from project P, invoicing_plan IP, invoicing_plan_item IPI
	where P.id = IP.project and IP.id = IPI.invoicing_plan;

	create temporary table temp_payment
	select cra.pid, payment.ipc_no, payment.value_date, payment.submitted_date, payment.gross_amount, payment.received_amount, payment.received_date, payment.accumulated_val_amount, cra.cum_received_amt from 
	(select * from project_payment Payment 
	where accumulated_val_amount = (select max(accumulated_val_amount) from project_payment where project_id = Payment.project_id)) payment
	inner join
	temp_cum_received_amount cra
	on payment.project_id = cra.pid;

	create temporary table res
	select * from temp_inv_plan Plan
	left join temp_payment Payment
	on Payment.pid = Plan.projectId
	where last_day(Payment.value_date) = last_day(Plan.day);



	create temporary table temp_earned
	select P.id projectId, P.contract_title, coalesce(P.contract_value * PPG.progress_percentage, 0) earned_value
	from project P left join (select data_date, progress_percentage, project from project_progress PP where data_date = (select max(data_date) from project_progress where project = PP.project)) PPG
	on P.id = PPG.project;
	
    create temporary table temp_final_res
	select E.projectId proj, E.contract_title, E.earned_value, coalesce(R.cum_planned_amount,0) cum_planned_amt, R.value_date, R.submitted_date, coalesce(R.gross_amount,0) latest_gross_amt, coalesce(R.received_amount,0) latest_received_amt, R.received_date, coalesce(R.accumulated_val_amount,0) accumulated_gross_amt, coalesce(R.cum_received_amt,0) cumulative_received_amt from temp_earned E left join
	res R on E.projectId = R.pid;
    
    if pid = 0 then
		select * from temp_final_res;
	else
		select * from temp_final_res where proj = pid;
    end if;
END $$
delimiter ;

drop procedure if exists get_project_progress_data;

delimiter $$
create procedure get_project_progress_data(IN projectId INTEGER)
BEGIN    
	if projectId = 0 then
		select actual.*, coalesce(planned.pv_percent, 0) planned_progress from
		(select P.id pid, p.contract_title, coalesce(max(PP.progress_percentage), 0) actual_progress, coalesce(max(PP.data_date), P.start_date) data_date from project P left join project_progress PP
		on P.id = PP.project
		group by P.id, P.completion_date, P.contract_title, P.contract_no, P.start_date) actual
		left join
		(select day, PlanItem.cum_pv_percent pv_percent, project.id id from project_plan Plan inner join project_plan_item PlanItem on Plan.id = PlanItem.plan inner join project on Plan.project = project.id) planned
		on actual.data_date = planned.day and actual.pid = planned.id;
	else
		select actual.*, coalesce(planned.pv_percent, 0) planned_progress from
		(select P.id pid, p.contract_title, coalesce(max(PP.progress_percentage), 0) actual_progress, coalesce(max(PP.data_date), P.start_date) data_date from project P left join project_progress PP
		on P.id = PP.project
		group by P.id, P.completion_date, P.contract_title, P.contract_no, P.start_date) actual
		left join
		(select day, PlanItem.cum_pv_percent pv_percent, project.id id from project_plan Plan inner join project_plan_item PlanItem on Plan.id = PlanItem.plan inner join project on Plan.project = project.id) planned
		on actual.data_date = planned.day and actual.pid = planned.id where pid = projectId;
	end if;
END $$
delimiter ;


drop procedure if exists get_project_profit_data;

delimiter $$
create procedure get_project_profit_data(IN projectId INTEGER)
BEGIN
	drop temporary table if exists temp_result_profit;
    drop temporary table if exists tmp_ab;
	drop temporary table if exists tmp_ob;

    drop temporary table if exists proj_ab_date;
	create temporary table proj_ab_date
	select project, data_date, max(updated_on) updated_on from anticipated_budget group by project, data_date;

	drop temporary table if exists proj_ab_date1;
	create temporary table proj_ab_date1
	select * from proj_ab_date;

	drop temporary table if exists proj_latest_ab;
	create temporary table proj_latest_ab
	select PD.project, PD.data_date, PD.updated_on from proj_ab_date PD inner join (select project, max(data_date) data_date from proj_ab_date1 group by project) PD1
    on PD.project = PD1.project and PD.data_date = PD1.data_date;

	create temporary table tmp_ab
	select P.id project_id, P.contract_title, P.contract_value - AB.total_budget profit from proj_latest_ab L inner join project P on L.project = P.id
	inner join anticipated_budget AB on L.updated_on = AB.updated_on and L.data_date = AB.data_date and L.project = AB.project;

	create temporary table tmp_ob
	select P.id project_id, P.contract_title, P.contract_value - OB.total_budget profit from operating_budget OB, project P
	where OB.project = P.id;
    
	create temporary table temp_result_profit
    select OB.project_id, OB.contract_title, OB.profit planned_profit, AB.profit actual_profit
	from tmp_ab AB inner join tmp_ob OB
	on AB.project_id = OB.project_id;
    
	if projectId = 0 then
		select * from temp_result_profit;
	else
		select * from temp_result_profit where project_id = projectId;
    end if;
    drop temporary table if exists temp_result_profit;
END $$
delimiter ;
