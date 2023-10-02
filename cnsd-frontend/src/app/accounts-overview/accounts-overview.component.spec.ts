import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AccountsOverviewComponent } from './accounts-overview.component';

describe('AccountsOverviewComponent', () => {
  let component: AccountsOverviewComponent;
  let fixture: ComponentFixture<AccountsOverviewComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AccountsOverviewComponent]
    });
    fixture = TestBed.createComponent(AccountsOverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
