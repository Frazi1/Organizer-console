import {Component, Input, OnInit} from '@angular/core';
import {Location } from "@angular/common";
import {Hero} from "./hero";
import {HeroService} from "../../services/hero-service/hero.service";
import {ActivatedRoute, ParamMap} from "@angular/router";
import "rxjs/add/operator/switchMap";

@Component({
  selector: 'hero-detail',
  templateUrl: './hero-detail.component.html',
  styleUrls: ['./hero-detail.component.css']
})
export class HeroDetailComponent implements OnInit {

  @Input() hero: Hero;

  constructor(private heroService: HeroService,
              private route: ActivatedRoute,
              private location: Location)
              { }

  ngOnInit() {
    this.route.paramMap
      .switchMap((params: ParamMap)=> this.heroService.getHero(+params.get('id')))
      .subscribe(hero => this.hero = hero);
  }


  goBack() : void {
    this.location.back();
  }
}
