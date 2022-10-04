import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PartidasListComponent } from './components/partidas-list/partidas-list.component';

const routes: Routes = [
  { path: '', redirectTo: '/partidas', pathMatch: 'full' },
  { path: 'partidas', component: PartidasListComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
