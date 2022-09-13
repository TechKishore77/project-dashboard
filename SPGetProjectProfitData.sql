--worked
use ADE;
drop procedure if exists get_project_profit_data;

delimiter $$
create procedure get_project_profit_data(IN projectId INTEGER)
BEGIN
	drop temporary table if exists temp_result_profit;
    drop temporary table if exists tmp_ab;
	drop temporary table if exists tmp_ob;

    drop temporary table if exists proj_ab_date;
	create temporary table proj_ab_date
	select project, data_date, max(updated_on) updated_on from ANTICIPATED_BUDGET group by project, data_date;

	drop temporary table if exists proj_ab_date1;
	create temporary table proj_ab_date1
	select * from proj_ab_date;

	drop temporary table if exists proj_latest_ab;
	create temporary table proj_latest_ab
	select PD.project, PD.data_date, PD.updated_on from proj_ab_date PD inner join (select project, max(data_date) data_date from proj_ab_date1 group by project) PD1
    on PD.project = PD1.project and PD.data_date = PD1.data_date;

	create temporary table tmp_ab
	select P.id project_id, P.contract_title, P.contract_value - AB.total_budget profit from proj_latest_ab L inner join PROJECT P on L.project = P.id
	inner join ANTICIPATED_BUDGET AB on L.updated_on = AB.updated_on and L.data_date = AB.data_date and L.project = AB.project;

	create temporary table tmp_ob
	select P.id project_id, P.contract_title, P.contract_value - OB.total_budget profit from OPERATING_BUDGET OB, PROJECT P
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

call ADE.get_project_profit_data(0);