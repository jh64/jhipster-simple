import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { JhipstersimpleSharedModule } from 'app/shared/shared.module';
import { JhipstersimpleCoreModule } from 'app/core/core.module';
import { JhipstersimpleAppRoutingModule } from './app-routing.module';
import { JhipstersimpleHomeModule } from './home/home.module';
import { JhipstersimpleEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    JhipstersimpleSharedModule,
    JhipstersimpleCoreModule,
    JhipstersimpleHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    JhipstersimpleEntityModule,
    JhipstersimpleAppRoutingModule,
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, FooterComponent],
  bootstrap: [MainComponent],
})
export class JhipstersimpleAppModule {}
