import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { ProjectProfitData } from 'src/app/model/project-profit-data';
import { ReportsService } from 'src/app/services/reports.service';

@Component({
  selector: 'app-profit-project-tile',
  templateUrl: './profit-project-tile.component.html',
  styleUrls: ['./profit-project-tile.component.scss']
})
export class ProfitProjectTileComponent implements OnInit, OnChanges {
  @Input() projectId:number = 0
  data: ProjectProfitData  = new ProjectProfitData();
  variance:number=0;

  constructor(private reportsService: ReportsService) { }
  ngOnChanges(changes: SimpleChanges): void {
    this.reportsService.getProfitReportForProject(this.projectId)?.subscribe(data => {
      this.data = data;
    });
  }

  ngOnInit(): void {
    this.reportsService.getProfitReportForProject(this.projectId)?.subscribe(data => {
      this.data = data;
      if(this.data && this.data.plannedProfit != 0)
        this.variance = ((this.data.actualProfit - this.data.plannedProfit) / this.data.plannedProfit) * 100;
    });
  }

}
