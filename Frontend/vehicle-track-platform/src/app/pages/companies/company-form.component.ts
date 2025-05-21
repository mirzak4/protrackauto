import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { CompanyService } from 'app/core/services/company.service';
import { CompanyDTO } from 'app/core/models/company.model';

@Component({
  selector: 'app-company-form',
  standalone: true,
  templateUrl: './company-form.component.html',
  imports: [CommonModule, ReactiveFormsModule, RouterModule]
})
export class CompanyFormComponent implements OnInit {
  form!: FormGroup;
  isEditMode = false;

  private fb = inject(FormBuilder);
  private route = inject(ActivatedRoute);
  private router = inject(Router);
  private companyService = inject(CompanyService);

  ngOnInit(): void {
    this.form = this.fb.group({
      name: ['', Validators.required],
      address: ['', Validators.required],
      companyType: ['', Validators.required]
    });

    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isEditMode = true;
      this.companyService.getById(+id).subscribe(company => {
        this.form.patchValue(company);
      });
    }
  }

  onSubmit() {
    if (this.form.invalid) return;

    const dto: CompanyDTO = this.form.value;

    if (this.isEditMode) {
      const id = this.route.snapshot.paramMap.get('id');
      this.companyService.update(+id!, dto).subscribe(() => {
        this.router.navigate(['/companies']);
      });
    } else {
      this.companyService.create(dto).subscribe(() => {
        this.router.navigate(['/companies']);
      });
    }
  }
}
