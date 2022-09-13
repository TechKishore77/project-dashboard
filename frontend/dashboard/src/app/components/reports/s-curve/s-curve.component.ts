import { Component, Input, OnChanges, OnInit, SimpleChanges, ViewChild } from '@angular/core';
import { ApexAxisChartSeries, ApexChart, ApexDataLabels, ApexLegend, ApexTitleSubtitle, ApexTooltip, ApexXAxis, ApexYAxis, ChartComponent } from 'ng-apexcharts';
import { Project } from 'src/app/model/project';
import { AnalyticsService } from 'src/app/services/analytics.service';

export type ChartOptions = {
  series: ApexAxisChartSeries;
  chart: ApexChart;
  xaxis: ApexXAxis;
  markers: any; //ApexMarkers;
  stroke: any; //ApexStroke;
  yaxis: ApexYAxis[];
  dataLabels: ApexDataLabels;
  title: ApexTitleSubtitle;
  legend: ApexLegend;
  //fill: ApexFill;
  tooltip: ApexTooltip;
};

@Component({
  selector: 'app-s-curve',
  templateUrl: './s-curve.component.html',
  styleUrls: ['./s-curve.component.scss']
})
export class SCurveComponent implements OnInit, OnChanges {

  @ViewChild("chart") chart!: ChartComponent;
  public chartOptions: ChartOptions;

  @Input() project?:Project
  @Input() active:boolean=false

  constructor(private analyticsService: AnalyticsService) {
    this.chartOptions = {
      markers: {},
      series: [],
      chart: {
        height: 350,
        type: "line",
        stacked: false
      },
      dataLabels: {
        enabled: false
      },
      stroke: {
        width: [1, 1, 4, 4]
      },
      title: {
        text: "Project Progress",
        align: "left",
        offsetX: 110
      },
      xaxis: {
        categories: [2009, 2010, 2011, 2012, 2013, 2014, 2015, 2016]
      },
      yaxis: [
        {
          seriesName: "Planned",
          axisTicks: {
            show: true
          },
          axisBorder: {
            show: true,
            color: "#008FFB"
          },
          labels: {
            style: {
              colors: "#008FFB"
            }
          },
          title: {
            text: "Cumulative Monthly Values",
            style: {
              color: "#008FFB"
            }
          },
          tooltip: {
            enabled: true
          }
        },
        {
          showAlways: false,
          labels: {
            show: false
          }
        },
        {
          seriesName: "Planned Progress %",
          opposite: true,
          axisTicks: {
            show: true
          },
          axisBorder: {
            show: true,
            color: "#00E396"
          },
          labels: {
            style: {
              colors: "#00E396"
            }
          },
          title: {
            text: "Progress %",
            style: {
              color: "#00E396"
            }
          },
          max: 100.0
        },
        {
          showAlways: false,
          labels: {
            show: false
          },
          max: 100.0
        },
      ],
      tooltip: {
        fixed: {
          enabled: true,
          position: "topLeft", // topRight, topLeft, bottomRight, bottomLeft
          offsetY: 30,
          offsetX: 60
        }
      },
      legend: {
        horizontalAlign: "left",
        offsetX: 40
      }
    };
  }
  ngOnChanges(changes: SimpleChanges): void {
    if((!changes.active.isFirstChange()) && changes.active.currentValue) {
      this.ngOnInit();
    }
  }

  ngOnInit(): void {
    this.analyticsService.getSCurveData(this.project?.id).subscribe(sCurveData => {
      let seriesData:ApexAxisChartSeries = [];
      seriesData.push({name: "Planned", type: "column", data: sCurveData.plannedValues});
      seriesData.push({name: "Earned", type: "column", data: sCurveData.actualValues});
      seriesData.push({name: "Planned Progress %", type: "line", data: sCurveData.cumulativePlannedPercentValues});
      seriesData.push({name: "Earned Progress %", type: "line", data: sCurveData.cumulativeActualPercentValues})
      this.chartOptions.series = seriesData;
      this.chartOptions.xaxis = {categories: sCurveData.months};
      this.chartOptions.yaxis[0].max = sCurveData.maxValue;
      this.chartOptions.yaxis[1].max = sCurveData.maxValue;
    });
  }

}
