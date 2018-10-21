import { AboutComponent } from './about/about.component';
import { CartComponentComponent } from './cart-component/cart-component.component';
import { ComicsComponentComponent } from './comics-component/comics-component.component';
import { ComicsDetailsComponent } from './comics-component/comics-details/comics-details.component';
import { ComicsItemComponent } from './comics-component/comics-item/comics-item.component';
import { ComicsListComponent } from './comics-component/comics-list/comics-list.component';
import { OrdersComponentComponent } from './orders-component/orders-component.component';
import { PreviewComponentComponent } from './preview-component/preview-component.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const appRoutes: Routes = [
  { path: '', redirectTo: '/humanity', pathMatch: 'full' },
  { path: 'humanity', component: AboutComponent },
  { path: 'releases', component: ComicsComponentComponent, children: [
    {path: 'list', component: ComicsListComponent, children: [
     { path: ':id', component: ComicsItemComponent }, 
      ]},
  ]},
  { path: 'previews', component: PreviewComponentComponent },
  { path: 'shopping-cart', component: CartComponentComponent },
  { path: 'order-history', component: OrdersComponentComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
  
}
