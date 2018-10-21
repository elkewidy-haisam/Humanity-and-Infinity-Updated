import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { CartComponentComponent } from './cart-component/cart-component.component';
import { PreviewComponentComponent } from './preview-component/preview-component.component';
import { OrdersComponentComponent } from './orders-component/orders-component.component';
import { UsersComponentComponent } from './users-component/users-component.component';
import { ComicsComponentComponent } from './comics-component/comics-component.component';
import { HeaderComponent } from './header/header.component';
import { ComicsListComponent } from './comics-component/comics-list/comics-list.component';
import { ComicsDetailsComponent } from './comics-component/comics-details/comics-details.component';
import { CartListComponent } from './cart-component/cart-list/cart-list.component';
import { OrdersListComponent } from './orders-component/orders-list/orders-list.component';
import { PreviewsListComponent } from './preview-component/previews-list/previews-list.component';
import { AboutComponent } from './about/about.component';
import { AppRoutingModule } from './app-routing.module';
import { CartService } from './cart-component/cart.service';
import { ComicService } from './comics-component/comic.service';
import { PreviewsDetailsComponent } from './preview-component/previews-details/previews-details.component';
import { ComicsItemComponent } from './comics-component/comics-item/comics-item.component';
import { OrderDetailsComponent } from './orders-component/order-details/order-details.component';
import { OrderService } from './orders-component/order.service';
import { PreviewService } from './preview-component/preview.service';
import { DataStorageService } from './shared/data-storage.service';
import { DropdownDirective } from './shared/dropdown.directive';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { CartItemsComponent } from './cart-component/cart-items/cart-items.component';
import { HttpsRequestInterceptor } from './shared/interceptor.module';
import { JQUERY_PROVIDER } from './shared/jquery.service';
import { HTTP_INTERCEPTORS } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    CartComponentComponent,
    PreviewComponentComponent,
    OrdersComponentComponent,
    UsersComponentComponent,
    ComicsComponentComponent,
    HeaderComponent,
    ComicsListComponent,
    ComicsDetailsComponent,
    CartListComponent,
    OrdersListComponent,
    PreviewsListComponent,
    AboutComponent,
    PreviewsDetailsComponent,
    ComicsItemComponent,
    OrderDetailsComponent,
    DropdownDirective,
    CartItemsComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [JQUERY_PROVIDER,
              CartService, 
              ComicService, 
              OrderService, 
              DataStorageService, 
              PreviewService, 
            { provide: HTTP_INTERCEPTORS, useClass: HttpsRequestInterceptor, multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule { }
