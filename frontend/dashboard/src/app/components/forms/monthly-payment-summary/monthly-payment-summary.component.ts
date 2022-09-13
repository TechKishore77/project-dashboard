import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { faEdit, faPaperPlane } from '@fortawesome/free-solid-svg-icons';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { HttpRestService } from 'src/app/services/http.rest.service';
import { NotificationService } from 'src/app/services/notification.service';
import { fallIn } from 'src/app/shared/animations/fallIn.animation';
import { LodashUtils } from 'src/app/shared/utility/lodash-functions';
import { StoreProvider } from '../../../shared/utility/store';

@Component({
  selector: 'app-monthly-payment-summary',
  templateUrl: './monthly-payment-summary.component.html',
  styleUrls: ['./monthly-payment-summary.component.scss'],
  animations: [fallIn()]
})
export class MonthlyPaymentSummaryComponent implements OnInit {

  public paymentSummaryForm: any;
  public faPaperPlane = faPaperPlane;
  public faEdit = faEdit;
  public minDate = new Date();
  public listOfMonthlyPaymentsSummary: any[] = [];
  public selectedPS: any = undefined;
  projectId: number = 0

  constructor(private _formBuilder: FormBuilder, private _store: StoreProvider, private modalService: NgbModal,
    private _router: Router, private _notifyService: NotificationService, private route: ActivatedRoute, private _httpService: HttpRestService) { }

  ngOnInit(): void {
    this.buildForm();
    this.getMonthlyPaymentSummary();
    let numString:(string|null) = this.route.snapshot.paramMap.get('projectId');
    if(numString)
      this.projectId = parseInt(numString)
    else
      this.projectId = 0
  }

  buildForm() {
    this.paymentSummaryForm = this._formBuilder.group({
      ipcNo: [null,
        {
          validators: [
            Validators.required,
            Validators.minLength(3)
          ],
        }],
      valueDate: [null, Validators.required],
      submittedDate: [null, Validators.required],
      grossAmount: [0],
      accumulatedValAmount: [0],
      netPayableAmount: [0],
      certifiedDate: [null, Validators.required],
      certifiedAmount: [0],
      paymentDueDate: [null, Validators.required],
      receivableAmount: [0],
      receivedAmount: [0],
      receivedDate: [null, Validators.required],
      remarks: [null]
    });
  }

  submitMonthlyPaymentSummary() {
    const availablePSRecords: any = this._store.getStoreItem('monthly-payment-summary-records') != '' ? JSON.parse(this._store.getStoreItem('monthly-payment-summary-records')) : false;
    if (availablePSRecords && this.selectedPS !== undefined && this.selectedPS != null) {
      //Filter the existing IPC if its edit flow
      let isUpdateFlag: boolean = false;
      // availablePSRecords.forEach((element: any, index: number) => {
      //   if (element.ipcNo === this.selectedPS.ipcNo) {
      //     availablePSRecords[index] = LodashUtils.deepCopy(this.paymentSummaryForm.value);
      //     isUpdateFlag = true;
      //   }
      // });

      if (!isUpdateFlag) {
        let paymentSummary = this.paymentSummaryForm.value;
        if(this.projectId != 0) {
          paymentSummary.projectId = this.projectId;
        }
        paymentSummary.id = this.selectedPS.id;
        this._httpService.putService("/project/payment", paymentSummary).subscribe(resp => {
          this.reloadCurrentRoute();
        }, (err) => {
          this._notifyService.showError("Failed to update payment details. Please verify all details are right.");
        });
        availablePSRecords.push(paymentSummary);
      } else {
        this._store.setStoreItem('monthly-payment-summary-records', JSON.stringify(availablePSRecords));
      }
      this._notifyService.successMessage("Data updated successfully.!!");
    }
    else {
      let tempArray: any[] = [];
      if (availablePSRecords)
        tempArray = availablePSRecords;
      let paymentSummary = this.paymentSummaryForm.value;
      if(this.projectId != 0) {
        paymentSummary.projectId = this.projectId;
      }
      this._httpService.postService("/project/payment", paymentSummary).subscribe(resp => {
        paymentSummary.id = resp.id;
        tempArray.push(paymentSummary);
        this._store.setStoreItem('monthly-payment-summary-records', JSON.stringify(tempArray));
        this._notifyService.successMessage("Data added successfully.!!");
        this.reloadCurrentRoute();
      }, (err) => {
        this._notifyService.showError("Failed to save payment details. Please verify all details are right.");
      });
    }
  }

  reloadCurrentRoute() {
    let currentUrl = this._router.url;
    this._router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
      this._router.navigate([currentUrl]);
    });
  }

  getMonthlyPaymentSummary() {
    this.listOfMonthlyPaymentsSummary = this._store.getStoreItem('monthly-payment-summary-records') != '' ? JSON.parse(this._store.getStoreItem('monthly-payment-summary-records')) : [];
  }

  openVerticallyCentered(content: any, selectedPS: any) {
    this.selectedPS = LodashUtils.deepCopy(selectedPS);
    this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' }).result.then((result) => {
      // Patch the form group with values
      this.populateIpcDataForUpdate(this.selectedPS);
    }, (reason) => {
      console.log("Dismiss Reason", reason);
    });
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }

  populateIpcDataForUpdate(element: any) {
    this.paymentSummaryForm.patchValue(element);
    //Patch the date fields which is coming as invalid due to format difference
    this.paymentSummaryForm.patchValue({
      "certifiedDate": new Date(element.certifiedDate),
      "valueDate": new Date(element.valueDate),
      "receivedDate": new Date(element.receivedDate),
      "paymentDueDate": new Date(element.paymentDueDate),
      "submittedDate": new Date(element.submittedDate)
    });
    //Scroll To Top always on click of Update.
    window.scrollTo(0, 0);
  }
}
