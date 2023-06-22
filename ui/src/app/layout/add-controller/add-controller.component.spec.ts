import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddControllerComponent } from './add-controller.component';

describe('AddControllerComponent', () => {
  let component: AddControllerComponent;
  let fixture: ComponentFixture<AddControllerComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddControllerComponent]
    });
    fixture = TestBed.createComponent(AddControllerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
