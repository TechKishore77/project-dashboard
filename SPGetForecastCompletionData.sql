--worked
use ADE;
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
	select max(id) latest_id, data_date, project from PROJECT_PROGRESS PPG group by data_date, project;

	create temporary table tmp_latest_for_project
	select max(data_date) latest_data_date, project from tmp_latest_per_day group by project; 

	-- select * from tmp_latest_for_project;
	create temporary table tmp_latest_progress
	select latest_id from tmp_latest_for_project tlfp inner join tmp_latest_per_day tlpd on tlfp.latest_data_date = tlpd.data_date and tlfp.project = tlpd.project;

	create temporary table progress
	select * from tmp_latest_progress tlp inner join PROJECT_PROGRESS PPG on tlp.latest_id = PPG.id;

	create temporary table temp_result_forecast
	select P.id, P.contract_no, P.contract_title, P.completion_date, coalesce(PG.forecast_completion_date, P.completion_date) forecast_completion, PG.data_date, DATEDIFF(P.completion_date, coalesce(PG.forecast_completion_date, P.completion_date)) delay from PROJECT P left join progress PG
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

call ADE.get_forecast_completion_data(0);