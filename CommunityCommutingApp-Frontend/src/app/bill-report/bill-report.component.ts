import { Component } from '@angular/core';
import { Billreport } from '../billreport';
import { BillService } from '../bill.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-bill-report',
  templateUrl: './bill-report.component.html',
  styleUrls: ['./bill-report.component.css']
})
export class BillReportComponent {
  billreports:Billreport[];

  constructor(private billService:BillService, private router:Router, private route:ActivatedRoute){}

  ngOnInit(): void {
    let rpId = this.route.snapshot.params['rpId'];

    console.log("id = "+ rpId);
    this.billService.getBillByRpId(rpId).subscribe(data=>{
        this.billreports=data;
     })
    }
}