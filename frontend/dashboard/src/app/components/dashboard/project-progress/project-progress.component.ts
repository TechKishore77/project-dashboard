import { ChangeDetectorRef, Component, Input, OnChanges, OnInit, SimpleChanges, ViewChild } from '@angular/core';
import { ApexChart, ApexDataLabels, ApexFill, ApexLegend, ApexNonAxisChartSeries, ApexResponsive, ChartComponent } from 'ng-apexcharts';
import { Project } from 'src/app/model/project';
import { ProjectProgressData } from 'src/app/model/project-progress-data';
import { HttpRestService } from 'src/app/services/http.rest.service';

export type ChartOptions = {
  series: ApexNonAxisChartSeries;
  chart: ApexChart;
  responsive: ApexResponsive[];
  labels: any;
  fill: ApexFill;
  legend: ApexLegend;
  dataLabels: ApexDataLabels;
};

@Component({
  selector: 'app-project-progress',
  templateUrl: './project-progress.component.html',
  styleUrls: ['./project-progress.component.scss']
})
export class ProjectProgressComponent implements OnInit, OnChanges {

  @ViewChild("chart") chart!: ChartComponent;
  public chartOptions: ChartOptions;

  @Input() project?: Project
  progressData?:ProjectProgressData
  complete:number = 0
  pending:number = 100

  constructor(private httpService:HttpRestService, private _changeRef: ChangeDetectorRef) {
    this.chartOptions = {
      labels: ["Complete", "Pending"],
      series: [45, 55],
      chart: {
        width: 380,
        type: "donut"
      },
      dataLabels: {
        enabled: true,
        formatter: function(val) {
          return val + "%";
        }
      },
      fill: {
        type: "gradient",
        colors: ["#00b050", "#2e75b6"]
      },
      legend: {
        formatter: function(val, opts) {
          return val + " - " + opts.w.globals.series[opts.seriesIndex];
        }
      },
      responsive: [
        {
          breakpoint: 480,
          options: {
            chart: {
              width: 200
            },
            legend: {
              position: "bottom"
            }
          }
        }
      ]
    };
  }
  ngOnChanges(changes: SimpleChanges): void {
    if(this.project) {
      this.httpService.getService(`/project/${this.project?.id}/reports/progress`).subscribe(progressData => {
        this.progressData = progressData;
        if(this.progressData) {
            this.complete = Math.floor(this.progressData.actualProgress);
            this.pending = 100 - this.complete;
            this.chartOptions.series = [this.complete, this.pending];
            this._changeRef.markForCheck();
        }
      });
    }
  }


  ngOnInit(): void {
    if(this.project) {
      this.httpService.getService(`/project/${this.project?.id}/reports/progress`).subscribe(progressData => {
        this.progressData = progressData;
        if(this.progressData) {
            this.complete = Math.floor(this.progressData.actualProgress);
            this.pending = 100 - this.complete;
            this.chartOptions.series = [this.complete, this.pending];
            this._changeRef.markForCheck();
        }
      });
    }
  }



}
