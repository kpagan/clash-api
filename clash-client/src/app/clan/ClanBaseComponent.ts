import { ErrorStateMatcher } from '@angular/material/core';
import { FormControl, FormGroupDirective, NgForm, Validators } from '@angular/forms';
import { OnInit, Directive } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';

export class MyErrorStateMatcher implements ErrorStateMatcher {
    isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
      const isSubmitted = form && form.submitted;
      return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
    }
  }

@Directive()
export class ClanBaseComponent implements OnInit {

    clanTagControl = new FormControl('', [Validators.required]);
    matcher = new MyErrorStateMatcher();
    constructor(protected cookieService: CookieService) {}

    ngOnInit(): void {
        const clanTag = this.cookieService.get('clanTag');
        if (clanTag !== undefined) {
          this.clanTagControl.setValue(clanTag);
        }
    }

    search() {
        this.cookieService.set('clanTag', this.clanTagControl.value);
    }

    clearSearchField() {
      this.clanTagControl.setValue('');
    }
}
