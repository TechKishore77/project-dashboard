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
  yaxis: ApexYAxis | ApexYAxis[];
  dataLabels: ApexDataLabels;
  title: ApexTitleSubtitle;
  legend: ApexLegend;
  //fill: ApexFill;
  tooltip: ApexTooltip;
};

@Component({
  selector: 'app-spi-variance-curve',
  templateUrl: './spi-variance-curve.component.html',
  styleUrls: ['./spi-variance-curve.component.scss']
})
export class SpiVarianceCurveComponent implements OnInit, OnChanges {

  @ViewChild("chart") chart!: ChartComponent;
  public chartOptions: ChartOptions;

  @Input() project?:Project
  @Input() active:boolean=false

  constructor(private analyticsService: AnalyticsService) {
    this.chartOptions = {
      markers: {},
      series: [],
      chart: {
        height: 400,
        redrawOnParentResize: true,
        type: "line",
        stacked: false
      },
      dataLabels: {
        enabled: false
      },
      stroke: {
        width: [1, 1, 4]
      },
      title: {
        text: "SPI & Variance",
        align: "left",
        offsetX: 110
      },
      xaxis: {
        categories: [2009, 2010, 2011, 2012, 2013, 2014, 2015, 2016]
      },
      yaxis: [
        {
          seriesName: "SPI",
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
            text: "SPI",
            style: {
              color: "#008FFB"
            }
          },
          tooltip: {
            enabled: true
          }
        },
        {
          seriesName: "Variance",
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
            text: "Variance",
            style: {
              color: "#00E396"
            }
          },
          max: 1.0
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
    this.analyticsService.getSpiVarianceData(this.project?.id).subscribe(spiData => {
      let seriesData:ApexAxisChartSeries = [];
      seriesData.push({name: "SPI", type: "line", data: spiData.spiData});
      seriesData.push({name: "Variance", type: "line", data: spiData.varianceData});
      this.chartOptions.series = seriesData;
      this.chartOptions.xaxis = {categories: spiData.months};
    });
  }

}
