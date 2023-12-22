import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HomeComponent } from './home/home.component';
import { FooterComponent } from './footer/footer.component';
import { HeaderComponent } from './header/header.component';
import { ProductDetailComponent } from './product-detail/product-detail.component';
import { OrderComponent } from './order/order.component';
import { OrderConfirmComponent } from './order-confirm/order-confirm.component';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';


@NgModule({
  declarations: [
    HomeComponent,
    FooterComponent,
    HeaderComponent,
    ProductDetailComponent,
    OrderComponent,
    OrderConfirmComponent,
    LoginComponent,
    SignupComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [
    //HomeComponent
    //ProductDetailComponent
    //OrderComponent
    //OrderConfirmComponent
    //LoginComponent
    SignupComponent

  ]
})
export class AppModule { }
