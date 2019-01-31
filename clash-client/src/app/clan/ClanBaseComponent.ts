import { ErrorStateMatcher } from '@angular/material';
import { FormControl, FormGroupDirective, NgForm, Validators } from '@angular/forms';
import { OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';

export class MyErrorStateMatcher implements ErrorStateMatcher {
    isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
      const isSubmitted = form && form.submitted;
      return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
    }
  }

export class ClanBaseComponent implements OnInit {

    clanTagControl = new FormControl('', [Validators.required]);
    matcher = new MyErrorStateMatcher();
    clanTag: string;
    constructor(protected cookieService: CookieService) {}

    ngOnInit(): void {
        const clanTag = this.cookieService.get('clanTag');
        if (clanTag !== undefined) {
          this.clanTag = clanTag;
        }
    }

    search() {
        this.cookieService.set('clanTag', this.clanTag);
    }
}
