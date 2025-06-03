import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Company } from '../company.model';
import { CompanyService } from '../company.service';

@Component({
  standalone: true,
  selector: 'app-company-detail',
  templateUrl: './company-detail.component.html',
  styleUrls: ['./company-detail.component.css']
})
export class CompanyDetailComponent implements OnInit {
  company?: Company;

  constructor(
    private route: ActivatedRoute,
    private companyService: CompanyService
  ) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.loadCompany(id);
  }

  loadCompany(id: number): void {
    this.companyService.getCompany(id).subscribe({
      next: (data) => this.company = data,
      error: (err) => console.error('Error loading company', err)
    });
  }
}
