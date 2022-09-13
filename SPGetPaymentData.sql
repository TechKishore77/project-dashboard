--worked
use ADE;
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
	select project_id pid, sum(received_amount) cum_received_amt from PROJECT_PAYMENT group by project_id;

	create temporary table temp_inv_plan
	select P.id projectId, IPI.day, IPI.cum_amount cum_planned_amount
	from PROJECT P, INVOICING_PLAN IP, INVOICING_PLAN_ITEM IPI
	where P.id = IP.project and IP.id = IPI.invoicing_plan;

	create temporary table temp_payment
	select cra.pid, payment.ipc_no, payment.value_date, payment.submitted_date, payment.gross_amount, payment.received_amount, payment.received_date, payment.accumulated_val_amount, cra.cum_received_amt from 
	(select * from PROJECT_PAYMENT Payment 
	where accumulated_val_amount = (select max(accumulated_val_amount) from PROJECT_PAYMENT where project_id = Payment.project_id)) payment
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
	from PROJECT P left join (select data_date, progress_percentage, project from PROJECT_PROGRESS PP where data_date = (select max(data_date) from PROJECT_PROGRESS where project = PP.project)) PPG
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

call ADE.get_payment_data(13);