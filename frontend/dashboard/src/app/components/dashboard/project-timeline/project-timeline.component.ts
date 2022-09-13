import { Component, Input, OnChanges, OnInit, SimpleChanges, ViewChild } from "@angular/core";
import {
  ApexAxisChartSeries,
  ApexChart,
  ApexDataLabels,
  ApexFill,
  ApexLegend, ApexPlotOptions,
  ApexStroke,
  ApexTitleSubtitle,
  ApexTooltip, ApexXAxis, ChartComponent
} from "ng-apexcharts";
import { Project } from "src/app/model/project";

export type ChartOptions = {
  series: ApexAxisChartSeries;
  chart: ApexChart;
  dataLabels: ApexDataLabels;
  plotOptions: ApexPlotOptions;
  xaxis: ApexXAxis;
  stroke: ApexStroke;
  title: ApexTitleSubtitle;
  tooltip: ApexTooltip;
  fill: ApexFill;
  legend: ApexLegend;
};

@Component({
  selector: 'app-project-timeline',
  templateUrl: './project-timeline.component.html',
  styleUrls: ['./project-timeline.component.scss']
})
export class ProjectTimelineComponent implements OnInit, OnChanges {

  @ViewChild("chart") chart!: ChartComponent;
  public chartOptions: ChartOptions;

  @Input() project?:Project

  constructor() {
    this.chartOptions = {
      dataLabels: {},
      series: [
        {
          name: "No. of days elapsed",
          data: [358]
        },
        {
          name: "No. of days remaining",
          data: [373]
        }
      ],
      chart: {
        type: "bar",
        height: 200,
        stacked: true,
        stackType: "100%"
      },
      plotOptions: {
        bar: {
          horizontal: true
        }
      },
      stroke: {
        width: 1,
        colors: ["#fff"]
      },
      title: {
        text: "Project Timeline"
      },
      xaxis: {
        categories: ["Days"]
      },
      tooltip: {
        y: {
          formatter: function (val) {
            return val + " days";
          }
        }
      },
      fill: {
        opacity: 1,
        colors: ["#00b050", "#2e75b6"]
      },
      legend: {
        position: "top",
        horizontalAlign: "right",
        offsetX: 40,
        markers: {
          fillColors: ["#00b050", "#2e75b6"]
        },
      }
    };
  }

  ngOnChanges(changes: SimpleChanges): void {
    if(this.project) {
      let startDate = this.project.startDate || new Date();
      let completionDate = this.project.completionDate || new Date();
      let today = Date.now();
      const totalTime = Math.abs(completionDate.getTime() - startDate.getTime()) / (86400 * 1000);
      const timeDiff = Math.abs(today - startDate?.getTime()) / (86400 * 1000);
      if(timeDiff < totalTime) {
        this.chartOptions.series = [
          {
            name: "No. of days elapsed",
            data: [timeDiff]
          },
          {
            name: "No. of days remaining",
            data: [totalTime - timeDiff]
          }
        ];
      }
    }

  }

  ngOnInit(): void {
    this.chartOptions.series = [
      {
        name: "No. of days elapsed",
        data: [358]
      },
      {
        name: "No. of days remaining",
        data: [373]
      }
    ];
  }

}
