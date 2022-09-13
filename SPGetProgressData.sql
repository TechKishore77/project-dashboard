--worked
use ADE;
drop procedure if exists get_project_progress_data;

delimiter $$
create procedure get_project_progress_data(IN projectId INTEGER)
BEGIN    
	if projectId = 0 then
		select actual.*, coalesce(planned.pv_percent, 0) planned_progress from
		(select P.id pid, P.contract_title, coalesce(max(PP.progress_percentage), 0) actual_progress, coalesce(max(PP.data_date), P.start_date) data_date from PROJECT P left join PROJECT_PROGRESS PP
		on P.id = PP.project
		group by P.id, P.completion_date, P.contract_title, P.contract_no, P.start_date) actual
		left join
		(select day, PlanItem.cum_pv_percent pv_percent, project.id id from PROJECT_PLAN Plan inner join PROJECT_PLAN_ITEM PlanItem on Plan.id = PlanItem.plan inner join PROJECT project on Plan.project = project.id) planned
		on actual.data_date = planned.day and actual.pid = planned.id;
	else
		select actual.*, coalesce(planned.pv_percent, 0) planned_progress from
		(select P.id pid, p.contract_title, coalesce(max(PP.progress_percentage), 0) actual_progress, coalesce(max(PP.data_date), P.start_date) data_date from PROJECT P left join PROJECT_PROGRESS PP
		on P.id = PP.project
		group by P.id, P.completion_date, P.contract_title, P.contract_no, P.start_date) actual
		left join
		(select day, PlanItem.cum_pv_percent pv_percent, project.id id from project_plan Plan inner join project_plan_item PlanItem on Plan.id = PlanItem.plan inner join PROJECT on Plan.project = project.id) planned
		on actual.data_date = planned.day and actual.pid = planned.id where pid = projectId;
	end if;
END $$
delimiter ;

call ADE.get_project_progress_data(0);