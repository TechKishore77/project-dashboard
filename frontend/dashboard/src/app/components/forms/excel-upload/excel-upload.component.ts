import { ChangeDetectorRef, Component, ElementRef, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { FileUploader } from 'ng2-file-upload';
import { NgxSpinnerService } from 'ngx-spinner';
import { AuthService } from 'src/app/core/services/auth.service';

@Component({
  selector: 'app-excel-upload',
  templateUrl: './excel-upload.component.html',
  styleUrls: ['./excel-upload.component.scss']
})
export class ExcelUploadComponent implements OnInit, OnChanges {
  @Input() autoUpload?:boolean = false;
  @Input() removeAfterUpload?:boolean = false;
  uploading?:boolean;
  @Input() postUrl?:string;
  uploader!: FileUploader;
  alerts:{message:string, type:string}[] = [];
  selected:boolean = false;
  file?:any
  timeout?:any
  @Input() disabled?:boolean
  authToken:string = ""
  @Output() success:EventEmitter<boolean> = new EventEmitter<boolean>();

  @ViewChild('uploadEl') uploadElRef!: ElementRef

  ngAfterViewInit() {
  this.uploader.onCompleteAll = () => {
    this.uploadElRef.nativeElement.value = ''
    };
  }

  constructor(private changeDetector: ChangeDetectorRef, private spinner: NgxSpinnerService, private authService: AuthService, private router: Router) {
    this.authToken = authService.getAuthToken();
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.uploader?.setOptions({ url: this.postUrl, removeAfterUpload: true, autoUpload: this.autoUpload, authToken: this.authToken });
  }

  ngOnInit(): void {
    this.uploader = new FileUploader({ url: this.postUrl, removeAfterUpload: true, autoUpload: this.autoUpload, authToken: this.authToken} );
    this.uploader.onAfterAddingFile = (file) => { file.withCredentials = false; };
    this.uploader.onSuccessItem = (item, reponse, status, headers) => {
      this.spinner.hide();
      if(status == 200) {
        this.alerts.push({message: "Project uploaded successfully", type: "success"});
        clearTimeout(this.timeout);
        this.router.navigate(['/projects']);
        this.success.emit(true);
      }
    }

    this.uploader.onErrorItem = (item, response, status, headers) => {
      this.spinner.hide();
      if(status == 409) {
        this.alerts.push({message: "Project with same contract number already exists.", type: "danger"});
      } else {
        this.alerts.push({message: "Failed to upload project file. Please check file format.", type: "danger"});
      }
      clearTimeout(this.timeout);
    }
  }

  onUpload() {
    this.uploading = true;
    if(this.selected) {
      this.uploader.uploadAll();
      this.timeout = setTimeout(()=> {
        this.uploader.cancelAll();
        this.alerts.push({message: "Upload is taking longer than expected. Please try again.", type: "danger"})
        this.spinner.hide();
      }, 15000);
      this.spinner.show();
    } else {
      this.alerts.push({message: "Please select a file to upload", type: "warning"});
    }
  }

  fileSelected(event:any) {
    this.selected = true;
    this.file = event[0];
  }

  onChange(event:any) {
  }

}
